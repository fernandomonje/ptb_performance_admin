package br.com.cleartech.ptb_performance_admin.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cleartech.ptb_performance_admin.Carrier;
import br.com.cleartech.ptb_performance_admin.dao.OracleDAO;


@WebServlet (urlPatterns="/DeleteCarrier")
public class DeleteCarrierServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 633653621733601724L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/DeleteCarrier.html").forward(req,resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String spid = req.getParameter("spid");
        
		OracleDAO myDao = new OracleDAO();
		Connection conn = myDao.getConnection();
		boolean delete_status = myDao.deleteCarrier(conn, spid);
		String ret_status = "false";
		if (delete_status) {
			ret_status = "true";
		}
		myDao.closeConnection(conn);
		req.setAttribute("delete_status", ret_status);
		req.getRequestDispatcher("/DeleteCarrier.jsp").forward(req,resp);
	
	}

}