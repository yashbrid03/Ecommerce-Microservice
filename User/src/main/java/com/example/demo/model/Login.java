package com.example.demo.model;

public class Login {
	
	private String username;
	
	private String password;
	
	public Login() {
		
	}
	
	public Login(String u, String p) {
		username = u;
		password = p;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUsername(String u) {
		username = u;
	}
	
	public void setPassword(String p) {
		password = p;
	}

}
