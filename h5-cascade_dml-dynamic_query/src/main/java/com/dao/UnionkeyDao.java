package com.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Dept;
import com.entity.Emp;
import com.entity.Key;
import com.entity.Unionkey;
import com.util.HibernateSessionFactory;

public class UnionkeyDao {
	public void save(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		// 准备联合主键
		Key key = new Key("1002", "tom3");

		Unionkey unionKey = new Unionkey(key, "f");
		
		session.save(unionKey);
		
		tx.commit();
		session.close();	
	}
	
	public static void main(String[] args) {
		UnionkeyDao u = new UnionkeyDao();
		u.save();
	}
}
