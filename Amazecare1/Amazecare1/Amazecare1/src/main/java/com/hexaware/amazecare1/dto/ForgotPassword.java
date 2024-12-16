package com.hexaware.amazecare1.dto;

public class ForgotPassword {
	private String username;
	private String password;
	public ForgotPassword() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ForgotPassword(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
	@Override
	public String toString() {
		return "ForgotPassword [username=" + username + ", password=" + password + "]";
	}
	
	

}
