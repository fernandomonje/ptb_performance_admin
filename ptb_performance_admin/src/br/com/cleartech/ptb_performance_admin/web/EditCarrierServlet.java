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


@WebServlet (urlPatterns="/EditCarrier")
public class EditCarrierServlet extends HttpServlet{
	

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -8216465516159611136L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String spid = req.getParameter("spid");
		OracleDAO myDao = new OracleDAO();
		Connection conn = myDao.getConnection();
		Carrier carrier = myDao.getCarrierData(conn, spid); 
		myDao.closeConnection(conn);
		req.setAttribute("Carrier", carrier);
		req.getRequestDispatcher("/EditCarrier.jsp").forward(req,resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String spid = req.getParameter("spid");
        String name = req.getParameter("name");
        String status_str = req.getParameter("status");
        String carrier_status_from_db_str = "false";
        boolean name_chaged = false;
        boolean status_changed = false;
        boolean status_bool = false;
        boolean update_status = false;
        String ret_status = "false";
        String no_changes = "false";
		OracleDAO myDao = new OracleDAO();
		Connection conn = myDao.getConnection();
		Carrier carrier = myDao.getCarrierData(conn, spid);
		if (String.valueOf(carrier.getStatus()).equals("1")) {
			carrier_status_from_db_str= "true";
		}		
		if(!carrier.getName().equals(name)) {
			name_chaged = true;
		}
		if(!carrier_status_from_db_str.equals(status_str)) {
			status_changed = true;
		}
		if (status_str.equals("true")) {
			status_bool = true;
		}
		if (name_chaged && status_changed) {
			update_status = myDao.updateCarrier(conn, spid, name, status_bool);
		} else if (name_chaged && !status_changed) {
			update_status = myDao.updateCarrier(conn, spid, name);
		} else if (!name_chaged && status_changed) {
			update_status = myDao.updateCarrier(conn, spid, status_bool);
		} else {
			no_changes = "true";
		}
		if(update_status) {
			ret_status = "true";
		}
		myDao.closeConnection(conn);
		req.setAttribute("Carrier", carrier);
		req.setAttribute("update_status", ret_status);
		req.setAttribute("no_changes", no_changes);
		req.getRequestDispatcher("/EditCarrierResult.jsp").forward(req,resp);
	
	}
	

}