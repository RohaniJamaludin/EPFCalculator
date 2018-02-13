package com.jobpoint.epfcalculator.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import com.jobpoint.epfcalculator.database.CreateDatabase;

//import com.jobpoint.epfcalculator.database.CreateDatabase;


public class Dashboard{
	
	public JFrame frame;
	public static Dashboard dashboard;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateDatabase.startDatabase();
					dashboard = new Dashboard();
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
		createLayout();
	}
	
	private void createLayout() {
		frame = new JFrame("EPF/SOCSO Calculator");
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
/*		JPanel contentPane = new JPanel(new BorderLayout());*/
		
		EmployeeMain employeeMain = new EmployeeMain();
/*		Border border = contentPane.getBorder();
		Border margin = new EmptyBorder(10,10,10,10);
		contentPane.setBorder(new CompoundBorder(border, margin));
		contentPane.add(employeeMain.initializePanel(), new GridLayout());	*/
		
		MenuBar menuBar = new MenuBar();
		frame.setJMenuBar(menuBar.createMenuBar());
		
		frame.setPreferredSize(new Dimension(1000, 500));
		
		frame.pack();
		frame.setLayout(new GridLayout());
		frame.getContentPane().add(employeeMain.initializePanel());
		frame.setLocationRelativeTo(null);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int w = frame.getSize().width;
		int h = frame.getSize().height;
	    int x = (dimension.width-w)/2;
	    int y = (dimension.height-h)/2;
	    frame.setLocation(x, y);
	    
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
		
}
