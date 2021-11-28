package de.mywebsite.pages;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import de.mywebsite.service.EventService;

@ManagedBean
public class CreateEventBean {
	private String eventName;
	private String host;
	private String location;
	private Date date;
	private String game;
	private int maxPlayers;
	private int registeretPlayers;
	
	public CreateEventBean() {
		
	}
	
	public String createEvent() {
		EventService.createEvent(eventName, host, location, game, maxPlayers, registeretPlayers, date);
		
		return "welcome.xhtml";
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public int getRegisteretPlayers() {
		return registeretPlayers;
	}

	public void setRegisteretPlayers(int registeretPlayers) {
		this.registeretPlayers = registeretPlayers;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
