package com.ss.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



public class SessionFactoryUtil {
	
	//to disallow creating objects by other classes.
	private static SessionFactory sessionFactory;
	private static StandardServiceRegistry serviceRegistry;
	
	static {
		
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");
        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
     	serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
        sessionFactory = configObj.buildSessionFactory(serviceRegistry);
	}
	
	//Make Session Factory Singleton
	public static SessionFactory getSessionFactory() {
        return sessionFactory;
   }

}
