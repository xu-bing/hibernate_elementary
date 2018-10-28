package com.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import com.entity.Stuclass;
import com.entity.Student;
import com.util.HibernateSessionFactory;

public class StuclassDao {
	/**
	 * 测试简单级联查询：1对多
	 * 查询班级时级联查询班级下的学生
	 */
	public void queryStuclass(){
		Session  session = HibernateSessionFactory.getSession();
		
		Stuclass stuclass = (Stuclass) session.get(Stuclass.class, 1);
		System.out.println(stuclass.getCname());
		
		// 级联查询班级下的学生
		/*Set studentSet = stuclass.getStudentSet();	// => 懒加载 
		//System.out.println(stuclass.getStudentSet());
		
		for (Object o : studentSet){
			Student student = (Student) o;
			System.out.println(student.getSid() + "::" + student.getSname());
		}*/
		
		/*List studentList = stuclass.getStudentList();
		System.out.println(studentList.size());*/
		
		/*Map studentMap = stuclass.getStudentMap();
		System.out.println(studentMap);*/
		
		List studentBag = stuclass.getStudentBag();
		System.out.println(studentBag.size());
		
		for (Object o : studentBag){
			Student student = (Student) o;
			System.out.println(student.getSid() + "::" + student.getSname());
		}
		
		session.close();
	}	// queryStuclass
	
	public static void main(String[] args) {
		StuclassDao s = new StuclassDao();
		s.queryStuclass();
	}	// main
}
