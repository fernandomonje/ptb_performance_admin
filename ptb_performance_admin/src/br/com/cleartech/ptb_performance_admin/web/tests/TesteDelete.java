package br.com.cleartech.ptb_performance_admin.web.tests;

import java.sql.Connection;

import br.com.cleartech.ptb_performance_admin.dao.OracleDAO;

public class TesteDelete {

	public static void main(String[] args) {

		OracleDAO myDao = new OracleDAO();

		Connection conn = myDao.getConnection();

		boolean deleteCarrier = myDao.deleteCarrier(conn, "0668");

		if (deleteCarrier) {
			System.out.println("Data delete successed.");
		} else {
			System.out.println("Data delete failed.");
		}

		myDao.closeConnection(conn);
	}

}
