package com.example.FinanceTracker.repositories;


import com.example.FinanceTracker.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	UserEntity getFirstById(Long id);

	Optional<Object> findById(Long id);

	List<UserEntity> findAllByUserName(String userName);
}
