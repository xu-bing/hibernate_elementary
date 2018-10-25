package com.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Dept;
import com.entity.Emp;
import com.entity.User;
import com.util.HibernateSessionFactory;

public class DeptDao {
	
	/**
	 * ���ԣ�һ�Զ���ӳټ���
	 */
	public void findDeptById(){
		Session session = HibernateSessionFactory.getSession();
		
		Dept d = (Dept) session.get(Dept.class, (byte)10);
		System.out.println(d.getDname());
		
		Set emps = d.getEmps();		// ������ѯ��Ĭ��Ϊ�ӳټ���  <set lazy="true">
		System.out.println(emps);
		
		session.close();
	}	// findDeptById
	
	
	/**
	 * ���ԣ����һ���ӳټ���
	 */
	public void findEmpById(){
		Session session = HibernateSessionFactory.getSession();
		
		Emp emp = (Emp) session.get(Emp.class, 7369);
		System.out.println(emp.getEname());
		
		Dept d = emp.getDept();	// ������ѯ��Ĭ��Ϊ�ӳټ���  <many-to-one lazy="proxy"> 
		System.out.println(d.getDname());
		
		session.close();
	}	// findEmpById
	
	/**
	 * �����������쳣1
	 */
	public void loadEmpById(){
		Session session = HibernateSessionFactory.getSession();
		
//		Emp emp = (Emp) session.get(Emp.class, 7369);
		Emp emp = (Emp) session.load(Emp.class, 7369);
		
		emp.getHiredate();
		
		session.close();
		System.out.println(emp.getEname());	
		// => org.hibernate.LazyInitializationException: 
		// could not initialize proxy - no Session
	}	// loadEmpById
	
	/**
	 * �����������쳣2
	 */
	public Set queryDept(){
		Session session = HibernateSessionFactory.getSession();
		
		Dept d = (Dept) session.get(Dept.class, (byte)10);
		System.out.println(d.getDname());
		
		Set emps = d.getEmps();		// �ӳټ���, ������� 
		
		//emps.size();	// ���extra: ��Ч
		//System.out.println(emps);
		//for (Object o : emps){
			
	    //}
		emps.iterator();
		
		// �������ĳ�ʼ��
		if (!Hibernate.isInitialized(emps)){
			Hibernate.initialize(emps);
		}
		
		session.close();
		
		return emps;
	}	// queryDept
	
	/**
	 * ���ԣ�query.list() ���ز���
	 * @return
	 */
	public List<Dept> queryAllDepts(){
		Session session = HibernateSessionFactory.getSession();
		
		String hql = "FROM Dept";
		
		Query query = session.createQuery(hql);
		
		List<Dept> deptList = query.list();		// query.list()�����������أ�����������Ĭ����Ϊ�ӳټ��ء�
	
		session.close();
		
		return deptList;
	}	// queryAllDepts
	
		
	public static void main(String[] args) {
		DeptDao deptDao = new DeptDao();
//		deptDao.findDeptById();
//		deptDao.findEmpById();
//		deptDao.loadEmpById();
		
		// ���ԣ�����������������쳣
//		Set empSet = deptDao.queryDept();
//		System.out.println(empSet.size()); //set��lazy="extra"�����������ã���������������쳣
//		System.out.println(empSet);
		
		// ���ԣ�query.list()�Ĺ�����ѯ
		List deptList = deptDao.queryAllDepts();
		System.out.println(deptList);	//�˴�ӡ���������ѯ����<set> lazy=falseʱ��ִ�й�������ļ��ء�
	}
}
