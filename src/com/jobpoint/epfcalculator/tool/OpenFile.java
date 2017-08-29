package com.jobpoint.epfcalculator.tool;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class OpenFile {
	
	public File browse() {
		
		JPanel contentPane = new JPanel(new BorderLayout());
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		//fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Excel Workbook (*.xlsx)","xlsx"));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Excel 97-2003 Workbook (*.xls)","xls"));
		int returnVal = fileChooser.showOpenDialog(contentPane);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			return file;
		}
		return null;
		
		//log.setCaretPosition(log.getDocument().getLength());
	}

}
