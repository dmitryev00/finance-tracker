package com.example.FinanceTracker.entities;


import com.example.FinanceTracker.DTO.TransferID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class TransactionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal amount;

	@JoinColumn(name = "wallet_id")
	@ManyToOne
	private WalletEntity wallets;

	@Column(name = "transfer_id")
	private String transferId;

	@Column(name = "created_at")
	private LocalDateTime createdAt;
}
