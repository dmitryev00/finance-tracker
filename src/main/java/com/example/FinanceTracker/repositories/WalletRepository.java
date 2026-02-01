package com.example.FinanceTracker.repositories;

import com.example.FinanceTracker.entities.UserEntity;
import com.example.FinanceTracker.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Integer> {


	List<WalletEntity> getAllByUser(UserEntity user);

	Optional<Object> findById(Long id);

	List<WalletEntity> id(Long id);

	Optional<Object> getById(Long id);
}
