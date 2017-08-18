package com.jobpoint.epfcalculator.gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.jobpoint.epfcalculator.model.Epf;

public class EpfTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Epf> epfList;
	private final String[] columnNames = new String[] {
            "No", "From", "To", "Employer Share", "Employee Share"};
	
	public EpfTableModel(List<Epf> epfList) {
		super();
		this.epfList = epfList;
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
		return epfList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Epf row = epfList.get(rowIndex);
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
	
	public void addEpf(Epf epf) {
	    epfList.add(epf);
	    fireTableRowsInserted(epfList.size() -1, epfList.size() -1);
	}
	    
	public void removeEpf(int rowIndex) {
	    epfList.remove(rowIndex);
	    fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	public void updateEpf(int rowIndex, Epf epf) {
		epfList.set(rowIndex, epf);
		this.fireTableRowsUpdated(rowIndex, rowIndex);
	}
}
