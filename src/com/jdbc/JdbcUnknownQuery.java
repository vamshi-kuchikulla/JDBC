package com.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class JdbcUnknownQuery {
	public static void main(String[] args) {
		InputStream fis = JdbcPropertiesApp.class.getResourceAsStream("/db.properties");
		try {
			Properties p = new Properties();
			p.load(fis);
			Connection con = DriverManager.getConnection(p.getProperty("DB_url"), p.getProperty("DB_username"),
					p.getProperty("DB_password"));
			System.out.println("connection Established !!!!!");
			
			Statement st = con.createStatement();

			Scanner s = new Scanner(System.in);
			System.out.println("enter Query :");
			String sql = s.nextLine();

			// String sql = "select * from emp";
			// String sql1 = "update emp set name='Vimal',age =44, designation ='Chairman' where id=133";

			boolean b = st.execute(sql);
			if (b == true) {
				ResultSet rs = st.getResultSet();
				while (rs.next()) {
					System.out.println(
							rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));
				}
			} else {
				int updateCount = st.getUpdateCount();
				System.out.println(updateCount + " Record updated successfully ");
			}
			
			con.close();
			s.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
				
		}
	}
}
