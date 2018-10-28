package com.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Dept;
import com.entity.Emp;
import com.util.HibernateSessionFactory;

public class DeptDao {
	/**
	 * 级联保存：1对多
	 * 条件：
	 * <set cascade="save-update | all"
	 */
	public void saveDeptOne2Many(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		Dept d = new Dept();
		d.setDeptno((byte)50);	  //Error: 在映射文件中需将id的generator改成assigned，否则会出现 byte不能转换成java.lang.Byte的问题。
		d.setDname("acc");
		
		// 准备员工 
		Emp emp = new Emp();
		emp.setEmpno(1001);
		emp.setEname("tom");
		
		Emp emp1 = new Emp();
		emp1.setEmpno(1002);
		emp1.setEname("jack");
		
		Set empSet = new HashSet();
		empSet.add(emp);
		empSet.add(emp1);
		
		//将员工保存到部门中
		d.setEmps(empSet);
		
		session.save(d);
		
		tx.commit();
		session.close();
	}	// saveDeptOne2Many
	
	/**
	 * 级联保存：多对一
	 * 条件：
	 * <many-to-one cascade="save-update | all">
	 * 
	 */
	public void saveEmpMany2One(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		Emp emp = new Emp();
		emp.setEmpno(1003);
		emp.setEname("john");

		// 准备dept
		Dept dept = new Dept();
		dept.setDeptno((byte)60);
		dept.setDname("acc2");
		
		// 将员工分配到部门中
		emp.setDept(dept);

		session.save(emp);
		
		tx.commit();
		session.close();
	} //saveEmpMany2One
	
	/**
	 * 级联删除：1对多
	 * 条件：
	 * 1. 持久化对象
	 * 2. 数据库允许级联删除
	 * 3. 映射文件 cascade=delete
	 * 
	 */
	public void deleteDept(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		// 临时对象
		//Dept dept = new Dept();
		//dept.setDeptno((byte)60);
		// => 不允许删除，违反外键约束
		// => 或
		// => 将子表记录的外键值置空，删除主表记录
		
		// 持久化对象
		Dept dept = (Dept) session.load(Dept.class, (byte)50);
			
		session.delete(dept);
		
		tx.commit();
		session.close();
	}	// deleteDept
	
	/**
	 * 级联更新：多对一
	 * 1. 持久化对象
	 * 2. <many-to-one cascade="save-update | all">
	 * 
	 */
	public void updateEmp(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
			
		// 持久化对象
		Emp emp = (Emp) session.get(Emp.class, 1003);
		emp.setEname("tommy");	
		
		// 级联更新部门名称
		Dept dept = emp.getDept();
		dept.setDname("sale2");
		
		session.update(emp);
		
		tx.commit();
		session.close();
		
	}  //updateEmp
	
	public static void main(String[] args) {
		DeptDao deptDao = new DeptDao();
//		deptDao.saveDeptOne2Many();
//		deptDao.saveEmpMany2One();
//		deptDao.deleteDept();
		deptDao.updateEmp();
	}
}
