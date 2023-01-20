package com.test.project.HibernateJPABeginner.controller;

import java.io.File;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import com.test.project.HibernateJPABeginner.models.User;

public class findUser_Hql {
	private static ServiceRegistry registry;
	private static SessionFactory factory;

	// Get all the users from the Database
	public void findUser() {
		try {
//		allows the application to specify properties and mapping documents to be used when creating a SessionFactory
			Configuration conf = new Configuration()
					.configure(new File("src/main/java/com/test/project/HibernateJPABeginner/hibernate.cfg.xml"));
			conf.addAnnotatedClass(User.class);
//		A ServiceRegistry, at its most basic, hosts and manages Services.
			registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();

//		The SessionFactory in Hibernate is responsible for the creation of Session objects. 
//		The Hibernate Session provides methods such as save, delete and update, 
//		all of which are used to perform CRUD-based operations on the database 
//		to which the SessionFactory connects.
			factory = conf.buildSessionFactory(registry);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}

		Session session = factory.openSession();
		try {
			String hql = "FROM User"; // Example of HQL to get all records of user class
			Query<User> query = session.createQuery(hql, User.class);
			List<User> results = query.getResultList();
			for (User u : results) {
				System.out.println("User Id: " + u.getId() + "|" + " Full name:" + u.getFullName() + "|" + "Email: "
						+ u.getEmail() + "|" + "password" + u.getPassword());
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}

//	Get all users from the Database using the select command
	public void findUserSelect(){
		try {
			Configuration conf = new Configuration()
					.configure(new File("src/main/java/com/test/project/HibernateJPABeginner/hibernate.cfg.xml"))
					.addAnnotatedClass(User.class);
			registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
			factory = conf.buildSessionFactory(registry);
		}catch(Throwable ex) {
			ex.printStackTrace();
		}
		Session session = factory.openSession();
		try {
			String hql = "select u FROM User u"; // Example of HQL to get all records of user class
			   Query<User> query = session.createQuery(hql, User.class);
			   List<User> results = query.getResultList();
			   for (User u : results) {
			       System.out.println("User Id: " +u.getId() + "|" + " Full name:" + u.getFullName() +"|"+ "Email: " + u.getEmail() +"|"+ "password" + u.getPassword());
			   }
			
		}catch(HibernateException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	//get record by id
	public void getRecordById() {
		try {
			Configuration conf = new Configuration()
					.configure(new File("src/main/java/com/test/project/HibernateJPABeginner/hibernate.cfg.xml"))
					.addAnnotatedClass(User.class);
			registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
			factory = conf.buildSessionFactory(registry);
		}catch(Throwable ex) {
			ex.printStackTrace();
		}
		Session session = factory.openSession();
		try {
			String hql = "From User u where u.Id>2 order by u.salary desc";
			Query<User> query = session.createQuery(hql, User.class);
			List<User> result = query.getResultList();
			for(User u:result) {
			     System.out.println("User Id: " +u.getId() + "|" + " Full name:" + u.getFullName() +"|"+ "Email: " + u.getEmail() +"|"+ "password" + u.getPassword());
			}
		}catch(HibernateException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	
	//Choose selected columns
	public void getRecords() {
		try {
			Configuration conf = new Configuration()
					.configure(new File("src/main/java/com/test/project/HibernateJPABeginner/hibernate.cfg.xml"))
					.addAnnotatedClass(User.class);
			registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
			factory = conf.buildSessionFactory(registry);
		}catch(Throwable ex) {
			ex.printStackTrace();
		}
		Session session = factory.openSession();
		try {
			String hql = "SELECT U.salary, U.city FROM User AS U";
			Query<Object[]> query = session.createQuery(hql,Object[].class);
			List<Object[]> result = query.getResultList();
			for(Object[] a: result) {
				System.out.println("Salary: " + a[0] + ", City: " + a[1]);
			}
		}catch(HibernateException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
	}
}
