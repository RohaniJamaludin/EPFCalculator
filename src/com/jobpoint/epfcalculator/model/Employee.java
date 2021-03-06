package com.jobpoint.epfcalculator.model;

public class Employee {
	
	private int no;
	private String employeeNo;
    private String name;
    private String nric;
    private double basicSalary;
    private double grossSalary;
    private double unpaidLeave;
    private double allowance;
    private double employerEpf;
    private double employerSocso;
    private double employerSip;
    private double employeeEpf;
    private double employeeSocso;
    private double employeeSip;
    private int row;
    private String sheet;
 
    public Employee(int no, String employeeNo, String name, String nric, double basicSalary, double grossSalary, double unpaidLeave,
    		double allowance, double employerEpf, double employerSocso, double employerSip, double employeeEpf, double employeeSocso,double employeeSip,
    		int row, String sheet)
    {
        this.no = no;
        this.employeeNo = employeeNo;
        this.name = name;
        this.nric = nric;
        this.basicSalary = basicSalary;
        this.grossSalary = grossSalary;
        this.allowance = allowance;
        this.unpaidLeave = unpaidLeave;
        this.employerEpf = employerEpf;
        this.employerSocso = employerSocso;
        this.employerSip = employerSip;
        this.employeeEpf = employeeEpf;
        this.employeeSocso = employeeSocso;
        this.employeeSip = employeeSip;
        this.row = row;
        this.sheet = sheet;
    }
    
    public int getNo()
    {
        return no;
    }
 
    public void setNo(int no)
    {
        this.no = no;
    }
    
    public String getEmployeeNo() {
		return employeeNo;
    }
    
    public void setEmployeeNo(String employeeNo) {
    	this.employeeNo = employeeNo;
    }
 
    public String getName()
    {
        return name;
    }
 
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getNric(){
    	return nric;
    }
    
    public void setNric(String nric){
    	this.nric = nric;
    }
 
    public double getBasicSalary() {
    	return basicSalary;
    }
    
    public void setBasicSalary(double basicSalary) {
    	this.basicSalary = basicSalary;
    }
    
    public double getGrossSalary() {
    	return grossSalary;
    }
    
    public void setGrossSalary(double grossSalary) {
    	this.grossSalary = grossSalary;
    }
    
    public double getUnpaidLeave() {
    	return unpaidLeave;
    }
    
    public void setUnpaidLeave(double unpaidLeave) {
    	this.unpaidLeave = unpaidLeave;
    }
    
    public double getAllowance() {
    	return allowance;
    }
    
    public void setAllowance(double allowance) {
    	this.allowance = allowance;
    }
    
    public double getEmployerEpf() {
    	return employerEpf;
    }
    
    public void setEmployerEpf(double employerEpf) {
    	this.employerEpf = employerEpf;
    }
    
    public double getEmployerSocso() {
    	return employerSocso;
    }
    
    public void setEmployerSocso(double employerSocso) {
    	this.employerSocso = employerSocso;
    }
    
    public double getEmployerSip() {
    	return employerSip;
    }
    
    public void setEmployerSip(double employerSip) {
    	this.employerSip = employerSip;
    }
    
    public double getEmployeeEpf() {
    	return employeeEpf;
    }
    
    public void setEmployeeEpf(double employeeEpf) {
    	this.employeeEpf = employeeEpf;
    }
    
    public double getEmployeeSocso() {
    	return employeeSocso;
    }
    
    public void setEmployeeSocso(double employeeSocso) {
    	this.employeeSocso = employeeSocso;
    }
    
    public double getEmployeeSip() {
    	return employeeSip;
    }
    
    public void setEmployeeSip(double employeeSip) {
    	this.employeeSip = employeeSip;
    }
    
    public int getRow() {
    	return row;
    }
    
    public void setRow(int row) {
    	this.row = row;
    }
    
    public String getSheet() {
    	return sheet;
    }
    
    public void setSheet(String sheet) {
    	this.sheet = sheet;
    }

}
