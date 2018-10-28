package com.dao;

import org.hibernate.Session;

import com.entity.Stuclass;
import com.entity.Student;
import com.util.HibernateSessionFactory;

public class StudentDao {
	/**
	 * 测试级联查询：多对一
	 */
	public void queryStudent(){
		Session session = HibernateSessionFactory.getSession();
		
		Student student = (Student) session.get(Student.class, 1001);
		System.out.println(student.getSname());
		
		// 级联查询学生所对应的班级
		Stuclass stuclass = student.getStuclass();	// 懒加载
		System.out.println(stuclass.getCname());
	
		
		session.close();
	}	// queryStudent
	
	
	public static void main(String[] args) {
		StudentDao s = new StudentDao();
		s.queryStudent();
	}
}
