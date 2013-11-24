package ananas.waymq.orm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class WaymqSessionFactory {

	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	private static Configuration configuration = new Configuration();
	private static SessionFactory sessionFactory;
	private static String configFile = CONFIG_FILE_LOCATION;

	private WaymqSessionFactory() {
	}

	public static Session getSession() {

		Session session = threadLocal.get();
		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession()
					: null;
			threadLocal.set(session);
		}
		return session;

	}

	public static void rebuildSessionFactory() {
		configuration.configure(configFile);
		sessionFactory = configuration.buildSessionFactory();
	}

	public static void closeSession() {
		Session session = threadLocal.get();
		threadLocal.set(null);
		if (session != null) {
			session.close();
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Configuration getConfiguration() {
		return configuration;
	}

}
