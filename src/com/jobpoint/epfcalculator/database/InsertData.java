package com.jobpoint.epfcalculator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InsertData {

		public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
		public static final String JDBC_URL = "jdbc:derby:jpdb;create=true";

		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			// TODO Auto-generated method stub
			//Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(JDBC_URL);
			/*connection.createStatement().execute("insert into socso (lowerBound, upperBound, employerShare, employeeShare, isSixty) values " +
					"(0.01, 30.00, 0.30, 0.00, true), " +
					"(30.01, 50.00, 0.50, 0.00, true), " +
					"(50.01, 70.00, 0.80, 0.00, true), " +
					"(70.01, 100.00, 1.10, 0.00, true), " +
					"(100.01, 140.00, 1.50, 0.00, true), " +
					"(140.01, 200.00, 2.10, 0.00, true), " +
					"(200.01, 300.00, 3.10, 0.00, true), " +
					"(300.01, 400.00, 4.40, 0.00, true), " +
					"(400.01, 500.00, 5.60, 0.00, true), " +
					"(500.01, 600.00, 6.90, 0.00, true), " +
					"(600.01, 700.00, 8.10, 0.00, true), " +
					"(700.01, 800.00, 9.40, 0.00, true), " +
					"(800.01, 900.00, 10.60, 0.00, true), " +
					"(900.01, 1000.00, 11.90, 0.00, true) ," +
					"(1000.01, 1100.00, 13.10, 0.00, true), " +
					"(1100.01, 1200.00, 14.40, 0.00, true), " +
					"(1200.01, 1300.00, 15.60, 0.00, true)," + 
					"(1300.01, 1400.00, 16.90, 0.00, true), " +
					"(1400.01, 1500.00, 18.10, 0.00, true), " +
					"(1500.01, 1600.00, 19.40, 0.00, true), " +
					"(1600.01, 1700.00, 20.60, 0.00, true), " +
					"(1700.01, 1800.00, 21.90, 0.00, true), " +
					"(1800.01, 1900.00, 23.10, 0.00, true), " +
					"(1900.01, 2000.00, 24.40, 0.00, true), " +
					"(2000.01, 2100.00, 25.60, 0.00, true), " +
					"(2100.01, 2200.00, 26.90, 0.00, true), " +
					"(2200.01, 2300.00, 28.10, 0.00, true), " +
					"(2300.01, 2400.00, 29.40, 0.00, true), " +
					"(2400.01, 2500.00, 30.60, 0.00, true), " + 
					"(2500.01, 2600.00, 31.90, 0.00, true), " +
					"(2600.01, 2700.00, 33.10, 0.00, true), " +
					"(2700.01, 2800.00, 34.40, 0.00, true), " +
					"(2800.01, 2900.00, 35.60, 0.00, true), " +
					"(2900.01, 3000.00, 36.90, 0.00, true), " +
					"(3000.01, 3100.00, 38.10, 0.00, true), " +
					"(3100.01, 3200.00, 39.40, 0.00, true), " +
					"(3200.01, 3300.00, 40.60, 0.00, true), " +
					"(3300.01, 3400.00, 41.90, 0.00, true), " +
					"(3400.01, 3500.00, 43.10, 0.00, true), " +
					"(3500.01, 3600.00, 44.40, 0.00, true), " +
					"(3600.01, 3700.00, 45.60, 0.00, true), " +
					"(3700.01, 3800.00, 46.90, 0.00, true), " +
					"(3800.01, 3900.00, 48.10, 0.00, true), " +
					"(3900.01, 4000.00, 49.40, 0.00, true), " +
					"(4000.01, 9999.00, 49.40, 0.00, true)");*/
			/*connection.createStatement().execute("insert into socso (lowerBound, upperBound, employerShare, employeeShare, isSixty) values " +
					"(0.01, 30.00, 0.40, 0.10, false), " +
					"(30.01, 50.00, 0.70, 0.20, false), " +
					"(50.01, 70.00, 1.10, 0.30, false), " +
					"(70.01, 100.00, 1.50, 0.40, false), " +
					"(100.01, 140.00, 2.10, 0.60, false), " +
					"(140.01, 200.00, 2.95, 0.85, false), " +
					"(200.01, 300.00, 4.35, 1.25, false), " +
					"(300.01, 400.00, 6.15, 1.75, false), " +
					"(400.01, 500.00, 7.85, 2.25, false), " +
					"(500.01, 600.00, 9.65, 2.75, false), " +
					"(600.01, 700.00, 11.35, 3.25, false), " +
					"(700.01, 800.00, 13.15, 3.75, false), " +
					"(800.01, 900.00, 14.85, 4.25, false), " +
					"(900.01, 1000.00, 16.65, 4.75, false) ," +
					"(1000.01, 1100.00, 18.35, 5.25, false), " +
					"(1100.01, 1200.00, 20.15, 5.75, false), " +
					"(1200.01, 1300.00, 21.85, 6.25, false)," + 
					"(1300.01, 1400.00, 23.65, 6.75, false), " +
					"(1400.01, 1500.00, 25.35, 7.25, false), " +
					"(1500.01, 1600.00, 27.15, 7.75, false), " +
					"(1600.01, 1700.00, 28.85, 8.25, false), " +
					"(1700.01, 1800.00, 30.65, 8.75, false), " +
					"(1800.01, 1900.00, 32.35, 9.25, false), " +
					"(1900.01, 2000.00, 34.15, 9.75, false), " +
					"(2000.01, 2100.00, 35.85, 10.25, false), " +
					"(2100.01, 2200.00, 37.65, 10.75, false), " +
					"(2200.01, 2300.00, 39.35, 11.25, false), " +
					"(2300.01, 2400.00, 41.15, 11.75, false), " +
					"(2400.01, 2500.00, 42.85, 12.25, false), " + 
					"(2500.01, 2600.00, 44.65, 12.75, false), " +
					"(2700.01, 2800.00, 48.15, 13.75, false), " +
					"(2800.01, 2900.00, 49.85, 14.25, false), " +
					"(2900.01, 3000.00, 51.65, 14.75, false), " +
					"(3000.01, 3100.00, 53.35, 15.25, false), " +
					"(3100.01, 3200.00, 55.15, 15.75, false), " +
					"(3200.01, 3300.00, 56.85, 16.25, false), " +
					"(3300.01, 3400.00, 58.65, 16.75, false), " +
					"(3400.01, 3500.00, 60.35, 17.25, false), " +
					"(3500.01, 3600.00, 62.15, 17.75, false), " +
					"(3600.01, 3700.00, 63.85, 18.25, false), " +
					"(3700.01, 3800.00, 65.65, 18.75, false), " +
					"(3800.01, 3900.00, 67.35, 19.25, false), " +
					"(3900.01, 4000.00, 69.05, 19.75, false), " +
					"(4000.01, 9000.00, 69.05, 19.75, false)");*/
			
			
			//connection.createStatement().execute("Create table channels(channel varchar(20), topiv varchar(20), videoclip varchar(20))");
			/*connection.createStatement().execute("insert into epf (lowerBound, upperBound, employerShare, employeeShare, isSixty) values " +
														"(0.01, 10.00, 0.00, 0.00, true), " +
														"(10.01, 20.00, 2.00, 1.00, true), " +
														"(20.01, 40.00, 3.00, 2.00, true), " +
														"(40.01, 60.00, 4.00, 3.00, true), " +
														"(60.01, 80.00, 6.00, 4.00, true), " +
														"(80.01, 100.00, 7.00, 4.00, true), " +
														"(100.01, 120.00, 8.00, 5.00, true), " +
														"(120.01, 140.00, 10.00, 6.00, true), " +
														"(140.01, 160.00, 11.00, 7.00, true), " +
														"(160.01, 180.00, 12.00, 8.00, true), " +
														"(180.01, 200.00, 13.00, 8.00, true), " +
														"(200.01, 220.00, 15.00, 9.00, true), " +
														"(220.01, 240.00, 16.00, 10.00, true), " +
														"(240.01, 260.00, 17.00, 11.00, true), " +
														"(260.01, 280.00, 19.00, 12.00, true), " +
														"(280.01, 300.00, 20.00, 12.00, true), " +
														"(300.01, 320.00, 21.00, 13.00, true), " +
														"(320.01, 340.00, 23.00, 14.00, true), " +
														"(340.01, 360.00, 24.00, 15.00, true), " +
														"(360.01, 380.00, 25.00, 16.00, true), " +
														"(380.01, 400.00, 26.00, 16.00, true), " +
														"(400.01, 420.00, 28.00, 17.00, true), " +
														"(420.01, 440.00, 29.00, 18.00, true), " +
														"(440.01, 460.00, 30.00, 19.00, true), " +
														"(460.01, 480.00, 32.00, 20.00, true), " +
														"(280.01, 500.00, 33.00, 20.00, true), " +
														"(500.01, 520.00, 34.00, 21.00, true), " +
														"(520.01, 540.00, 36.00, 22.00, true), " +
														"(540.01, 560.00, 37.00, 23.00, true), " +
														"(560.01, 580.00, 38.00, 24.00, true), " +
														"(580.01, 600.00, 39.00, 24.00, true), " +
														"(600.01, 620.00, 41.00, 25.00, true), " +
														"(620.01, 640.00, 42.00, 26.00, true), " +
														"(640.01, 660.00, 43.00, 27.00, true), " +
														"(660.01, 680.00, 45.00, 28.00, true), " +
														"(680.01, 700.00, 46.00, 28.00, true), " +
														"(700.01, 720.00, 47.00, 29.00, true), " +
														"(720.01, 740.00, 49.00, 30.00, true), " +
														"(740.01, 760.00, 50.00, 31.00, true), " +
														"(760.01, 780.00, 51.00, 32.00, true), " +
														"(780.01, 800.00, 52.00, 32.00, true), " +
														"(800.01, 820.00, 54.00, 33.00, true), " +
														"(820.01, 840.00, 55.00, 34.00, true), " +
														"(840.01, 860.00, 56.00, 35.00, true), " +
														"(860.01, 880.00, 58.00, 36.00, true), " +
														"(880.01, 900.00, 59.00, 36.00, true), " +
														"(900.01, 920.00, 60.00, 37.00, true), " +
														"(920.01, 940.00, 62.00, 38.00, true), " +
														"(940.01, 960.00, 63.00, 39.00, true), " +
														"(960.01, 980.00, 64.00, 40.00, true), " +
														"(980.01, 1000.00, 65.00, 40.00, true), " +
														"(1000.01, 1020.00, 67.00, 41.00, true), " +
														"(1020.01, 1040.00, 68.00, 42.00, true), " +
														"(1040.01, 1060.00, 69.00, 43.00, true), " +
														"(1060.01, 1080.00, 71.00, 44.00, true), " +
														"(1080.01, 1100.00, 72.00, 44.00, true), " +
														"(1100.01, 1120.00, 73.00, 45.00, true), " +
														"(1120.01, 1140.00, 75.00, 46.00, true), " +
														"(1140.01, 1160.00, 76.00, 47.00, true), " +
														"(1160.01, 1180.00, 77.00, 48.00, true), " +
														"(1180.01, 1200.00, 78.00, 48.00, true), " +
														"(1200.01, 1220.00, 80.00, 49.00, true), " +
														"(1220.01, 1240.00, 81.00, 50.00, true), " +
														"(1240.01, 1260.00, 82.00, 51.00, true), " +
														"(1260.01, 1280.00, 84.00, 52.00, true), " +
														"(1280.01, 1300.00, 85.00, 52.00, true), " +
														"(1300.01, 1320.00, 86.00, 53.00, true), " +
														"(1320.01, 1340.00, 88.00, 54.00, true), " +
														"(1340.01, 1360.00, 89.00, 55.00, true), " +
														"(1360.01, 1380.00, 90.00, 56.00, true), " +
														"(1380.01, 1400.00, 91.00, 56.00, true), " +
														"(1400.01, 1420.00, 93.00, 57.00, true), " +
														"(1420.01, 1440.00, 94.00, 58.00, true), " +
														"(1440.01, 1460.00, 95.00, 59.00, true), " +
														"(1460.01, 1480.00, 97.00, 60.00, true), " +
														"(1480.01, 1500.00, 98.00, 60.00, true), " +
														"(1500.01, 1520.00, 99.00, 61.00, true), " +
														"(1520.01, 1540.00, 101.00, 62.00, true), " +
														"(1540.01, 1560.00, 102.00, 63.00, true), " +
														"(1560.01, 1580.00, 103.00, 64.00, true), " +
														"(1580.01, 1600.00, 104.00, 64.00, true), " +
														"(1600.01, 1620.00, 106.00, 65.00, true), " +
														"(1620.01, 1640.00, 107.00, 66.00, true), " +
														"(1640.01, 1660.00, 108.00, 67.00, true), " +
														"(1660.01, 1680.00, 110.00, 68.00, true), " +
														"(1680.01, 1700.00, 111.00, 68.00, true)" );*/
														
			//connection.createStatement().execute("CALL SYSCS_UTIL.SYSCS_EXPORT_TABLE (null,'EPF','c:/temp/epfbelow60.dat',',',null,null)"); 

			//connection.createStatement().execute("ALTER TABLE socso ALTER upperBound set data type decimal(10,2)");
			//connection.createStatement().execute("Alter table epf Add isSixty boolean");
			connection.createStatement().execute("Delete from epf where ID = 2702");
			//connection.createStatement().execute("update socso set isSixty = false where id = 1301");
			
			System.out.println("channels table created and records successfully inserted ....");
		}

}