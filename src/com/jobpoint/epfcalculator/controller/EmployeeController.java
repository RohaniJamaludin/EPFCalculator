package com.jobpoint.epfcalculator.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
		List<List<String[]>> dataArrayList;
		
		dataArrayList = EmployeeInsert.categoryOneRadio.isSelected() ?  readExcel.readMasterCategoryOne(fileName): 
			readExcel.readMasterCategoryTwo(fileName) ;
		List<String[]> dataListMaster = dataArrayList.get(0);
		List<String[]> dataListPbb = dataArrayList.get(1);

		for( int i = 0 ; i < dataListMaster.size(); i++) {
			String[] dataList = dataListMaster.get(i);
			if(dataList[dataList.length-1].equals("zonage")){
				for(int l = 0; l < dataList.length; l++ ) {
					System.out.println(dataList[l]);
				}
			}
			
		}
		
		for( int i = 0 ; i < dataListMaster.size(); i++) {
			String[] dataList = dataListMaster.get(i);
			if(dataList[dataList.length-1].equals("bgs")){
				for(int l = 0; l < dataList.length; l++ ) {
					System.out.println(dataList[l]);
				}
			}
			
		}
		
			for( int i = 0 ; i < dataListMaster.size(); i++) {
				String[] dataMaster = dataListMaster.get(i);
				
				int no = Integer.valueOf((String)dataMaster[0]);
				String employeeNo = (String)dataMaster[1];
				
				int index = 0;
				
				boolean isSixty = false;
				String nric = "";
				
				for(String[] dataPbb : dataListPbb) {
					if(employeeNo.equals(dataPbb[0])) {
						nric = dataPbb[1];
						
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
				String name = (String)dataMaster[2];
	       		Double basicSalary = Double.valueOf((String)dataMaster[3]);
	       		Double unpaidLeave =  Double.valueOf(!dataMaster[4].equals("")?(String)dataMaster[4]:"0");
	       		Double allowance = Double.valueOf(!dataMaster[5].equals("")?(String)dataMaster[5]:"0");
	       		Double grossSalary = Double.valueOf((String)dataMaster[6]);
	       		
	       		Double basicSalaryAllowance = basicSalary + allowance - unpaidLeave;
	       		
	       		Epf epf = new Epf();
	       		epf = epfController.getEpf(basicSalaryAllowance, isSixty);
	       		
	       		Socso socso = new Socso();
	       		socso = socsoController.getSocso(grossSalary, isSixty);
	       		
	       		Double employerEpf = epf.getEmployerShare();
	       		Double employerSocso = socso.getEmployerShare();
	       		Double employeeEpf = epf.getEmployeeShare();
	       		Double employeeSocso = socso.getEmployeeShare();
	       		int row = Integer.valueOf((String)dataMaster[7]); //
	       		String sheet = dataMaster[8];
	       		EmployeeMain.model.addEmployee(new Employee(no, employeeNo, name, nric, basicSalary, grossSalary, unpaidLeave, allowance, employerEpf, employerSocso,
						employeeEpf, employeeSocso, row, sheet));
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
				String fileNameCopy = fileName;
				if(!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls")) {
					fileNameCopy = fileName + "-copy" + extension;
					fileName = fileName + extension;
					
				}
				
				System.out.println(fileName);
				File newFile = new File(fileName);
				File newFileCopy = new File(fileNameCopy);
				
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
				if(copyFile.duplicateFile2(oldFile, newFile, newFileCopy)){
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
