package com.jobpoint.epfcalculator.model;

public class Epf {
	private int id;
	private double lowerBound;
	private double upperBound;
	private double employerShare;
	private double employeeShare;
	private boolean isSixty;
	private int percentage;
	
	public Epf() {
		
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
	
	public double getEmployeeShare() {
		return employeeShare;
	}
	
	public void setEmployeeShare(double employeeShare) {
		this.employeeShare = employeeShare;
	}
	
	public double getEmployerShare() {
		return employerShare;
	}
	
	public void setEmployerShare(double employerShare) {
		this.employerShare = employerShare;
	}
	
	public boolean getIsSixty() {
		return isSixty;
	}
	
	public void setIsSixty(boolean isSixty) {
		this.isSixty = isSixty;
	}
	
	public int getPercentage() {
		return percentage;
	}
	
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}


}
