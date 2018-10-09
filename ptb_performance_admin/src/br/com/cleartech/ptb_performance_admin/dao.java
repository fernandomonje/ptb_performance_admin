package br.com.cleartech.ptb_performance_admin;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class dao {

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
                    "jdbc:oracle:thin:@" + this.db_host + ":" + this.db_port + "/" + this.db_service, this.db_user, this.db_pass);
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
    		 pstmt = conn.prepareStatement ("INSERT INTO PTB_MEASURE_SPID (SPID, NAME, STATUS) values (?, ?, ?)");
    		 pstmt.setString(1,spid);
    		 pstmt.setString(2,name);
    		 if (status) {
    			 pstmt.setInt(3,1);
    		 } else {
    			 pstmt.setInt(3,0);
    		 }
    		 pstmt.execute ();
    	} catch (SQLException e) {
    		// TODO - Create Better error desc
    		e.printStackTrace();
    		return false;
    	} finally {
            if(pstmt!=null) {
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