package com.example.FinanceTracker.DTO;


public record TransferID(
		String id
) {

	@Override public String toString()
	{
		return id;
	}
}
