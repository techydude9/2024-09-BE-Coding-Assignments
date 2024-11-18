package projects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import projects.exception.DbException;

/*
 * This is Bob Ruzga's Week 7 Coding assignment.
 */

public class DbConnection {
	private static final String SCHEMA = "projects";
	private static final String USER = "projects";
	private static final String PASSWORD = "projects";
	private static final String HOST = "localhost";
	private static final int PORT = 3306;
	
	public static Connection getConnection() {
		String url = 
			String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s&useSSL=false",
					HOST, PORT, SCHEMA, USER, PASSWORD);
		
		System.out.println("Connecting with url=" + url);
		
		try {
			Connection conn = DriverManager.getConnection(url);
			System.out.println("Successfully obtained connection!");
			return conn;
		} catch (SQLException e) {
			System.out.println("Error getting a connection.");
			throw new DbException(e);
		}
	} // end of Connection method

} // end of DbConnection class
