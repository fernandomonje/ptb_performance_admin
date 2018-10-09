package br.com.cleartech.ptb_performance_admin.web;

import java.sql.Connection;

import br.com.cleartech.ptb_performance_admin.dao.dao;

public class TesteDelete {

	public static void main(String[] args) {
		 
		dao myDao = new dao();
		
		Connection conn = myDao.getConnection();
		
		boolean deleteCarrier = myDao.deleteCarrier(conn, "0668");
		
		if(deleteCarrier ) {
			System.out.println("Data delete successed.");	
		} else {
			System.out.println("Data delete failed.");
		}
		
		myDao.closeConnection(conn);
	}

}
