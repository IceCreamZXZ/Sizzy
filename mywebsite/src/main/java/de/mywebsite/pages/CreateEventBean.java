package de.mywebsite.pages;


import java.util.Date;

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
		eventModel = new EventModel();
	}
	
	
	public String createEvent() {
		
		EventService.createEvent(getEventModel().getEventName(), getUser().getUsername(), getEventModel().getLocation(), getEventModel().getGame(), getEventModel().getMaxPlayer(), getEventModel().getRegisteredPlayers(), getEventModel().getDate());
		
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
