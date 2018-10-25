package com.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.User;
import com.util.HibernateSessionFactory;

public class UserDao {
	/**
	 * ���Զ�����������
	 */
	public void testObjectLife(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		// ��ʱ״̬
		User user = new User();
		user.setUserId(1003);
		user.setUname("jack");
		
		// ��ʱ״̬ת��Ϊ�־û�״̬
		session.save(user);
		//user.setUserId(1004);		// OID����Ϊnull�������޸�

		tx.commit();	// �ύ
		// => SQL: insert into....
		
		
		// ����״̬
		session.clear();
		session.close();
		System.out.println(user.getUname());
		
		user = null;	// �����������ڽ���
	}	// testObjectLife
	
	
	/**
	 * �־û����������ֵ���Ը����ݿ��е�ֵ����һ��
	 * ���룺���뿪������
	 */
	public void findUserById(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		User user = (User) session.get(User.class, 1002);	// �־û�����
		// dirty check
		user.setUname("jerry");
		
		tx.commit();	
		session.close();
	}	// findUserById
	
	
	/**
	 * ���ԣ�session.get/load
	 */
	public void getOrLoadUser(){
		Session session = HibernateSessionFactory.getSession();

		// ��������
		User user = (User) session.get(User.class, 1001);	// => select...
		System.out.println("get ---------------");
		System.out.println(user.getUname());
		// => ����ѯ���󲻴��ڣ���ָ���쳣
		
		// ������/�ӳټ��� 
		User user1 = (User) session.load(User.class, 1008);	
		System.out.println("load ------------");
		System.out.println(user1.getUname());		// => select ...
		// => ����ѯ���󲻴��ڣ� org.hibernate.ObjectNotFoundException
		
		session.close();
	}	// getOrLoadUser
	
	/**
	 * session�Ļ������
	 * 
	 * �ȼ�鵱ǰSession����(һ������)��
	 * �����ھͼ��������棬		//���ã����ٷ������ݿ��Ƶ�ʣ���߷���Ч��
	 * �������ھʹ����ݿ���л�ȡ
	 */
	public void testSessionCache(){
		Session session = HibernateSessionFactory.getSession();

		User user = (User) session.get(User.class, 1001);	
		System.out.println("------------");
		System.out.println(user.getUname());
		
		User user1 = (User) session.load(User.class, 1001);	
		System.out.println(user1.getUname());		
		
		session.close();
	}	// testSessionCache
	
	
	/**
	 * load()Ĭ��ʹ���ӳټ���
	 * ���ã�<class>lazy=false�������������
	 */
	public void testLoad(){
		Session session = HibernateSessionFactory.getSession();
		
		User user = (User) session.load(User.class, 1001); 	
		System.out.println("------------");
		System.out.println(user.getUname());		
		
		session.close();
	}	// testLoad
	
	public void updateUser(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		User user = new User();
		user.setUserId(1003);
		user.setUname("jerry");
		
		session.update(user);
		user.setUname("john");	//? jerry, john
		
		tx.commit();
		session.close();
	}	// updateUser
		
	/**
	 * ���ԣ�saveOrUpdate
	 */
	public void saveOrUpdateUser(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		User user = new User();
		user.setUserId(1003);
		user.setUname("jerry1");
		
		session.saveOrUpdate(user);
			
		tx.commit();
		session.close();
	}	// saveOrUpdateUser
	
	public static void main(String[] args) {
		UserDao userDao = new UserDao();
//		userDao.testObjectLife();
//		userDao.findUserById();
//		userDao.getOrLoadUser();
//		userDao.testSessionCache();
//		userDao.testLoad();
		userDao.updateUser();
//		userDao.saveOrUpdateUser();
	}
}
