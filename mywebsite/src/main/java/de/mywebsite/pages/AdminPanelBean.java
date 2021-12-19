package de.mywebsite.pages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import de.mywebsite.service.LoginService;

@ManagedBean
@RequestScoped
public class AdminPanelBean {
	
	private String bannedUser;
	
	private String reason;
	
	public AdminPanelBean() {
		
	}
	
	public String banUser() {
		LoginService.bannAccount(bannedUser, reason);
		
		return "adminPanel.xhtml";
	}

	public String getBannedUser() {
		return bannedUser;
	}

	public void setBannedUser(String bannedUser) {
		this.bannedUser = bannedUser;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
