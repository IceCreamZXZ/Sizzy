package de.mywebsite.pages;

import javax.faces.bean.ManagedBean;

import de.mywebsite.persistence.UserEntity;
import de.mywebsite.service.UserService;

import java.util.List;
import java.util.ArrayList;

@ManagedBean
public class GetUserFromDB {
	private String fNameInput;
	private List<UserEntity> list = new ArrayList<UserEntity>();
	private List<String> userList = new ArrayList<String>();
	
	public String getUser() {
		list = UserService.getUser(this.fNameInput);
		
		for (int i = 0; i < list.size(); i++) {
			String name = (list.get(i).getfName() +" " +list.get(i).getlName());
			
			userList.add(name);
		}
		
		return "UserResponse.xhtml";
	}
	
	public GetUserFromDB() {
		
	}

	public List<UserEntity> getList() {
		return list;
	}

	public void setList(List<UserEntity> list) {
		this.list = list;
	}

	public String getfNameInput() {
		return fNameInput;
	}

	public void setfNameInput(String fNameInput) {
		this.fNameInput = fNameInput;
	}

	public List<String> getUserList() {
		return userList;
	}

	public void setUserList(List<String> userList) {
		this.userList = userList;
	}
	
}
