package com.test;

import com.entity.Emp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class TestEmp {
    public void testQueryEmpById(){
        // 1. Configuration
        Configuration config = new Configuration().configure();

        // 2. ServiceRegistry
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();

        // 3. SessionFactory
        SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);

        // 4. Session
        Session session = sessionFactory.openSession();

        // 5. CRUD
        Emp emp = (Emp) session.get(Emp.class, (long)7369);
        System.out.println(emp.getEname());

        session.close();

    }

    public static void main(String[] args) {
        TestEmp t = new TestEmp();
        t.testQueryEmpById();
    }

}
