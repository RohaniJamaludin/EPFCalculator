package com.jobpoint.epfcalculator.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;


import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


import com.jobpoint.epfcalculator.controller.EmployeeController;
import com.jobpoint.epfcalculator.controller.EpfController;
import com.jobpoint.epfcalculator.controller.SocsoController;

public class MenuBar implements ActionListener,ItemListener {
	
	JMenuBar menuBar;
	JMenu menu, epfMenu, socsoMenu;
	JMenuItem insertMenu, exportMenu, overSixtyEpfMenu, belowSixtyEpfMenu, overSixtySocsoMenu, belowSixtySocsoMenu;

	public JMenuBar createMenuBar() {
		
		menuBar = new JMenuBar();
		
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu);
		
		insertMenu = new JMenuItem("Insert Data");
		insertMenu.setMnemonic(KeyEvent.VK_T);
		insertMenu.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		insertMenu.addActionListener(this);
		menu.add(insertMenu);
		
		exportMenu = new JMenuItem("Export Data");
		exportMenu.setMnemonic(KeyEvent.VK_T);
		exportMenu.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		exportMenu.addActionListener(this);
		menu.add(exportMenu);
		
		menu = new JMenu("Setting");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu);
		
		/*epfMenu = new JMenuItem("EPF");
		epfMenu.setMnemonic(KeyEvent.VK_T);
		epfMenu.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		epfMenu.addActionListener(this);
		menu.add(epfMenu);*/
		
		/*socsoMenu = new JMenuItem("SOCSO");
		socsoMenu.setMnemonic(KeyEvent.VK_T);
		socsoMenu.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		socsoMenu.addActionListener(this);
		menu.add(socsoMenu);*/
		
		epfMenu = new JMenu("EPF");
		epfMenu.setMnemonic(KeyEvent.VK_T);
		epfMenu.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu.add(epfMenu);
		
		belowSixtyEpfMenu = new JMenuItem("Below 60");
		belowSixtyEpfMenu.setMnemonic(KeyEvent.VK_T);
		belowSixtyEpfMenu.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		belowSixtyEpfMenu.addActionListener(this);
		epfMenu.add(belowSixtyEpfMenu);
		
		overSixtyEpfMenu = new JMenuItem("Over 60");
		overSixtyEpfMenu.setMnemonic(KeyEvent.VK_T);
		overSixtyEpfMenu.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		overSixtyEpfMenu.addActionListener(this);
		epfMenu.add(overSixtyEpfMenu);
		
		socsoMenu = new JMenu("SOCSO");
		socsoMenu.setMnemonic(KeyEvent.VK_T);
		socsoMenu.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu.add(socsoMenu);
		
		belowSixtySocsoMenu = new JMenuItem("Below 60");
		belowSixtySocsoMenu.setMnemonic(KeyEvent.VK_T);
		belowSixtySocsoMenu.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		belowSixtySocsoMenu.addActionListener(this);
		socsoMenu.add(belowSixtySocsoMenu);
		
		overSixtySocsoMenu = new JMenuItem("Over 60");
		overSixtySocsoMenu.setMnemonic(KeyEvent.VK_T);
		overSixtySocsoMenu.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		overSixtySocsoMenu.addActionListener(this);
		socsoMenu.add(overSixtySocsoMenu);
		
		return menuBar;
		
	}
	
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == insertMenu) {
			
			EmployeeController employeeController = new EmployeeController();
			employeeController.insertEmployee(EmployeeMain.dashboard);
			//employeeController.showEmployee();			
		}
		
		if(event.getSource().equals(exportMenu)) {
			EmployeeController employeeController = new EmployeeController();
			employeeController.exportEmployee(EmployeeMain.dashboard);
			
		}
		
		if(event.getSource().equals(overSixtyEpfMenu)) {
			EpfController epfController = new EpfController();
			epfController.showEpf(true);
		}
		
		if(event.getSource().equals(belowSixtyEpfMenu)) {
			EpfController epfController = new EpfController();
			epfController.showEpf(false);
		}
		
		if(event.getSource().equals(belowSixtySocsoMenu)) {
			SocsoController socsoController = new SocsoController();
			socsoController.showSocso(false);
		}
		
		if(event.getSource().equals(overSixtySocsoMenu)) {
			SocsoController socsoController = new SocsoController();
			socsoController.showSocso(true);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		//EmployeeTableModel model = new EmployeeTableModel(null);
		//model.addEmployee(new Employee(4, "Rohani", 1000, 1000, 13, 10, 8, 8));
		
	}
	
	

	
	

}
