package com.jobpoint.epfcalculator.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

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
	
	public boolean duplicateFile2(File oldFile, File newFile, File newFileCopy) {
		 InputStream input = null;
	        OutputStream output = null;

	        try {
	            int buf_size=1024;
	            input = new FileInputStream(oldFile);
	            output = new FileOutputStream(newFileCopy);
	            System.out.println("Size of the file :"+input.available()+" Byte");
	            byte[] buf = new byte[buf_size];
	            int bytesRead;
	            while ((bytesRead = input.read(buf)) > 0 ) {
	            	output.write(buf, 0, bytesRead);
	            	if(input.available()<buf_size){
	            		System.out.println("Availble byte now is : "+input.available()+" so change the size of the array byte the to the same size");
	                //if the available byte are <1024  you will copy  a array of 1024 to the file that cause damage to file
	            		buf= new byte[input.available()];
	            	}
	            }                               
	            input.close();
	            output.close();
	            
	            newFileCopy.renameTo(newFile);
	            return true;
	        }

	        catch (IOException e) { 
	        JOptionPane.showMessageDialog(null, e.getMessage() ); 
	        System.exit(-1); 
	        return false;
	     }
	}
}
