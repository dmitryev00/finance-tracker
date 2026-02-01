package com.example.FinanceTracker.DTO.inputs;


import com.example.FinanceTracker.DTO.TransferID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TransactionInput {

	BigDecimal amount;
	Long walletId;
	TransferID transferId;

	public TransactionInput(BigDecimal amount, Long walletId)
	{
		this.amount = amount;
		this.walletId = walletId;
		this.transferId = null;
	}
}
