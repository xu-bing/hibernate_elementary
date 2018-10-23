package com.dao;

import com.entity.Emp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

public class EmpDaoTest {
    @Test
    public void testQueryEmpById(){
        // 1. Configuration
        Configuration config = new Configuration().configure();

        // 2. ServiceRegistry
//        ServiceRegistry: java.image中也有serviceRegistry
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();

        // 3. SessionFactory
        SessionFactory  sessionFactory = config.buildSessionFactory(serviceRegistry);

        // 4. Session
        Session session = sessionFactory.openSession();

        // 5. CRUD
        Emp emp = (Emp) session.get(Emp.class, Long.valueOf(7369));
        System.out.println(emp.getEname());

        session.close();

    }
}
