package com.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO 
{
	private int id;
	
	@NotEmpty
	@Size(min = 4, max=20, message = "!! Name should be between 4 and 20 letters !!")
	private String name;
	
	@Email(message = "!! Not a valid Email !!")
	private String email;
	
	@NotEmpty
	@Size(min = 3, max = 10,message = "!! Password should be between 3 to 10 characters !!")
	@Pattern(regexp = "[a-zA-Z0-9]{3,}+",message = "!! Password should have atleast one capital,one small and one number in password !!")
	private String password;
	
	@NotEmpty
	private String about;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	public UserDTO(int id, String name, String email, String password, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
				+ about + "]";
	}
		
}
