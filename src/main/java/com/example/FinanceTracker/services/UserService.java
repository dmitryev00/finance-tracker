package com.example.FinanceTracker.services;

import com.example.FinanceTracker.DTO.inputs.UserInput;
import com.example.FinanceTracker.DTO.responses.UserResponse;
import com.example.FinanceTracker.DTO.wrappers.UserMapper;
import com.example.FinanceTracker.entities.UserEntity;
import com.example.FinanceTracker.exeptions.UserNotFoundException;
import com.example.FinanceTracker.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

	UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	public List<UserResponse> getUsersByName(String name)
	{
		return userRepository.findAllByUserName(name).stream().map(
				UserMapper::toResponse
		).toList();
	}


	public UserResponse getUserById(Long id)
	{
		UserEntity userEntity = (UserEntity) userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		return UserMapper.toResponse(userEntity);
	}


	public List<UserResponse> getAllUsers()
	{
		List<UserEntity> userEntities = userRepository.findAll();
		return userEntities.stream().map(
				UserMapper::toResponse
		).toList();
	}

	@Transactional
	public UserResponse createUser(UserInput userInput)
	{
		return UserMapper.toResponse(userRepository.save(UserMapper.toEntity(userInput)));
	}


	@Transactional
	public UserResponse deleteUser(Long id)
	{
		UserEntity userEntity = (UserEntity) userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		userRepository.delete(userEntity);
		return UserMapper.toResponse(userEntity);
	}
}
