package com.example.FinanceTracker.services;

import com.example.FinanceTracker.DTO.TransferID;
import com.example.FinanceTracker.DTO.inputs.TransactionInput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TransferIdGenerator {

	public TransferID generate() {
		return new TransferID(UUID.randomUUID().toString());
	}

	@Transactional
	public void setTransferID(TransactionInput... transactions)
	{
		TransferID transferID = generate();
		for (TransactionInput transactionInput : transactions)
		{
			transactionInput.setTransferId(transferID);
		}
	}

}
