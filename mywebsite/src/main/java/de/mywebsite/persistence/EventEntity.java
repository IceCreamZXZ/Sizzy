package de.mywebsite.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class EventEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eventID", unique = true)
	private int eventId;
	
	@Column(name = "eventname", nullable = false)
	private String eventName;
	
	@Column(name = "eventhost", nullable = false)
	private String host;
	
	@Column(name = "location", nullable = false)
	private String location;
	
	@Column(name = "game", nullable = false)
	private String game;
	
	@Column(name = "maxplayern", nullable = true)
	private int maxPlayer;
	
	@Column(name = "registeretplayers", nullable = false)
	private int registeretPlayers;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
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

	public int getMaxPlayer() {
		return maxPlayer;
	}

	public void setMaxPlayer(int maxPlayer) {
		this.maxPlayer = maxPlayer;
	}

	public int getRegisteretPlayers() {
		return registeretPlayers;
	}

	public void setRegisteretPlayers(int registeretPlayers) {
		this.registeretPlayers = registeretPlayers;
	}
	
}
