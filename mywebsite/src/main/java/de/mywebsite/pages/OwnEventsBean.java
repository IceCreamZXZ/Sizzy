package de.mywebsite.pages;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


import de.mywebsite.model.EventModel;
import de.mywebsite.model.UserModel;
import de.mywebsite.service.EventService;

@ManagedBean
@RequestScoped
public class OwnEventsBean {
	
	@ManagedProperty("#{userModel}")
	private UserModel user;
	
	private EventModel selectedModel;
	
	private List<EventModel> events = new ArrayList<EventModel>();
	
	public OwnEventsBean() {
		
	}
	
	@PostConstruct
	public void init() {
		events = getUser().getOwnEvents();
	}
	
	public List<UserModel> players(EventModel event) {
		List<UserModel> list = new ArrayList<UserModel>();
		
		list = EventService.getUserForEvent(event.getEventID());
		
		return list;
		
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public List<EventModel> getEvents() {
		return events;
	}

	public void setEvents(List<EventModel> list) {
		this.events = list;
	}

	public EventModel getSelectedModel() {
		return selectedModel;
	}

	public void setSelectedModel(EventModel selectedModel) {
		this.selectedModel = selectedModel;
	}
	
}
