package com.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Emp;
import com.util.HibernateSessionFactory;

public class EmpDao {
	/**
	 * 保存1个员工
	 */
	public void saveEmp(){
		Session session = HibernateSessionFactory.getSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		
		// 瞬时状态的对象(transient obj)
		Emp emp = new Emp();
		//emp.setEmpno(1001);
		emp.setEname("tom");
		emp.setJob("sales");
		emp.setSal(2000.00);
		
		// emp对象变成持久化状态 
		// 执行保存，返回这条记录的主键
		Integer empno = (Integer) session.save(emp);
		System.out.println(empno);
		
		// 提交事务，对象被持久化到数据库
		tx.commit();
		
		session.close();
	}	// saveEmp
	
	/**
	 * 更新1个员工
	 */
	public void updateEmp(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		// (1) session.update()
		// 配置：select-before-update
		Emp emp = new Emp();
		emp.setEmpno(1001);
		emp.setEname("jack");	
		
		// 执行更新(根据主键更新)
		session.update(emp);	// => 其它未赋值的属性所对应的列的值为null.
		
		// (2) query接口，HQL语句，实现批量更新
		/*String hql = "UPDATE Emp SET comm = 230.00 WHERE comm = NULL";
		// error: ".... WHERE comm IS NULL";
		
		Query query = session.createQuery(hql);
		// 执行批量更新
		// 返回影响的记录数
		int result = query.executeUpdate();
		System.out.println(result);*/
		
		tx.commit();
		session.close();
	}	// updateEmp
	
	/**
	 * 删除1个员工
	 */
	public void deleteEmp(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		/*// 1. session.delete()
		Emp emp = new Emp();
		emp.setEmpno(1001);
		
		// 执行删除(根据主键删除)
		// 主键生成方式  ---> 行为 
		// assinged      先查询该员工是否存在，如果存在，则删除，否则不执行删除操作。
		// identity      直接删除，不执行预查询操作
		session.delete(emp);
		*/	
			
		// 2. query.executeUpdate(), 实现批量删除
		String hql = "DELETE FROM Emp WHERE comm = 230.00";
		
		Query query = session.createQuery(hql);
		
		// 执行批量删除
		// 返回影响的记录数
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
