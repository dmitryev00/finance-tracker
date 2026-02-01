package com.example.FinanceTracker.exeptions;

public class TransactionNotFoundException extends RuntimeException {
	public TransactionNotFoundException(Long transaction) {
		super("Transaction with id " + transaction + " not found");
	}
}
