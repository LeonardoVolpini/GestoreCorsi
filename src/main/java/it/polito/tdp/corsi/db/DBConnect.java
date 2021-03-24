package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	public static Connection getConnection() throws SQLException {
		String jdbcURL= "jdbc:mysql://localhost/iscritticorsi?user=root&password=Leonardo00";
		return DriverManager.getConnection(jdbcURL);
	}
}
