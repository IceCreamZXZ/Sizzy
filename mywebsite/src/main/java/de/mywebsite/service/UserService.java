package de.mywebsite.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.mywebsite.persistence.UserEntity;

public class UserService {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("mywebsite");
	
	public static void addUser(String fname, String lname) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			UserEntity user = new UserEntity();
			user.setfName(fname);
			user.setlName(lname);
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
	
	public static List<UserEntity> getUser(String fName) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT u FROM UserEntity u WHERE u.fName = :firstName";
		
		TypedQuery<UserEntity> tq = em.createQuery(query, UserEntity.class);
		List<UserEntity> list = new ArrayList<UserEntity>(); 
		tq.setParameter("firstName", fName);
		try {
			list = tq.getResultList();
		}
		catch (NoResultException e){
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return list;
	}
	
	public static void deleteUser(int userID) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		UserEntity user = null;
		try {
			et = em.getTransaction();
			et.begin();
			user = em.find(UserEntity.class, userID);
			em.remove(user);
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
	
	public static void changeEntry(int userID, String newFName, String newLName) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		UserEntity user = null;
		try {
			et = em.getTransaction();
			et.begin();
			user = em.find(UserEntity.class, userID);
			user.setfName(newFName);
			user.setlName(newLName);
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

}
