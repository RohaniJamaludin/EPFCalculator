package com.jobpoint.epfcalculator.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import com.jobpoint.epfcalculator.model.Employee;
import com.jobpoint.epfcalculator.tool.LineNumberTableRowHeader;

public class EmployeeMain{
	
	public static EmployeeTableModel model;
	private JTable table;
	private boolean DEBUG = false;
	public static String fileName;

	public JScrollPane initializePanel() {
		
		List<Employee> employeeList = new ArrayList<Employee>();
		
		model = new EmployeeTableModel(employeeList);
		
		table = new JTable(model);
		
		
		TableColumnModel tableColumnModelNo = table.getColumnModel();
		tableColumnModelNo.removeColumn(tableColumnModelNo.getColumn(0));
		
		TableColumnModel tableColumnModelRow = table.getColumnModel();
		tableColumnModelRow.removeColumn(tableColumnModelRow.getColumn(14));
		
		TableColumnModel tableColumnModelWorksheet = table.getColumnModel();
		tableColumnModelWorksheet.removeColumn(tableColumnModelWorksheet.getColumn(14));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(20);//numbering
		table.getColumnModel().getColumn(1).setPreferredWidth(100);//employee no
		table.getColumnModel().getColumn(2).setPreferredWidth(200);//Name
		table.getColumnModel().getColumn(3).setPreferredWidth(120);//Nric
		table.getColumnModel().getColumn(4).setPreferredWidth(110);//Basic Salary
		table.getColumnModel().getColumn(5).setPreferredWidth(110);//Gross Salary
		table.getColumnModel().getColumn(6).setPreferredWidth(100);//Unpaid Leave
		table.getColumnModel().getColumn(7).setPreferredWidth(80);//Allowance
		table.getColumnModel().getColumn(8).setPreferredWidth(120);//Employer Epf
		table.getColumnModel().getColumn(9).setPreferredWidth(120);//Employer Socso
		table.getColumnModel().getColumn(10).setPreferredWidth(120);//Employer Sip
		table.getColumnModel().getColumn(11).setPreferredWidth(120);//Employee Epf
		table.getColumnModel().getColumn(12).setPreferredWidth(120);//Employee Socso
		table.getColumnModel().getColumn(13).setPreferredWidth(120);//Employee Sip
/*		table.getColumnModel().getColumn(14).setPreferredWidth(50);//Worksheet
		table.getColumnModel().getColumn(15).setPreferredWidth(50);//row
*/		
		if(DEBUG){
	        table.addMouseListener(new MouseAdapter() {
	               public void mouseClicked(MouseEvent e) {
	                    printDebugData(table);
	                }
	            });
	        }
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		JPanel panel = new JPanel();
		panel.add(scrollPane);
		
		table.setPreferredSize(new Dimension(500, 500));
		table.setFillsViewportHeight(true);
		
		return scrollPane;
	}
	
	private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
