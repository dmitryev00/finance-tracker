package com.example.FinanceTracker.services;


import com.example.FinanceTracker.DTO.inputs.TransactionInput;
import com.example.FinanceTracker.DTO.responses.TransactionResponse;
import com.example.FinanceTracker.DTO.wrappers.TransactionMapper;
import com.example.FinanceTracker.entities.TransactionEntity;
import com.example.FinanceTracker.entities.WalletEntity;
import com.example.FinanceTracker.exeptions.InsufficientFundsException;
import com.example.FinanceTracker.exeptions.TransactionNotFoundException;
import com.example.FinanceTracker.exeptions.WalletNotFoundException;
import com.example.FinanceTracker.repositories.TransactionRepository;
import com.example.FinanceTracker.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

	private final TransactionRepository transactionRepository;
	private final WalletRepository walletRepository;
	private final TransferIdGenerator transferIdGenerator;

	@Autowired
	public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository, TransferIdGenerator transferIdGenerator) {
		this.transactionRepository = transactionRepository;
		this.walletRepository = walletRepository;
		this.transferIdGenerator = transferIdGenerator;
	}

	public List<TransactionResponse> getAllTransactions()
	{
		List<TransactionEntity> transactionEntities = transactionRepository.findAll();
		return transactionEntities.stream().map
				(
					TransactionMapper::toResponse
				).toList();
	}


	public TransactionResponse getTransactionById(Long id)
	{
		TransactionEntity transactionEntity = (TransactionEntity) transactionRepository.findById(id)
				.orElseThrow(() -> new TransactionNotFoundException(id));
		return TransactionMapper.toResponse(transactionEntity);
	}


	public List<TransactionResponse> getTransactionsByWalletId(Long walletId)
	{
		WalletEntity walletEntity = (WalletEntity) walletRepository.getById(walletId)
				.orElseThrow(() -> new WalletNotFoundException(walletId));
		List<TransactionEntity> transactionEntities = transactionRepository.getAllByWallets(walletEntity);
		return transactionEntities.stream().map(
				TransactionMapper::toResponse
		).toList();
	}


	@Transactional
	public TransactionResponse addTransaction(TransactionInput transactionInput)
	{
		Long walletId = transactionInput.getWalletId();
		WalletEntity walletEntity = (WalletEntity) walletRepository.getById(walletId)
				.orElseThrow(() -> new WalletNotFoundException(walletId));
		if(transactionInput.getTransferId() == null)
			transferIdGenerator.setTransferID(transactionInput);

		BigDecimal amount = transactionInput.getAmount();
		if (walletEntity.getBalance().add(amount)
				.compareTo(BigDecimal.ZERO) < 0) {
			throw new InsufficientFundsException(
					walletEntity.getBalance(), amount
			);
		}

		walletEntity.setBalance(
				walletEntity.getBalance().add(amount));

		return TransactionMapper.toResponse(
				transactionRepository.save(TransactionMapper.toEntity(transactionInput, walletEntity)));
	}

	@Transactional
	public void transferMoney(TransactionInput transactionInput, Long walletIdTo)
	{
		Long walletIdFrom = transactionInput.getWalletId();
		WalletEntity walletEntityFrom = (WalletEntity) walletRepository.getById(walletIdFrom)
				.orElseThrow(() -> new WalletNotFoundException(walletIdFrom));

		WalletEntity walletEntityTo = (WalletEntity) walletRepository.getById(walletIdTo)
				.orElseThrow(() -> new WalletNotFoundException(walletIdTo));

		transactionInput.setWalletId(walletIdTo);

		TransactionInput negativeTransaction = new TransactionInput(
				transactionInput.getAmount().negate(),
				walletEntityFrom.getId()
		);

		transferIdGenerator.setTransferID(transactionInput, negativeTransaction);
		addTransaction(negativeTransaction);
		addTransaction(transactionInput);
	}


	@EntityGraph
	public List<TransactionResponse> getAllByTransferId(String id)
	{
		List<TransactionEntity> transactionEntities = transactionRepository.findAllByTransferId(id);
		return transactionEntities.stream().map(
				TransactionMapper::toResponse
		).toList();
	}
}

