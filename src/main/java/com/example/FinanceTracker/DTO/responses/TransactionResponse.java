package com.example.FinanceTracker.DTO.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
		Long id,
		BigDecimal amount,
		Long walletsId,
		String transferId,
		LocalDateTime createdAt
) {
}
