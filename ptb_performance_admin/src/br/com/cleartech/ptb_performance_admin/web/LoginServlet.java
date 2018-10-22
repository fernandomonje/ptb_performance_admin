package br.com.cleartech.ptb_performance_admin.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cleartech.ptb_performance_admin.util.LoginLDAP;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6045464356213668756L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	boolean auth_status = false;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //Map<String, String> messages = new HashMap<String, String>();

        if (username == null || username.isEmpty()) {
            //messages.put("username", "Please enter username");
        }

        if (password == null || password.isEmpty()) {
            //messages.put("password", "Please enter password");
        }

     //   if (messages.isEmpty()) {
        	
        	LoginLDAP auth = new LoginLDAP();
        	try {
				auth_status = auth.authUser(username, password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            if (auth_status) {
                request.getSession().setAttribute("username", username);
                request.getSession().setMaxInactiveInterval(600);
                response.sendRedirect(request.getContextPath() + "/Home");
                return;
            } else {
                //messages.put("login", "Unknown login, please try again");
            }  
    //    }

        request.setAttribute("messages", "Falha No Login");
        request.getRequestDispatcher("/Login.jsp").forward(request, response);
    }

}