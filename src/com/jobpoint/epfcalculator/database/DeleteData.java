package com.jobpoint.epfcalculator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DeleteData {
	
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String JDBC_URL = "jdbc:derby:jpdb;create=true";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//Class.forName(DRIVER);
		Connection connection = DriverManager.getConnection(JDBC_URL);
		//connection.createStatement().execute("Create table channels(channel varchar(20), topiv varchar(20), videoclip varchar(20))");
		connection.createStatement().execute("Delete from APP.EPF where id = 3001");
		System.out.println("channels table created and records successfully inserted ....");
	}


}
