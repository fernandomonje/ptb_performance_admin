package br.com.cleartech.ptb_performance_admin.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cleartech.ptb_performance_admin.Carrier;
import br.com.cleartech.ptb_performance_admin.dao.dao;


@WebServlet (urlPatterns="/ListaSPID")
public class ListCarrier extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8788977022429259626L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		dao myDao = new dao();
		Connection conn = myDao.getConnection();
		List<Carrier> CarrierList = myDao.getCarrierList(conn); 
		myDao.closeConnection(conn);
		req.setAttribute("listCarrier", CarrierList);
		req.getRequestDispatcher("/listCarrier.jsp").forward(req,resp);
	
	}

}