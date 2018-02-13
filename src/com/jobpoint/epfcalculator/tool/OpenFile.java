package com.jobpoint.epfcalculator.tool;

import java.awt.BorderLayout;
import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jobpoint.epfcalculator.controller.AppController;


public class OpenFile {
	
	public File browse() {
		
		Preferences preferences = Preferences.userRoot().node(getClass().getName());
		JPanel contentPane = new JPanel(new BorderLayout());
		JFileChooser fileChooser = new JFileChooser(preferences.get(AppController.LAST_USED_FOLDER,
			    new File(".").getAbsolutePath()));
		
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Excel 97-2003 Workbook (*.xls)","xls"));
		//fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Excel Documents (*.xlsx)","xlsx"));
		int returnVal = fileChooser.showOpenDialog(contentPane);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			preferences.put(AppController.LAST_USED_FOLDER, fileChooser.getSelectedFile().getParent());
			return file;
		}
		return null;
		
	}

}
