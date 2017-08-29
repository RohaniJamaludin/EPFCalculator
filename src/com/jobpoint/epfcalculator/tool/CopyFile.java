package com.jobpoint.epfcalculator.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFile {
	
	public boolean duplicateFile(File oldFile, File newFile) {
		try {
			InputStream inputStream = new FileInputStream(oldFile);
			OutputStream outputStream = new FileOutputStream(newFile);
		
			byte[] buffer = new byte[1024];
		
			int length;
		
			while((length = inputStream.read(buffer)) > 0 ) {
				outputStream.write(buffer, 0, length);
			}
			
			inputStream.close();
			outputStream.close();
			System.out.println("Copy file success");
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}	
}
