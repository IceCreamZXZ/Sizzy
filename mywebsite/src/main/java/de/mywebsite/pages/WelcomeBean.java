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
public class WelcomeBean {
	
	@ManagedProperty("#{eventModel}")
	private EventModel eventModel;
	
	@ManagedProperty("#{userModel}")
	private UserModel user;
	
	
	List<EventModel> list = new ArrayList<EventModel>();
	
	public WelcomeBean() {
		
	}
	
	@PostConstruct
	public void init() {
		list = getUser().getRegisteredEvents();
	}
	
	public boolean isSignedUp() {
		List<EventModel> list = EventService.eventsForUser(getUser().getUsername());
		
		if(list.size()==0) {
			return false;
		}
		else {
			return true;
		}
		
	}
	
	public String signOut() {
		getUser().flush();
		
		return "index.xhtml";
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

	public List<EventModel> getList() {
		return list;
	}

	public void setList(List<EventModel> list) {
		this.list = list;
	}
	
}
