package com.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class JdbcDynamicQuery {
	public static void main(String[] args) {
		InputStream is = JdbcDynamicQuery.class.getResourceAsStream("/db.properties");
		Properties p = new Properties();
		try {
			p.load(is);
			
			Connection con = DriverManager.getConnection(p.getProperty("DB_url"), p.getProperty("DB_username"),
					p.getProperty("DB_password"));
			
			Scanner s = new Scanner(System.in);
			
			System.out.println("enter id");
			String s1 = s.nextLine();
			int id = Integer.parseInt(s1);

			System.out.println("enter name");
			String name = s.nextLine();

			System.out.println("enter age");
			String s2 = s.nextLine();
			int age = Integer.parseInt(s2);

			System.out.println("enter designation");
			String designation = s.nextLine();
			
			String sql = String.format("insert into emp values(%d,'%s',%d,'%s')", id, name, age, designation);
			
			System.out.println("Your sql query is :");
			System.out.println(sql);
			
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			System.out.println("record successfully saved");

             s.close();
             con.close();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}
