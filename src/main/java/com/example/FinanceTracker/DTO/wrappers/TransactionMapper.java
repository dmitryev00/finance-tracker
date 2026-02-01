package com.example.FinanceTracker.DTO.wrappers;

import com.example.FinanceTracker.DTO.TransferID;
import com.example.FinanceTracker.DTO.inputs.TransactionInput;
import com.example.FinanceTracker.DTO.responses.TransactionResponse;
import com.example.FinanceTracker.entities.TransactionEntity;
import com.example.FinanceTracker.entities.WalletEntity;

import java.time.LocalDateTime;

public class TransactionMapper {

	public static TransactionEntity toEntity(TransactionInput input, WalletEntity wallet)
	{
		TransactionEntity entity = new TransactionEntity();
		entity.setAmount(input.getAmount());
		entity.setWallets(wallet);
		entity.setCreatedAt(LocalDateTime.now());
		entity.setTransferId(input.getTransferId().toString());
		return entity;
	}

	public static TransactionResponse toResponse(TransactionEntity entity)
	{
		return new TransactionResponse
				(
						entity.getId(),
						entity.getAmount(),
						entity.getWallets().getId(),
						entity.getTransferId(),
						entity.getCreatedAt()
				);
	}
}
