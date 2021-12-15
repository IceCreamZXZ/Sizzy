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
	
	List<EventModel> events = new ArrayList<EventModel>();
	
	List<UserModel> player = new ArrayList<UserModel>();
	
	public OwnEventsBean() {
		
	}
	
	@PostConstruct
	public void init() {
		events = getUser().getOwnEvents();
		player = EventService.getUserForEvent(getSelectedModel().getEventID());
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public List<EventModel> getList() {
		return events;
	}

	public void setList(List<EventModel> list) {
		this.events = list;
	}

	public EventModel getSelectedModel() {
		return selectedModel;
	}

	public void setSelectedModel(EventModel selectedModel) {
		this.selectedModel = selectedModel;
	}
	
}
