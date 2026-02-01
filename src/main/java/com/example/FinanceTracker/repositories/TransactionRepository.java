package com.example.FinanceTracker.repositories;


import com.example.FinanceTracker.entities.TransactionEntity;
import com.example.FinanceTracker.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

	List<TransactionEntity> findAllByWallets(WalletEntity wallets);

	List<TransactionEntity> findAllByTransferId(String transferId);

	List<TransactionEntity> getAllByWallets(WalletEntity wallets);

	Optional<Object> findById(Long id);
}
