package com.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.User;
import com.util.HibernateSessionFactory;

public class UserDao {
	/**
	 * 测试对象生命周期
	 */
	public void testObjectLife(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		// 临时状态
		User user = new User();
		user.setUserId(1003);
		user.setUname("jack");
		
		// 临时状态转换为持久化状态
		session.save(user);
		//user.setUserId(1004);		// OID不能为null，不能修改

		tx.commit();	// 提交
		// => SQL: insert into....
		
		
		// 游离状态
		session.clear();
		session.close();
		System.out.println(user.getUname());
		
		user = null;	// 对象生命周期结束
	}	// testObjectLife
	
	
	/**
	 * 持久化对象的属性值可以跟数据库中的值保持一致
	 * 代码：必须开启事务
	 */
	public void findUserById(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		User user = (User) session.get(User.class, 1002);	// 持久化对象
		// dirty check
		user.setUname("jerry");
		
		tx.commit();	
		session.close();
	}	// findUserById
	
	
	/**
	 * 测试：session.get/load
	 */
	public void getOrLoadUser(){
		Session session = HibernateSessionFactory.getSession();

		// 立即加载
		User user = (User) session.get(User.class, 1001);	// => select...
		System.out.println("get ---------------");
		System.out.println(user.getUname());
		// => 若查询对象不存在：空指针异常
		
		// 懒加载/延迟加载 
		User user1 = (User) session.load(User.class, 1008);	
		System.out.println("load ------------");
		System.out.println(user1.getUname());		// => select ...
		// => 若查询对象不存在： org.hibernate.ObjectNotFoundException
		
		session.close();
	}	// getOrLoadUser
	
	/**
	 * session的缓存机制
	 * 
	 * 先检查当前Session缓存(一级缓存)，
	 * 不存在就检查二级缓存，		//作用：减少访问数据库的频率，提高访问效率
	 * 还不存在就从数据库表中获取
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
	 * load()默认使用延迟加载
	 * 配置：<class>lazy=false，变成立即加载
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
	 * 测试：saveOrUpdate
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
