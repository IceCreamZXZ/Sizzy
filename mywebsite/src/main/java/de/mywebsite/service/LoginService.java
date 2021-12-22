package de.mywebsite.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.mywebsite.model.UserModel;
import de.mywebsite.persistence.BannedEntity;
import de.mywebsite.persistence.LoginEntity;

public class LoginService {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("mywebsite");
	
	public static void createAccount(String username, String password) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		LoginEntity user = new LoginEntity();
		try {
			et = em.getTransaction();
			et.begin();
			user.setUsername(username);
			user.setPassword(password);
			user.setPermission("user");
			em.persist(user);
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
	
	public static LoginEntity getAccount(String username) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT u FROM LoginEntity u WHERE u.username = :username";
		
		TypedQuery<LoginEntity> tq = em.createQuery(query, LoginEntity.class);
		LoginEntity result = null;
		tq.setParameter("username", username);
		try {
			result = tq.getSingleResult();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		
		UserModel user = new UserModel();
		
		user.setPassword(result.getPassword());
		user.setPermission(result.getPermission());
		user.setOwnEvents(EventService.getOwnEvents(username));
		user.setRegisteredEvents(EventService.eventsForUser(username));
		user.setUsername(username);
		
		return result;
		
	}
	
	public static void deleteAccount(String username) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		LoginEntity user = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			user = em.find(LoginEntity.class, getAccount(username).getId());
			em.remove(user);
			et.commit();
		}
		catch (Exception e) {
			if(et!=null) {
				et.rollback();
			}
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static boolean isPasswordRight(String username, String password) {
		LoginEntity user = getAccount(username);
		String compPassword = user.getPassword();
		
		if (password.equals(compPassword)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public static void bannAccount(String username, String reason) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		BannedEntity user = new BannedEntity();
		
		deleteAccount(username);
		
		try {
			et.begin();
			user.setUsername(username);
			user.setReason(reason);
			em.persist(user);
			et.commit();
		}
		catch(Exception e) {
			if(et!=null) {
				et.rollback();
			}
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static void updatePermission(String username, String permission) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		LoginEntity user = getAccount(username);
		try {
			et = em.getTransaction();
			et.begin();
			user.setPermission(permission);
			em.merge(user);
			et.commit();
		}
		catch(Exception e) {
			if(et!=null) {
				et.rollback();
			}
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static List<UserModel> getAllUsers() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT u FROM LoginEntity u";
		
		TypedQuery<LoginEntity> tq = em.createQuery(query, LoginEntity.class);
		List<LoginEntity> result = new ArrayList<LoginEntity>();
		List<UserModel> users = new ArrayList<UserModel>();
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
			LoginEntity le = result.get(i);
			UserModel ue = new UserModel();
			
			ue.setOwnEvents(EventService.getOwnEvents(le.getUsername()));
			ue.setPassword(le.getPassword());
			ue.setPermission(le.getPermission());
			ue.setRegisteredEvents(EventService.eventsForUser(le.getUsername()));
			ue.setUsername(le.getUsername());
			
			users.add(ue);
		}
		
		return users;
		
	}
	
	public static boolean isBanned(String username) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT u FROM BannedEntity u";
		
		TypedQuery<BannedEntity> tq = em.createQuery(query, BannedEntity.class);
		List<BannedEntity> result = new ArrayList<BannedEntity>();
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
			BannedEntity be = result.get(i);
			
			if(be.getUsername().equals(username)) {
				return false;
			}
			
		}
		
		return true;
		
	}
	
	public static String getBannedMessage(String username) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT u FROM BannedEntity u WHERE u.username = :username";
		
		TypedQuery<BannedEntity> tq = em.createQuery(query, BannedEntity.class);
		tq.setParameter("username", username);
		BannedEntity result = new BannedEntity();
		try {
			result = tq.getSingleResult();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		
		String reason = result.getReason();
		
		return reason;
		
	}
	
}
