package com.jobpoint.epfcalculator.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ViewData extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean DEBUG = false;
	
	public ViewData() {
		super();
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
	 
	 public void showData(String[] columnNames, Object[][] data) {
		 
		 //TablePane tablePane = new TablePane();
		 //ViewData tablePanel = new ViewData();
		 //tablePanel.setOpaque(true);
		 
		 //final JTable table = new JTable(data, columnNames);
		 //table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		 //table.setFillsViewportHeight(true);
		 //JScrollPane scrollPane = new JScrollPane(table);
		 //tablePanel.add(scrollPane);
			
		 /*
		 if(DEBUG){
		        table.addMouseListener(new MouseAdapter() {
		               public void mouseClicked(MouseEvent e) {
		                    printDebugData(table);
		                }
		            });
		        }
		        
		  */
		
		 //Dashboard dashboard = new Dashboard();
		 //dashboard.showPanel(tablePanel);
		 //JFrame tableFrame = new JFrame("Table Data");
		 //tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 //tableFrame.setContentPane(tablePanel);
		 //tableFrame.pack();
		 //tableFrame.setVisible(true);
	 }
	 
	 
	 public void showData() {
		 
		 JPanel tablePanel = new JPanel(new GridLayout());
		 tablePanel.setOpaque(true);
			
		 String[] columnNames = {"First Name",
                 "Last Name",
                 "Sport",
                 "# of Years",
                 "Vegetarian"};

		Object[][] data = {{"Kathy", "Smith","Snowboarding", new Integer(5), new Boolean(false)},
				 			{"John", "Doe","Rowing", new Integer(3), new Boolean(true)},
				 			{"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
				 			{"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
				 			{"Joe", "Brown","Pool", new Integer(10), new Boolean(false)}};
		
		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		tablePanel.add(scrollPane);
		
		if(DEBUG){
	         table.addMouseListener(new MouseAdapter() {
	                public void mouseClicked(MouseEvent e) {
	                    printDebugData(table);
	                }
	            });
	        }
		JFrame tableFrame = new JFrame("Table Data");
		tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tableFrame.setContentPane(tablePanel);
		tableFrame.pack();
		tableFrame.setVisible(true);
		 
	 }

	
	

}
