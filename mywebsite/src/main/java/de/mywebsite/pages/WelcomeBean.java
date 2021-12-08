package de.mywebsite.pages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.mywebsite.model.UserModel;

@ManagedBean
public class WelcomeBean {
	
	@ManagedProperty("#{userModel}")
	private UserModel userModel;

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	
}
