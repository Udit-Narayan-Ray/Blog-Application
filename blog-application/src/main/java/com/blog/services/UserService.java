package com.blog.services;

import java.util.List;

import com.blog.payloads.UserDTO;

public interface UserService {

	//creating new user
	UserDTO createUser(UserDTO userDTO);

	//updating existed user
	UserDTO updateUser(UserDTO userDTO, Integer id);

	//retrieving single user
	UserDTO getUserById(Integer id);

	//retrieving all users
	List<UserDTO> getAllUsers();

	//deleting user corresponding to the given id
	void deleteUserById(Integer id);
}
