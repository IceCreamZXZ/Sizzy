package de.mywebsite.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
		
		return result;
		
	}
	
	public static void deleteAccount(String username) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		LoginEntity user = new LoginEntity();
		
		try {
			int id = user.getId();
			et = em.getTransaction();
			et.begin();
			user = em.find(LoginEntity.class, id);
			em.remove(user);
			em.persist(user);
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
	
}
