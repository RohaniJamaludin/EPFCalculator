package com.jobpoint.epfcalculator.tool;

public class ValidateDataInput {
	
	public ValidateDataInput() {
		
	}
	
	public boolean isNumeric(String string) {
		return string != null && string.matches("[-+]?\\d*\\.?\\d+");  
	}

}
