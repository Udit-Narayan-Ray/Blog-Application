package com.blog.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDTO;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		
		User user =this.DTOtoUser(userDTO);

		return this.UsertoDTO(this.userRepo.save(user));
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer id) {
		
		User user=this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setAbout(userDTO.getAbout());
				
		return this.UsertoDTO(this.userRepo.save(user));
	}

	@Override
	public UserDTO getUserById(Integer id) {
		
		User user=this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
		
		return UsertoDTO(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {

		List<User> users=this.userRepo.findAll();
//		List<UserDTO> usersdto=new ArrayList<>();
//		usersdto.clear();
//		for(User user:users) {
//			UserDTO userDTO=new UserDTO();
//			userDTO=this.UsertoDTO(user);
//			usersdto.add(userDTO);
//		}
		
		// the above iteration can be done using stream collection also
		List<UserDTO> usersdto=users.stream().map(user->this.UsertoDTO(user)).collect(Collectors.toList());
		
		return usersdto;
	}

	@Override
	public void deleteUserById(Integer id) {
		
		User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","Id",id));
		this.userRepo.delete(user);

	}
	
	
	//for converting user to Data Transfer Object(DTO)
	public UserDTO UsertoDTO(User user)
	{
//		UserDTO userDTO=new UserDTO();
//		
//		userDTO.setId(user.getId());
//		userDTO.setName(user.getName());
//		userDTO.setEmail(user.getEmail());
//		userDTO.setPassword(user.getPassword());
//		userDTO.setAbout(user.getAbout());		
//		
//		return userDTO;
		
		return this.modelMapper.map(user, UserDTO.class);
	}
	
	// for converting User Data Transfer Object(DTO) to User
	public User DTOtoUser(UserDTO userDTO)
	{
//		User user=new User();
//		
//		user.setId(userDTO.getId());
//		user.setName(userDTO.getName());
//		user.setEmail(userDTO.getEmail());
//		user.setPassword(userDTO.getPassword());
//		user.setAbout(userDTO.getAbout());
//		
//		return user;
		
		return this.modelMapper.map(userDTO, User.class);
	}

}
