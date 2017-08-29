package com.jobpoint.epfcalculator.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jobpoint.epfcalculator.gui.Dashboard;
import com.jobpoint.epfcalculator.gui.EmployeeInsert;
import com.jobpoint.epfcalculator.gui.EmployeeMain;
import com.jobpoint.epfcalculator.model.Employee;
import com.jobpoint.epfcalculator.model.Epf;
import com.jobpoint.epfcalculator.model.Socso;
import com.jobpoint.epfcalculator.tool.Age;
import com.jobpoint.epfcalculator.tool.CopyFile;
import com.jobpoint.epfcalculator.tool.OpenFile;
import com.jobpoint.epfcalculator.tool.ReadExcel;
import com.jobpoint.epfcalculator.tool.WriteExcel;

import jxl.write.WriteException;

public class EmployeeController {
	
	public void showEmployee() {
		
		ReadExcel readExcel = new ReadExcel();
		
		int rows = EmployeeMain.model.getRowCount();
   		if (rows > 0) {
   			System.out.println("employer List size = " + EmployeeMain.model.employee.size());
   			EmployeeMain.model.employee.clear();
   			EmployeeMain.model.fireTableRowsDeleted(0, rows - 1);
        }
        
   		EpfController epfController = new EpfController();
   		SocsoController socsoController = new SocsoController();
		
		String fileName = EmployeeInsert.sourceFileText.getText();
		EmployeeMain.fileName = fileName;
		List<List<String[]>> dataArrayList = readExcel.readMaster(fileName);
		List<String[]> dataListMaster = dataArrayList.get(0);
		List<String[]> dataListPbb = dataArrayList.get(1);
			
	/*		for(String[] arrList : dataListPbb) {
  				System.out.println("Employee No = " + arrList[0]);
  				System.out.println("IC No = " + arrList[1]);
  			}
		*/
			
			for( int i = 0 ; i < dataListMaster.size(); i++) {
				String[] data = dataListMaster.get(i);
				int no = Integer.valueOf((String)data[0]);
				String employeeNo = (String)data[1];
				
				int index = 0;
				
				boolean isSixty = false;
				String nric = "";
				
				for(String[] arrList : dataListPbb) {
	  				if( employeeNo == arrList[0]) {
	  					nric = arrList[1];
	  					
	  					System.out.println(nric);
	  				    int birthYear = Integer.valueOf(nric.substring(0, 2));
	  					if(birthYear > 10 ) {
	  						birthYear = 1900 + birthYear;
	  					}else {
	  						birthYear = 2000 + birthYear;
	  					}
	  					String birthMonth = nric.substring(2, 4);
	  					String birthDay = nric.substring(4, 6);
	  					
	  					String salaryMonthName = EmployeeInsert.monthCombo.getSelectedItem().toString();
	  					int salaryYear = Integer.valueOf(EmployeeInsert.yearCombo.getSelectedItem().toString());
	  					
	  					String birthDate = birthYear + "-" + birthMonth + "-" + birthDay;
	  					String salaryDate = String.format("01-%s-%d", salaryMonthName, salaryYear);
	  					Age age = new Age();
	  					if(age.getAge(birthDate,salaryDate) >= 60) {
	  						isSixty = true;
	  					}
	  					dataListPbb.remove(index);
	  					break;
	  				}
	  				index++;
	  			}
				
	       		String name = (String)data[2];
	       		Double basicSalary = Double.valueOf((String)data[3]);
	       		Double unpaidLeave =  Double.valueOf((String)data[4]);
	       		Double allowance = Double.valueOf((String)data[5]);
	       		Double grossSalary = Double.valueOf((String)data[6]);
	       		
	       		Double basicSalaryAllowance = basicSalary + allowance - unpaidLeave;
	       		
	       		Epf epf = new Epf();
	       		epf = epfController.getEpf(basicSalaryAllowance, isSixty);
	       		
	       		Socso socso = new Socso();
	       		socso = socsoController.getSocso(grossSalary, isSixty);
	       		
	       		Double employerEpf = epf.getEmployerShare();
	       		Double employerSocso = socso.getEmployerShare();
	       		Double employeeEpf = epf.getEmployeeShare();
	       		Double employeeSocso = socso.getEmployeeShare();
	       		int row = Integer.valueOf((String)data[7]); //
	       		EmployeeMain.model.addEmployee(new Employee(no, employeeNo, name, nric, basicSalary, grossSalary, unpaidLeave, allowance, employerEpf, employerSocso,
						employeeEpf, employeeSocso, row));
			}
		
		
	}
	
	public void insertEmployee(Dashboard dashboard) {
		new EmployeeInsert(dashboard);
	}
	
	public void exportEmployee(Dashboard dashboard) {
		if(EmployeeMain.model.getRowCount() > 0) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.setDialogTitle("Specify a file to export data");
		
			//fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Excel Workbook (*.xlsx)","xlsx"));
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Excel 97-2003 Workbook (*.xls)","xls"));
			
			int userSelection = fileChooser.showSaveDialog(dashboard);
			 
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				System.out.println(fileChooser.getFileFilter().getDescription());
				String extension = ".txt";
				if(fileChooser.getFileFilter().getDescription().equals("Microsoft Excel Workbook (*.xlsx)")) {
					extension = ".xlsx";
				}
				if(fileChooser.getFileFilter().getDescription().equals("Microsoft Excel 97-2003 Workbook (*.xls)")) {
					extension = ".xls";
				}
				
				String fileName = fileChooser.getSelectedFile().getAbsolutePath();
				if(!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls")) {
					fileName = fileName + extension;
				}
				
				System.out.println(fileName);
				File newFile = new File(fileName);
				
				
				System.out.println(newFile.getAbsolutePath());
				if (newFile.exists()) {
				    int response = JOptionPane.showConfirmDialog(null, 
				            "Do you want to replace the existing file?", 
				            "Confirm", JOptionPane.YES_NO_OPTION, //
				            JOptionPane.QUESTION_MESSAGE);
				    if (response != JOptionPane.YES_OPTION) {
				        return;
				    }   
				}
				
				System.out.println(EmployeeMain.fileName);
				
				File oldFile = new File(EmployeeMain.fileName);
				
				CopyFile copyFile = new CopyFile();
				if(copyFile.duplicateFile(oldFile, newFile)){
					WriteExcel writeExcel = new WriteExcel();
					try {
						writeExcel.write(newFile);
					} catch (WriteException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			    File fileToSave = fileChooser.getSelectedFile();
			    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
			    JOptionPane.showMessageDialog(null, "Export Data Success!");
			}
		}else {
			JOptionPane.showMessageDialog(null, "Data is empty. Please insert data!");
		}
	}
	
	public String getFilePath() {
		String filePath;
		
		OpenFile openFile = new OpenFile();
		File file = openFile.browse();
		filePath = file.getPath();
		
		return filePath;
	}


}
