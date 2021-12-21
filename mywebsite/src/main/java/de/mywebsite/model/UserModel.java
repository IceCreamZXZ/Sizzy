package de.mywebsite.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserModel {
	private String username;
	private String password;
	private String permission;
	private List<EventModel> registeredEvents = new ArrayList<EventModel>();
	private List<EventModel> ownEvents = new ArrayList<EventModel>();
	
	public UserModel() {
		
	}
	
	public void flush() {
		username = null;
		password = null;
		
		registeredEvents.clear();
		ownEvents.clear();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<EventModel> getRegisteredEvents() {
		return registeredEvents;
	}
	public void setRegisteredEvents(List<EventModel> registeredEvents) {
		this.registeredEvents = registeredEvents;
	}
	public List<EventModel> getOwnEvents() {
		return ownEvents;
	}
	public void setOwnEvents(List<EventModel> ownEvents) {
		this.ownEvents = ownEvents;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}
