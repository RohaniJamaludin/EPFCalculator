package com.jobpoint.epfcalculator.model;

public class Socso {
	
	private int id;
	private double upperBound;
	private double lowerBound;
	private double employerShare;
	private double employeeShare;
	private boolean isSixty;
	
	public Socso() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getUpperBound() {
		return upperBound;
	}
	
	public void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}
	
	public double getLowerBound() {
		return lowerBound;
	}
	
	public void setLowerBound(double lowerBound) {
		this.lowerBound = lowerBound;
	}
	
	public double getEmployerShare() {
		return employerShare;
	}
	
	public void setEmployerShare(double employerShare) {
		this.employerShare = employerShare;
	}
	
	public double getEmployeeShare() {
		return employeeShare;
	}
	
	public void setEmployeeShare(double employeeShare) {
		this.employeeShare = employeeShare;
	}
	
	public boolean getIsSixty() {
		return isSixty;
	}
	
	public void setIsSixty(boolean isSixty) {
		this.isSixty = isSixty;
	}
	
}
