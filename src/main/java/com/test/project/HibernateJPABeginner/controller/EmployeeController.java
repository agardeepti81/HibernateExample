package com.test.project.HibernateJPABeginner.controller;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.test.project.HibernateJPABeginner.ConnectionUtil;
import com.test.project.HibernateJPABeginner.models.Employee;

public class EmployeeController {
	public void createEmployeeTable() {
		ConnectionUtil.connection();
		Session session = ConnectionUtil.factory.openSession();
		Transaction t =session.beginTransaction();
		Employee u1 = new Employee();
		t.commit();
		System.out.println("Successfully Saved");
		session.close();
	}
	
	public void findEmployeeById() {
		ConnectionUtil.connection();
		Session session = ConnectionUtil.factory.openSession();
		Query<Object[]> query = session.createNamedQuery("getEmpNameById",Object[].class)
				.setParameter("id", 3);
		Object[] result = query.getSingleResultOrNull();
		System.out.println("Employee name: " +result[0] +" | Employee Salary: "+ result[1] +" | Emp Job Title: "+ result[2]);
//		List<Object[]> result = query.getResultList();
//		for(Object[] o:result)
//			System.out.println("Employee name: " +o[0] +" | Employee Salary: "+ o[1] +" | Emp Job Title: "+ o[2]);
		session.close();
	}
	
	public void findEmployeeByname() {
		ConnectionUtil.connection();
		Session session = ConnectionUtil.factory.openSession();
		Query<Employee> query = session.createNamedQuery("findEmployeeByName", Employee.class)
				.setParameter("name", "Tom Thele");
		List<Employee> result = query.getResultList();
		for(Employee e:result)
			System.out.println(e);
		session.close();
	}
	
	public void ShowOfficeCodes_AsDepartment() {
		ConnectionUtil.connection();
		Session session = ConnectionUtil.factory.openSession();
		Query<Object[]> query = session.createNamedQuery("empDepAlias",Object[].class);
		List<Object[]> result = query.getResultList();
		for(Object[] o:result) {
				 System.out.println("OfficeCode: " + o[1] + " | Dep Name: " +o[3]+ " | Employee Name: " + o[2]);
		}
		session.close();
	}
	
	public void getAllEmployees() {
		ConnectionUtil.connection();
		Session session = ConnectionUtil.factory.openSession();
		Query<Employee> query = session.createNamedQuery("getAllEmployees",Employee.class);
		List<Employee> result = query.getResultList();
		for(Employee e:result)
			System.out.println(e.toString());
		session.close();
	}
}
