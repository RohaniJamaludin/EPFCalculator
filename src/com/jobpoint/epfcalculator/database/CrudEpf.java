package com.jobpoint.epfcalculator.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jobpoint.epfcalculator.model.Epf;

public class CrudEpf {
	private Connection connection;
	private Statement statement;
	
	public CrudEpf(){
		
	}
	
	public List<Epf> findAll() throws SQLException {
		
		connection = DriverManager.getConnection(ConnectDatabase.JDBC_URL);
		statement = connection.createStatement();
		
		String query = "Select * from epf where 1=1 ";

		ResultSet resultSet;
		
		try {
			resultSet = statement.executeQuery(query);			
			List<Epf> epfList = new ArrayList<>();
			
			while(resultSet.next()) {
				Epf epf = new Epf();
				epf.setId(resultSet.getInt("id"));
				epf.setUpperBound(resultSet.getDouble("upperBound"));
				epf.setLowerBound(resultSet.getDouble("lowerBound"));
				epf.setEmployerShare(resultSet.getDouble("employerShare"));
				epf.setEmployeeShare(resultSet.getDouble("employeeShare"));
				
				epfList.add(epf);
			}
			
			if(statement != null) statement.close();
			if(connection != null) connection.close();
			
			return epfList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
		
		
	}
	
public List<Epf> findAll(boolean isSixty) throws SQLException {
		
		connection = DriverManager.getConnection(ConnectDatabase.JDBC_URL);
		statement = connection.createStatement();
		
		String query; 
		query = "Select * from epf where isSixty = " + isSixty +
				" order by lowerBound ASC";
		
		ResultSet resultSet;
		
		try {
			resultSet = statement.executeQuery(query);			
			List<Epf> epfList = new ArrayList<>();
			
			while(resultSet.next()) {
				Epf epf = new Epf();
				epf.setId(resultSet.getInt("id"));
				epf.setUpperBound(resultSet.getDouble("upperBound"));
				epf.setLowerBound(resultSet.getDouble("lowerBound"));
				epf.setEmployerShare(resultSet.getDouble("employerShare"));
				epf.setEmployeeShare(resultSet.getDouble("employeeShare"));
				
				epfList.add(epf);
			}
			
			if(statement != null) statement.close();
			if(connection != null) connection.close();
			
			return epfList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
		
		
	}
	
	public Epf findById(int id) throws SQLException {
		
		connection = DriverManager.getConnection(ConnectDatabase.JDBC_URL);
		statement = connection.createStatement();
		
		Epf epf = new Epf();
		
		String query = "Select * from epf where id = " + id;
		ResultSet resultSet = statement.executeQuery(query);
		
		while(resultSet.next()) {
			epf.setId(resultSet.getInt("id"));
			epf.setUpperBound(resultSet.getDouble("upperBound"));
			epf.setLowerBound(resultSet.getDouble("lowerBound"));
			epf.setEmployerShare(resultSet.getDouble("employerShare"));
			epf.setEmployeeShare(resultSet.getDouble("employeeShare"));
		}
		
		if(statement != null) statement.close();
		if(connection != null) connection.close();
		
		return epf;
	}
	
	public Epf findByBoundLimit(Double basicSalaryAllowance, boolean isSixty) throws SQLException {
		connection = DriverManager.getConnection(ConnectDatabase.JDBC_URL);
		statement = connection.createStatement();
		
		Epf epf = new Epf();
		
		String query = "Select * from epf where  lowerBound <= " + basicSalaryAllowance + 
				                           " AND upperBound >= " +  basicSalaryAllowance +
				                           " AND isSixty = " + isSixty;
		ResultSet resultSet = statement.executeQuery(query);
		
		while(resultSet.next()) {
			epf.setId(resultSet.getInt("id"));
			epf.setUpperBound(resultSet.getDouble("upperBound"));
			epf.setLowerBound(resultSet.getDouble("lowerBound"));
			epf.setEmployerShare(resultSet.getDouble("employerShare"));
			epf.setEmployeeShare(resultSet.getDouble("employeeShare"));
		}
		
		if(statement != null) statement.close();
		if(connection != null) connection.close();
		return epf;
		
	}
	
	public int saveNew(Epf epf) {
		int id = 0;
		
		String query = "insert into epf (lowerBound, upperBound, employerShare, employeeShare, isSixty) values " + 
				"(" + epf.getLowerBound() + ", " + epf.getUpperBound() + ", " + 
					epf.getEmployerShare() + ", " + epf.getEmployeeShare() + ", " + epf.getIsSixty() + ")";
	
		
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
	
	public boolean updateChange(Epf epf) {
		String query = "Update epf Set lowerBound =  " + epf.getLowerBound() + ", " +
				"upperBound = " + epf.getUpperBound() + ", " +
				"employerShare = " + epf.getEmployerShare() + ", " +
				"employeeShare = " + epf.getEmployeeShare() + " " +
				"where id = " + epf.getId();
		
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
		String query = "Delete from epf where id = " + id;
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
