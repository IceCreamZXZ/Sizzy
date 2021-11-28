package de.mywebsite.pages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.mywebsite.model.UserModel;
import de.mywebsite.service.EventService;
import de.mywebsite.service.LoginService;

@ManagedBean
@SessionScoped
public class LoginBean {
	@ManagedProperty("#{userModel}")
	private UserModel userModel;
	
	public LoginBean() {
		
	}
	
	public String isPwCorrect() {
		boolean pw = LoginService.isPasswordRight(getUserModel().getUsername(), getUserModel().getPassword());
		
		if (pw==true) {
			getUserModel().setRegisteredEvents(EventService.eventsForUser(getUserModel().getUsername()));
			return "welcome.xhtml";
		}
		else {
			return "index.xhtml";
		}
		
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	
}
