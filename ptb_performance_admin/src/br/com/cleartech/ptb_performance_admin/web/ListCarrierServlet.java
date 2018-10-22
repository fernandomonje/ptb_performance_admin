package br.com.cleartech.ptb_performance_admin.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cleartech.ptb_performance_admin.dao.OracleDAO;
import br.com.cleartech.ptb_performance_admin.util.Carrier;

@WebServlet(urlPatterns = "/ListCarrier")
public class ListCarrierServlet extends HttpServlet {

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

		String CarrierListJson = "{\n";
		CarrierListJson += "	\"Carrier\": [\n";
		for (int i = 0; i < CarrierList.size(); i++) {
			Carrier carrier = new Carrier();
			carrier = CarrierList.get(i);
			CarrierListJson += "		{\n";
			CarrierListJson += "			\"spid\": \"" + carrier.getSpid() + "\",\n";
			CarrierListJson += "			\"name\": \"" + carrier.getName() + "\",\n";
			CarrierListJson += "			\"status\": \"" + carrier.getStatus() + "\"\n";
			if (i == (CarrierList.size() - 1)) {
				CarrierListJson += "		}\n";
			} else {
				CarrierListJson += "		},\n";
			}

		}
		CarrierListJson += "	]\n";
		CarrierListJson += "}\n";
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(CarrierListJson);

	}

}