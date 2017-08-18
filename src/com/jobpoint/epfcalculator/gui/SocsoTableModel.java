package com.jobpoint.epfcalculator.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import com.jobpoint.epfcalculator.model.Socso;

public class SocsoTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Socso> socsoList;
	private final String[] columnNames = new String[] {
            "No", "From", "To", "Employer Share", "Employee Share"};
	
	public SocsoTableModel(List<Socso> socsoList) {
		super();
		this.socsoList = socsoList;
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
		return socsoList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Socso row = socsoList.get(rowIndex);
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
	
	public void addSocso(Socso socso) {
	    socsoList.add(socso);
	    fireTableRowsInserted(socsoList.size() -1, socsoList.size() -1);
	}
	    
	public void removeSocso(int rowIndex) {
	    socsoList.remove(rowIndex);
	    fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	public void updateSocso(int rowIndex, Socso socso) {
		socsoList.set(rowIndex, socso);
		this.fireTableRowsUpdated(rowIndex, rowIndex);
	}

}
