package com.example.FinanceTracker.DTO.wrappers;

import com.example.FinanceTracker.DTO.inputs.WalletInput;
import com.example.FinanceTracker.DTO.responses.WalletResponse;
import com.example.FinanceTracker.entities.UserEntity;
import com.example.FinanceTracker.entities.WalletEntity;

public class WalletMapper {

	public static WalletEntity toEntity(WalletInput input, UserEntity userEntity)
	{
		WalletEntity walletEntity = new WalletEntity();
		walletEntity.setBalance(input.balance());
		walletEntity.setUser(userEntity);
		return walletEntity;
	}

	public static WalletResponse toResponse(WalletEntity walletEntity)
	{
		return new WalletResponse(
				walletEntity.getId(),
				walletEntity.getBalance(),
				walletEntity.getUser().getId()
		);
	}
}
