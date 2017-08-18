package com.jobpoint.epfcalculator.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jobpoint.epfcalculator.model.Employee;
import com.jobpoint.epfcalculator.tool.LineNumberTableRowHeader;

public class EmployeeMain{
	
	public static EmployeeTableModel model;
	private JTable table;
	private boolean DEBUG = false;
	public JPanel panel;
	public static Dashboard dashboard;

	public EmployeeMain() {
		initializePanel();
	}
	
	public EmployeeMain(Dashboard dashboard) {
		EmployeeMain.dashboard = dashboard;
		initializePanel();
	}
	
	private void initializePanel() {
		//Employee row1 = new Employee(0, null, 0, 0, 0, 0, 0, 0);
		
		List<Employee> employeeList = new ArrayList<Employee>();
		//employeeList.add(row1);
		
		model = new EmployeeTableModel(employeeList);
		
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(800, 800));
		table.setFillsViewportHeight(true);
		
		//JTable rowTable = new RowNumberTable(table);
		
		if(DEBUG){
	        table.addMouseListener(new MouseAdapter() {
	               public void mouseClicked(MouseEvent e) {
	                    printDebugData(table);
	                }
	            });
	        }
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		LineNumberTableRowHeader tableLineNumber = new LineNumberTableRowHeader(scrollPane, table);
		tableLineNumber.setBackground(Color.LIGHT_GRAY);
		scrollPane.setRowHeaderView(tableLineNumber);
		
		panel = new JPanel();
		panel.add(scrollPane);
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
