package com.jobpoint.epfcalculator.tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.jobpoint.epfcalculator.controller.AppController;

//import jxl.Cell;
//import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

    private String inputFile;
    File file;
    
    public ReadExcel() {
    	
    }
    
    public ReadExcel(File file) {
    	this.file = file;
    }

    private void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }
    
    public List<List<String[]>> readMasterTemplateOne(String filePath){
    	AppController.template = 1;
    	File inputWorkbook = new File(filePath);
    	Workbook w;
    	List<String[]> dataListMaster = new ArrayList<String[]>();
    	List<String[]> dataListPbb = new ArrayList<String[]>();
    	
    	try {
            w = Workbook.getWorkbook(inputWorkbook);
            
            Sheet masterSheet = w.getSheet("MASTER");
            Sheet pbbSheet = w.getSheet("PBB");
           
            ValidateDataInput validateDataInput = new ValidateDataInput();
            
            if(masterSheet != null) {
            	 for(int i = 0; i < masterSheet.getRows(); i++) {
               		 if(validateDataInput.isNumeric((String)masterSheet.getCell(0, i).getContents())){
               			String[] data = new String[9];
               			data[0] = masterSheet.getCell(0, i).getContents();
               			data[1] = masterSheet.getCell(1, i).getContents();
               			data[2] = masterSheet.getCell(2, i).getContents();
               			data[3] = masterSheet.getCell(3, i).getContents();
               			data[4] = masterSheet.getCell(5, i).getContents();
               			data[5] = masterSheet.getCell(16, i).getContents();
               			data[6] = masterSheet.getCell(22, i).getContents();
               			data[7] = String.valueOf(masterSheet.getCell(0, i).getRow());
               			data[8] = "bgs";
               			dataListMaster.add(data);
               		 }
               }
                
               for(int i = 0; i < pbbSheet.getRows(); i++) {
            	   if(validateDataInput.isNumeric((String)pbbSheet.getCell(0, i).getContents())) {
            		   String[] data = new String[3];
            		   data[0] = pbbSheet.getCell(1, i).getContents();
            		   data[1] = pbbSheet.getCell(3, i).getContents();
            		   data[2] = "bgs";
            		   dataListPbb.add(data);
            	   }
               }
            }else {
            	JOptionPane.showMessageDialog(null, "File format is not correct!");
            }
            
           
            
        } catch (BiffException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "File format is not correct!");
        }  
    	List<List<String[]>> dataArrayList = new ArrayList<List<String[]>>();
    	dataArrayList.add(dataListMaster);
    	dataArrayList.add(dataListPbb);
		return dataArrayList;
    }
    
    public List<List<String[]>> readMasterTemplateThree(String filePath){
    	AppController.template = 3;
    	File inputWorkbook = new File(filePath);
    	Workbook w;
    	List<String[]> dataList = new ArrayList<String[]>();
    	List<String[]> ic = new ArrayList<String[]>();
    	
    	try {
            w = Workbook.getWorkbook(inputWorkbook);
            
            Sheet sheet = w.getSheet("Sheet 1");
            
            if(sheet != null) {
            	 for(int i = 0; i < sheet.getRows(); i++) {
               			String[] data = new String[7];
               			data[0] = sheet.getCell(0, i).getContents();
               			data[1] = sheet.getCell(4, i).getContents();
               			data[2] = sheet.getCell(7, i).getContents();
               			data[3] = sheet.getCell(8, i).getContents();
               			data[4] = sheet.getCell(12, i).getContents();
               			data[5] = sheet.getCell(14, i).getContents();
               			data[6] = sheet.getCell(15, i).getContents();
               			dataList.add(data);
            	 }
            	 
            	 String[] dataIc1 = new String[2];
            	 dataIc1[0] = sheet.getCell(9, 2).getContents();
           		 dataIc1[1] = sheet.getCell(9, 3).getContents();
           		 ic.add(dataIc1);
           		 
           		 String[] dataIc2 = new String[2];
           		 dataIc2[0] = sheet.getCell(9, 35).getContents();
          		 dataIc2[1] = sheet.getCell(9, 36).getContents();
           		 ic.add(dataIc2);
           		 
            }else {
            	JOptionPane.showMessageDialog(null, "File format is not correct!");
            }
        } catch (BiffException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "File format is not correct!");
        }  
    	
    	List<List<String[]>> dataArrayList = new ArrayList<List<String[]>>();
    	dataArrayList.add(dataList);
    	dataArrayList.add(ic);
		return dataArrayList;
    }
    
    public List<List<String[]>> readMasterTemplateTwo(String filePath){
    	AppController.template = 2;
    	File inputWorkbook = new File(filePath);
    	Workbook w;
    	List<String[]> dataListMaster = new ArrayList<String[]>();
    	List<String[]> dataListPbb = new ArrayList<String[]>();
    	
    	try {
            w = Workbook.getWorkbook(inputWorkbook);
            
            Sheet masterSheetZonage = w.getSheet("Zonage");
            Sheet pbbSheetZonage = w.getSheet("ZONAGE PBB");
            Sheet masterSheetBgs = w.getSheet("BGS");
            Sheet pbbSheetBgs = w.getSheet("PBB");
           
            ValidateDataInput validateDataInput = new ValidateDataInput();
            
            if(masterSheetZonage != null && masterSheetBgs != null) {
            	 for(int i = 0; i < masterSheetZonage.getRows(); i++) {
               		 if(validateDataInput.isNumeric((String)masterSheetZonage.getCell(0, i).getContents())){
               			String[] data = new String[9];
               			data[0] = masterSheetZonage.getCell(0, i).getContents();
               			data[1] = masterSheetZonage.getCell(1, i).getContents();
               			data[2] = masterSheetZonage.getCell(2, i).getContents();
               			data[3] = masterSheetZonage.getCell(3, i).getContents();
               			data[4] = masterSheetZonage.getCell(5, i).getContents();
               			data[5] = masterSheetZonage.getCell(16, i).getContents();
               			data[6] = masterSheetZonage.getCell(20, i).getContents();
               			data[7] = String.valueOf(masterSheetZonage.getCell(0, i).getRow());
               			data[8] = "zonage";
               			dataListMaster.add(data);
               		 }
            	 }
            	 
            	 for(int i = 0; i < masterSheetBgs.getRows(); i++) {
               		 if(validateDataInput.isNumeric((String)masterSheetBgs.getCell(0, i).getContents())){
               			String[] data = new String[9];
               			data[0] = masterSheetBgs.getCell(0, i).getContents();
               			data[1] = masterSheetBgs.getCell(1, i).getContents();
               			data[2] = masterSheetBgs.getCell(2, i).getContents();
               			data[3] = masterSheetBgs.getCell(3, i).getContents();
               			data[4] = masterSheetBgs.getCell(5, i).getContents();
               			data[5] = masterSheetBgs.getCell(16, i).getContents();
               			data[6] = masterSheetBgs.getCell(20, i).getContents();
               			data[7] = String.valueOf(masterSheetBgs.getCell(0, i).getRow());
               			data[8] = "bgs";
               			dataListMaster.add(data);
               		 }
               }
            	 
            	 
               for(int i = 0; i < pbbSheetZonage.getRows(); i++) {
            	   if(validateDataInput.isNumeric((String)pbbSheetZonage.getCell(0, i).getContents())) {
            		   String[] data = new String[3];
            		   data[0] = pbbSheetZonage.getCell(1, i).getContents();
            		   data[1] = pbbSheetZonage.getCell(3, i).getContents();
            		   data[2] = "zonage";
            		   dataListPbb.add(data);
            	   }
               	}
               
               for(int i = 0; i < pbbSheetBgs.getRows(); i++) {
            	   if(validateDataInput.isNumeric((String)pbbSheetBgs.getCell(0, i).getContents())) {
            		   String[] data = new String[3];
            		   data[0] = pbbSheetBgs.getCell(1, i).getContents();
            		   data[1] = pbbSheetBgs.getCell(3, i).getContents();
            		   data[2] = "zonage";
            		   dataListPbb.add(data);
            	   }
               	}
            }else {
            	JOptionPane.showMessageDialog(null, "File format is not correct!");
            }
            
           
            
        } catch (BiffException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "File format is not correct!");
        }  
    	List<List<String[]>> dataArrayList = new ArrayList<List<String[]>>();
    	dataArrayList.add(dataListMaster);
    	dataArrayList.add(dataListPbb);
		return dataArrayList;
    	
    }

    private void read() throws IOException  {
    	List<String[]> dataList;
        File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
            //Sheet sheet = w.getSheet(0);
            Sheet masterSheet = w.getSheet("MASTER");
            Sheet pbbSheet = w.getSheet("PBB");
            
            System.out.println(masterSheet.getRows());
            System.out.println(masterSheet.getColumns());
            
            System.out.println(pbbSheet.getRows());
            System.out.println(pbbSheet.getColumns());
            
            dataList = new ArrayList<String[]>();
            
            System.out.println("Column count = " + masterSheet.getColumns());
            ValidateDataInput validateDataInput = new ValidateDataInput();
            
            for(int i = 0; i < masterSheet.getRows(); i++) {
            	
           		 if(validateDataInput.isNumeric((String)masterSheet.getCell(0, i).getContents())){
           			String[] data = new String[8];
           			data[0] = masterSheet.getCell(0, i).getContents();
           			data[1] = masterSheet.getCell(1, i).getContents();
           			data[2] = masterSheet.getCell(2, i).getContents();
           			data[3] = masterSheet.getCell(3, i).getContents();
           			data[4] = masterSheet.getCell(5, i).getContents();
           			data[5] = masterSheet.getCell(16, i).getContents();
           			data[6] = masterSheet.getCell(20, i).getContents();
           			data[7] = String.valueOf(masterSheet.getCell(0, i).getRow());
           			dataList.add(data);
           			for(String[] arrList : dataList) {
           				System.out.println("No = " + arrList[0]);
           				System.out.println("EmployeeNo = " + arrList[1]);
           			}
           		 }
           }
            
        } catch (BiffException e) {
            e.printStackTrace();
        }
        
    }

    public void openFile() throws IOException{
        setInputFile(file.getPath());
        read();
    }

}