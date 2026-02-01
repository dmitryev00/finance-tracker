package com.example.FinanceTracker.controllers;


import com.example.FinanceTracker.DTO.inputs.WalletInput;
import com.example.FinanceTracker.DTO.responses.WalletResponse;
import com.example.FinanceTracker.services.WalletService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController {

	private final WalletService walletService;

	public WalletController(WalletService walletService) {
		this.walletService = walletService;
	}


	@GetMapping
	public List<WalletResponse> getAllWallets()
	{
		return walletService.getAllWallets();
	}

	@GetMapping("/id/{id}")
	public WalletResponse getWalletById(@PathVariable Long id)
	{
		return walletService.getWalletById(id);
	}


	@PostMapping
	public WalletResponse addWallet(@RequestBody WalletInput walletInput)
	{
		return walletService.addWallet(walletInput);
	}


	@DeleteMapping("/{id}")
	public WalletResponse deleteWallet(@PathVariable Long id)
	{
		return walletService.removeWallet(id);
	}
}
