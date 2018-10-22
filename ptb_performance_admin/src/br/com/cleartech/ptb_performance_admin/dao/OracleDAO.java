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

public class OracleDAO {

	private final String db_user = "scada";
	private final String db_pass = "sca#0406";
	private final String db_host = "smcx5db01-vip";
	private final String db_port = "1521";
	private final String db_service = "compp";

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

}