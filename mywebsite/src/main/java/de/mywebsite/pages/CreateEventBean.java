package de.mywebsite.pages;


import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.mywebsite.model.EventModel;
import de.mywebsite.model.UserModel;
import de.mywebsite.service.EventService;

@ManagedBean
@RequestScoped
public class CreateEventBean {
	
	@ManagedProperty("#{eventModel}")
	private EventModel eventModel;
	
	@ManagedProperty("#{userModel}")
	private UserModel user;
	
	
	private Date date;
	
	public CreateEventBean() {
		
	}
	
	@PostConstruct
	public void init() {
		eventModel = new EventModel();
	}
	
	
	public String createEvent() {
		
		int eventID = EventService.createEvent(getEventModel().getEventName(), getUser().getUsername(), getEventModel().getLocation(), getEventModel().getGame(), getEventModel().getMaxPlayer(), 1, getEventModel().getDate());
		
		EventService.eventSignUp(eventID, getUser().getUsername());
		
		return "welcome.xhtml";
	}

	

	public EventModel getEventModel() {
		return eventModel;
	}


	public void setEventModel(EventModel eventModel) {
		this.eventModel = eventModel;
	}


	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}
	
}
