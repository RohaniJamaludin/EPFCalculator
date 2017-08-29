package com.jobpoint.epfcalculator.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jobpoint.epfcalculator.controller.EpfController;
import com.jobpoint.epfcalculator.tool.ValidateDataInput;

public class EpfAdd implements ActionListener{
	
	private JDialog dialog;
	private JButton saveButton;
	private JButton cancelButton;
	private JLabel messageLabel;
	public static JTextField lowerBoundText;
	public static JTextField upperBoundText;
	public static JTextField employerShareText;
	public static JTextField employeeShareText;
	public static boolean isSixty;
	
	public EpfAdd (boolean isSixty) {
		EpfAdd.isSixty = isSixty;
		initialize();
	}
	
	private void initialize() {
		EpfMain.footerPanel.getComponent(0).setEnabled(false); 
		
		String s;
		if(isSixty) {
			s = "Above 60";
		}else {
			s = "Below 60";
		}
		
		dialog = new JDialog(EpfMain.frame,"Add EPF Contribution Rate " + s, true);
		dialog.addWindowListener(new WindowAdapter() {
			public void  windowClosed(WindowEvent event) {
				System.out.println("Closed");
				EpfMain.footerPanel.getComponent(0).setEnabled(true);
			}
		});
		
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		dialog.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		GridBagConstraints constraint = new GridBagConstraints();
		
		
		JLabel lowerBoundLabel = new JLabel("Basic Salary + Allowance From");
		constraint.weightx = 0.5;
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 0;
		constraint.gridy = 0;
		constraint.insets = new Insets(0, 10, 2, 10);
		mainPanel.add(lowerBoundLabel,constraint);
		
		lowerBoundText = new JTextField();
		lowerBoundText.setColumns(10);
		lowerBoundText.setText("0.00");
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.weightx = 0.5;
		constraint.gridx = 1;
		constraint.gridy = 0;
		constraint.insets = new Insets(0, 10, 2, 10);
		mainPanel.add(lowerBoundText,constraint);
		
		JLabel upperBoundLabel = new JLabel("Basic Salary + Allowance To");
		constraint.weightx = 0.5;
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 0;
		constraint.gridy = 1;
		constraint.insets = new Insets(0, 10, 2, 10);
		mainPanel.add(upperBoundLabel,constraint);
		
		upperBoundText = new JTextField();
		upperBoundText.setColumns(10);
		upperBoundText.setText("0.00");
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.weightx = 0.5;
		constraint.gridx = 1;
		constraint.gridy = 1;
		constraint.insets = new Insets(0, 10, 2, 10);
		mainPanel.add(upperBoundText,constraint);
		
		JLabel employerShareLabel = new JLabel("Employer Share");
		constraint.weightx = 0.5;
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 0;
		constraint.gridy = 2;
		constraint.insets = new Insets(0, 10, 2, 10);
		mainPanel.add(employerShareLabel,constraint);
		
		employerShareText = new JTextField();
		employerShareText.setColumns(10);
		employerShareText.setText("0.00");
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.weightx = 0.5;
		constraint.gridx = 1;
		constraint.gridy = 2;
		constraint.insets = new Insets(0, 10, 2, 10);
		mainPanel.add(employerShareText,constraint);


		JLabel employeeShareLabel = new JLabel("Employee Share");
		constraint.weightx = 0.5;
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 0;
		constraint.gridy = 3;
		constraint.insets = new Insets(0, 10, 0, 10);
		mainPanel.add(employeeShareLabel,constraint);
		
		employeeShareText = new JTextField();
		employeeShareText.setColumns(10);
		employeeShareText.setText("0.00");
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.weightx = 0.5;
		constraint.gridx = 1;
		constraint.gridy = 3;
		constraint.insets = new Insets(0, 10, 0, 10);
		mainPanel.add(employeeShareText,constraint);
		
		messageLabel = new JLabel();
		messageLabel.setFont(new Font("Serif", Font.ITALIC, 12));
		messageLabel.setForeground(Color.RED);
		constraint.weightx = 0.5;
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 0;
		constraint.gridy = 4;
		constraint.gridwidth = 2;
		constraint.weighty = 1.0;
		constraint.insets = new Insets(10, 10, 0, 10);
		mainPanel.add(messageLabel,constraint);
		
		JPanel footerPanel = new JPanel();
		dialog.getContentPane().add(footerPanel,BorderLayout.PAGE_END);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		footerPanel.add(saveButton);
		
		cancelButton = new JButton("Cancel");
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
		boolean error = false;
		ValidateDataInput validateTextField = new ValidateDataInput();
		
		if(event.getSource().equals(saveButton)) {
			String message = "";
			System.out.println(lowerBoundText.getText().toString());

			if(validateTextField.isNumeric(lowerBoundText.getText())) {
				System.out.println("Input " + lowerBoundText.getText() + " is valid ");
			}else {
				message += "Basic Salary + Allowance From input is invalid <br>";
			}
				
			if(validateTextField.isNumeric(upperBoundText.getText())) {
				System.out.println("Input " + upperBoundText.getText() + " is valid ");
			}else {
				message += "Basic Salary + Allowance To input is invalid <br>";
			}
			
			if(validateTextField.isNumeric(employerShareText.getText())) {
				System.out.println("Input " + employerShareText.getText() + " is valid ");
			}else {
				message += "Employer Share input is invalid <br>";
			}
			
			if(validateTextField.isNumeric(employeeShareText.getText())) {
				System.out.println("Input " + employeeShareText.getText() + " is valid ");
			}else {
				message += "Employee Share input is invalid <br>";
			}
					
			if(message != "") {
				error = true;
				messageLabel.setText("<html>" + message + "</html>");
				dialog.setSize(395,300);
			}
			
			if(!error) {
				System.out.println("Save");
				//save 
				EpfController epfController = new EpfController();
				if(epfController.saveEpf(isSixty)) {
					dialog.dispose();
					JOptionPane.showMessageDialog(null, "New data has been added!");
				}else {
					JOptionPane.showMessageDialog(null, "Data cannot be added!");
				}
				
			}
		}
		
		if(event.getSource().equals(cancelButton)) {
			System.out.println("Cancel");
			dialog.dispose();
		}
		
	}

}
