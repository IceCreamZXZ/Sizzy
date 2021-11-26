package de.mywebsite.pages;

import javax.faces.bean.ManagedBean;

import de.mywebsite.service.UserService;

@ManagedBean
public class AddUserToDB {
	private String fName;
	private String lName;
	
	public String addUser() {
		UserService.addUser(this.fName, this.lName);
		
		return "index.xhtml";
	}
	
	public AddUserToDB() {
		
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	
}
