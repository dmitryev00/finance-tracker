package com.example.FinanceTracker.controllers;


import com.example.FinanceTracker.DTO.inputs.UserInput;
import com.example.FinanceTracker.DTO.responses.UserResponse;
import com.example.FinanceTracker.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService)
	{
		this.userService = userService;
	}

	@GetMapping
	public List<UserResponse> getUsers()
	{
		return userService.getAllUsers();
	}

	@GetMapping("/id/{id}")
	public UserResponse getUserById(@PathVariable Long id)
	{
		return userService.getUserById(id);
	}


	@GetMapping("/name/{name}")
	public List<UserResponse> getUsersByName(@PathVariable String name)
	{
		return userService.getUsersByName(name);
	}

	@PostMapping()
	public UserResponse addUser(@RequestBody UserInput userInput)
	{
		return userService.createUser(userInput);
	}

	@DeleteMapping("/{id}")
	public UserResponse deleteUser(@PathVariable Long id)
	{
		return userService.deleteUser(id);
	}
}
