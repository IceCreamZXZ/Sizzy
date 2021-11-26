package de.mywebsite.pages;

import javax.faces.bean.ManagedBean;

import de.mywebsite.service.LoginService;

@ManagedBean
public class RegistrationBean {
	private String username;
	private String password;
	
	public RegistrationBean() {
		
	}
	
	public String register() {
		LoginService.createAccount(username, password);
		
		return "login.xhtml";
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
	
}
