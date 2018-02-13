package com.jobpoint.epfcalculator.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.jobpoint.epfcalculator.database.CrudSip;
import com.jobpoint.epfcalculator.gui.SipAdd;
import com.jobpoint.epfcalculator.gui.SipEdit;
import com.jobpoint.epfcalculator.gui.SipMain;
import com.jobpoint.epfcalculator.model.Sip;

public class SipController {
	public List<Sip> showSip(){
		CrudSip crudSip = new CrudSip();
		List<Sip> sipList = new ArrayList<Sip>();
		try {
			sipList = crudSip.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return sipList;
	}
	
	public void showSip(boolean flag){
		CrudSip crudSip = new CrudSip();
		List<Sip> sipList = new ArrayList<Sip>();
		
		try {
			sipList = crudSip.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		new SipMain(sipList);
	}
	
	
	public Sip getSip(Double grossSalary) {
		CrudSip crudSip = new CrudSip();
		
		try {
			Sip sip = new Sip();
			sip = crudSip.findByBoundLimit(grossSalary);
			return sip;
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	
	public void newSip(){
		new SipAdd();
	}

	public boolean saveSip() {
		CrudSip crudSip = new CrudSip();
		
		Sip sip = new Sip();
		sip.setLowerBound(Double.parseDouble(SipAdd.lowerBoundText.getText()));
		sip.setUpperBound(Double.parseDouble(SipAdd.upperBoundText.getText()));
		sip.setEmployerShare(Double.parseDouble(SipAdd.employerShareText.getText()));
		sip.setEmployeeShare(Double.parseDouble(SipAdd.employeeShareText.getText()));
		
		int id = crudSip.saveNew(sip);
		if(id != 0) {
			try {
				SipMain.model.addSip(crudSip.findById(id));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		}else {
			return false;
		}
	}
	
	public void editSip(Object[] data, int index) {
		new SipEdit(data, index);
	}
	
	public boolean updateSip(int id, int index) {
		Sip sip = new Sip();
		sip.setId(id);
		sip.setLowerBound(Double.parseDouble(SipEdit.lowerBoundText.getText()));
		sip.setUpperBound(Double.parseDouble(SipEdit.upperBoundText.getText()));
		sip.setEmployerShare(Double.parseDouble(SipEdit.employerShareText.getText()));
		sip.setEmployeeShare(Double.parseDouble(SipEdit.employeeShareText.getText()));
		
		CrudSip crudSip = new CrudSip();
		if(crudSip.updateChange(sip)) {
			SipMain.model.updateSip(index, sip);
			JOptionPane.showMessageDialog(null, "Update data success!");
			return true;
		}else {
			JOptionPane.showMessageDialog(null, "Data cannot be updated!");
			return false;
		}
	}
	
	public boolean deleteSip(int id, int index) {
		CrudSip crudSip = new CrudSip();
		
		int response = JOptionPane.showConfirmDialog(null, 
	            "Do you want to delete selected row?", 
	            "Confirm", JOptionPane.YES_NO_OPTION, //
	            JOptionPane.QUESTION_MESSAGE);
	    if (response == JOptionPane.YES_OPTION) {
	    	if(crudSip.deleteRecord(id)) {
	    		SipMain.model.removeSip(index);
				return true;
			}
	    }  
	    return false;
	}
	
}
