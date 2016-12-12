package com.choilab.proj.skt;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import com.choilab.proj.skt.Configure.ConfigureDTO;

public class ConfigUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 214258294366175674L;

	Container contentPane = this.getContentPane();
	
	// INPUT
	JPanel inputPanel = new JPanel();
	JPanel cachePanel = new JPanel();
	Border border1 = BorderFactory.createEtchedBorder();

	JLabel cacheLabel = new JLabel("Cache status : ");
	
	JRadioButton rb1 = new JRadioButton("On");
	JRadioButton rb2 = new JRadioButton("Off");
	ButtonGroup bg = new ButtonGroup();

	JLabel typeLabel = new JLabel("Type : ");
	JTextField txtType = new JTextField(10); 

	JLabel conLabel = new JLabel("Number of containers : ");
	JTextField txtCon = new JTextField(10);
	
	// LOG
	Border border2 = BorderFactory.createEtchedBorder();

	JTextArea textArea = new JTextArea(10,39); // LOG 기록 @@@
	JScrollPane infoScroll = new JScrollPane(textArea);
	
	// BUTTON
	JPanel buttonPanel = new JPanel();
	JButton buttonRun = new JButton("Run");
	JButton buttonExit = new JButton("Exit");

	public ConfigUI() {	
		try {
			setTitle("Executor");
			setBounds(100, 100, 400, 500);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			// INPUT
			border1 = BorderFactory.createTitledBorder(border1, "Configure");
			
			inputPanel.setBorder(border1);
			
			cachePanel.setLayout(new GridLayout(1,2));
			bg.add(rb1);
			bg.add(rb2);
			cachePanel.add(rb1);
			cachePanel.add(rb2);
			
			if(ConfigureDTO.isCache() == true) {
				rb1.setSelected(true);
			} else {
				rb2.setSelected(true);
			}
			
			inputPanel.setLayout(new GridLayout(3,2,10,10));
			inputPanel.add(cacheLabel);
			inputPanel.add(cachePanel);
			
			inputPanel.add(typeLabel);
			inputPanel.add(txtType);
			txtType.setText(String.valueOf(ConfigureDTO.getType()));

			inputPanel.add(conLabel);
			inputPanel.add(txtCon);
			txtCon.setText(String.valueOf(ConfigureDTO.getContainers()));
			
			inputPanel.setPreferredSize(new Dimension(inputPanel.getWidth(), 120));
			
			contentPane.add(inputPanel, BorderLayout.NORTH);
			
			// LOG 
			border2 = BorderFactory.createTitledBorder(border2, "Log information");
			infoScroll.setBorder(border2);
			textArea.setCaretPosition(textArea.getText().length());
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setEditable(false);
			contentPane.add(infoScroll, BorderLayout.CENTER);

			// BOTTON
			buttonRun.setMnemonic('R');
			buttonPanel.add(buttonRun);
			buttonPanel.add(buttonExit);
			buttonRun.addActionListener(this);
			buttonExit.addActionListener(this);
			
			contentPane.add(buttonPanel, BorderLayout.SOUTH);

			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			setVisible(true);
		}catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonRun) {
			JOptionPane.showMessageDialog(this, "이걸 고치면 됨!!"); // RUN @@@
		} else
			System.exit(0);
	}

}
