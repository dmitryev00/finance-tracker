package com.example.FinanceTracker.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InsufficientFundsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleInsufficientFunds(
			InsufficientFundsException ex
	) {
		return new ErrorResponse(ex.getMessage());
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleUserNotFound(
			UserNotFoundException ex
	) {
		return new ErrorResponse(ex.getMessage());
	}

	@ExceptionHandler(WalletNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleWalletNotFound(
			WalletNotFoundException ex
	)
		{
		return new ErrorResponse(ex.getMessage());
	}


	@ExceptionHandler(TransactionNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleTransactionNotFound(
			TransactionNotFoundException ex
	)
	{
		return new ErrorResponse(ex.getMessage());
	}
}
