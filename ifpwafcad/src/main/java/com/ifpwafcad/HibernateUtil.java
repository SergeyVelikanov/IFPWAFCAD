/**
 * Hibernate Singleton SessionFactory class
 * for entire application
 */
package com.ifpwafcad;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * @author Sergio
 * 
 */
public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	// Create a Hibernate SessionFactory that is set up once for an application
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(configuration.getProperties())
					.buildServiceRegistry();
			SessionFactory sessionFactory = configuration
					.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		} catch (Throwable ex) {
		}
		return null;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
