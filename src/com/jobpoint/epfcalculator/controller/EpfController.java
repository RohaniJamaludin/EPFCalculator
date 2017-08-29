package com.jobpoint.epfcalculator.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.jobpoint.epfcalculator.database.CrudEpf;
import com.jobpoint.epfcalculator.gui.EpfAdd;
import com.jobpoint.epfcalculator.gui.EpfEdit;
import com.jobpoint.epfcalculator.gui.EpfMain;
import com.jobpoint.epfcalculator.model.Epf;

public class EpfController {

	public EpfController() {
		
	}
	
	public List<Epf> showEpf(){
		CrudEpf crudEpf = new CrudEpf();
		List<Epf> epfList = new ArrayList<Epf>();
		try {
			epfList = crudEpf.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return epfList;
	}
	
	public void showEpf(boolean isSixty){
		CrudEpf crudEpf = new CrudEpf();
		List<Epf> epfList = new ArrayList<Epf>();
		try {
			if(isSixty)
				epfList = crudEpf.findAll(true);
			else
				epfList = crudEpf.findAll(false);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		new EpfMain(epfList, isSixty);
	}
	
	public Epf getEpf(Double basicSalaryAllowance, boolean isSixty) {
		CrudEpf crudEpf = new CrudEpf();
		
		try {
			Epf epf = new Epf();
			epf = crudEpf.findByBoundLimit(basicSalaryAllowance, isSixty);
			return epf;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	
	public void newEpf(boolean isSixty) {
		new EpfAdd(isSixty);
	}

	public boolean saveEpf(boolean isSixty) {
		CrudEpf crudEpf = new CrudEpf();
		
		Epf epf = new Epf();
		epf.setLowerBound(Double.parseDouble(EpfAdd.lowerBoundText.getText()));
		epf.setUpperBound(Double.parseDouble(EpfAdd.upperBoundText.getText()));
		epf.setEmployerShare(Double.parseDouble(EpfAdd.employerShareText.getText()));
		epf.setEmployeeShare(Double.parseDouble(EpfAdd.employeeShareText.getText()));
		epf.setIsSixty(isSixty);
		
		int id = crudEpf.saveNew(epf);
		try {
			EpfMain.model.addEpf(crudEpf.findById(id));
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public void editEpf(Object[] data, int index, boolean isSixty) {
		new EpfEdit(data, index, isSixty);
	}
	
	public boolean updateEpf(int id, int index) {
		Epf epf = new Epf();
		epf.setId(id);
		epf.setLowerBound(Double.parseDouble(EpfEdit.lowerBoundText.getText()));
		epf.setUpperBound(Double.parseDouble(EpfEdit.upperBoundText.getText()));
		epf.setEmployerShare(Double.parseDouble(EpfEdit.employerShareText.getText()));
		epf.setEmployeeShare(Double.parseDouble(EpfEdit.employeeShareText.getText()));
		
		CrudEpf crudEpf = new CrudEpf();
		if(crudEpf.updateChange(epf)) {
			EpfMain.model.updateEpf(index, epf);
			return true;
		}
		return false;
	}
	
	public boolean deleteEpf(int id, int index) {
		CrudEpf crudEpf = new CrudEpf();
		
		int response = JOptionPane.showConfirmDialog(null, 
	            "Do you want to delete selected row?", 
	            "Confirm", JOptionPane.YES_NO_OPTION, //
	            JOptionPane.QUESTION_MESSAGE);
	    if (response == JOptionPane.YES_OPTION) {
	    	if(crudEpf.deleteRecord(id)) {
				
				EpfMain.model.removeEpf(index);
				return true;
			}
	    }  
	    
	    return false;
		
	}
	
}
