package com.dao;

public class EmpDao {
    public void queryEmpById(){
        /**
         * 根据主键查询员工
         */
        public void queryEmpById(){
            // 1. 实例化Configuration对象, 并调用configure方法来加载hibernate.cfg.xml文件
            Configuration cfg = new Configuration().configure();

            // 2. 通过cfg，创建sessionFactory
            SessionFactory sessionFactory = cfg.buildSessionFactory();

            // 3. 通过sessionFactory，打开会话
            Session session = sessionFactory.openSession();

            // 4. 调用session的get方法来查询员工
            Emp emp = (Emp) session.get(Emp.class, 7369);
            System.out.println(emp.getEname());

            // 5. 关闭资源
            session.close();

        }	// queryEmpById

        public static void main(String[] args) {
            EmpDao e = new EmpDao();
            e.queryEmpById();
        }	// main

}
