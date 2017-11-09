package com.jobpoint.epfcalculator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateData {
	
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String JDBC_URL = "jdbc:derby:jpdb;create=true";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//Class.forName(DRIVER);
		Connection connection = DriverManager.getConnection(JDBC_URL);
		Statement statement = connection.createStatement();
		
		String query = "Select * from socso where 1=1 ";

		ResultSet resultSet;
		
		try {
			resultSet = statement.executeQuery(query);			
			while(resultSet.next()) {
			
				query = "Update socso Set lowerBoundTemp =  " + resultSet.getDouble("lowerBound") + ", " +
						"upperBoundTemp = " + resultSet.getDouble("upperBound")  +
						" where id = " + resultSet.getInt("id");
				connection.createStatement().execute(query);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(connection != null) connection.close();
		if(statement != null) statement.close();
		
		System.out.println("channels table created and records successfully updated ....");
	}


}
