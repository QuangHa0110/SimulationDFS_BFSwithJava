package com.nhom6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame implements ActionListener {

	private final String title = "Chương trình duyệt đồ thị";
	private final String author = "Nhóm 6";
	private final int colTextField = 5;
	private int widthGraphicsPanl = 400;
	private int heightGraphicsPanl = 0;
	private DataInput dataInput = new DataInput();
	private GraphicsPanel graphicsPanel;
	private JButton btnCreateGraph, btnRun;
	private JRadioButton radBFS, radDFS;

	public GUI() {
		add(createMainPanel());
		setDisplay();
	}

	private void setDisplay() {
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(title);
		//pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		panel.add(createTitlePanel(), BorderLayout.PAGE_START);
		panel.add(createControlPanel(), BorderLayout.WEST);
		panel.add(createShowPanel(), BorderLayout.CENTER);
		panel.add(createAuthorPanel(), BorderLayout.PAGE_END);

		graphicsPanel.setPreferredSize(new Dimension(widthGraphicsPanl, heightGraphicsPanl));
		return panel;
	}

	private JPanel createTitlePanel() {
		JPanel panel = new JPanel();
		panel.add(new JLabel(title.toUpperCase()));
		return panel;
	}

	private JPanel createControlPanel() {

		// create select panel - select algorithm
		JPanel selectPanel = new JPanel(new GridLayout(4, 2, 5, 5));

		selectPanel.add(new JLabel("Thuật toán"));
		selectPanel.add(radBFS = createRadioButton("BFS", true));
		selectPanel.add(new JLabel(""));
		selectPanel.add(radDFS = createRadioButton("DFS", false));
		selectPanel.add(new JLabel(""));
		selectPanel.add(btnCreateGraph = createButton("Tạo đồ thị"));

		ButtonGroup btnG = new ButtonGroup();
		btnG.add(radBFS);
		btnG.add(radDFS);

		
		

		
		// create edge panel - select algorithm
		JPanel runPanel = new JPanel(new GridLayout(2, 2, 5, 5));

		runPanel.add(new JLabel(""));
		runPanel.add(btnRun = createButton("Duyệt"));

		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(selectPanel, BorderLayout.PAGE_START);
		contentPanel.add(runPanel, BorderLayout.PAGE_END);
		// contentPanel.setBorder(new LineBorder(Color.blue));

		JPanel panel = new JPanel();
		panel.add(contentPanel);
		// panel.setBorder(new LineBorder(Color.red));
		heightGraphicsPanl = (int) contentPanel.getPreferredSize().getHeight();
		return panel;
	}

	private JPanel createShowPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(graphicsPanel = new GraphicsPanel());
		return panel;
	}

	private JPanel createAuthorPanel() {
		JPanel panel = new JPanel();
		panel.add(new JLabel(author.toUpperCase()));
		return panel;
	}

	private JButton createButton(String text) {
		JButton btn = new JButton(text);
		btn.addActionListener(this);
		return btn;
	}

	private JRadioButton createRadioButton(String text, boolean select) {
		JRadioButton rad = new JRadioButton(text, select);
		rad.addActionListener(this);
		return rad;
	}

	private JTextField createTextField() {
		JTextField tf = new JTextField(colTextField);
		return tf;
	}

	// ----------------------

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCreateGraph) {
			createGraph();
			addEdge();
			count =0;
			return;
		}
		if (e.getSource() == btnRun) {
			run();
		}
	}

	private void createGraph() {
		int numberPoint = dataInput.getMaxDinh();
		if (numberPoint > 0) {
			graphicsPanel.setNumberPoint(numberPoint);
			graphicsPanel.start(graphicsPanel.getPreferredSize().width, graphicsPanel.getPreferredSize().height);

		}
	}
	private void addEdge() {

		graphicsPanel.addLine();
	}
	int count =0;
	private void run() {
		if(radBFS.isSelected()) {
		
			graphicsPanel.addLineBFs(count);
			
		}
		if(radDFS.isSelected()){
		
			graphicsPanel.addLineDFS(count);
			
		}
		count++;
	}




	public static void main(String[] args) {
		GUI giaoDien = new GUI();
	}

}
