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


@WebServlet (urlPatterns="/CarrierData")
public class CarrierData extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8788977022429259626L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String spid = req.getParameter("spid");
		dao myDao = new dao();
		Connection conn = myDao.getConnection();
		Carrier carrier = myDao.getCarrierData(conn, spid); 
		myDao.closeConnection(conn);
		req.setAttribute("Carrier", carrier);
		req.getRequestDispatcher("/carrierData.jsp").forward(req,resp);
	
	}

}