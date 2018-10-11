package br.com.cleartech.ptb_performance_admin.web.tests;

import java.sql.Connection;

import br.com.cleartech.ptb_performance_admin.dao.dao;

public class UpdateTeste {

	public static void main(String[] args) {
		
		dao myDao = new dao();
		
		Connection conn = myDao.getConnection();
		
		boolean updateCarrier = myDao.updateCarrier(conn, "0066", "teste1", false);
		if (updateCarrier) {
			System.out.println("Data update successed.");
		} else {
			System.out.println("Data update failed.");
		}
		
		updateCarrier = myDao.updateCarrier(conn, "0668", false);
		if (updateCarrier) {
			System.out.println("Data update successed.");
		} else {
			System.out.println("Data update failed.");
		}
		
		updateCarrier = myDao.updateCarrier(conn, "0669", "teste1");
		if (updateCarrier) {
			System.out.println("Data update successed.");
		} else {
			System.out.println("Data update failed.");
		}
		myDao.closeConnection(conn);
		
		
		
		
	}

}
