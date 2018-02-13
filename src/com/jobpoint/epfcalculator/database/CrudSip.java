package com.jobpoint.epfcalculator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jobpoint.epfcalculator.model.Sip;

public class CrudSip {

	private Connection connection;
	private Statement statement;
	
public List<Sip> findAll() throws SQLException {
		
		connection = DriverManager.getConnection(ConnectDatabase.JDBC_URL);
		statement = connection.createStatement();
		
		String query = "Select * from sip where 1 = 1";

		ResultSet resultSet;
		
		try {
			resultSet = statement.executeQuery(query);			
			List<Sip> socsoList = new ArrayList<>();
			
			while(resultSet.next()) {
				Sip sip = new Sip();
				sip.setId(resultSet.getInt("id"));
				sip.setUpperBound(resultSet.getDouble("upperBound"));
				sip.setLowerBound(resultSet.getDouble("lowerBound"));
				sip.setEmployerShare(resultSet.getDouble("employerShare"));
				sip.setEmployeeShare(resultSet.getDouble("employeeShare"));
				
				socsoList.add(sip);
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

	public Sip findById(int id) throws SQLException {
	
		connection = DriverManager.getConnection(ConnectDatabase.JDBC_URL);
		statement = connection.createStatement();
	
		Sip sip = new Sip();
	
		String query = "Select * from sip where id = " + id;
		ResultSet resultSet = statement.executeQuery(query);
	
		while(resultSet.next()) {
			sip.setId(resultSet.getInt("id"));
			sip.setUpperBound(resultSet.getDouble("upperBound"));
			sip.setLowerBound(resultSet.getDouble("lowerBound"));
			sip.setEmployerShare(resultSet.getDouble("employerShare"));
			sip.setEmployeeShare(resultSet.getDouble("employeeShare"));
		}
	
		if(statement != null) statement.close();
		if(connection != null) connection.close();
	
		return sip;
	}
	
	public Sip findByBoundLimit(Double grossSalary) throws SQLException {
		connection = DriverManager.getConnection(ConnectDatabase.JDBC_URL);
		statement = connection.createStatement();

		Sip sip = new Sip();
		
		String query = "Select * from sip where  lowerBound <= " + grossSalary + " AND upperBound >=" + grossSalary;
		
		ResultSet resultSet = statement.executeQuery(query);
		
		while(resultSet.next()) {
			sip.setId(resultSet.getInt("id"));
			sip.setUpperBound(resultSet.getDouble("upperBound"));
			sip.setLowerBound(resultSet.getDouble("lowerBound"));
			sip.setEmployerShare(resultSet.getDouble("employerShare"));
			sip.setEmployeeShare(resultSet.getDouble("employeeShare"));
		}
		
		if(statement != null) statement.close();
		if(connection != null) connection.close();
		return sip;
		
	}
	
	public int saveNew(Sip sip) {
		int id = 0;
		
		String query = "insert into sip (lowerBound, upperBound, employerShare, employeeShare) values " + 
				"(" + sip.getLowerBound() + ", " + sip.getUpperBound() + ", " + 
				sip.getEmployerShare() + ", " + sip.getEmployeeShare() + ")";
	
		
		try {
			connection = DriverManager.getConnection(ConnectDatabase.JDBC_URL);
			
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
	
	
	public boolean updateChange(Sip sip) {
		String query = "Update sip Set lowerBound =  " + sip.getLowerBound() + ", " +
				"upperBound = " + sip.getUpperBound() + ", " +
				"employerShare = " + sip.getEmployerShare() + ", " +
				"employeeShare = " + sip.getEmployeeShare() + " " +
				"where id = " + sip.getId();
		
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
		String query = "Delete from sip where id = " + id;
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
