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
import br.com.cleartech.ptb_performance_admin.util.Measurement;

/**
 * Servlet implementation class CarrierMeasurementListServlet
 */
@WebServlet(urlPatterns = "/CarrierMeasurementList")
public class CarrierMeasurementListServlet extends HttpServlet {
	private static final long serialVersionUID = -6797663899283448011L;
       
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String spid = req.getParameter("spid");
		String environment = req.getParameter("environment");
		if (spid == null || environment == null) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("{ \"error\" : \"invalid data values\"}");
			return;
		}
		OracleDAO myDao = new OracleDAO();
		Connection conn = myDao.getConnection();
		List<Measurement> MeasurementList = myDao.getCarrierMeasurements(conn, spid, environment);
		myDao.closeConnection(conn);
		if (MeasurementList != null) {
			String MeasurementListJson = "{\n";
			MeasurementListJson += "	\"Measurement\": {\n";
			MeasurementListJson += "		\"" + spid + "\" : [\n";
			for (Measurement measurement: MeasurementList) {
				MeasurementListJson += "		{\n";
				MeasurementListJson += "			\"datetime\": \"" + measurement.getDateTime().toString() + "\",\n";
				MeasurementListJson += "			\"upload_bandwidth\": \"" + String.valueOf(measurement.getUploadBandwidth()) + "\",\n";
				MeasurementListJson += "			\"download_bandwidth\": \"" + String.valueOf(measurement.getDownloadBandwidth()) + "\",\n";
				MeasurementListJson += "			\"ping_response_time\": \"" + String.valueOf(measurement.getPingResponseTime()) + "\",\n";
				MeasurementListJson += "			\"ping_packet_loss\": \"" + String.valueOf(measurement.getPingPacketLoss()) + "\",\n";
				MeasurementListJson += "			\"environment\": \"" + measurement.getEnvironment() + "\"\n";
				
				if (MeasurementList.indexOf(measurement) == (MeasurementList.size() -1)) {
					MeasurementListJson += "		}]\n";
				} else {
					MeasurementListJson += "		},\n";
				}

			}
			MeasurementListJson += "	}\n";
			MeasurementListJson += "}\n";
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(MeasurementListJson);
			
		} else {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("{ \"" + spid + "\" : \"no-data\"}");
			return;
		}
		
	}

}
