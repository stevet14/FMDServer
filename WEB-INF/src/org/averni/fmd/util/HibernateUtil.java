package org.averni.fmd.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {

		int lport;
		String rhost;
		int rport;

		try {
			JSch jsch = new JSch();

			String user = "steve";
			String host = "www.averni.org";

			Session session = jsch.getSession(user, host, 22);

			lport = 3310;
			rhost = "localhost";
			rport = 3306;

			session.setPassword("seidokan");
			LocalUserInfo userinfo = new LocalUserInfo();
			session.setUserInfo(userinfo);
			session.connect();

			int assinged_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assinged_port + " -> " + rhost
					+ ":" + rport);
		} catch (Exception e) {
			System.err.println(e);
		}

		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed

			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static class LocalUserInfo implements UserInfo {
		String passwd;

		public String getPassword() {
			return passwd;
		}

		public boolean promptYesNo(String str) {
			return true;
		}

		public String getPassphrase() {
			return null;
		}

		public boolean promptPassphrase(String message) {
			return true;
		}

		public boolean promptPassword(String message) {
			return true;
		}

		public void showMessage(String message) {
		}
	}

}