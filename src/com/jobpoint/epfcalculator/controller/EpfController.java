package com.jobpoint.epfcalculator.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		}
		
		new EpfMain(epfList, isSixty);
	}
	
	public Epf getEpf(Double basicSalaryAllowance) {
		CrudEpf crudEpf = new CrudEpf();
		
		try {
			Epf epf = new Epf();
			epf = crudEpf.findByBoundLimit(basicSalaryAllowance);
			return epf;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void newEpf(boolean isSixty) {
		new EpfAdd(isSixty);
	}

	public void saveEpf(boolean isSixty) {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editEpf(Object[] data, int index, boolean isSixty) {
		new EpfEdit(data, index, isSixty);
	}
	
	public void updateEpf(int id, int index) {
		Epf epf = new Epf();
		epf.setId(id);
		epf.setLowerBound(Double.parseDouble(EpfEdit.lowerBoundText.getText()));
		epf.setUpperBound(Double.parseDouble(EpfEdit.upperBoundText.getText()));
		epf.setEmployerShare(Double.parseDouble(EpfEdit.employerShareText.getText()));
		epf.setEmployeeShare(Double.parseDouble(EpfEdit.employeeShareText.getText()));
		
		CrudEpf crudEpf = new CrudEpf();
		if(crudEpf.updateChange(epf)) {
			EpfMain.model.updateEpf(index, epf);
		}
	}
	
	public boolean deleteEpf(int id, int index) {
		CrudEpf crudEpf = new CrudEpf();
		if(crudEpf.deleteRecord(id)) {
			EpfMain.model.removeEpf(index);
			return true;
		}else {
			return false;
		}
	}
	
}
