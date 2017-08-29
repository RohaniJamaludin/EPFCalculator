package com.jobpoint.epfcalculator.tool;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Age {
	
	public int getAge(String birthDate, String salaryDate) {
		//LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
		//LocalDate salaryDate = LocalDate.of(salaryYear, salaryMonth, 1);
		DateTimeFormatter birthDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter salaryDateFormatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
		
		LocalDate localBirthDate = LocalDate.parse(birthDate, birthDateFormatter);
		LocalDate localSalaryDate = LocalDate.parse(salaryDate, salaryDateFormatter);
		
/*		LocalDate localBirthDate = LocalDate.parse("19840821", birthDateFormatter);
		LocalDate localSalaryDate = LocalDate.parse("21-August-2017", salaryDateFormatter);
		*/
		
		Period p = Period.between(localBirthDate, localSalaryDate);
		
		System.out.println(p.getDays());
		System.out.println(p.getMonths());
		System.out.println("Age : " + p.getYears());
		
		return p.getYears();
	}

}
