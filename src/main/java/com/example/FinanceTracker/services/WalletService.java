package com.example.FinanceTracker.services;

import com.example.FinanceTracker.DTO.inputs.WalletInput;
import com.example.FinanceTracker.DTO.responses.WalletResponse;
import com.example.FinanceTracker.DTO.wrappers.WalletMapper;
import com.example.FinanceTracker.entities.UserEntity;
import com.example.FinanceTracker.entities.WalletEntity;
import com.example.FinanceTracker.exeptions.UserNotFoundException;
import com.example.FinanceTracker.exeptions.WalletNotFoundException;
import com.example.FinanceTracker.repositories.UserRepository;
import com.example.FinanceTracker.repositories.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WalletService {

	WalletRepository walletRepository;
	UserRepository userRepository;

	public WalletService(WalletRepository walletRepository, UserRepository userRepository) {
		this.walletRepository = walletRepository;
		this.userRepository = userRepository;
	}

	public List<WalletResponse> getUserWallets(Long userId)
	{
		UserEntity userEntity = (UserEntity) userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(userId));

		List<WalletEntity> walletResponseList = walletRepository.getAllByUser(userEntity);

		return walletResponseList.stream().map(
				WalletMapper::toResponse
		).toList();
	}


	public List<WalletResponse> getAllWallets()
	{
		List<WalletEntity> walletResponseList = walletRepository.findAll();
		return walletResponseList.stream().map(
				WalletMapper::toResponse
		).toList();
	}


	public WalletResponse getWalletById(Long id)
	{
		WalletEntity walletEntity = (WalletEntity) walletRepository.findById(id)
				.orElseThrow(() -> new WalletNotFoundException(id));
		return WalletMapper.toResponse(walletEntity);
	}


	@Transactional
	public WalletResponse addWallet(WalletInput walletInput)
	{
		Long userId = walletInput.usersId();
		UserEntity userEntity = (UserEntity) userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(userId));
		return WalletMapper.toResponse(
				walletRepository.save(WalletMapper.toEntity(walletInput, userEntity)));
	}


	@Transactional
	public WalletResponse removeWallet(Long id)
	{
		WalletEntity walletEntity = (WalletEntity) walletRepository.findById(id)
				.orElseThrow(() -> new WalletNotFoundException(id));
		walletRepository.delete(walletEntity);
		return WalletMapper.toResponse(walletEntity);
	}
}
