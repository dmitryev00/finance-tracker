package com.example.FinanceTracker.controllers;


import com.example.FinanceTracker.DTO.inputs.TransactionInput;
import com.example.FinanceTracker.DTO.responses.TransactionResponse;
import com.example.FinanceTracker.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	TransactionService transactionService;

	public TransactionController(TransactionService transactionService)
	{
		this.transactionService = transactionService;
	}

	@GetMapping("/id/{id}")
	public TransactionResponse getTransactionById(@PathVariable Long id)
	{
		return transactionService.getTransactionById(id);
	}


	@GetMapping
	public List<TransactionResponse> getAllTransactions()
	{
		return transactionService.getAllTransactions();
	}

	@GetMapping("/wallets/{id}")
	public List<TransactionResponse> getTransactionsByWalletId(@PathVariable Long id)
	{
		return transactionService.getTransactionsByWalletId(id);
	}


	@PostMapping("/transfer")
	public TransactionResponse addTransaction(@RequestBody TransactionInput transaction)
	{
		return transactionService.addTransaction(transaction);
	}

	@PostMapping("/transfer/{walletIdTo}")
	@ResponseStatus(HttpStatus.OK)
	public void transferMoney(@RequestBody TransactionInput transaction,
									 @PathVariable Long walletIdTo)
	{
		transactionService.transferMoney(transaction, walletIdTo);
	}

	@GetMapping("/transfers/{id}")
	public List<TransactionResponse> getTransactionsByTransferId(@PathVariable String id)
	{
		return transactionService.getAllByTransferId(id);
	}
}
