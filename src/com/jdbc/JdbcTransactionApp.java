package com.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

class JdbcTransactionApp {
	public static void main(String args[]) {
		try {
			InputStream fis = JdbcTransactionApp.class.getResourceAsStream("/db.properties");
			Properties p = new Properties();
			p.load(fis);

			Class.forName(p.getProperty("DB_driver"));
			Connection con = DriverManager.getConnection(p.getProperty("DB_url"), p.getProperty("DB_username"),
					p.getProperty("DB_password"));

			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement("insert into emp values(?,?,?,?)");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			while (true) {

				System.out.println("enter id");
				String s1 = br.readLine();
				int id = Integer.parseInt(s1);

				System.out.println("enter name");
				String name = br.readLine();

				System.out.println("enter age");
				String s2 = br.readLine();
				int age = Integer.parseInt(s2);

				System.out.println("enter designation");
				String designation = br.readLine();

				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setInt(3, age);
				ps.setString(4, designation);
				ps.executeUpdate();

				System.out.println("commit/rollback");
				String answer = br.readLine();
				if (answer.equals("commit")) {
					con.commit();
				}
				if (answer.equals("rollback")) {
					con.rollback();
				}

				System.out.println("Want to add more records y/n");
				String ans = br.readLine();
				if (ans.equals("n")) {
					break;
				}

			}
			con.commit();
			System.out.println("record successfully saved");

			con.close();// before closing connection commit() is called
		} catch (IOException | ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}

	}
}