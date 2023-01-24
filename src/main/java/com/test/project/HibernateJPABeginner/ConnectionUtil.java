package com.test.project.HibernateJPABeginner;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.test.project.HibernateJPABeginner.models.Employee;
import com.test.project.HibernateJPABeginner.models.User;

public class ConnectionUtil {
	public static ServiceRegistry registry;
	public static SessionFactory factory;

	public static void connection() {
	try
	{
//		allows the application to specify properties and mapping documents to be used when creating a SessionFactory
		Configuration conf = new Configuration()
				.configure(new File("src/main/java/com/test/project/HibernateJPABeginner/hibernate.cfg.xml"));
		conf.addAnnotatedClass(User.class);
		conf.addAnnotatedClass(Employee.class);
//		A ServiceRegistry, at its most basic, hosts and manages Services.
		registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();

//		The SessionFactory in Hibernate is responsible for the creation of Session objects. 
//		The Hibernate Session provides methods such as save, delete and update, 
//		all of which are used to perform CRUD-based operations on the database 
//		to which the SessionFactory connects.
		factory = conf.buildSessionFactory(registry);
	}catch(Throwable ex)
	{
		ex.printStackTrace();
	}
	}
}
