package com.dao;

import org.hibernate.Session;

import com.entity.Stuclass;
import com.entity.Student;
import com.util.HibernateSessionFactory;

public class StudentDao {
	/**
	 * ���Լ�����ѯ�����һ
	 */
	public void queryStudent(){
		Session session = HibernateSessionFactory.getSession();
		
		Student student = (Student) session.get(Student.class, 1001);
		System.out.println(student.getSname());
		
		// ������ѯѧ������Ӧ�İ༶
		Stuclass stuclass = student.getStuclass();	// ������
		System.out.println(stuclass.getCname());
	
		
		session.close();
	}	// queryStudent
	
	
	public static void main(String[] args) {
		StudentDao s = new StudentDao();
		s.queryStudent();
	}
}
