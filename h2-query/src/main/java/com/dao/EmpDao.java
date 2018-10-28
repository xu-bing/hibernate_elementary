package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.entity.Emp;
import com.util.HibernateSessionFactory;

public class EmpDao {
	public void queryEmpById(){
		Session session = HibernateSessionFactory.getSession();
		
		/*// ��ʽ1�� session.get()
		Emp emp = (Emp) session.get(Emp.class, 7369);
		System.out.println(emp.getEname());
		
		// ��ʽ2��session.load()
		Emp emp1 = (Emp) session.load(Emp.class, 7499);
		System.out.println(emp1.getEname());*/
		
		// ��ʽ3�� query�ӿ�, hql���
		String hql = "FROM com.entity.Emp WHERE empno = 7521";
		
		Query query = session.createQuery(hql);
		Emp emp2 = (Emp) query.uniqueResult();
		
		System.out.println(emp2.getEname());
		
		
		session.close();
	}	// queryEmpById
	
	/**
	 * ��ѯ����Ա��
	 * query.list()
	 */
	public void queryAllEmps(){
		Session session = HibernateSessionFactory.getSession();
		
		
		//String hql = "FROM Emp";
		//String hql = "FROM��emp";  //=> HB��ȥ��hhm�еĶ����mapped�Ķ���
		//String hql = "SELECT * FROM Emp";	// hql�в�֧��select * ��д��
		String hql = "SELECT e FROM Emp e";	// ʹ�ñ���
		
		Query query = session.createQuery(hql);		
		List<Emp> empList = query.list();
		
		for (Emp emp : empList){
			System.out.println(emp.getEmpno() + "::" + emp.getEname());
		}
			
		session.close();
	}	// queryAllEmps
	
	/**
	 * ���Բ�ѯ
	 */
	public void queryAttrs(){
		Session session = HibernateSessionFactory.getSession();
		
		String hql = "SELECT empno, ename FROM Emp";
		
		Query query = session.createQuery(hql);
		
		// -------------------------------------
		List<Object> objList = query.list();	
		for (Object obj : objList){
			Object[] emp = (Object[])obj;	// ���Բ�ѯʱ����ѯ����ÿ���������������
			
			for (Object o : emp){
				System.out.print(o + "\t");
			}
			
			System.out.println();
		}
		// --------------------------------------
		
		session.close();
	}	// queryAttrs
	
	/**
	 * ʵ������ѯ
	 * 1) ��POJO���ж��幹�췽��
	 * 2) ����ʵ������ѯ
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
	 * ��������ѯ
	 * 1. λ�÷� : ��0��ʼ
	 * 2. ���Ʒ� �� =:(�м��޿ո�)����
	 */
	public void queryEmpsByParameters(int empno){
		Session session = HibernateSessionFactory.getSession();
		
		// 1. ��λ�ð󶨲���
		String hql = "FROM Emp WHERE empno = ?"; 
		
		Query query = session.createQuery(hql);
		//query.setInteger(0, empno);	// λ�ã���0��ʼ
		query.setParameter(0, empno);
		
		// 2. �����ư󶨲���
		hql = "FROM Emp WHERE empno = :empno";	// (:(�����пո�)��������)
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
	 * ģ������ѯ
	 * ͨ��like�ؼ��֣�%��Ҫд��hql�����
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
	 * �����ѯ
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
	 * �����ź�ͳ��ÿ�����ŵ�Ա��ƽ��нˮ
	 * ���Ϊ�����źţ�ƽ��нˮ
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
	 * ��ҳ��ѯ
	 * setFirstResut: begin (�±��0��ʼ)
	 * setMaxResults: pageSize
	 */
	public void pagingQuery(){
		Session session = HibernateSessionFactory.getSession();
		
		String hql = "FROM Emp";
		
		Query query = session.createQuery(hql);	
		// ---------------------------
		query.setFirstResult(5);
		query.setMaxResults(3);
		// => Hibernate���Զ�����ת��Ϊmysql�µ����ҳ��ѯ��limit������
		// ---------------------------
		
	
		List<Emp> empList = query.list();
		
		for (Emp emp : empList){
			System.out.println(emp.getEmpno() + "::" + emp.getEname());
		}
		
		session.close();
		
	}	// pagingQuery
	
	/**
	 * ������ѯ
	 * 
	 */
	public void namedQuery(){
		Session session = HibernateSessionFactory.getSession();
		
		Query query = session.getNamedQuery("namedQuery");	// ��ȡ������ѯ	
		
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
