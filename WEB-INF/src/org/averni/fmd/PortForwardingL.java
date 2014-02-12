package org.averni.fmd;

/* -*-mode:java; c-basic-offset:2; indent-tabs-mode:nil -*- */
/**
 * This program will demonstrate the port forwarding like option -L of
 * ssh command; the given port on the local host will be forwarded to
 * the given remote host and port on the remote side.
 *   $ CLASSPATH=.:../build javac PortForwardingL.java
 *   $ CLASSPATH=.:../build java PortForwardingL
 * You will be asked username, hostname, port:host:hostport and passwd. 
 * If everything works fine, you will get the shell prompt.
 * Try the port on localhost.
 *
 */
import com.jcraft.jsch.*;

public class PortForwardingL {
	public static void main(String[] arg) {

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

			// Channel channel=session.openChannel("shell");
			// channel.connect();

			int assinged_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assinged_port + " -> " + rhost
					+ ":" + rport);
		} catch (Exception e) {
			System.out.println(e);
		}
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