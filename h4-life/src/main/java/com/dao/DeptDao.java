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
	 * 测试：一对多的延迟加载
	 */
	public void findDeptById(){
		Session session = HibernateSessionFactory.getSession();
		
		Dept d = (Dept) session.get(Dept.class, (byte)10);
		System.out.println(d.getDname());
		
		Set emps = d.getEmps();		// 关联查询，默认为延迟加载  <set lazy="true">
		System.out.println(emps);
		
		session.close();
	}	// findDeptById
	
	
	/**
	 * 测试：多对一的延迟加载
	 */
	public void findEmpById(){
		Session session = HibernateSessionFactory.getSession();
		
		Emp emp = (Emp) session.get(Emp.class, 7369);
		System.out.println(emp.getEname());
		
		Dept d = emp.getDept();	// 关联查询，默认为延迟加载  <many-to-one lazy="proxy"> 
		System.out.println(d.getDname());
		
		session.close();
	}	// findEmpById
	
	/**
	 * 测试懒加载异常1
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
	 * 测试懒加载异常2
	 */
	public Set queryDept(){
		Session session = HibernateSessionFactory.getSession();
		
		Dept d = (Dept) session.get(Dept.class, (byte)10);
		System.out.println(d.getDname());
		
		Set emps = d.getEmps();		// 延迟加载, 代理对象 
		
		//emps.size();	// 针对extra: 无效
		//System.out.println(emps);
		//for (Object o : emps){
			
	    //}
		emps.iterator();
		
		// 代理对象的初始化
		if (!Hibernate.isInitialized(emps)){
			Hibernate.initialize(emps);
		}
		
		session.close();
		
		return emps;
	}	// queryDept
	
	/**
	 * 测试：query.list() 加载策略
	 * @return
	 */
	public List<Dept> queryAllDepts(){
		Session session = HibernateSessionFactory.getSession();
		
		String hql = "FROM Dept";
		
		Query query = session.createQuery(hql);
		
		List<Dept> deptList = query.list();		// query.list()总是立即加载，但关联对象默认仍为延迟加载。
	
		session.close();
		
		return deptList;
	}	// queryAllDepts
	
		
	public static void main(String[] args) {
		DeptDao deptDao = new DeptDao();
//		deptDao.findDeptById();
//		deptDao.findEmpById();
//		deptDao.loadEmpById();
		
		// 测试：关联级别的懒加载异常
//		Set empSet = deptDao.queryDept();
//		System.out.println(empSet.size()); //set的lazy="extra"对它不起作用，不会出现懒加载异常
//		System.out.println(empSet);
		
		// 测试：query.list()的关联查询
		List deptList = deptDao.queryAllDepts();
		System.out.println(deptList);	//此打印不会关联查询。将<set> lazy=false时就执行关联级别的加载。
	}
}
