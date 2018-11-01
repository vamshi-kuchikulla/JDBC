package com.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcCallableStmtApp {
	public static void main(String[] args) {
		Connection con = null;
		CallableStatement cstmt =null;
		InputStream fis = JdbcCallableStmtApp.class.getResourceAsStream("/db.properties");
		try {
			Properties p = new Properties();
			p.load(fis);
			con = DriverManager.getConnection(p.getProperty("DB_url"), p.getProperty("DB_username"),
					p.getProperty("DB_password"));
			System.out.println("connection Established !!!!!");

		    cstmt=con.prepareCall("{call sp_addEmp(?,?,?,?)}");  
			cstmt.setInt(1, 333);
			cstmt.setString(2, "satyamma");
			cstmt.setInt(3, 63);
			cstmt.setString(4, "Director");
			cstmt.execute();
            
			System.out.println("success");
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(cstmt !=null) {
					cstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
