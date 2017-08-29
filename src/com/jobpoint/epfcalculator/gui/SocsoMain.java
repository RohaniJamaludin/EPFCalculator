package com.jobpoint.epfcalculator.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import com.jobpoint.epfcalculator.controller.SocsoController;
import com.jobpoint.epfcalculator.model.Socso;

public class SocsoMain implements ActionListener{
	
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	public static JPanel footerPanel = new JPanel();
	public static JFrame frame;
	public static SocsoTableModel model;
	private JTable table;
	private List<Socso> socsoList;
	private boolean isSixty;
	
	public SocsoMain(List<Socso> socsoList, boolean isSixty) {
		this.isSixty = isSixty;
		this.socsoList = socsoList;
		initialize();
	}
	
	private void initialize() {
		
		//SocsoController socsoController = new SocsoController();

		//List<Socso> socsoList = socsoController.showSocso();		
		model = new SocsoTableModel(socsoList);
		
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(800, 800));
		table.setFillsViewportHeight(true);
		
		TableColumnModel tableColumnModel = table.getColumnModel();
		tableColumnModel.removeColumn(tableColumnModel.getColumn(0));
		
		String s;
		if(isSixty) {
			s = "Above 60";
		}else {
			s = "Below 60";
		}
		
		frame = new JFrame("Socso Contribution Rate Table " + s);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(table);
		mainPanel.add(scrollPane);
		
		footerPanel = new JPanel();
		frame.getContentPane().add(footerPanel, BorderLayout.PAGE_END);
		frame.setLocationRelativeTo(null);
		
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		footerPanel.add(addButton);
		
		editButton = new JButton("Edit");
		editButton.addActionListener(this);
		footerPanel.add(editButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		footerPanel.add(deleteButton);
		
		frame.setSize(600,600);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		
		int w = frame.getSize().width;
		int h = frame.getSize().height;
	    int x = (dimension.width-w)/2;
	    int y = (dimension.height-h)/2;
	    frame.setLocation(x, y);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		SocsoController socsoController = new SocsoController();
		if(event.getSource().equals(addButton)) {
			System.out.println("Add button clicked");
			socsoController.newSocso(isSixty);
		}
	
		if(event.getSource().equals(editButton)) {
			System.out.println("Edit button clicked");
			int[] selection = table.getSelectedRows();
		
			if(selection.length > 0) {
				if(selection.length == 1) {
				
					int indexRow = selection.length -1;

					Object[] data = new Object[5];
					data[0] = model.getValueAt(selection[indexRow], 0); 
					data[1] = model.getValueAt(selection[indexRow], 1);
					data[2] = model.getValueAt(selection[indexRow], 2);
					data[3] = model.getValueAt(selection[indexRow], 3);
					data[4] = model.getValueAt(selection[indexRow], 4);
				
					socsoController.editSocso(data, selection[indexRow], isSixty);				
				}else {
					JOptionPane.showMessageDialog(null, "More than one row selected. Please select only one row!");
				}
			}else {
				JOptionPane.showMessageDialog(null, "No row selected. Please select one row!");
			}
		
		}
	
		if(event.getSource().equals(deleteButton)) {
			int[] selection = table.getSelectedRows();
			
			if(selection.length > 0) {
				for(int i = selection.length - 1 ; i >= 0; i--) {
					int id = (int) model.getValueAt(selection[i], 0); 
					if(socsoController.deleteSocso(id, selection[i])) {
						JOptionPane.showMessageDialog(null, "Row/s successfully deleted!");
					}
				}	
			}else {
				JOptionPane.showMessageDialog(null, "No row selected. Please select one or more rows!");
			}
		
					
		} 
	
	}

}
