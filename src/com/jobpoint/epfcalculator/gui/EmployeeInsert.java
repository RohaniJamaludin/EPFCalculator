package com.jobpoint.epfcalculator.gui;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.jobpoint.epfcalculator.controller.EmployeeController;

public class EmployeeInsert  implements ActionListener{
	public static JTextField sourceFileText;
	public static JComboBox<String> yearCombo;
	public static JComboBox<String> monthCombo;
	private Dashboard dashboard;
	private JDialog dialog; 
	private JButton okButton;
	private JButton cancelButton;
	private JButton browseButton;
	public static JRadioButton templateOneRadio, templateTwoRadio, templateThreeRadio,  eightPercentRadio, elevenPercentRadio;
	
	public EmployeeInsert() {
		initialize();
	}
	
	public EmployeeInsert(Dashboard dashboard) {
		this.dashboard = dashboard;
		initialize();
	}
	
	private void initialize() {
		
		
		dialog = new JDialog(dashboard.frame, "Insert Data", true);
		
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		dialog.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		footerPanel.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		footerPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 5, 20));
		dialog.getContentPane().add(footerPanel, BorderLayout.PAGE_END);
		
		GridBagConstraints constraint = new GridBagConstraints();
		
		JLabel sourceFileLabel = new JLabel("Source File");
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 0;
		constraint.gridy = 0;
		constraint.gridwidth = 1;
		constraint.weightx = 1;
		constraint.insets = new Insets(0, 10, 2, 10);
		mainPanel.add(sourceFileLabel,constraint);
		
		sourceFileText = new JTextField();
		sourceFileText.setColumns(20);
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 1;
		constraint.gridy = 0;
		constraint.gridwidth = 2;
		constraint.weightx = 1;
		constraint.insets = new Insets(0, 10, 2, 5);
		mainPanel.add(sourceFileText,constraint);
		
		browseButton = new JButton("Browse File");
		browseButton.addActionListener(this);
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 3;
		constraint.gridy = 0;
		constraint.gridwidth = 1;
		constraint.weightx = 1;
		constraint.insets = new Insets(0, 0, 2, 10);
		mainPanel.add(browseButton,constraint);
		
		JLabel salaryMonthLabel = new JLabel("Month");
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 0;
		constraint.gridy = 1;
		constraint.gridwidth = 1;
		constraint.weightx = 1;
		constraint.insets = new Insets(0, 10, 2, 10);
		mainPanel.add(salaryMonthLabel,constraint);
		
		/*monthText = new JTextField();
		//monthText.setColumns(10);
		constraint.weightx = 0.5;
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 1;
		constraint.gridy = 1;
		constraint.insets = new Insets(0, 10, 2, 10);
		mainPanel.add(monthText,constraint);*/
		
		/*UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		constraint.weightx = 0.5;
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 1;
		constraint.gridy = 1;
		constraint.insets = new Insets(0, 10, 2, 10);
		mainPanel.add(datePicker,constraint);*/
		
		Calendar now = Calendar.getInstance();
		int currentYear = now.get(Calendar.YEAR);
		String currentMonth = now.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		
		String[] months = {"January", "February", "March", "April", "May", "June", "July", 
				  			"August", "September", "October", "November", "December"}; 
		
		monthCombo = new JComboBox<>(months);
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 1;
		constraint.gridy = 1;
		constraint.gridwidth = 1;
		constraint.weightx = 1;
		constraint.insets = new Insets(0, 10, 2, 2);
		mainPanel.add(monthCombo, constraint);
		monthCombo.setSelectedItem(currentMonth);
		
		String[] years = new String[61];
		int year = 1970;
		for(int i = 0; i <= 60; i++) {
			years[i] = Integer.toString(year);
			year++;
		}
		
		yearCombo = new JComboBox<>(years);
		yearCombo.setSelectedItem(Integer.toString(currentYear));
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 2;
		constraint.gridy = 1;
		constraint.gridwidth = 1;
		constraint.weightx = 1;
		constraint.insets = new Insets(0, 0, 2, 10);
		mainPanel.add(yearCombo, constraint);
		
		JLabel percentageLabel = new JLabel("EPF Contribution");
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 0;
		constraint.gridy = 2;
		constraint.gridwidth = 1;
		constraint.weightx = 1;
		constraint.insets = new Insets(0, 10, 2, 10);
		mainPanel.add(percentageLabel,constraint);
		
		eightPercentRadio = new JRadioButton("8%");
		eightPercentRadio.setSelected(true);
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 1;
		constraint.gridy = 2;
		constraint.gridwidth = 1;
		constraint.weightx = 1;
		constraint.insets = new Insets(0, 10, 2, 5);
		mainPanel.add(eightPercentRadio,constraint);
		
		elevenPercentRadio = new JRadioButton("11%");
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 2;
		constraint.gridy = 2;
		constraint.gridwidth = 1;
		constraint.weightx = 1;
		constraint.insets = new Insets(0, 10, 2, 5);
		mainPanel.add(elevenPercentRadio,constraint);
		
		ButtonGroup percentButtonGroup = new ButtonGroup();
		percentButtonGroup.add(eightPercentRadio);
		percentButtonGroup.add(elevenPercentRadio);
		
		JLabel categoryLabel = new JLabel("Template");
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 0;
		constraint.gridy = 3;
		constraint.gridwidth = 1;
		constraint.weightx = 1;
		constraint.insets = new Insets(0, 10, 2, 10);
		mainPanel.add(categoryLabel,constraint);
		
		templateOneRadio = new JRadioButton("Basic RM1100 - RM1350");
		templateOneRadio.setSelected(true);
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 1;
		constraint.gridy = 3;
		constraint.gridwidth = 1;
		constraint.weightx = 1;
		constraint.insets = new Insets(0, 10, 2, 5);
		mainPanel.add(templateOneRadio,constraint);
		
		templateTwoRadio = new JRadioButton("Basic RM1000");
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 2;
		constraint.gridy = 3;
		constraint.gridwidth = 1;
		constraint.weightx = 1;
		constraint.insets = new Insets(0, 10, 2, 5);
		mainPanel.add(templateTwoRadio,constraint);
		
		templateThreeRadio = new JRadioButton("Payslip");
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 3;
		constraint.gridy = 3;
		constraint.gridwidth = 1;
		constraint.weightx = 1;
		constraint.insets = new Insets(0, 10, 2, 5);
		mainPanel.add(templateThreeRadio,constraint);
		
		ButtonGroup templateButtonGroup = new ButtonGroup();
		templateButtonGroup.add(templateOneRadio);
		templateButtonGroup.add(templateTwoRadio);
		templateButtonGroup.add(templateThreeRadio);
		
		dialog.getContentPane().add(footerPanel,BorderLayout.PAGE_END);
		
		okButton = new JButton("OK");
		okButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		okButton.addActionListener(this);
		footerPanel.add(okButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		cancelButton.addActionListener(this);
		footerPanel.add(cancelButton);	
		
		//mainPanel.add(new JLabel("Hello dialog"));
		
		
		dialog.setLocation(500,150);
		dialog.pack();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		EmployeeController employeeController = new EmployeeController();
		if(event.getSource().equals(browseButton)) {
			String filePath = employeeController.getFilePath();
			sourceFileText.setText(filePath);
		}
		
		if(event.getSource().equals(okButton)) {
			if(!sourceFileText.getText().isEmpty()) {
				dialog.dispose();
				employeeController.showEmployee();
			}else {
				System.out.println("Source file not valid");
			}
		}
		
		if(event.getSource().equals(cancelButton)) {
			dialog.dispose();
		}
		
	}

}
