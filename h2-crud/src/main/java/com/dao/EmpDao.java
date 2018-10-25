package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.entity.Emp;
import com.util.HibernateSessionFactory;

public class EmpDao {
	public void queryEmpById(){
		Session session = HibernateSessionFactory.getSession();
		
		/*// 方式1： session.get()
		Emp emp = (Emp) session.get(Emp.class, 7369);
		System.out.println(emp.getEname());
		
		// 方式2：session.load()
		Emp emp1 = (Emp) session.load(Emp.class, 7499);
		System.out.println(emp1.getEname());*/
		
		// 方式3： query接口, hql语句
		String hql = "FROM com.entity.Emp WHERE empno = 7521";
		
		Query query = session.createQuery(hql);
		Emp emp2 = (Emp) query.uniqueResult();
		
		System.out.println(emp2.getEname());
		
		
		session.close();
	}	// queryEmpById
	
	/**
	 * 查询所有员工
	 * query.list()
	 */
	public void queryAllEmps(){
		Session session = HibernateSessionFactory.getSession();
		
		
		//String hql = "FROM Emp";
		//String hql = "FROM　emp";  //=> HB会去找hhm中的定义的mapped的对象
		//String hql = "SELECT * FROM Emp";	// hql中不支持select * 的写法
		String hql = "SELECT e FROM Emp e";	// 使用别名
		
		Query query = session.createQuery(hql);		
		List<Emp> empList = query.list();
		
		for (Emp emp : empList){
			System.out.println(emp.getEmpno() + "::" + emp.getEname());
		}
			
		session.close();
	}	// queryAllEmps
	
	/**
	 * 属性查询
	 */
	public void queryAttrs(){
		Session session = HibernateSessionFactory.getSession();
		
		String hql = "SELECT empno, ename FROM Emp";
		
		Query query = session.createQuery(hql);
		
		// -------------------------------------
		List<Object> objList = query.list();	
		for (Object obj : objList){
			Object[] emp = (Object[])obj;	// 属性查询时，查询到的每个结果存于数组中
			
			for (Object o : emp){
				System.out.print(o + "\t");
			}
			
			System.out.println();
		}
		// --------------------------------------
		
		session.close();
	}	// queryAttrs
	
	/**
	 * 实例化查询
	 * 1) 在POJO类中定义构造方法
	 * 2) 创建实例并查询
	 */
	public void queryInstance(){
		Session session = HibernateSessionFactory.getSession();
		
		String hql = "SELECT new com.entity.Emp(e.empno, e.ename) FROM Emp e";
		
		Query query = session.createQuery(hql);
		
		List<Emp> empList = query.list();
		
		for (Emp emp : empList){
			System.out.println(emp.getEmpno() + "::" + emp.getEname());
		}
		
		session.close();
	}	// queryInstance
	
	/**
	 * 参数化查询
	 * 1. 位置法 : 从0开始
	 * 2. 名称法 ： =:(中间无空格)名称
	 */
	public void queryEmpsByParameters(int empno){
		Session session = HibernateSessionFactory.getSession();
		
		// 1. 按位置绑定参数
		String hql = "FROM Emp WHERE empno = ?"; 
		
		Query query = session.createQuery(hql);
		//query.setInteger(0, empno);	// 位置：从0开始
		query.setParameter(0, empno);
		
		// 2. 按名称绑定参数
		hql = "FROM Emp WHERE empno = :empno";	// (:(不能有空格)参数名称)
		query = session.createQuery(hql);
		//query.setInteger("empno", empno);
		query.setParameter("empno", empno);
		
		List<Emp> empList = query.list();
		
		for (Emp emp : empList){
			System.out.println(emp.getEmpno() + "::" + emp.getEname());
		}
		
		
		session.close();
	}	// queryEmpsByParameters
	
	/**
	 * 模糊化查询
	 * 通过like关键字，%不要写在hql语句中
	 */
	public void fuzzyQuery(String ename){
		Session session = HibernateSessionFactory.getSession();
		
		String hql = "FROM Emp WHERE ename LIKE ?";
		
		Query query = session.createQuery(hql);	
		query.setParameter(0, "%"+ename+"%");
		
		List<Emp> empList = query.list();
		
		for (Emp emp : empList){
			System.out.println(emp.getEmpno() + "::" + emp.getEname());
		}
		
		session.close();
	}	// fuzzyQuery
	
	/**
	 * 报表查询
	 */
	public void countEmps(){
		Session session = HibernateSessionFactory.getSession();
		
		// 1. count
		String hql = "SELECT COUNT(*) FROM Emp";  
		
		Query query = session.createQuery(hql);
		long count = (long) query.uniqueResult();
		System.out.println(count);
		
		// 2. max, min
		hql = "SELECT MAX(sal), MIN(sal) FROM Emp";
		
		query = session.createQuery(hql);
		Object[] objectArr = (Object[]) query.uniqueResult();
		System.out.println(objectArr[0] + "\t" + objectArr[1]);
		
		session.close();
	}	// countEmps
	
	/**
	 * 按部门号统计每个部门的员工平均薪水
	 * 结果为：部门号，平均薪水
	 */
	public void groupQuery(){
		Session session = HibernateSessionFactory.getSession();
		
		String hql = "SELECT deptno, AVG(sal) FROM Emp GROUP BY deptno";
		Query query = session.createQuery(hql);
		
		List list = query.list();
		
		for (Object obj : list){
			Object[] result = (Object[]) obj;
			
			System.out.println(result[0] + "::" + result[1]); 
		}
		
		session.close();
	}  // groupQuery
	
	/**
	 * 分页查询
	 * setFirstResut: begin (下标从0开始)
	 * setMaxResults: pageSize
	 */
	public void pagingQuery(){
		Session session = HibernateSessionFactory.getSession();
		
		String hql = "FROM Emp";
		
		Query query = session.createQuery(hql);	
		// ---------------------------
		query.setFirstResult(5);
		query.setMaxResults(3);
		// => Hibernate会自动将其转换为mysql下的真分页查询（limit函数）
		// ---------------------------
		
	
		List<Emp> empList = query.list();
		
		for (Emp emp : empList){
			System.out.println(emp.getEmpno() + "::" + emp.getEname());
		}
		
		session.close();
		
	}	// pagingQuery
	
	/**
	 * 命名查询
	 * 
	 */
	public void namedQuery(){
		Session session = HibernateSessionFactory.getSession();
		
		Query query = session.getNamedQuery("namedQuery");	// 获取命名查询	
		
		List<Emp> empList = query.list();
		
		for (Emp emp : empList){
			System.out.println(emp.getEmpno() + "::" + emp.getEname());
		}
		
		session.close();
	}	// namedQuery
	
	
	
	public static void main(String[] args) {
		EmpDao e = new EmpDao();
//		e.queryEmpById();
//		e.queryAllEmps();
//		e.queryAttrs();
//		e.queryInstance();
//		e.queryEmpsByParameters(7369);
//		e.fuzzyQuery("e");
//		e.countEmps();
//		e.groupQuery();
//		e.pagingQuery();
		e.namedQuery();
		
		
		
	}
}
