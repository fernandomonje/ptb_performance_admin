package br.com.cleartech.ptb_performance_admin.web.tests;

import java.sql.Connection;
import java.util.List;

import br.com.cleartech.ptb_performance_admin.dao.OracleDAO;
import br.com.cleartech.ptb_performance_admin.util.Measurement;

public class TesteListMeasurement {

	public static void main(String[] args) {

	OracleDAO myDao = new OracleDAO();

	Connection conn = myDao.getConnection();
	
	List<Measurement> measurementList = myDao.getCarrierMeasurements(conn, "0999", "primary");
	if (measurementList != null) {
		for(Measurement measurement : measurementList) {
			System.out.println(measurement.getSpid() + "|" +
					measurement.getDateTime().toString() + "|" +
					String.valueOf(measurement.getUploadBandwidth()) + "|" +
					String.valueOf(measurement.getDownloadBandwidth()) + "|" +
					String.valueOf(measurement.getPingResponseTime()) + "|" +
					String.valueOf(measurement.getPingPacketLoss()) + "|" +
					measurement.getEnvironment());
			}
		}

	measurementList = myDao.getCarrierMeasurements(conn, "0999", "secondary");
	if (measurementList != null) {
		for(Measurement measurement : measurementList) {
			System.out.println(measurement.getSpid() + "|" +
					measurement.getDateTime().toString() + "|" +
					String.valueOf(measurement.getUploadBandwidth()) + "|" +
					String.valueOf(measurement.getDownloadBandwidth()) + "|" +
					String.valueOf(measurement.getPingResponseTime()) + "|" +
					String.valueOf(measurement.getPingPacketLoss()) + "|" +
					measurement.getEnvironment());
			}
		}
	}
	
	
}
