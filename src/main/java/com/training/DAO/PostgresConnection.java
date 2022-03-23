package com.training.DAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresConnection {

	// Make a postgresql connection
	// return null if unable to make connection
	
	static Connection getConnection() {
		final String propertiesFile = "/home/mark/dbase.properties";
		
		Properties properties = new Properties();
		FileReader rdr = null;
		try {
			rdr = new FileReader(propertiesFile);
			properties.load(rdr);
		} catch (FileNotFoundException e1) {
			System.out.println("Unable to Open " + propertiesFile);
			return null;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			Class.forName(properties.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to load driver");
			System.exit(1);
		}
		
		Connection connection = null;
        try {
        	connection = DriverManager.getConnection(
        			properties.getProperty("url"),
        			properties.getProperty("username"),
        			properties.getProperty("password"));
        } catch (SQLException e) {
			System.out.println("No connection: " + e.getMessage());
			System.exit(1);
		}
        return connection;
	}
}
