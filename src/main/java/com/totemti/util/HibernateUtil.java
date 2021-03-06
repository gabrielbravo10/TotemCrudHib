package com.totemti.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import com.totemti.model.Funcionario;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		
		if (sessionFactory == null) {
			try {
				
				Configuration config = new Configuration();
				
				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();
				
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/TotemTI");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "password");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				
				settings.put(Environment.SHOW_SQL, "true");
				
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				
				settings.put(Environment.HBM2DDL_AUTO, "update");
				
				config.setProperties(settings);
				config.addAnnotatedClass(Funcionario.class);
				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
				
				sessionFactory = config.buildSessionFactory(serviceRegistry);
				
				
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return sessionFactory;
		
	}
	
	
	

}
