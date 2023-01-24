package com.test.project.HibernateJPABeginner.controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.test.project.HibernateJPABeginner.ConnectionUtil;
import com.test.project.HibernateJPABeginner.models.User;

public class findUser_Hql {

	// Get all the users from the Database
	public void findUser() {
		System.out.println("Running findUser");
		ConnectionUtil.connection();
		Session session = ConnectionUtil.factory.openSession();
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
		System.out.println("Running findUserSelect");
		ConnectionUtil.connection();
		Session session = ConnectionUtil.factory.openSession();
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
		System.out.println("Running getRecordById");
		ConnectionUtil.connection();
		Session session = ConnectionUtil.factory.openSession();
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
		System.out.println("Running getRecords");
		ConnectionUtil.connection();
		Session session = ConnectionUtil.factory.openSession();
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
	
	//get single result
	public void  getmaxSalary()
	{
		System.out.println("Running getMaxSalary");
		ConnectionUtil.connection();
		Session session = ConnectionUtil.factory.openSession();
		try {
			String hql = "SELECT max(U.salary) FROM User U";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql,User.class);
			double result = (double) query.getSingleResult();
			System.out.println("Max Salary: "+result);
			
			String hq = "SELECT COUNT(*) FROM User U";
			@SuppressWarnings("rawtypes")
			Query query1 = session.createQuery(hq,User.class);   
			long results = (long) query1.uniqueResult();
			System.out.println("No to total records: "+results);

		}catch(HibernateException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
	}

	public void getMaxSalaryGroupBy() {
		System.out.println("Running getMaxSalary group by");
		ConnectionUtil.connection();
		Session session = ConnectionUtil.factory.openSession();
		try {
			String hql = "SELECT SUM(U.salary), U.city FROM User U GROUP BY U.city";
			Query<Object[]> query = session.createQuery(hql,Object[].class);
			List<Object[]> result = query.getResultList();
			for(Object[] o:result) {
				System.out.println("Total salary " +o[0] +" | city: "+ o[1]);
			}
		}catch(HibernateException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public void namedQueryExample() {
		System.out.println("Running namedQueryExample");
		ConnectionUtil.connection();
		Session session = ConnectionUtil.factory.openSession();
		try {
			String hql = "FROM User u WHERE u.id = :id";
			Query<User> query = session.createQuery(hql, User.class);
			query.setParameter("id", 3);
			List<User> result = query.getResultList();
			for (User u : result) {
				System.out.println("User Id: " + u.getId() + "|" + " Full name:" + u.getFullName() + "|" + "Email: "
						+ u.getEmail() + "|" + "password: " + u.getPassword());
			}

		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}
	
}
