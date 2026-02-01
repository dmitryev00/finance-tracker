package com.example.FinanceTracker.DTO.responses;

import java.math.BigDecimal;

public record WalletResponse(
		Long id,
		BigDecimal balance,
		Long userId
) {
}
