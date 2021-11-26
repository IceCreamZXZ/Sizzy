package de.mywebsite.pages;

import javax.faces.bean.ManagedBean;

import de.mywebsite.service.UserService;

@ManagedBean
public class ChangeUserFromDB {
	private String newFName;
	private String newLName;
	private int userID;
	
	public ChangeUserFromDB() {
		
	}
	
	public String changeUser(){
		UserService.changeEntry(userID, newFName, newLName);
		
		return "index.xhtml";
	}

	public String getNewFName() {
		return newFName;
	}

	public void setNewFName(String newFName) {
		this.newFName = newFName;
	}

	public String getNewLName() {
		return newLName;
	}

	public void setNewLName(String newLName) {
		this.newLName = newLName;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
}
