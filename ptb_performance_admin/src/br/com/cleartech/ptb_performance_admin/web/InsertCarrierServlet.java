package br.com.cleartech.ptb_performance_admin.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cleartech.ptb_performance_admin.dao.OracleDAO;


@WebServlet (urlPatterns="/InsertCarrier")
public class InsertCarrierServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 831376228153712209L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/InsertCarrier.html").forward(req,resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String spid = req.getParameter("spid");
        String name = req.getParameter("name");
        String status_str = req.getParameter("status");
        
        boolean status = false;
        if (status_str.equals("true")) {
        	status = true;
        }
		OracleDAO myDao = new OracleDAO();
		Connection conn = myDao.getConnection();
		boolean insert_status = myDao.insertCarrier(conn, spid, name, status);
		String ret_status = "false";
		if (insert_status) {
			ret_status = "true";
		}
		myDao.closeConnection(conn);
		req.setAttribute("insert_status", ret_status);
		req.getRequestDispatcher("/InsertCarrier.jsp").forward(req,resp);
	
	}

}