package de.mywebsite.pages;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
	
	List<EventModel> list = new ArrayList<EventModel>();
	
	private EventModel selectedModel;
	
	public SignUpForEventBean() {
		
	}
	
	@PostConstruct
	public void init() {
		list = EventService.listAllEvents();
	}
	
	public String signUpForEvent() {
		EventService.eventSignUp(getSelectedModel().getEventID(), getUserModel().getUsername());
		
		EventService.addPlayer(getSelectedModel().getEventID(), getUserModel().getUsername());
		
		getUserModel().getRegisteredEvents().add(selectedModel);
		
		return "singUpForEvent.xhtml";
	}
	
	public boolean isSignedUp(EventModel em) {
		List<EventModel> list = EventService.eventsForUser(getUserModel().getUsername());
		
		for (int i = 0; i < list.size(); i++) {
			EventModel event = list.get(i);
			
			if(event.getEventID()==em.getEventID()) {
				return false;
			}
			
		}
		return true;
	}
	
	public boolean slotsLeft(EventModel em) {
		EventEntity event = EventService.getSingleEvent(em.getEventID());
		
		if(event.getMaxPlayer()>event.getRegisteretPlayers()) {
			return true;
		}
		
		return false;
		
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

	public List<EventModel> getList() {
		return list;
	}

	public void setList(List<EventModel> list) {
		this.list = list;
	}

	public EventModel getSelectedModel() {
		return selectedModel;
	}

	public void setSelectedModel(EventModel selectedModel) {
		this.selectedModel = selectedModel;
	}
	
}
