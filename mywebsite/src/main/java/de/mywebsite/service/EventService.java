package de.mywebsite.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.mywebsite.persistence.EventEntity;

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
			event.setDate(null);
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
	
}
