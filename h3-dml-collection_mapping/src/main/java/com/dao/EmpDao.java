package com.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Emp;
import com.util.HibernateSessionFactory;

public class EmpDao {
	/**
	 * ����1��Ա��
	 */
	public void saveEmp(){
		Session session = HibernateSessionFactory.getSession();
		// ��������
		Transaction tx = session.beginTransaction();
		
		// ˲ʱ״̬�Ķ���(transient obj)
		Emp emp = new Emp();
		//emp.setEmpno(1001);
		emp.setEname("tom");
		emp.setJob("sales");
		emp.setSal(2000.00);
		
		// emp�����ɳ־û�״̬ 
		// ִ�б��棬����������¼������
		Integer empno = (Integer) session.save(emp);
		System.out.println(empno);
		
		// �ύ���񣬶��󱻳־û������ݿ�
		tx.commit();
		
		session.close();
	}	// saveEmp
	
	/**
	 * ����1��Ա��
	 */
	public void updateEmp(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		// (1) session.update()
		// ���ã�select-before-update
		Emp emp = new Emp();
		emp.setEmpno(1001);
		emp.setEname("jack");	
		
		// ִ�и���(������������)
		session.update(emp);	// => ����δ��ֵ����������Ӧ���е�ֵΪnull.
		
		// (2) query�ӿڣ�HQL��䣬ʵ����������
		/*String hql = "UPDATE Emp SET comm = 230.00 WHERE comm = NULL";
		// error: ".... WHERE comm IS NULL";
		
		Query query = session.createQuery(hql);
		// ִ����������
		// ����Ӱ��ļ�¼��
		int result = query.executeUpdate();
		System.out.println(result);*/
		
		tx.commit();
		session.close();
	}	// updateEmp
	
	/**
	 * ɾ��1��Ա��
	 */
	public void deleteEmp(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		/*// 1. session.delete()
		Emp emp = new Emp();
		emp.setEmpno(1001);
		
		// ִ��ɾ��(��������ɾ��)
		// �������ɷ�ʽ  ---> ��Ϊ 
		// assinged      �Ȳ�ѯ��Ա���Ƿ���ڣ�������ڣ���ɾ��������ִ��ɾ��������
		// identity      ֱ��ɾ������ִ��Ԥ��ѯ����
		session.delete(emp);
		*/	
			
		// 2. query.executeUpdate(), ʵ������ɾ��
		String hql = "DELETE FROM Emp WHERE comm = 230.00";
		
		Query query = session.createQuery(hql);
		
		// ִ������ɾ��
		// ����Ӱ��ļ�¼��
		int result = query.executeUpdate();
		System.out.println(result);
		
		tx.commit();
		session.close();
	}	// deleteEmp
		
	public static void main(String[] args) {
		EmpDao empDao = new EmpDao();
		empDao.saveEmp();
//		empDao.updateEmp();
//		empDao.deleteEmp();
	}
}
