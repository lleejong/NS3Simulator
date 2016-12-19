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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import com.choilab.proj.skt.Configure.Configure;
import com.choilab.proj.skt.Configure.ConfigureManager;

public class ConfigUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 214258294366175674L;

	Container contentPane = this.getContentPane();

	// INPUT
	JPanel inputPanel = new JPanel();
	JPanel cachePanel = new JPanel();
	JPanel typePanel = new JPanel();
	Border border1 = BorderFactory.createEtchedBorder();

	JLabel cacheLabel = new JLabel("Cache mode : ");

	JRadioButton crb1 = new JRadioButton("On");
	JRadioButton crb2 = new JRadioButton("Off");
	ButtonGroup bg1 = new ButtonGroup();

	JLabel typeLabel = new JLabel("Type : ");

	JRadioButton trb1 = new JRadioButton("1");
	JRadioButton trb2 = new JRadioButton("2");
	JRadioButton trb3 = new JRadioButton("3");
	ButtonGroup bg2 = new ButtonGroup();

	JLabel conLabel = new JLabel("Number of containers : ");
	String[] number = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
	JComboBox<Object> bx1 = new JComboBox<Object>(number);

	// LOG
	Border border2 = BorderFactory.createEtchedBorder();

	static JTextArea textArea = new JTextArea(10, 39); // LOG 기록 @@@
	static JScrollPane infoScroll = new JScrollPane(textArea);

	JPanel buttonPanel = new JPanel();
	JButton buttonRun = new JButton("Run");
	JButton buttonExit = new JButton("Exit");
	boolean isRunning = false;

	public ConfigUI() {
		setTitle("Executor");
		setBounds(100, 100, 400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// INPUT
		border1 = BorderFactory.createTitledBorder(border1, "Configure");

		inputPanel.setBorder(border1);
		inputPanel.setLayout(new GridLayout(3, 2, 10, 10));

		cachePanel.setLayout(new GridLayout(1, 2));
		bg1.add(crb1);
		bg1.add(crb2);
		cachePanel.add(crb1);
		cachePanel.add(crb2);

		if (Configure.isCache() == true) {
			crb1.setSelected(true);
		} else {
			crb2.setSelected(true);
		}

		inputPanel.add(cacheLabel);
		inputPanel.add(cachePanel);

		typePanel.setLayout(new GridLayout(1, 3));
		bg2.add(trb1);
		bg2.add(trb2);
		bg2.add(trb3);
		typePanel.add(trb1);
		typePanel.add(trb2);
		typePanel.add(trb3);

		if (Configure.getType() == 1) {
			trb1.setSelected(true);
		} else if (Configure.getType() == 2) {
			trb2.setSelected(true);
		} else {
			trb3.setSelected(true);
		}

		inputPanel.add(typeLabel);
		inputPanel.add(typePanel);

		inputPanel.add(conLabel);
		inputPanel.add(bx1);

		inputPanel.setPreferredSize(new Dimension(inputPanel.getWidth(), 120));

		contentPane.add(inputPanel, BorderLayout.NORTH);

		// LOG
		border2 = BorderFactory.createTitledBorder(border2, "Log information");
		infoScroll.setBorder(border2);
		textArea.setCaretPosition(textArea.getText().length());
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(true);
		textArea.append("---txLoss : 0.0 , txDelay : 751.0 , txJitter : 57.0 , rxLoss : 0.0, rxDelay : 751.0, rxJitter : 57.0");
		textArea.append("--[MISS] Throughput from DCE : 150.00 Mbps");
		textArea.append("Elapsed Time : 129.788 sec.");
		
		textArea.append("---txLoss : 0.0 , txDelay : 768.0 , txJitter : 81.0 , rxLoss : 0.0, rxDelay : 724.0, rxJitter : 9.0");
		textArea.append("---[HIT] Througput from Server : 118 Mbps");
		textArea.append("Elapsed Time : 0.373 sec.");
		
		textArea.append("---txLoss : 0.0 , txDelay : 788.0 , txJitter : 118.0 , rxLoss : 0.0, rxDelay : 759.0, rxJitter : 68.0");
		textArea.append("--[MISS] Throughput from DCE : 150.00 Mbps");
		textArea.append("Elapsed Time : 139.788 sec.");
		
		textArea.append("---txLoss : 0.0 , txDelay : 787.0 , txJitter : 111.0 , rxLoss : 0.0, rxDelay : 726.0, rxJitter : 19.0");
		textArea.append("--[MISS] Throughput from DCE : 150.00 Mbps");
		textArea.append("Elapsed Time : 157.138 sec.");

		contentPane.add(infoScroll, BorderLayout.CENTER);

		// BOTTON
		buttonRun.setMnemonic('R');
		buttonPanel.add(buttonRun);
		buttonPanel.add(buttonExit);
		buttonRun.addActionListener(this);
		buttonExit.addActionListener(this);

		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		setVisible(true);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				System.out.println("AA");
				ConfigureManager.writeXML(Configure.isCache(), Configure.getType(), Configure.getContainers());
			}
		});
	}
	public static void log(String str) {
		textArea.append(str + "\n");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonRun) {
			if (!isRunning) {
				isRunning = true;
				// JOptionPane.showMessageDialog(this, "이걸 고치면 됨!!"); // RUN @@@
				Configure.setCache(crb1.isSelected());
				Configure.setContainers(Integer.parseInt(number[bx1.getSelectedIndex()]));
				Configure.setType(1);
				isRunning = true;
				buttonRun.setEnabled(false);
				Executor.run();
			}

		} else {
			ConfigureManager.writeXML(Configure.isCache(), Configure.getType(), Configure.getContainers());
			System.exit(0);
		}
	}

}