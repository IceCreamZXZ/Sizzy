package de.mywebsite.pages;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.mywebsite.model.EventModel;
import de.mywebsite.model.UserModel;
import de.mywebsite.service.EventService;

@ManagedBean
@RequestScoped
public class WelcomeBean {
	@ManagedProperty("#{userModel}")
	private UserModel user;
	
	@ManagedProperty("#{eventModel}")
	private EventModel eventModel;
	
	List<EventModel> list = new ArrayList<EventModel>();

	public WelcomeBean() {
		list = EventService.eventsForUser(getUser().getUsername());
	}
	
	public String signOut() {
		user.flush();
		
		return "index.xhtml";
	}

	public EventModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(EventModel eventModel) {
		this.eventModel = eventModel;
	}

	public List<EventModel> getList() {
		return list;
	}

	public void setList(List<EventModel> list) {
		this.list = list;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
	
}
