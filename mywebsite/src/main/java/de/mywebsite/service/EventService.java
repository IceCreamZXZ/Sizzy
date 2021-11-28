package de.mywebsite.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.mywebsite.model.EventModel;
import de.mywebsite.persistence.EventEntity;
import de.mywebsite.persistence.EventsPlayersEntity;

public class EventService {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("mywebsite");
	
	public static void createEvent(String eventName, String host, String location, String game, int maxPlayer, int registeretPlayers, Date date) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		EventEntity event = new EventEntity();
		try {
			et = em.getTransaction();
			et.begin();
			event.setEventName(eventName);
			event.setHost(host);
			event.setLocation(location);
			event.setGame(game);
			event.setMaxPlayer(maxPlayer);
			event.setRegisteretPlayers(registeretPlayers);
			event.setDate(date);
			em.persist(event);
			et.commit();
		}
		catch(Exception e) {
			if(et != null) {
				et.rollback();
			}
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static List<EventEntity> listAllEvents(int eventID) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT u FROM EventEntity u WHERE u.eventID = :eventID";
		
		TypedQuery<EventEntity> tq = em.createQuery(query, EventEntity.class);
		List<EventEntity> result = new ArrayList<EventEntity>();
		try {
			result = tq.getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return result;
		
	}
	
	public static EventEntity getSingleEvent(int eventID) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT u FROM EventEntity u WHERE u.eventId = :eventID";
		
		TypedQuery<EventEntity> tq = em.createQuery(query, EventEntity.class);
		EventEntity result = new EventEntity();
		tq.setParameter("eventID", eventID);
		try {
			result = tq.getSingleResult();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return result;
		
	}
	
	public static List<EventModel> eventsForUser(String username) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT u FROM EventsPlayersEntity u WHERE u.player = :username";
		
		TypedQuery<EventsPlayersEntity> tq = em.createQuery(query, EventsPlayersEntity.class);
		List<EventsPlayersEntity> result = new ArrayList<EventsPlayersEntity>();
		tq.setParameter("username", username);
		try {
			result = tq.getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		
		List<EventModel> list = new ArrayList<EventModel>();
		for(int i = 0; i < result.size(); i++) {
			EventModel event = new EventModel();
			EventsPlayersEntity epe = result.get(i);
			EventEntity ee = getSingleEvent(epe.getEventID());
			
			event.setDate(ee.getDate());
			event.setEventName(ee.getEventName());
			event.setGame(ee.getGame());
			event.setHost(ee.getHost());
			event.setLocation(ee.getLocation());
			event.setMaxPlayer(ee.getMaxPlayer());
			event.setRegisteredPlayers(ee.getRegisteretPlayers());
			
			list.add(event);
		}
		
		return list;
		
	}
	
}
