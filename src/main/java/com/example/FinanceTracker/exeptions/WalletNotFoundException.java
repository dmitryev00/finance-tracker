package com.example.FinanceTracker.exeptions;

public class WalletNotFoundException extends RuntimeException {
	public WalletNotFoundException(Long walletId) {
		super("Wallet with id " + walletId + " not found");
	}
}
