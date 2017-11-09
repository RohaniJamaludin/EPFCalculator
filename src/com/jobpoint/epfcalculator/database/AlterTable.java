package com.jobpoint.epfcalculator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AlterTable {
	public static final String JDBC_URL = "jdbc:derby:jpdb;create=true";
	//public static final String SQL_STATEMENT = "ALTER TABLE APP.SOCSO drop lowerBound";
	//public static final String SQL_STATEMENT = "ALTER TABLE APP.EPF drop lowerBound";
	//public static final String SQL_STATEMENT = "ALTER TABLE APP.SOCSO ADD lowerBoundTemp decimal(10,2)";
	//public static final String SQL_STATEMENT = "ALTER TABLE APP.EPF ADD lowerBoundTemp decimal(10,2)";
	public static final String SQL_STATEMENT = "ALTER TABLE APP.EPF ADD percentage int default 8";
	//public static final String SQL_STATEMENT = "RENAME COLUMN APP.SOCSO.lowerBoundTemp TO lowerBound ";
	//public static final String SQL_STATEMENT = "RENAME COLUMN APP.EPF.lowerBoundTemp TO lowerBound ";
	/*		+ "second_bar_enter double, "
			+ "third_bar_enter double, "
			+ "first_bar_exit double, "
			+ "second_bar_exit double, "
			+ "third_bar_exit double";*/
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = DriverManager.getConnection(JDBC_URL);
		Statement statement = connection.createStatement();
		connection.createStatement().execute(SQL_STATEMENT);
		
		if(statement != null) statement.close();
		if(connection != null) connection.close();
		
		System.out.println("column successfully inserted ....");
			

	}


}
