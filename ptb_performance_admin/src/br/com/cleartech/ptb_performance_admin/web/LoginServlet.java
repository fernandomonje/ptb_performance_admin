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
import br.com.cleartech.ptb_performance_admin.util.LoginLDAP;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6045464356213668756L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/Login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean auth_status = false;
		request.getSession().setMaxInactiveInterval(600);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Map<String, String> messages = new HashMap<String, String>();

		if (username == null || username.isEmpty()) {
			messages.put("username", "Preencha o usuario.");
		}

		if (password == null || password.isEmpty()) {
			messages.put("password", "Preencha a senha.");
		}

		if (messages.isEmpty()) {

			LoginLDAP auth = new LoginLDAP();
			try {
				auth_status = auth.authUser(username, password);
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}

			if (auth_status) {
				request.getSession().setAttribute("username", username);
				OracleDAO myDAO = new OracleDAO();
				Connection conn = myDAO.getConnection();
				myDAO.logAction(conn, username, 10);
				myDAO.closeConnection(conn);
				response.sendRedirect(request.getContextPath() + "/Home");
				return;
			} else {
				messages.put("login", "Falha no Login, tente novamente.");
			}
		}

		request.setAttribute("messages", messages);
		request.getRequestDispatcher("/Login.jsp").forward(request, response);
	}

}