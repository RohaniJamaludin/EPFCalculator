package com.jobpoint.epfcalculator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jobpoint.epfcalculator.model.Socso;

public class CrudSocso {
	private Connection connection;
	private Statement statement;
	
public List<Socso> findAll() throws SQLException {
		
		connection = DriverManager.getConnection(ConnectDatabase.JDBC_URL);
		statement = connection.createStatement();
		
		String query = "Select * from socso where 1 = 1";

		ResultSet resultSet;
		
		try {
			resultSet = statement.executeQuery(query);			
			List<Socso> socsoList = new ArrayList<>();
			
			while(resultSet.next()) {
				Socso socso = new Socso();
				socso.setId(resultSet.getInt("id"));
				socso.setUpperBound(resultSet.getDouble("upperBound"));
				socso.setLowerBound(resultSet.getDouble("lowerBound"));
				socso.setEmployerShare(resultSet.getDouble("employerShare"));
				socso.setEmployeeShare(resultSet.getDouble("employeeShare"));
				
				socsoList.add(socso);
			}
			
			if(statement != null) statement.close();
			if(connection != null) connection.close();
			
			return socsoList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	public List<Socso> findAll(boolean isSixty) throws SQLException {
		
		connection = DriverManager.getConnection(ConnectDatabase.JDBC_URL);
		statement = connection.createStatement();
		
		String query = "Select * from socso where isSixty = " + isSixty + 
				" order by lowerBound ASC";

		ResultSet resultSet;
		
		try {
			resultSet = statement.executeQuery(query);			
			List<Socso> socsoList = new ArrayList<>();
			
			while(resultSet.next()) {
				Socso socso = new Socso();
				socso.setId(resultSet.getInt("id"));
				socso.setUpperBound(resultSet.getDouble("upperBound"));
				socso.setLowerBound(resultSet.getDouble("lowerBound"));
				socso.setEmployerShare(resultSet.getDouble("employerShare"));
				socso.setEmployeeShare(resultSet.getDouble("employeeShare"));
				
				socsoList.add(socso);
			}
			
			if(statement != null) statement.close();
			if(connection != null) connection.close();
			
			return socsoList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}

	public Socso findById(int id) throws SQLException {
	
		connection = DriverManager.getConnection(ConnectDatabase.JDBC_URL);
		statement = connection.createStatement();
	
		Socso socso = new Socso();
	
		String query = "Select * from socso where id = " + id;
		ResultSet resultSet = statement.executeQuery(query);
	
		while(resultSet.next()) {
			socso.setId(resultSet.getInt("id"));
			socso.setUpperBound(resultSet.getDouble("upperBound"));
			socso.setLowerBound(resultSet.getDouble("lowerBound"));
			socso.setEmployerShare(resultSet.getDouble("employerShare"));
			socso.setEmployeeShare(resultSet.getDouble("employeeShare"));
		}
	
		if(statement != null) statement.close();
		if(connection != null) connection.close();
	
		return socso;
	}
	
	public Socso findByBoundLimit(Double grossSalary, boolean isSixty) throws SQLException {
		connection = DriverManager.getConnection(ConnectDatabase.JDBC_URL);
		statement = connection.createStatement();

		Socso socso = new Socso();
		
		String query = "Select * from socso where  lowerBound <= " + grossSalary + " AND upperBound >=" + grossSalary + " AND isSixty = " + isSixty;
		
		ResultSet resultSet = statement.executeQuery(query);
		
		while(resultSet.next()) {
			socso.setId(resultSet.getInt("id"));
			socso.setUpperBound(resultSet.getDouble("upperBound"));
			socso.setLowerBound(resultSet.getDouble("lowerBound"));
			socso.setEmployerShare(resultSet.getDouble("employerShare"));
			socso.setEmployeeShare(resultSet.getDouble("employeeShare"));
		}
		
		if(statement != null) statement.close();
		if(connection != null) connection.close();
		return socso;
		
	}
	
	public int saveNew(Socso socso) {
		int id = 0;
		
		String query = "insert into socso (lowerBound, upperBound, employerShare, employeeShare, isSixty) values " + 
				"(" + socso.getLowerBound() + ", " + socso.getUpperBound() + ", " + 
				socso.getEmployerShare() + ", " + socso.getEmployeeShare() + " , " + socso.getIsSixty() + ")";
	
		
		try {
			connection = DriverManager.getConnection(ConnectDatabase.JDBC_URL);
			
			//statement = connection.createStatement(); 
			PreparedStatement preparedStatement;
			
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.executeUpdate();
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();

			if(resultSet.next()) {
				id = resultSet.getInt(1);
			}
			
			if(statement != null) statement.close();
			if(connection != null) connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	
	public boolean updateChange(Socso socso) {
		String query = "Update socso Set lowerBound =  " + socso.getLowerBound() + ", " +
				"upperBound = " + socso.getUpperBound() + ", " +
				"employerShare = " + socso.getEmployerShare() + ", " +
				"employeeShare = " + socso.getEmployeeShare() + " " +
				"where id = " + socso.getId();
		
		try {
			connection = DriverManager.getConnection(ConnectDatabase.JDBC_URL);
			statement = connection.createStatement();
			statement.execute(query);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteRecord(int id) {
		String query = "Delete from socso where id = " + id;
		try {
			connection = DriverManager.getConnection(ConnectDatabase.JDBC_URL);
			statement = connection.createStatement();
			statement.execute(query);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	

}
