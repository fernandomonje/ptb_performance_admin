package br.com.cleartech.ptb_performance_admin.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cleartech.ptb_performance_admin.dao.OracleDAO;

@WebServlet(urlPatterns = "/Logout")
public class LogoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6797003899283448011L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = (String) req.getSession().getAttribute("username");
		Map<String,String> message = new HashMap<String,String>();
		message.put("login", "Logout efetuado");
		req.setAttribute("messages", message);
		OracleDAO myDao = new OracleDAO();
		Connection conn = myDao.getConnection();
		myDao.logAction(conn, username, 11);
		myDao.closeConnection(conn);
		req.getSession().removeAttribute("username");
		req.getRequestDispatcher("/Login.jsp").forward(req, resp);

	}

}
