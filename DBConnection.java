package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
private final static String URL = "jdbc:mysql://localhost:3306/prosports";
private final static String USERNAME = "root";
private final static String PASSWORD = "raiz";

private static DBConnection instance = new DBConnection();
private static Connection connection;

private DBConnection() {}

public static DBConnection getInstance() {
	return instance;
}

public Connection getConnection() { //if there is no connection, tries to connect using stored credentials, returns connection
	if ( connection == null ) {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println( "Connection was a success!" );
		}catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	return connection;
}

public static void closeConnection() { //ends the connection
	try {
		connection.close();
	}catch ( Exception e ) {
		e.printStackTrace();
	}finally {
		connection = null;
	}
}
}
