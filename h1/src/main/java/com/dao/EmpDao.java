package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.entity.Emp;

public class EmpDao {
	/**
	 * 根据主键查询员工
	 */
	public void queryEmpById(){
		// 1. 实例化Configuration对象，并调用configure方法来加载hibernate.cfg.xml文件
		Configuration cfg = new Configuration().configure();
		
		// 2. 创建SessionFactory
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		
		// 3. 打开会话Session
		Session session = sessionFactory.openSession();
		
		// 4. 调用session中的方法进行CRUD操作
		Emp emp = (Emp) session.get(Emp.class, 7369);
		System.out.println(emp.getEname());
		
		// 5. 关闭资源
		session.close();
		
	}
	
	public static void main(String[] args) {
		EmpDao e = new EmpDao();
		e.queryEmpById();
	}
}
