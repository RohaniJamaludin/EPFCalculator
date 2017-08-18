package com.jobpoint.epfcalculator.tool;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;


public class OpenFile {
	
	public File browse() {
		
		JPanel contentPane = new JPanel(new BorderLayout());
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fileChooser.showOpenDialog(contentPane);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			
			return file;
			//ReadExcel readExcel = new ReadExcel(file);
			/*
			try {
				
				readExcel.openFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			//log.append("Opening: " + file.getName() + "." + newLine);
		}else {
			//log.append("Open command cancelled by user." + newLine);
		}
		return null;
		
		//log.setCaretPosition(log.getDocument().getLength());
	}

}
