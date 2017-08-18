package com.jobpoint.epfcalculator.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.jobpoint.epfcalculator.gui.Dashboard;
import com.jobpoint.epfcalculator.gui.EmployeeInsert;
import com.jobpoint.epfcalculator.gui.EmployeeMain;
import com.jobpoint.epfcalculator.model.Employee;
import com.jobpoint.epfcalculator.model.Epf;
import com.jobpoint.epfcalculator.model.Socso;
import com.jobpoint.epfcalculator.tool.OpenFile;
import com.jobpoint.epfcalculator.tool.ReadExcel;

public class EmployeeController {
	
	public void showEmployee() {
		
		ReadExcel readExcel = new ReadExcel();
		
		int rows = EmployeeMain.model.getRowCount();
   		if (rows > 0) {
   			System.out.println("employer List size = " + EmployeeMain.model.employee.size());
   			EmployeeMain.model.employee.clear();
   			EmployeeMain.model.fireTableRowsDeleted(0, rows - 1);
        }
        
   		//EpfController epfController = new EpfController();
   		//SocsoController socsoController = new SocsoController();
		
		try {
			List<String[]> dataList = readExcel.readFile();
			for( int i = 0 ; i < dataList.size(); i++) {
				String[] data = dataList.get(i);
				int no = Integer.valueOf((String)data[0]);
				String employeeNo = (String)data[1];
	       		String name = (String)data[2];
	       		Double basicSalary = Double.valueOf((String)data[3]);
	       		Double unpaidLeave =  Double.valueOf((String)data[4]);
	       		Double allowance = Double.valueOf((String)data[5]);
	       		Double grossSalary = Double.valueOf((String)data[6]);
	       		
	       		Double basicSalaryAllowance = basicSalary + allowance - unpaidLeave;
	       		
	       		Epf epf = new Epf();
	       		//epf = epfController.getEpf(basicSalaryAllowance);
	       		
	       		Socso socso = new Socso();
	       		//socso = socsoController.getSocso(grossSalary);
	       		
	       		Double employerEpf = epf.getEmployeeShare();
	       		Double employerSocso = socso.getEmployeeShare();
	       		Double employeeEpf = epf.getEmployeeShare();
	       		Double employeeSocso = socso.getEmployeeShare();
	       		int row = Integer.valueOf((String)data[11]);
	       		EmployeeMain.model.addEmployee(new Employee(no, employeeNo, name, basicSalary, grossSalary, unpaidLeave, allowance, employerEpf, employerSocso,
						employeeEpf, employeeSocso, row));
			}
		} catch (IOException event) {
			// TODO Auto-generated catch block
			event.printStackTrace();
		}
		
	}
	
	public void insertEmployee(Dashboard dashboard) {
		new EmployeeInsert(dashboard);
	}
	
	public String getFilePath() {
		String filePath;
		
		OpenFile openFile = new OpenFile();
		File file = openFile.browse();
		filePath = file.getPath();
		
		return filePath;
	}


}
