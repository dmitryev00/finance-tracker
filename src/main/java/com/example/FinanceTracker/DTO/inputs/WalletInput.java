package com.example.FinanceTracker.DTO.inputs;


import java.math.BigDecimal;

public record WalletInput(
		BigDecimal balance,
		Long usersId
) {
}
