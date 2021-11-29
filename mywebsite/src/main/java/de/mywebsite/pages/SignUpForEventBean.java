package de.mywebsite.pages;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.mywebsite.model.EventModel;
import de.mywebsite.model.UserModel;
import de.mywebsite.persistence.EventEntity;
import de.mywebsite.service.EventService;

@ManagedBean
public class SignUpForEventBean {
	
	@ManagedProperty("#{eventModel}")
	private EventModel eventModel;
	
	@ManagedProperty("#{userModel}")
	private UserModel userModel;
	
	List<EventEntity> list = new ArrayList<EventEntity>();
	
	public SignUpForEventBean() {
		list = EventService.listAllEvents();
	}
	
	public String signUpForEvent() {
		EventService.eventSignUp(getEventModel().getEventID(), getUserModel().getUsername());
		
		return "signUpForEvent.xhtml";
	}

	public EventModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(EventModel eventModel) {
		this.eventModel = eventModel;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public List<EventEntity> getList() {
		return list;
	}

	public void setList(List<EventEntity> list) {
		this.list = list;
	}
	
}
