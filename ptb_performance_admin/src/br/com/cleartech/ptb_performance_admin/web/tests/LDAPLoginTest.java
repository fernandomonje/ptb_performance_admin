package br.com.cleartech.ptb_performance_admin.web.tests;

import br.com.cleartech.ptb_performance_admin.util.LoginLDAP;

public class LDAPLoginTest {

	public static void main(String[] args) {
		LoginLDAP auth = new LoginLDAP();
		boolean auth_status = false;
		try {
			auth_status = auth.authUser("gcardoso", "clt@2018");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} finally {
			if(auth_status) {
				System.out.println("User authentication Successed.");
			} else {
				System.out.println("User authentication failed.");
			}
		}

	}

}
