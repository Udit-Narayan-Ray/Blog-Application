package com.blog.payloads;

public class LoginAuth {
	
	private String username;
	private String password;
	
	public LoginAuth() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginAuth(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginAuth [username=" + username + ", password=" + password + "]";
	}
}
