package com.jobpoint.epfcalculator.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jobpoint.epfcalculator.gui.Dashboard;
import com.jobpoint.epfcalculator.gui.EmployeeInsert;
import com.jobpoint.epfcalculator.gui.EmployeeMain;
import com.jobpoint.epfcalculator.model.Employee;
import com.jobpoint.epfcalculator.model.Epf;
import com.jobpoint.epfcalculator.model.Sip;
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
   			EmployeeMain.model.employee.clear();
   			EmployeeMain.model.fireTableRowsDeleted(0, rows - 1);
        }
        
		String fileName = EmployeeInsert.sourceFileText.getText();
		EmployeeMain.fileName = fileName;
		List<List<String[]>> dataArrayList = new ArrayList<List<String[]>>();
		
		int percentage = EmployeeInsert.eightPercentRadio.isSelected() ? 8 : 11;
		if(EmployeeInsert.templateOneRadio.isSelected()) {
			dataArrayList = readExcel.readMasterTemplateOne(fileName);
			extractEmployeeBGS(dataArrayList, percentage);
		}
		
		if(EmployeeInsert.templateTwoRadio.isSelected()) {
			dataArrayList = readExcel.readMasterTemplateTwo(fileName);
			extractEmployeeBGS(dataArrayList, percentage);
		}
		
		if(EmployeeInsert.templateThreeRadio.isSelected()) {
			dataArrayList = readExcel.readMasterTemplateThree(fileName);
			extractEmployeeJP(dataArrayList, percentage);
		}
		
	}
	
	public void insertEmployee(Dashboard dashboard) {
		new EmployeeInsert(dashboard);
	}
	
	public void exportEmployee(Dashboard dashboard) {
		if(EmployeeMain.model.getRowCount() > 0) {
			Preferences preferences = Preferences.userRoot().node(getClass().getName());
			JFileChooser fileChooser = new JFileChooser(preferences.get(AppController.LAST_USED_FOLDER,
				    new File(".").getAbsolutePath()));
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.setDialogTitle("Specify a file to export data");
		
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Excel 97-2003 Workbook (*.xls)","xls"));
			//fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Excel Documents (*.xlsx)","xlsx"));
			
			int userSelection = fileChooser.showSaveDialog(dashboard.frame);
			 
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				preferences.put(AppController.LAST_USED_FOLDER, fileChooser.getSelectedFile().getParent());
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
				
				//System.out.println(newFile.getAbsolutePath());
				if (newFile.exists()) {
				    int response = JOptionPane.showConfirmDialog(null, 
				            "Do you want to replace the existing file?", 
				            "Confirm", JOptionPane.YES_NO_OPTION, //
				            JOptionPane.QUESTION_MESSAGE);
				    if (response != JOptionPane.YES_OPTION) {
				        return;
				    }   
				}
				
				//System.out.println(EmployeeMain.fileName);
				
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
	
	private void extractEmployeeBGS(List<List<String[]>> dataArrayList, int percentage) {
		
		EpfController epfController = new EpfController();
   		SocsoController socsoController = new SocsoController();
   		SipController sipController = new SipController();
   		
		List<String[]> dataListMaster = dataArrayList.get(0);
		List<String[]> dataListPbb = dataArrayList.get(1);
		
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
       		epf = epfController.getEpf(basicSalaryAllowance, isSixty, percentage);
       		
       		Socso socso = new Socso();
       		socso = socsoController.getSocso(grossSalary, isSixty);
       		
       		Sip sip = new Sip();
       		sip = sipController.getSip(grossSalary);
       		
       		Double employerEpf = epf.getEmployerShare();
       		Double employerSocso = socso.getEmployerShare();
       		Double employerSip = sip.getEmployerShare();
       		Double employeeEpf = epf.getEmployeeShare();
       		Double employeeSocso = socso.getEmployeeShare();
       		Double employeeSip = sip.getEmployeeShare();
       		int row = Integer.valueOf((String)dataMaster[7]); //
       		String sheet = dataMaster[8];
       		EmployeeMain.model.addEmployee(new Employee(no, employeeNo, name, nric, basicSalary, grossSalary, unpaidLeave, allowance, employerEpf, employerSocso,
					employerSip, employeeEpf, employeeSocso, employeeSip, row, sheet));
		}
	}
	
	private void extractEmployeeJP(List<List<String[]>> dataArrayList, int percentage) {
		
		EpfController epfController = new EpfController();
   		SocsoController socsoController = new SocsoController();
   		SipController sipController = new SipController();
   		
		List<String[]> dataList = dataArrayList.get(0);
		List<String[]> icList = dataArrayList.get(1);
		
		String[] unpaidLeaveArray = new String[2];
		String[] basicSalaryArray = new String[2];
		String[] grossSalaryArray = new String[2];
		String[] nameArray = new String[2];
		String[] icArray = new String[2];

		int count = 0;
		for( int i = 0 ; i < dataList.size(); i++) {
			String[] data = dataList.get(i);
			
			if(data[0].equals("Gross Salary")) {
				grossSalaryArray[count] = data[1].replaceAll(",", "");
				continue;
			}
			
			
			if(data[3].equals("Total Day(s)")) {
				unpaidLeaveArray[count] = data[5].replaceAll(",", "");
				continue;
			}
			
			if(data[2].equals("[d]")) {
				AppController.epfEmployeeRowIndex[count] = i;
				basicSalaryArray[count] = data[6].replaceAll(",", "");
				continue;
			}
			
			if(data[2].equals("[e]")) {
				AppController.socsoEmployeeRowIndex[count] = i;
				continue;
			}
			
			if(data[2].equals("[g]")) {
				AppController.sipEmployeeRowIndex[count] = i;
				continue;
			}
			
			if(data[2].equals("Employer EPF (13%)")) {
				AppController.epfEmployerRowIndex[count] = i;
				continue;
			}
			
			if(data[2].equals("Employer SOCSO")) {
				AppController.socsoEmployerRowIndex[count] = i;
				continue;
			}
			
			if(data[2].equals("Employer EIS")) {
				AppController.sipEmployerRowIndex[count] = i;
				continue;
			}
			
			
			if(data[0].equals("Prepared By:")) {
				count++;
				continue;
			}
			
		}
		
		count = 0;
		for(int i = 0 ; i < icList.size() ; i++ ) {
			String[] data = icList.get(i);
			nameArray[count] = data[0];
			icArray[count] = data[1];
			count++;
		}
		
		for(int i = 0 ; i < count ; i++) {
			boolean isSixty = false;
			
			String name = "";
			String nric = "880808-01-8888";
			
			String rawName = nameArray[i];
			String[] splitName = rawName.split(":");
			if(splitName.length == 2) {
				name = splitName[1];
			}
			
			String rawIc = icArray[i];
			String[] splitIc = rawIc.split(":");
			if(splitIc.length == 2) {
				nric = splitIc[1];
				nric = nric.replaceAll("\\s", "");
			}

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
			
			Epf epf = new Epf();
	   		epf = epfController.getEpf(Double.valueOf(basicSalaryArray[i]), isSixty, percentage);
	   		
	   		Socso socso = new Socso();
	   		socso = socsoController.getSocso(Double.valueOf(grossSalaryArray[i]), isSixty);
	   		
	   		Sip sip = new Sip();
	   		sip = sipController.getSip(Double.valueOf(grossSalaryArray[i]));
	   		
	   		Double employerEpf = epf.getEmployerShare();
	   		Double employerSocso = socso.getEmployerShare();
	   		Double employerSip = sip.getEmployerShare();
	   		Double employeeEpf = epf.getEmployeeShare();
	   		Double employeeSocso = socso.getEmployeeShare();
	   		Double employeeSip = sip.getEmployeeShare();

	   		EmployeeMain.model.addEmployee(new Employee(0, "0", name, nric, Double.valueOf(basicSalaryArray[i]), Double.valueOf(grossSalaryArray[i]), Double.valueOf(unpaidLeaveArray[i]), 0, employerEpf, employerSocso,
					employerSip, employeeEpf, employeeSocso, employeeSip, 0, "JP"));
		}
	}
}
