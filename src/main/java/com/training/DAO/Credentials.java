package com.training.DAO;

public class Credentials {
	private String username;
	private String password;
	
	public Credentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}

}
