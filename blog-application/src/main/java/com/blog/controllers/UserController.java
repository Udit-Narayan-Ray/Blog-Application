package com.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.UserDTO;
import com.blog.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController
{
	//here we are calling interface not the service implementation
	@Autowired
	private UserService userService;
	
	@PostMapping("/post")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO)
	{
		UserDTO createdUserDTO = this.userService.createUser(userDTO);
		
		return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);

	}
	
	@PutMapping("/put/{userid}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO,@PathVariable("userid") Integer id)
	{
		UserDTO updatedUserDTO = this.userService.updateUser(userDTO, id);
		
		return  ResponseEntity.ok(updatedUserDTO);
	}
	
	@DeleteMapping("/delete/{userid}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userid") Integer id)
	{
		this.userService.deleteUserById(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User successfully deleted with id :- "+id,true), HttpStatus.OK);
	}
	
	@GetMapping("get/{userid}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("userid")Integer id)
	{
		UserDTO userDTO = this.userService.getUserById(id);
	
		return ResponseEntity.ok(userDTO);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<UserDTO>> getUsers()
	{
		List<UserDTO> users=this.userService.getAllUsers();
		
		return ResponseEntity.ok(users);
	}
}
