package com.jobpoint.epfcalculator.tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jobpoint.epfcalculator.gui.EmployeeInsert;

//import jxl.Cell;
//import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

    private String inputFile;
    File file;
    //Object[][] employee;
    private List<String[]> dataList;
    
    public ReadExcel() {
    	
    }
    
    public ReadExcel(File file) {
    	this.file = file;
    }

    private void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }
    
    public List<String[]> readFile() throws IOException {
    	File inputWorkbook = new File(EmployeeInsert.sourceFileText.getText());
    	Workbook w;
    	try {
            w = Workbook.getWorkbook(inputWorkbook);
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
           			String[] data = new String[12];
           			data[0] = masterSheet.getCell(0, i).getContents();
           			data[1] = masterSheet.getCell(1, i).getContents();
           			data[2] = masterSheet.getCell(2, i).getContents();
           			data[3] = masterSheet.getCell(3, i).getContents();
           			data[4] = masterSheet.getCell(5, i).getContents();
           			data[5] = masterSheet.getCell(16, i).getContents();
           			data[6] = masterSheet.getCell(19, i).getContents();
           			data[7] = masterSheet.getCell(20, i).getContents();
           			data[8] = masterSheet.getCell(21, i).getContents();
           			data[9] = masterSheet.getCell(22, i).getContents();
           			data[10] = masterSheet.getCell(23, i).getContents();
           			data[11] = String.valueOf(masterSheet.getCell(0, i).getRow());
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
    	return dataList;
    }

    private void read() throws IOException  {
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
           			String[] data = new String[12];
           			data[0] = masterSheet.getCell(0, i).getContents();
           			data[1] = masterSheet.getCell(1, i).getContents();
           			data[2] = masterSheet.getCell(2, i).getContents();
           			data[3] = masterSheet.getCell(3, i).getContents();
           			data[4] = masterSheet.getCell(5, i).getContents();
           			data[5] = masterSheet.getCell(16, i).getContents();
           			data[6] = masterSheet.getCell(19, i).getContents();
           			data[7] = masterSheet.getCell(20, i).getContents();
           			data[8] = masterSheet.getCell(21, i).getContents();
           			data[9] = masterSheet.getCell(22, i).getContents();
           			data[10] = masterSheet.getCell(23, i).getContents();
           			data[11] = String.valueOf(masterSheet.getCell(0, i).getRow());
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

    public List<String[]> openFile() throws IOException{
       // ReadExcel test = new ReadExcel();
        setInputFile(file.getPath());
        read();
        for(String[] arrList : dataList) {
        	System.out.println("No = " + arrList[0]);
        	System.out.println("EmployeeNo = " + arrList[1]);
        }
		return dataList;
    }

}