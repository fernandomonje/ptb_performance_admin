package br.com.cleartech.ptb_performance_admin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cleartech.ptb_performance_admin.util.Carrier;
import br.com.cleartech.ptb_performance_admin.util.Measurement;

public class OracleDAO {

	private final String db_user = "xxxx";
	private final String db_pass = "xxxxx";
	private final String db_host = "xxxx";
	private final String db_port = "1521";
	private final String db_service = "xxxxx";

	public Connection getConnection() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Filed to find JDBC driver.");
			e.printStackTrace();
			return null;
		}
		Connection connection = null;
		try {

			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + this.db_host + ":" + this.db_port + "/" + this.db_service, this.db_user,
					this.db_pass);
		} catch (SQLException e) {
			System.out.println("Connection failed.");
			e.printStackTrace();
			return null;
		}

		if (connection != null) {
			return connection;
		} else {
			System.out.println("Failed to create connection.");
			return null;
		}
	}

	public void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO - Create Better error desc
			e.printStackTrace();
		}
	}

	public boolean insertCarrier(Connection conn, String spid, String name, boolean status) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("INSERT INTO PTB_MEASURE_SPID (SPID, NAME, STATUS) values (?, ?, ?)");
			pstmt.setString(1, spid);
			pstmt.setString(2, name);
			if (status) {
				pstmt.setInt(3, 1);
			} else {
				pstmt.setInt(3, 0);
			}
			pstmt.execute();
		} catch (SQLException e) {
			// TODO - Create Better error desc
			e.printStackTrace();
			return false;
		} finally {
			if (pstmt != null) {
				// Close the statement
				try {
					pstmt.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
		}

		return false;
	}

	public boolean deleteCarrier(Connection conn, String spid) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("DELETE FROM PTB_MEASURE_SPID WHERE SPID = ?");
			pstmt.setString(1, spid);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO - Create Better error desc
			e.printStackTrace();
			return false;
		} finally {
			if (pstmt != null) {
				// Close the statement
				try {
					pstmt.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
		}

		return false;
	}

	public boolean updateCarrier(Connection conn, String spid, String name, boolean status) {
		PreparedStatement pstmt = null;
		int status_int;
		if (status) {
			status_int = 1;
		} else {
			status_int = 0;
		}
		try {
			pstmt = conn.prepareStatement("UPDATE PTB_MEASURE_SPID SET NAME = ?, STATUS = ? WHERE SPID = ?");
			pstmt.setString(1, name);
			pstmt.setInt(2, status_int);
			pstmt.setString(3, spid);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO - Create Better error desc
			e.printStackTrace();
			return false;
		} finally {
			if (pstmt != null) {
				// Close the statement
				try {
					pstmt.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
		}

		return false;
	}

	public boolean updateCarrier(Connection conn, String spid, boolean status) {
		PreparedStatement pstmt = null;
		int status_int;
		if (status) {
			status_int = 1;
		} else {
			status_int = 0;
		}
		try {
			pstmt = conn.prepareStatement("UPDATE PTB_MEASURE_SPID SET STATUS = ? WHERE SPID = ?");
			pstmt.setInt(1, status_int);
			pstmt.setString(2, spid);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO - Create Better error desc
			e.printStackTrace();
			return false;
		} finally {
			if (pstmt != null) {
				// Close the statement
				try {
					pstmt.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
		}

		return false;
	}

	public boolean updateCarrier(Connection conn, String spid, String name) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("UPDATE PTB_MEASURE_SPID SET NAME = ? WHERE SPID = ?");
			pstmt.setString(1, name);
			pstmt.setString(2, spid);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO - Create Better error desc
			e.printStackTrace();
			return false;
		} finally {
			if (pstmt != null) {
				// Close the statement
				try {
					pstmt.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
		}

		return false;
	}

	public List<Carrier> getCarrierList(Connection conn) {
		List<Carrier> CarrierList = new ArrayList<Carrier>();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PTB_MEASURE_SPID");
			while (rs.next()) {
				Carrier carrier_result = new Carrier();
				carrier_result.setSpid(rs.getString("SPID"));
				carrier_result.setName(rs.getString("NAME"));
				carrier_result.setStatus(rs.getInt("STATUS"));
				CarrierList.add(carrier_result);
			}
		} catch (SQLException e) {
			// TODO - Create Better error desc
			e.printStackTrace();
			return null;
		} finally {
			if (stmt != null) {
				// Close the statement
				try {
					stmt.close();
					return CarrierList;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
		}

		return null;

	}

	public Carrier getCarrierData(Connection conn, String spid) {
		PreparedStatement pstmt = null;
		Carrier carrier_result = new Carrier();
		try {
			pstmt = conn.prepareStatement("SELECT * FROM PTB_MEASURE_SPID WHERE SPID = ?");
			pstmt.setString(1, spid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				carrier_result.setSpid(rs.getString("SPID"));
				carrier_result.setName(rs.getString("NAME"));
				carrier_result.setStatus(rs.getInt("STATUS"));
			}
		} catch (SQLException e) {
			// TODO - Create Better error desc
			e.printStackTrace();
			return null;
		} finally {
			if (pstmt != null) {
				// Close the statement
				try {
					pstmt.close();
					return carrier_result;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
		}

		return null;

	}
	
	public List<Measurement> getCarrierMeasurements(Connection conn, String spid, String environment) {
		PreparedStatement pstmt = null;
		List<Measurement> measurementList = new  ArrayList<Measurement>();
		try {
			pstmt = conn.prepareStatement("SELECT * FROM (SELECT * FROM PTB_MEASURE_DATA WHERE SPID = ? AND ENVIRONMENT = ? ORDER BY DATETIME DESC) WHERE ROWNUM < ?");
			pstmt.setString(1, spid);
			pstmt.setString(2, environment);
			pstmt.setInt(3, 4);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Measurement measurement = new Measurement();
				measurement.setSpid(rs.getString("SPID"));
				measurement.setDateTime(rs.getTimestamp("DATETIME"));
				measurement.setUploadBandwidth(rs.getFloat("UPLOAD_BANDWIDTH"));
				measurement.setDownloadBandwidth(rs.getFloat("DOWNLOAD_BANDWIDTH"));
				measurement.setPingResponseTime(rs.getFloat("PING_RESPONSE_TIME"));
				measurement.setPingPacketLoss(rs.getFloat("PING_PACKET_LOSS"));
				measurement.setEnvironment(rs.getString("ENVIRONMENT"));
				measurementList.add(measurement);
			}
			
		} catch (SQLException e) {
			// TODO - Create Better error desc
			e.printStackTrace();
			return null;
		} finally {
			if (pstmt != null) {
				// Close the statement
				try {
					pstmt.close();
					if (measurementList.isEmpty()) {
						return null;
					} else {
						return measurementList;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}
	
	public boolean logAction(Connection conn, String spid, String username, int actionId) {
		/*
		 * actionId - Refers to the actionId located at the action domain table.
		 * values:
		 * 1 - Insert new Carrier/SPID
		 * 2 - Edit Carrier/SPID
		 * 3 - Delete Carrier/SPID
		 * 10 - User Log in
		 * 11 - User Log out
		 * 
		 */
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("INSERT INTO PTB_MEASURE_ACTION_LOG (TIMESTAMP, USERNAME, ACTION_ID, SPID) VALUES (SYSDATE, ?, ?, ?)");
			pstmt.setString(1, username);
			pstmt.setInt(2, actionId);
			pstmt.setString(3, spid);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO - Create Better error desc
			e.printStackTrace();
			return false;
		} finally {
			if (pstmt != null) {
				// Close the statement
				try {
					pstmt.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}
		
	public boolean logAction(Connection conn, String username, int actionId) {
		/*
		 * actionId - Refers to the actionId located at the action domain table.
		 * values:
		 * 1 - Insert new Carrier/SPID
		 * 2 - Edit Carrier/SPID
		 * 3 - Delete Carrier/SPID
		 * 10 - User Log in
		 * 11 - User Log out
		 * 
		 */
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("INSERT INTO PTB_MEASURE_ACTION_LOG (TIMESTAMP, USERNAME, ACTION_ID) VALUES (SYSDATE, ?, ?)");
			pstmt.setString(1, username);
			pstmt.setInt(2, actionId);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO - Create Better error desc
			e.printStackTrace();
			return false;
		} finally {
			if (pstmt != null) {
				// Close the statement
				try {
					pstmt.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}
		
}
