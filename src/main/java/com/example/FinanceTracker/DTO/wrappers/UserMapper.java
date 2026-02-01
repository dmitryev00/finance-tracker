package com.example.FinanceTracker.DTO.wrappers;

import com.example.FinanceTracker.DTO.inputs.UserInput;
import com.example.FinanceTracker.DTO.responses.UserResponse;
import com.example.FinanceTracker.entities.UserEntity;

public class UserMapper {

	public static UserEntity toEntity(UserInput input)
	{
		UserEntity user = new UserEntity();
		user.setUserName(input.userName());
		return user;
	}


	public static UserResponse toResponse(UserEntity user)
	{
		return new UserResponse(
				user.getId(),
				user.getUserName()
		);
	}
}
