package com.jobpoint.epfcalculator.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.jobpoint.epfcalculator.model.Employee;

public class EmployeeTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<Employee> employee;
	
	private final String[] columnNames = new String[] {
			"No.", "Employee No.", "Name", "NRIC", "Basic Salary", "Gross Salary", "Unpaid Leave", "Allowance",
			"Employer Epf", "Employer Socso", "Employee Epf", "Employee Socso", "Row in Excel"
    };
	
	public EmployeeTableModel(List<Employee> employee) {
		super();
		this.employee = employee;
	}
	    
	
	@Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
 
 
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
 
    @Override
    public int getRowCount()
    {
        return employee.size();
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Employee row = employee.get(rowIndex);
        if(0 == columnIndex) {
            return row.getNo();
        }
        else if(1 == columnIndex) {
            return row.getEmployeeNo();
        }
        else if(2 == columnIndex) {
            return row.getName();
        }
        else if(3 == columnIndex) {
            return row.getNric();
        }
        else if(4 == columnIndex) {
            //return row.getBasicSalary();
        	return String.format("%.2f", row.getBasicSalary());
        }
        else if(5 == columnIndex) {
            //return row.getGrossSalary();
        	return String.format("%.2f", row.getGrossSalary());
        }
        else if(6 == columnIndex) {
            //return row.getUnpaidLeave();
            return String.format("%.2f", row.getUnpaidLeave());
        }
        else if(7 == columnIndex) {
            //return row.getAllowance();
        	return String.format("%.2f", row.getAllowance());
        }
        else if(8 == columnIndex) {
        	//return row.getEmployerEpf();
        	 return String.format("%.2f", row.getEmployerEpf());
        }
        else if(9 == columnIndex) {
        	//return row.getEmployerSocso();
        	return String.format("%.2f", row.getEmployerSocso());
        }
        else if(10 == columnIndex) {
        	//return row.getEmployeeEpf();
        	return String.format("%.2f", row.getEmployeeEpf());
        }
        else if(11 == columnIndex) {
        	//return row.getEmployeeSocso();
        	return String.format("%.2f", row.getEmployeeSocso());
        }
        else if(12 == columnIndex) {
        	return row.getRow();
        }
        return null;
    }
    
    public void addEmployee(Employee employee) {
    	this.employee.add(employee);
    	fireTableRowsInserted(this.employee.size() -1, this.employee.size() -1);
    }
    
    public void removeEmployee(int rowIndex) {
    	employee.remove(rowIndex);
    	fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
}
