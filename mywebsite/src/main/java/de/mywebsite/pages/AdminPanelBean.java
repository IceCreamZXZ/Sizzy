package de.mywebsite.pages;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import de.mywebsite.model.EventModel;
import de.mywebsite.model.UserModel;
import de.mywebsite.service.EventService;
import de.mywebsite.service.LoginService;

@ManagedBean
@RequestScoped
public class AdminPanelBean {
	
	private String bannedUser;
	
	private String reason;
	
	private List<EventModel> eventModels = new ArrayList<EventModel>();
	
	private List<String> eventNames = new ArrayList<String>();
	
	private String selectedEvent;
	
	private String selectedUser;
	
	private String selectedPermission;
	
	public List<UserModel> userList = new ArrayList<UserModel>();
	
	public List<String> userNames = new ArrayList<String>();
	
	public AdminPanelBean() {
		
	}
	
	@PostConstruct
	public void init() {
		eventModels = EventService.listAllEvents();
		
		for (int i = 0; i < eventModels.size(); i++) {
			eventNames.add(eventModels.get(i).getEventName());
		}
		
		userList = LoginService.getAllUsers();
		
		for (int i = 0; i < userList.size(); i++) {
			userNames.add(userList.get(i).getUsername());
		}
		
	}
	
	public String banUser() {
		LoginService.bannAccount(bannedUser, reason);
		
		return "adminPanel.xhtml";
	}
	
	public String banEvent() {
		int eventPosition =  eventNames.indexOf(selectedEvent);
		
		EventService.deleteEvent(eventModels.get(eventPosition).getEventID());
		
		return "adminPanel.xhtml";
	}
	
	public String changePermission() {
		
		LoginService.updatePermission(selectedUser, selectedPermission);
		
		
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

	public List<EventModel> getEventModels() {
		return eventModels;
	}

	public void setEventModels(List<EventModel> eventModels) {
		this.eventModels = eventModels;
	}

	public List<String> getEventNames() {
		return eventNames;
	}

	public void setEventNames(List<String> eventNames) {
		this.eventNames = eventNames;
	}

	public String getSelectedEvent() {
		return selectedEvent;
	}

	public void setSelectedEvent(String selectedEvent) {
		this.selectedEvent = selectedEvent;
	}

	public String getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(String selectedUser) {
		this.selectedUser = selectedUser;
	}

	public String getSelectedPermission() {
		return selectedPermission;
	}

	public void setSelectedPermission(String selectedPermission) {
		this.selectedPermission = selectedPermission;
	}

	public List<UserModel> getUserList() {
		return userList;
	}

	public void setUserList(List<UserModel> userList) {
		this.userList = userList;
	}

	public List<String> getUserNames() {
		return userNames;
	}

	public void setUserNames(List<String> userNames) {
		this.userNames = userNames;
	}
	
}
