package com.jobpoint.epfcalculator.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateDatabase {


	public static void startDatabase() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Class.forName(ConnectDatabase.DRIVER);
		Connection connection = DriverManager.getConnection(ConnectDatabase.JDBC_URL);
		List<String[]> sqlQueryList = new ArrayList<String[]>();
		
		String[] sqlQueryEpf = {"Create table epf " +
				"(id int not null  GENERATED ALWAYS AS IDENTITY " + 
				"(START WITH 1, INCREMENT BY 1), " +
				"upperBound decimal(10,2) not null, " +
				"lowerBound decimal(10,2) not null, " +
				"employerShare decimal(10,2) not null, " +
				"employeeShare decimal(10,2) not null, " +
				"isSixty boolean not null, " +
				"percentage int not null, " +
				"PRIMARY KEY (id))", "epf"};
		sqlQueryList.add(sqlQueryEpf);
		
		String[] sqlQuerySocso = {"Create table socso " +
				"(id int not null  GENERATED ALWAYS AS IDENTITY " + 
				"(START WITH 1, INCREMENT BY 1), " +
				"upperBound decimal(10,2) not null, " +
				"lowerBound decimal(10,2) not null, " +
				"employerShare decimal(10,2) not null, " +
				"employeeShare decimal(10,2) not null, " +
				"isSixty boolean not null, " +
				"percentage int not null, " +
				"PRIMARY KEY (id))", "socso"};
		sqlQueryList.add(sqlQuerySocso);
		
		String[] sqlQuerySIP = {"Create table sip " +
				"(id int not null  GENERATED ALWAYS AS IDENTITY " + 
				"(START WITH 1, INCREMENT BY 1), " +
				"upperBound decimal(10,2) not null, " +
				"lowerBound decimal(10,2) not null, " +
				"employerShare decimal(10,2) not null, " +
				"employeeShare decimal(10,2) not null, " +
				"PRIMARY KEY (id))", "sip"};
		sqlQueryList.add(sqlQuerySIP);
		
		Iterator<String[]> iterator = sqlQueryList.iterator();
		
		while(iterator.hasNext()) {
			String[] iteratorResult = (String[])iterator.next();
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			ResultSet resultSet = databaseMetaData.getTables(null, "APP", iteratorResult[1].toUpperCase(), null);
			if(resultSet.next()) {
				System.out.println("Table " + iteratorResult[1] + " exists");
			}else {
				connection.createStatement().execute(iteratorResult[0]);
			}
		}
		
		

		//connection.createStatement().execute(sqlQuery);
		
	}

}
