package com.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcPstmtApp {
	public static void main(String[] args) {
		InputStream fis = JdbcPropertiesApp.class.getResourceAsStream("/db.properties");
		Properties p = new Properties();
		Connection con = null;
        PreparedStatement pstmt= null;
       // ResultSet rs =null;
		try {
			p.load(fis);
		    con = DriverManager.getConnection(p.getProperty("DB_url"), p.getProperty("DB_username"),
					p.getProperty("DB_password"));
			System.out.println("connection Established !!!!!");
			String sql ="insert into emp values(?,?,?,?)";
			//pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 201);
			pstmt.setString(2, "kris");
			pstmt.setInt(3, 31);
			pstmt.setString(4, "CA");
		    int updateCount = pstmt.executeUpdate();
		    
			pstmt.setInt(1, 204);
			pstmt.setString(2, "pid");
			pstmt.setInt(3, 30);
			pstmt.setString(4, "Erp");
			updateCount = pstmt.executeUpdate();
			
		    System.out.println(updateCount +"Records updated Sucesfully");
		   	
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}finally {
			try{
                if(pstmt != null) pstmt.close();
                if(con != null) con.close();
            } catch(Exception ex){}
		}
	}
}
