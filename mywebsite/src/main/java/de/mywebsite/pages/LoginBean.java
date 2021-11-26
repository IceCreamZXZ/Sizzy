package de.mywebsite.pages;

import javax.faces.bean.ManagedBean;

import de.mywebsite.service.LoginService;

@ManagedBean
public class LoginBean {
	private String username;
	private String password;
	private String compPassword;
	
	public LoginBean() {
		
	}
	
	public String isPwCorrect() {
		boolean pw = LoginService.isPasswordRight(username, password);
		
		if (pw==true) {
			return "welcome.xhtml";
		}
		else {
			return "index.xhtml";
		}
		
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

	public String getCompPassword() {
		return compPassword;
	}

	public void setCompPassword(String compPassword) {
		this.compPassword = compPassword;
	}
	
}
