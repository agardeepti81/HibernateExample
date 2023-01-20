package com.test.project.HibernateJPABeginner;

import com.test.project.HibernateJPABeginner.controller.findUser_Hql;

public class Mainrunner {

	public static void main(String[] args) {
		findUser_Hql test = new findUser_Hql();
//		test.findUser();
//		test.findUserSelect();
//		test.getRecordById();
		test.getRecords();
	}

}
