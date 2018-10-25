package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.entity.Emp;

public class EmpDao {
	/**
	 * ����������ѯԱ��
	 */
	public void queryEmpById(){
		// 1. ʵ����Configuration���󣬲�����configure����������hibernate.cfg.xml�ļ�
		Configuration cfg = new Configuration().configure();
		
		// 2. ����SessionFactory
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		
		// 3. �򿪻ỰSession
		Session session = sessionFactory.openSession();
		
		// 4. ����session�еķ�������CRUD����
		Emp emp = (Emp) session.get(Emp.class, 7369);
		System.out.println(emp.getEname());
		
		// 5. �ر���Դ
		session.close();
		
	}
	
	public static void main(String[] args) {
		EmpDao e = new EmpDao();
		e.queryEmpById();
	}
}
