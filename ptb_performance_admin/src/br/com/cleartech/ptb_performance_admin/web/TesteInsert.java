package br.com.cleartech.ptb_performance_admin.web;

import java.sql.Connection;

import br.com.cleartech.ptb_performance_admin.dao.dao;

public class TesteInsert {
	
	public static void main(String[] args) {
		
		dao myDao = new dao();
		
		Connection conn = myDao.getConnection();
		
		boolean InsertCarrier = myDao.insertCarrier(conn, "0668", "TESTE DAO", true);
		if (InsertCarrier) {
			System.out.println("Data insert successed.");
		} else {
			System.out.println("Data insert failed.");
		}
		
		myDao.closeConnection(conn);
		
	}

}
