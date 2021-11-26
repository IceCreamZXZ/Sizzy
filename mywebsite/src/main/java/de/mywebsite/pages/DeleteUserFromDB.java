package de.mywebsite.pages;

import javax.faces.bean.ManagedBean;

import de.mywebsite.service.UserService;

@ManagedBean
public class DeleteUserFromDB {
	private int userID;
	
	public String deleteUser() {
		UserService.deleteUser(this.userID);
		
		return "index.xhtml";
	}
	
	public DeleteUserFromDB() {
		
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
}
