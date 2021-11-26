package de.mywebsite.pages;

import javax.faces.bean.ManagedBean;

import de.mywebsite.service.LoginService;

@ManagedBean
public class RemoveAccBean {
	private String username;
	
	public RemoveAccBean() {
		
	}
	
	public String removeAcc() {
		LoginService.deleteAccount(username);
		
		return "index.xhtml";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
