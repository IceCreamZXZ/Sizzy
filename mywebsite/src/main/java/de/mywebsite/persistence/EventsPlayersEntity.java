package de.mywebsite.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eventsPlayers")
public class EventsPlayersEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "eventID", nullable = false)
	private int eventID;
	
	@Column(name = "players", nullable = false)
	private String player;

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}
	
}
