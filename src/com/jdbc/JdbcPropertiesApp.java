package com.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcPropertiesApp {
	public static void main(String[] args) throws ClassNotFoundException {
		InputStream fis = JdbcPropertiesApp.class.getResourceAsStream("/db.properties");
		Properties p = new Properties();
		try {
			p.load(fis);
			//Class.forName(p.getProperty("DB_driver"));
			Connection con = DriverManager.getConnection(p.getProperty("DB_url"), p.getProperty("DB_username"),
					p.getProperty("DB_password"));
			System.out.println("connection Established :");

			Statement st = con.createStatement();
			String sql = "select * from emp";
			String sql1 = "update emp set name='Vimal',age =44, designation ='Chairman' where id=133";
           
			//if you know the type of the query and it is select
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2));
			}

			//if you know the type of the query and it is non-select
			int rs1 = st.executeUpdate(sql1);
			if(rs1 ==1) {
				System.out.println("Record Updated Successfully");
			}

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}
