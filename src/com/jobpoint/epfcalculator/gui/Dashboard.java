package com.jobpoint.epfcalculator.gui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jobpoint.epfcalculator.database.CreateDatabase;

//import com.jobpoint.epfcalculator.database.CreateDatabase;


public class Dashboard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//public static final TablePanel tablePanel = new TablePanel();

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateDatabase.startDatabase();
					Dashboard frame = new Dashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public Dashboard() {
		super();
		initUI();
	}
	
	private void initUI() {
		createLayout();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("EPF/SOCSO Calculator");
		setSize(new Dimension(1200,700));
		setLocationRelativeTo(null);
		
	}
	
	private void createLayout() {
		contentPane = new JPanel(new BorderLayout());
		//contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setContentPane(contentPane);
		this.pack();
		this.setVisible(true);
		
		
		//TablePanel tablePanel = new TablePanel();
		EmployeeMain employeeMain = new EmployeeMain();
		contentPane.add(employeeMain.panel, BorderLayout.CENTER);	
		
		MenuBar menuBar = new MenuBar();
		this.setJMenuBar(menuBar.createMenuBar());
	}
		
}
