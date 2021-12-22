package de.mywebsite.pages;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.mywebsite.model.UserModel;
import de.mywebsite.service.LoginService;

@ManagedBean
@SessionScoped
public class BannedBean {
	
	@ManagedProperty("#{userModel}")
	public UserModel user;
	
	public String reason;
	
	public BannedBean() {
		
	}
	
	@PostConstruct
	public void init() {
		reason = LoginService.getBannedMessage(getUser().getUsername());
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
