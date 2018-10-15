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
import br.com.cleartech.ptb_performance_admin.dao.OracleDAO;


@WebServlet (urlPatterns="/ListCarrier")
public class ListCarrierServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7272523035504302841L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		OracleDAO myDao = new OracleDAO();
		Connection conn = myDao.getConnection();
		List<Carrier> CarrierList = myDao.getCarrierList(conn); 
		myDao.closeConnection(conn);
		req.setAttribute("listCarrier", CarrierList);
		req.getRequestDispatcher("/ListCarrier.jsp").forward(req,resp);
	
	}

}