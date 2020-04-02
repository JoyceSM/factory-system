package ie.ait.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {//create connection with MySQL Workbench
	
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "admin";
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/factory", USER_NAME, PASSWORD);	
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
