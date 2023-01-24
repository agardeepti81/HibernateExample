package com.test.project.HibernateJPABeginner.models;

import java.util.Date;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity 
@NamedQuery(name="getAllEmployees", query = "select e from Employee e")
@NamedQueries({
	@NamedQuery(name="getEmpNameById", query="select e.name, e.salary, e.job from Employee e where id=:id"),
	@NamedQuery(name="findEmployeeByName", query="from Employee e where e.name=:name"),
	@NamedQuery(name ="empDepAlias", query="select e, e.officeCode , e.name, CASE When (e.officeCode = '1') THEN 'IT'"
			+ "WHEN (e.officeCode = '6') THEN 'Admin'"
			+ "WHEN (e.officeCode = '5') THEN 'HR'"
			+ "WHEN (e.officeCode = '4') THEN 'Developers'"
			+ "WHEN(e.officeCode = '3') THEN 'Accounts'"
			+ "WHEN (e.officeCode ='2') THEN 'Finanace'"
			+ "ELSE 'General' END FROM Employee e  "),	
})
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	int salary;
	String job;
	String addressLine;
	String zipCode;
	String city;
	Date startDate;
	int officeCode;
	
	public Employee(String name, int salary, String job, String addressLine, String zipCode, String city,
			Date startDate, int officeCode) {
		super();
		this.name = name;
		this.salary = salary;
		this.job = job;
		this.addressLine = addressLine;
		this.zipCode = zipCode;
		this.city = city;
		this.startDate = startDate;
		this.officeCode = officeCode;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", job=" + job + ", addressLine="
				+ addressLine + ", zipCode=" + zipCode + ", city=" + city + ", startDate=" + startDate + "]";
	}
	
}
