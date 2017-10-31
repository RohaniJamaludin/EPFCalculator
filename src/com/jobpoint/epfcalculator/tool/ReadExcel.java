package com.jobpoint.epfcalculator.tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //Object[][] employee;
    
    public ReadExcel() {
    	
    }
    
    public ReadExcel(File file) {
    	this.file = file;
    }

    private void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }
    
    public List<List<String[]>> readMasterCategoryOne(String filePath){
    	AppController.category = 1;
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
    
    public List<List<String[]>> readMasterCategoryTwo(String filePath){
    	AppController.category = 2;
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
               			data[6] = masterSheetZonage.getCell(22, i).getContents();
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
               			data[6] = masterSheetBgs.getCell(22, i).getContents();
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
            
            // Loop over first 10 column and lines

            /*
            for (int j = 0; j < sheet.getColumns(); j++) {
                for (int i = 0; i < sheet.getRows(); i++) {
                    Cell cell = sheet.getCell(j, i);
                    CellType type = cell.getType();
                    if (type == CellType.LABEL) {
                        System.out.println("I got a label "
                                + cell.getContents());
                    }

                    if (type == CellType.NUMBER) {
                        System.out.println("I got a number "
                                + cell.getContents());
                    }

                }
            }
           
            int noOfRows = sheet.getRows();
            int noOfColumns = sheet.getColumns();
            
            String[] columnNames = new String[noOfColumns];
            String cellData;
            Object[][] data = new Object[noOfRows-1][noOfColumns];
            
            for(int i = 0; i < sheet.getRows(); i++) {
            	 for(int j = 0 ; j < sheet.getColumns(); j++) {
            		 Cell cell = sheet.getCell(j, i);
            		 cellData =  cell.getContents();
            		 if(i == 0 ) { 
                    	 columnNames[j] = cellData; 
                    	 System.out.println("Title = " + cellData);
            		 }else {
            			 data[i-1][j] = cellData;
                		 System.out.println("Data = " + data[i-1][j]);
            		 }
            	 }
            }
            
             */
            
            //employee = data;
            
            //int noOfRows = sheet.getRows();
            //int noOfColumns = sheet.getColumns();
            
            //String[] columnNames = new String[noOfColumns];
            //String cellData;
            //Object[][] data = new Object[noOfRows][11];
            
            dataList = new ArrayList<String[]>();
            
            System.out.println("Column count = " + masterSheet.getColumns());
            ValidateDataInput validateDataInput = new ValidateDataInput();
            
            for(int i = 0; i < masterSheet.getRows(); i++) {
           	 //for(int j = 0 ; j < sheet.getColumns(); j++) {
           		 //Cell cell = sheet.getCell(0, i);
           		 //cellData =  cell.getContents();
           		 ///data[i][j] = cellData;
            	
        
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

           	 //}
           }
            
           
          
            //System.out.println("I got a label " + columnNames[2]);
            
            //ViewData viewData = new ViewData();
			//viewData.showData(columnNames,data);
            

            //ViewData viewData = new ViewData();
            //viewData.showData(columnNames, data);
			//System.out.println("I got a label " + data[2]);
            
          /*  for (int j = 0; j < sheet.getRows(); j++) {
                for (int i = 0; i < sheet.getColumns(); i++) {
                    Cell cell = sheet.getCell(i, j);
                    
                   // if(cell.getContents() == "") {
                    	
                   // }
                    
                    System.out.println("Row = " + sheet.getRows());
                    System.out.println("Column = " + sheet.getColumns());
                    
                    CellType type = cell.getType();
                    if (type == CellType.LABEL) {
                        System.out.println("I got a label "
                                + cell.getContents());
                    }

                    if (type == CellType.NUMBER) {
                        System.out.println("I got a number "
                                + cell.getContents());
                    }

                }
            }
            */
        } catch (BiffException e) {
            e.printStackTrace();
        }
        
    }

    public void openFile() throws IOException{
        setInputFile(file.getPath());
        read();
    }

}