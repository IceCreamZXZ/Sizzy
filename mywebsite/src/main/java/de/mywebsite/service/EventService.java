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
import de.mywebsite.model.UserModel;
import de.mywebsite.persistence.EventEntity;
import de.mywebsite.persistence.EventsPlayersEntity;
import de.mywebsite.persistence.LoginEntity;

public class EventService {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("mywebsite");
	
	public static int createEvent(String eventName, String host, String location, String game, int maxPlayer, int registeretPlayers, Date date) {
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
		
		return event.getEventId();
		
	}
	
	public static List<EventModel> listAllEvents() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT u FROM EventEntity u";
		
		TypedQuery<EventEntity> tq = em.createQuery(query, EventEntity.class);
		List<EventEntity> result = new ArrayList<EventEntity>();
		List<EventModel> events = new ArrayList<EventModel>();
		try {
			result = tq.getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		
		for (int i = 0; i < result.size(); i++) {
			EventEntity ee = result.get(i);
			EventModel event = new EventModel();
			
			event.setDate(ee.getDate());
			event.setEventID(ee.getEventId());
			event.setEventName(ee.getEventName());
			event.setGame(ee.getGame());
			event.setHost(ee.getHost());
			event.setLocation(ee.getLocation());
			event.setMaxPlayer(ee.getMaxPlayer());
			event.setRegisteredPlayers(ee.getRegisteretPlayers());
			
			events.add(event);
		
		}
		
		return events;
		
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
			event.setEventID(ee.getEventId());
			
			list.add(event);
		}
		
		return list;
		
	}
	
	public static void eventSignUp(int eventID, String username) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		EventsPlayersEntity eventUser = new EventsPlayersEntity();
		try {
			et = em.getTransaction();
			et.begin();
			eventUser.setEventID(eventID);
			eventUser.setPlayer(username);
			em.persist(eventUser);
			et.commit();
			
		}
		catch (Exception e) {
			if (et != null) {
				et.rollback();
			}
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static void addPlayer(int eventID, String username) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		EventEntity event = getSingleEvent(eventID);
		int registeredPlayers = 0;
		try {
			et = em.getTransaction();
			et.begin();
			registeredPlayers = event.getRegisteretPlayers();
			registeredPlayers++;
			event.setRegisteretPlayers(registeredPlayers);
			em.merge(event);
			et.commit();
		}
		catch (Exception e) {
			if(et != null) {
				et.rollback();
			}
			
		}
		finally {
			em.close();
		}
		
	}
	
	public static List<EventModel> getOwnEvents(String username) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT u FROM EventEntity u WHERE u.host = :username";
		
		TypedQuery<EventEntity> tq = em.createQuery(query, EventEntity.class);
		List<EventEntity> result = new ArrayList<EventEntity>();
		tq.setParameter("username", username);
		try {
			result = tq.getResultList();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		
		List<EventModel> events = new ArrayList<EventModel>();
		
		for (int i = 0; i < result.size(); i++) {
			EventModel event = new EventModel();
			EventEntity ee = result.get(i);
			
			event.setDate(ee.getDate());
			event.setEventID(ee.getEventId());
			event.setEventName(ee.getEventName());
			event.setGame(ee.getGame());
			event.setHost(ee.getHost());
			event.setLocation(ee.getLocation());
			event.setMaxPlayer(ee.getMaxPlayer());
			event.setRegisteredPlayers(ee.getRegisteretPlayers());
			
			events.add(event);
		}
		
		return events;
		
	}
	
	public static List<UserModel> getUserForEvent(int eventID) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT u FROM EventsPlayersEntity u WHERE u.eventID = :eventID";
		
		TypedQuery<EventsPlayersEntity> tq = em.createQuery(query, EventsPlayersEntity.class);
		List<EventsPlayersEntity> result = new ArrayList<EventsPlayersEntity>();
		tq.setParameter("eventID", eventID);
		try {
			result = tq.getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		
		List<UserModel> list = new ArrayList<UserModel>();
		
		for (int i = 0; i < result.size(); i++) {
			EventsPlayersEntity epe = result.get(i);
			LoginEntity le = new LoginEntity();
			UserModel user = new UserModel();
			
			le = LoginService.getAccount(epe.getPlayer());
			
			user.setUsername(le.getUsername());
			user.setOwnEvents(getOwnEvents(le.getUsername()));
			user.setPassword(le.getPassword());
			user.setRegisteredEvents(eventsForUser(le.getUsername()));
			
			list.add(user);
		}
		
		return list;
		
	}
		
}
