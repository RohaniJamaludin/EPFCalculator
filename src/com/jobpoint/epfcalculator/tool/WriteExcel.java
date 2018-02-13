package com.jobpoint.epfcalculator.tool;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import com.jobpoint.epfcalculator.controller.AppController;
import com.jobpoint.epfcalculator.gui.EmployeeMain;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class WriteExcel {
	private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private String inputFile;
    
    public void setOutputFile(String inputFile) {
        this.inputFile = inputFile;
        }
    
    public void write() throws IOException, WriteException {
        File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Report", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);
        createContent(excelSheet, "bgs");

        workbook.write();
        workbook.close();
    }
    
    public void write(File file) throws IOException, WriteException {
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));
			try {
				Workbook workbook;
				System.out.println(file.getAbsolutePath());
				workbook = Workbook.getWorkbook(new File(EmployeeMain.fileName));
				WritableWorkbook writeWorkBook = Workbook.createWorkbook(file, workbook);
				WritableSheet masterSheetBgs;
				WritableSheet masterSheetZonage;
				WritableSheet sheetJp;
				if(AppController.template == 1) {
					masterSheetBgs = writeWorkBook.getSheet(0);
					createContent(masterSheetBgs,"bgs");
				}
				
				if(AppController.template == 2) {
					masterSheetZonage = writeWorkBook.getSheet(0);
					createContent(masterSheetZonage, "zonage");
					masterSheetBgs = writeWorkBook.getSheet(2);
					createContent(masterSheetBgs, "bgs");
				}
				
				if(AppController.template == 3) {
					sheetJp = writeWorkBook.getSheet(0);
					createContentPaySlip(sheetJp, "Sheet 1");
				}
				
		        writeWorkBook.write();
		        writeWorkBook.close();
			} catch (BiffException e) {
				e.printStackTrace();
			}
    }
    
    void createLabel(WritableSheet sheet) throws WriteException {
        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        // Define the cell format
        times = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        times.setWrap(true);

        // create create a bold font with underlines
        WritableFont times10ptBoldUnderline = new WritableFont(
                WritableFont.TIMES, 10, WritableFont.BOLD, false,
                UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(true);

        // Write a few headers
        addCaption(sheet, 0, 0, "Header 1");
        addCaption(sheet, 1, 0, "This is another header");
    }
    
    private void createContent(WritableSheet sheet, String sheetName) throws WriteException,RowsExceededException {
    	// Lets calculate the sum of it
    	int rowCount = EmployeeMain.model.getRowCount();
    	
    	int columnEmployerEpf = 0;
		int columnEmployerSocso = 0;
		int columnEmployerSip = 0;
		int columnEmployeeEpf = 0;
		int columnEmployeeSocso = 0;
		int columnEmployeeSip = 0;
		
    	if(AppController.template == 1) {
    		columnEmployerEpf = 23;
    		columnEmployerSocso = 24;
    		columnEmployerSip = 25;
    		columnEmployeeEpf = 26;
    		columnEmployeeSocso = 27;
    		columnEmployeeSip = 28;
    	}else if(AppController.template == 2){
    		columnEmployerEpf = 21;
    		columnEmployerSocso = 22;
    		columnEmployerSip = 23;
    		columnEmployeeEpf = 24;
    		columnEmployeeSocso = 25;
    		columnEmployeeSip = 26;
    	}
    	
    	
    	// now a bit of text
    	for (int row = 0 ; row < rowCount; row++) {
    		if(EmployeeMain.model.getValueAt(row, 16).equals(sheetName)) {
    			addNumber(sheet, (Integer)EmployeeMain.model.getValueAt(row, 15), columnEmployerEpf, (String)EmployeeMain.model.getValueAt(row, 9));
    	    	addNumber(sheet, (Integer)EmployeeMain.model.getValueAt(row, 15), columnEmployerSocso, (String)EmployeeMain.model.getValueAt(row, 10));
    	    	addNumber(sheet, (Integer)EmployeeMain.model.getValueAt(row, 15), columnEmployerSip, (String)EmployeeMain.model.getValueAt(row, 11));
    	    	addNumber(sheet, (Integer)EmployeeMain.model.getValueAt(row, 15), columnEmployeeEpf, (String)EmployeeMain.model.getValueAt(row, 12));
    	    	addNumber(sheet, (Integer)EmployeeMain.model.getValueAt(row, 15), columnEmployeeSocso, (String)EmployeeMain.model.getValueAt(row, 13));
    	    	addNumber(sheet, (Integer)EmployeeMain.model.getValueAt(row, 15), columnEmployeeSip, (String)EmployeeMain.model.getValueAt(row, 14));
    		}
    	}
    }
    
    private void createContentPaySlip(WritableSheet sheet, String sheetName) throws WriteException,RowsExceededException {
    	// Lets calculate the sum of it
 
    	int columnEmployerEpf = 12;
		int columnEmployerSocso = 12;
		int columnEmployerSip = 12;
		int columnEmployeeEpf = 14;
		int columnEmployeeSocso = 14;
		int columnEmployeeSip = 14;

		System.out.println((String)EmployeeMain.model.getValueAt(0, 9));
		System.out.println((String)EmployeeMain.model.getValueAt(0, 10));
		System.out.println((String)EmployeeMain.model.getValueAt(0, 11));
		System.out.println(AppController.epfEmployerRowIndex);
		
    	for(int i = 0; i < EmployeeMain.model.getRowCount(); i++) {
    		System.out.println(AppController.epfEmployerRowIndex[i]);
    		addNumber(sheet, AppController.epfEmployerRowIndex[i], columnEmployerEpf, (String)EmployeeMain.model.getValueAt(0, 9));
    	    addNumber(sheet, AppController.socsoEmployerRowIndex[i], columnEmployerSocso, (String)EmployeeMain.model.getValueAt(0, 10));
    	    addNumber(sheet, AppController.sipEmployerRowIndex[i], columnEmployerSip, (String)EmployeeMain.model.getValueAt(0, 11));
    	    addNumber(sheet, AppController.epfEmployeeRowIndex[i], columnEmployeeEpf, (String)EmployeeMain.model.getValueAt(0, 12));
    	    addNumber(sheet, AppController.socsoEmployeeRowIndex[i], columnEmployeeSocso, (String)EmployeeMain.model.getValueAt(0, 13));
    	    addNumber(sheet,AppController.sipEmployeeRowIndex[i], columnEmployeeSip, (String)EmployeeMain.model.getValueAt(0, 14));
    	}
    }
    
    private void addCaption(WritableSheet sheet, int column, int row, String s)
            throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    private void addNumber(WritableSheet sheet, int row, int column, String cellValue) throws WriteException, RowsExceededException {
        
        //number = new Number(column, row, cellValue, times);
        //WritableCell cell = sheet.getWritableCell(column, row);
    	WritableCell cell;
    	WritableCellFormat cellFormat = new WritableCellFormat();
    	cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
    	Number number = new Number(column, row, Double.parseDouble(cellValue), cellFormat);
    	cell = (WritableCell) number;
    	sheet.addCell(cell);
    }
    
    
 /*   private void addLabel(WritableSheet sheet, int column, int row, String s)
            throws WriteException, RowsExceededException {
        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);
    }*/


}
