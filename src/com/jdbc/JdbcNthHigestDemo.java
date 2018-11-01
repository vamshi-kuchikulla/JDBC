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

public class JdbcNthHigestDemo {
	public static void main(String[] args) {
		InputStream is = JdbcDynamicQuery.class.getResourceAsStream("/db.properties");
		Properties p = new Properties();
		try {
			p.load(is);

			Connection con = DriverManager.getConnection(p.getProperty("DB_url"), p.getProperty("DB_username"),
					p.getProperty("DB_password"));

			Scanner s = new Scanner(System.in);

			System.out.println("enter N'th value");
			int n = s.nextInt();

			String sql = String.format(
					"SELECT * from( SELECT id, name, age, designation, RANK() OVER (order by age DESC) As k  from emp) As k where k="
							+ n);

			System.out.println("Your sql query is :");
			System.out.println(sql);

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				System.out
						.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));
			}

			// execute() demo
			boolean b = st.execute(sql);
			if (b == true) {
				ResultSet rs1 = st.getResultSet();
				while (rs1.next()) {
					System.out.println(
							rs1.getInt(1) + "\t" + rs1.getString(2) + "\t" + rs1.getInt(3) + "\t" + rs1.getString(4));
				}
			} else {
              int rowCount = st.getUpdateCount();
              System.out.println("no.of rows :"+rowCount);
			}

			s.close();
			con.close();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}
