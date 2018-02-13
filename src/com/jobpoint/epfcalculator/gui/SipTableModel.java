package com.jobpoint.epfcalculator.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.jobpoint.epfcalculator.model.Sip;

public class SipTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Sip> sipList;
	private final String[] columnNames = new String[] {
            "No", "From", "To", "Employer Share", "Employee Share"};
	
	public SipTableModel(List<Sip> sipList) {
		super();
		this.sipList = sipList;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

	@Override
	public int getRowCount() {
		return sipList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Sip row = sipList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getId();
        }
        else if(1 == columnIndex) {
            return String.format("%.2f", row.getLowerBound());
        }
        else if(2 == columnIndex) {
            return String.format("%.2f", row.getUpperBound());
        }
        else if(3 == columnIndex) {
            return String.format("%.2f", row.getEmployerShare());
        }
        else if(4 == columnIndex) {
        	return String.format("%.2f", row.getEmployeeShare());
        }
        return null;
	}
	
	public void addSip(Sip sip) {
		sipList.add(sip);
	    fireTableRowsInserted(sipList.size() -1, sipList.size() -1);
	}
	    
	public void removeSip(int rowIndex) {
		sipList.remove(rowIndex);
	    fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	public void updateSip(int rowIndex, Sip sip) {
		sipList.set(rowIndex, sip);
		this.fireTableRowsUpdated(rowIndex, rowIndex);
	}
}
