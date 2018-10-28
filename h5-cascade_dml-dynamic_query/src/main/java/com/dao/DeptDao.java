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
	 * �������棺1�Զ�
	 * ������
	 * <set cascade="save-update | all"
	 */
	public void saveDeptOne2Many(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		Dept d = new Dept();
		d.setDeptno((byte)50);	  //Error: ��ӳ���ļ����轫id��generator�ĳ�assigned���������� byte����ת����java.lang.Byte�����⡣
		d.setDname("acc");
		
		// ׼��Ա�� 
		Emp emp = new Emp();
		emp.setEmpno(1001);
		emp.setEname("tom");
		
		Emp emp1 = new Emp();
		emp1.setEmpno(1002);
		emp1.setEname("jack");
		
		Set empSet = new HashSet();
		empSet.add(emp);
		empSet.add(emp1);
		
		//��Ա�����浽������
		d.setEmps(empSet);
		
		session.save(d);
		
		tx.commit();
		session.close();
	}	// saveDeptOne2Many
	
	/**
	 * �������棺���һ
	 * ������
	 * <many-to-one cascade="save-update | all">
	 * 
	 */
	public void saveEmpMany2One(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		Emp emp = new Emp();
		emp.setEmpno(1003);
		emp.setEname("john");

		// ׼��dept
		Dept dept = new Dept();
		dept.setDeptno((byte)60);
		dept.setDname("acc2");
		
		// ��Ա�����䵽������
		emp.setDept(dept);

		session.save(emp);
		
		tx.commit();
		session.close();
	} //saveEmpMany2One
	
	/**
	 * ����ɾ����1�Զ�
	 * ������
	 * 1. �־û�����
	 * 2. ���ݿ�������ɾ��
	 * 3. ӳ���ļ� cascade=delete
	 * 
	 */
	public void deleteDept(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		// ��ʱ����
		//Dept dept = new Dept();
		//dept.setDeptno((byte)60);
		// => ������ɾ����Υ�����Լ��
		// => ��
		// => ���ӱ��¼�����ֵ�ÿգ�ɾ�������¼
		
		// �־û�����
		Dept dept = (Dept) session.load(Dept.class, (byte)50);
			
		session.delete(dept);
		
		tx.commit();
		session.close();
	}	// deleteDept
	
	/**
	 * �������£����һ
	 * 1. �־û�����
	 * 2. <many-to-one cascade="save-update | all">
	 * 
	 */
	public void updateEmp(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
			
		// �־û�����
		Emp emp = (Emp) session.get(Emp.class, 1003);
		emp.setEname("tommy");	
		
		// �������²�������
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
