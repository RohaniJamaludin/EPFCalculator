/*package com.jobpoint.epfcalculator.gui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.jobpoint.epfcalculator.model.Employee;
import com.jobpoint.epfcalculator.tool.OpenFile;
import com.jobpoint.epfcalculator.tool.ReadExcel;

public class TablePane extends JPanel{
	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;

	private boolean DEBUG = false;

	EmployeeTableModel model;
	JTable table;
	
	public TablePane() {
		super();
		initializeTable();
	}
	
	private void initializeTable() {
		
		Employee row1 = new Employee(0, null, 0, 0, 0, 0, 0, 0);
		
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(row1);
		
		model = new EmployeeTableModel(employeeList);
		
		table = new JTable(model);
		
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		
		if(DEBUG){
	        table.addMouseListener(new MouseAdapter() {
	               public void mouseClicked(MouseEvent e) {
	                    printDebugData(table);
	                }
	            });
	        }
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		this.add(scrollPane);
		 
		JFrame frame = new JFrame();
		frame.add(new JScrollPane(table));
		
		frame.setTitle("Editable Table Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        frame.pack();
        frame.setVisible(true);
        MenuBar menuBar = new MenuBar();
		frame.setJMenuBar(menuBar.createMenuBar());
        
        
		JPanel buttons = new JPanel();
        
        JButton addButton = new JButton("Add");
        
        addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				model.addEmployee(new Employee(4, "Rohani", 1000, 1000, 13, 10, 8, 8));
				
				
			}
        	
        });
        
        JButton uploadButton = new JButton("Upload");
        
        uploadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				
				int[] selection = table.getSelectedRows();
				
				for(int i = selection.length - 1 ; i >= 0; i--) {
					model.removeEmployee(selection[i]);
				}
				
				OpenFile openFile = new OpenFile();
				File file = openFile.browse();
				
				ReadExcel readExcel = new ReadExcel(file);
				
				try {
					//List<String[]> data = readExcel.openFile();
					//updateModel(data);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				*
			}
        });
        
        buttons.add(addButton, BorderLayout.LINE_END);
        buttons.add(uploadButton, BorderLayout.LINE_END);

        frame.getContentPane().add(buttons, BorderLayout.SOUTH);
   
		
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
	
	public JScrollPane show() {
		JScrollPane scrollPane = new JScrollPane(table);
		return scrollPane;
	}
	
	
	
	public void updateModel(Object[][] data) {
	
		for( int i = 0 ; i < data.length; i++) {
       		int id = Integer.valueOf((String) data[i][0]);
       		String name = (String)data[i][1];
       		Double basicSalary = Double.valueOf((String)data[i][2]);
       		Double grossSalary = Double.valueOf((String)data[i][3]);
       		int employerEpf = Integer.valueOf((String)data[i][4]);
       		int employerSocso = Integer.valueOf((String)data[i][5]);
       		int employeeEpf = Integer.valueOf((String)data[i][6]);
       		int employeeSocso = Integer.valueOf((String)data[i][7]);
       		model.addEmployee(new Employee(id, name, basicSalary, grossSalary, employerEpf, employerSocso,
					employeeEpf, employeeSocso));
		}
	}
	
	
	public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TablePane();
            }
        });
    }   
    


	
	
	


}
*/