package com.jobpoint.epfcalculator.tool;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

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
        createContent(excelSheet);

        workbook.write();
        workbook.close();
    }
    
    public void write(File file) throws IOException, WriteException {
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));

        //WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        //workbook.createSheet("Report", 0);
        
			try {
				Workbook workbook;
				System.out.println(file.getAbsolutePath());
				workbook = Workbook.getWorkbook(new File(EmployeeMain.fileName));
				WritableWorkbook writeWorkBook = Workbook.createWorkbook(file, workbook);
				//WritableSheet excelSheet = workbook.getSheet(0);
				WritableSheet masterSheet = writeWorkBook.getSheet(0);
				System.out.println(masterSheet.getCell(7,7).getContents());
		        createContent(masterSheet);
		        writeWorkBook.write();
		        writeWorkBook.close();
			} catch (BiffException e) {
				// TODO Auto-generated catch block
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

        // create create a bold font with unterlines
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
    
    private void createContent(WritableSheet sheet) throws WriteException,RowsExceededException {
    	// Lets calculate the sum of it
    	int rowCount = EmployeeMain.model.getRowCount();
    	
   /* 	StringBuffer buf = new StringBuffer();
    	buf.append("SUM(A2:A10)");
    	Formula f = new Formula(0, 10, buf.toString());
    	sheet.addCell(f);
    	buf = new StringBuffer();
    	buf.append("SUM(B2:B10)");
    	f = new Formula(1, 10, buf.toString());
    	sheet.addCell(f);*/

    	// now a bit of text
    	for (int row = 0 ; row < rowCount; row++) {
    		int columnEmployerEpf = 21;
    		int columnEmployerSocso = 22;
    		int columnEmployeeEpf = 23;
    		int columnEmployeeSocso = 24;
    		System.out.println(EmployeeMain.model.getValueAt(row, 8));
    		System.out.println(EmployeeMain.model.getValueAt(row, 9));
    		System.out.println(EmployeeMain.model.getValueAt(row, 10));
    		System.out.println(EmployeeMain.model.getValueAt(row, 11));
    		System.out.println(EmployeeMain.model.getValueAt(row, 12));
    		addNumber(sheet, (Integer)EmployeeMain.model.getValueAt(row, 12), columnEmployerEpf, (String)EmployeeMain.model.getValueAt(row, 8));
    		addNumber(sheet, (Integer)EmployeeMain.model.getValueAt(row, 12), columnEmployerSocso, (String)EmployeeMain.model.getValueAt(row, 9));
    		addNumber(sheet, (Integer)EmployeeMain.model.getValueAt(row, 12), columnEmployeeEpf, (String)EmployeeMain.model.getValueAt(row, 10));
    		addNumber(sheet, (Integer)EmployeeMain.model.getValueAt(row, 12), columnEmployeeSocso, (String)EmployeeMain.model.getValueAt(row, 11));
    		// First column
    		//addLabel(sheet, 0, i, "Boring text " + i);
    		// Second column
    		//addLabel(sheet, 1, i, "Another text");
    	}


    }
    
/*    private void createContent(WritableSheet sheet) throws WriteException,RowsExceededException {
    	// Write a few number
    	for (int i = 1; i < 10; i++) {
    		// First column
    		addNumber(sheet, 0, i, i + 10);
    		// Second column
    		addNumber(sheet, 1, i, i * i);
    	}
    	
    	// Lets calculate the sum of it
    	StringBuffer buf = new StringBuffer();
    	buf.append("SUM(A2:A10)");
    	Formula f = new Formula(0, 10, buf.toString());
    	sheet.addCell(f);
    	buf = new StringBuffer();
    	buf.append("SUM(B2:B10)");
    	f = new Formula(1, 10, buf.toString());
    	sheet.addCell(f);

    	// now a bit of text
    	for (int i = 12; i < 20; i++) {
    		// First column
    		addLabel(sheet, 0, i, "Boring text " + i);
    		// Second column
    		addLabel(sheet, 1, i, "Another text");
    	}


    }*/
    
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
