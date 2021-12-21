package de.mywebsite.pages;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import de.mywebsite.model.EventModel;
import de.mywebsite.service.EventService;

@ManagedBean
@RequestScoped
public class AllEventsBean {
	
	private List<EventModel> events = new ArrayList<EventModel>();
	
	private EventModel selectedModel;
	
	public AllEventsBean() {
		
	}
	
	@PostConstruct
	public void init() {
		events = EventService.listAllEvents();
	}

	public List<EventModel> getEvents() {
		return events;
	}

	public void setEvents(List<EventModel> events) {
		this.events = events;
	}

	public EventModel getSelectedModel() {
		return selectedModel;
	}

	public void setSelectedModel(EventModel selectedModel) {
		this.selectedModel = selectedModel;
	}
	
}
