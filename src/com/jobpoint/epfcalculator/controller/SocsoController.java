package com.jobpoint.epfcalculator.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jobpoint.epfcalculator.database.CrudSocso;
import com.jobpoint.epfcalculator.gui.SocsoAdd;
import com.jobpoint.epfcalculator.gui.SocsoEdit;
import com.jobpoint.epfcalculator.gui.SocsoMain;
import com.jobpoint.epfcalculator.model.Socso;

public class SocsoController {
	
	public List<Socso> showSocso(){
		CrudSocso crudSocso = new CrudSocso();
		List<Socso> socsoList = new ArrayList<Socso>();
		try {
			socsoList = crudSocso.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return socsoList;
	}
	
	public void showSocso(boolean isSixty){
		CrudSocso crudSocso = new CrudSocso();
		List<Socso> socsoList = new ArrayList<Socso>();
		
		try {
			socsoList = crudSocso.findAll(isSixty);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		new SocsoMain(socsoList, isSixty);
	}
	
	
	public Socso getSocso(Double grossSalary) {
		CrudSocso crudSocso = new CrudSocso();
		
		try {
			Socso socso = new Socso();
			socso = crudSocso.findByBoundLimit(grossSalary);
			return socso;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void newSocso(boolean isSixty){
		new SocsoAdd(isSixty);
	}

	public boolean saveSocso(boolean isSixty) {
		CrudSocso crudSocso = new CrudSocso();
		
		Socso socso = new Socso();
		socso.setLowerBound(Double.parseDouble(SocsoAdd.lowerBoundText.getText()));
		socso.setUpperBound(Double.parseDouble(SocsoAdd.upperBoundText.getText()));
		socso.setEmployerShare(Double.parseDouble(SocsoAdd.employerShareText.getText()));
		socso.setEmployeeShare(Double.parseDouble(SocsoAdd.employeeShareText.getText()));
		socso.setIsSixty(isSixty);
		
		int id = crudSocso.saveNew(socso);
		if(id != 0) {
			try {
				SocsoMain.model.addSocso(crudSocso.findById(id));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}else {
			return false;
		}
	}
	
	public void editSocso(Object[] data, int index, boolean isSixty) {
		new SocsoEdit(data, index, isSixty);
	}
	
	public boolean updateSocso(int id, int index) {
		Socso socso = new Socso();
		socso.setId(id);
		socso.setLowerBound(Double.parseDouble(SocsoEdit.lowerBoundText.getText()));
		socso.setUpperBound(Double.parseDouble(SocsoEdit.upperBoundText.getText()));
		socso.setEmployerShare(Double.parseDouble(SocsoEdit.employerShareText.getText()));
		socso.setEmployeeShare(Double.parseDouble(SocsoEdit.employeeShareText.getText()));
		
		CrudSocso crudSocso = new CrudSocso();
		if(crudSocso.updateChange(socso)) {
			SocsoMain.model.updateSocso(index, socso);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean deleteSocso(int id, int index) {
		CrudSocso crudSocso = new CrudSocso();
		if(crudSocso.deleteRecord(id)) {
			SocsoMain.model.removeSocso(index);
			return true;
		}else {
			return false;
		}
		
		
	}
	
	

}
