package com.test.project.HibernateJPABeginner;

import java.io.File;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.test.project.HibernateJPABeginner.models.User;

import lombok.NonNull;

public class App {
	private static ServiceRegistry registry;
	private static SessionFactory factory;

	public static void main(String[] args) {

		try {

			// configuration
			Configuration conf = new Configuration()
					.configure(new File("src/main/java/com/test/project/HibernateJPABeginner/hibernate.cfg.xml"));
			conf.addAnnotatedClass(User.class);
			// registry
			registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();

			factory = conf.buildSessionFactory(registry);

		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		
		Transaction tx = null;
		Transaction tx1 = null;
		Transaction tx2 = null;
		Session session = factory.openSession();
		//Create Users and Read Users
		try {
			System.out.println("Creating and Reading new Users");
			tx = session.beginTransaction();
			//Create User
			User u1 = new User("User 1","user0011@yahoo.com","password1",25,46789.45,"Washington DC");
			User u2 = new User("User 2","user0012@yahoo.com","password2",26,57789.45,"Austin");
			User u3 = new User("User 3","user0013@yahoo.com","password3",27,35789.45,"Detroit");
			User u4 = new User("User 4","user0014@yahoo.com","password4",28,38789.45,"Seattle");
			User u5 = new User("User 5","user0015@yahoo.com","password5",29,53789.45,"Charlotte");
			session.persist(u1);
			session.persist(u2);
			session.persist(u3);
			session.persist(u4);
			session.persist(u5);
			tx.commit();
			
			//Read User
			List<User> list = session.createQuery("from User", User.class).getResultList();
			System.out.println(list+"\n");
			
			//Deleting user with id 3
//			System.out.println("Delete user with id 3");
//			tx1 = session.beginTransaction();
//			session.merge(u3);
//			session.remove(u3);
//			tx1.commit();
//			//Read User
//			list = session.createQuery("from User", User.class).getResultList();
//			System.out.println(list+"\n");
//			
			//Update User
//			System.out.println("Update user with id 2");
//			tx2 = session.beginTransaction();
//			session.merge(u2);
//			u2.setCity("Chicago");
//			u2.setFullName("Name Updated");
//			session.persist(u2);
//			tx2.commit();
//			//Read User
//			list = session.createQuery("from User", User.class).getResultList();
//			System.out.println(list+"\n");
			

		} catch (HibernateException ex) {

			ex.printStackTrace();
			tx.rollback();

		}finally {
			session.close();

		}
	}
}
