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
	JMenuItem insertMenu, exportMenu, overSixtyEpfEightMenu, overSixtyEpfElevenMenu, belowSixtyEpfEightMenu, belowSixtyEpfElevenMenu , overSixtySocsoMenu, belowSixtySocsoMenu;

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
		
		belowSixtyEpfEightMenu = new JMenuItem("Below 60(8%)");
		belowSixtyEpfEightMenu.setMnemonic(KeyEvent.VK_T);
		belowSixtyEpfEightMenu.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		belowSixtyEpfEightMenu.addActionListener(this);
		epfMenu.add(belowSixtyEpfEightMenu);
		
		belowSixtyEpfElevenMenu = new JMenuItem("Below 60(11%)");
		belowSixtyEpfElevenMenu.setMnemonic(KeyEvent.VK_T);
		belowSixtyEpfElevenMenu.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		belowSixtyEpfElevenMenu.addActionListener(this);
		epfMenu.add(belowSixtyEpfElevenMenu);
		
		overSixtyEpfEightMenu = new JMenuItem("Over 60(8%)");
		overSixtyEpfEightMenu.setMnemonic(KeyEvent.VK_T);
		overSixtyEpfEightMenu.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		overSixtyEpfEightMenu.addActionListener(this);
		epfMenu.add(overSixtyEpfEightMenu);
		
		overSixtyEpfElevenMenu = new JMenuItem("Over 60(11%)");
		overSixtyEpfElevenMenu.setMnemonic(KeyEvent.VK_T);
		overSixtyEpfElevenMenu.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		overSixtyEpfElevenMenu.addActionListener(this);
		epfMenu.add(overSixtyEpfElevenMenu);
		
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
		
		if(event.getSource().equals(overSixtyEpfEightMenu)) {
			EpfController epfController = new EpfController();
			epfController.showEpf(true, 8);
		}
		
		if(event.getSource().equals(overSixtyEpfElevenMenu)) {
			EpfController epfController = new EpfController();
			epfController.showEpf(true, 11);
		}
		
		if(event.getSource().equals(belowSixtyEpfEightMenu)) {
			EpfController epfController = new EpfController();
			epfController.showEpf(false, 8);
		}
		
		if(event.getSource().equals(belowSixtyEpfElevenMenu)) {
			EpfController epfController = new EpfController();
			epfController.showEpf(false, 11);
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
