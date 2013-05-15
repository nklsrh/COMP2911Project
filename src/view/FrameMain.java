package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.util.ArrayList;

public class FrameMain extends JFrame {

	private JPanel contentPane;
	private ArrayList<ArrayList<JTextField>> boxes;
	
	private Integer numberOfRows;
	private Integer padding;
	private Integer textboxWidth;
	private Integer widthBetweenTextBoxes;
	private Integer totalWidthOfGrid;
	private Integer widthOfSidebar;
	/**
	 * Create the frame.
	 */
	public FrameMain() {
		numberOfRows = 9;
		padding = 6;
		textboxWidth = 28;
		widthBetweenTextBoxes = 31;
		widthOfSidebar = 300;
		
		totalWidthOfGrid = padding + (numberOfRows * widthBetweenTextBoxes) + textboxWidth;
		
		setResizable(false);
		setBackground(SystemColor.window);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, totalWidthOfGrid + widthOfSidebar, totalWidthOfGrid + textboxWidth);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel fullPanel = new JPanel();
		contentPane.add(fullPanel);
		fullPanel.setLayout(null);
		
		JPanel sidebarPanel = new JPanel();
		sidebarPanel.setBackground(SystemColor.windowBorder);
		sidebarPanel.setBounds(totalWidthOfGrid, 0, widthOfSidebar, totalWidthOfGrid);
		fullPanel.add(sidebarPanel);
		sidebarPanel.setLayout(null);
		
		JTabbedPane timerPane = new JTabbedPane(JTabbedPane.TOP);
		timerPane.setBounds(0, 6, 288, 301);
		sidebarPanel.add(timerPane);
		
		JLabel lblNewLabel = new JLabel("TIMER:");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		timerPane.addTab("TIMER", null, lblNewLabel, null);
		
		JLabel lblStats = new JLabel("STATS:");
		timerPane.addTab("STATS", null, lblStats, null);
		lblStats.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel lblHint = new JLabel("HINT:");
		timerPane.addTab("HINT", null, lblHint, null);
		lblHint.setVerticalAlignment(SwingConstants.TOP);
		
		JPanel gridPanel = new JPanel();
		gridPanel.setBounds(padding, padding, padding + (numberOfRows * widthBetweenTextBoxes) + textboxWidth, padding + (numberOfRows * widthBetweenTextBoxes) + textboxWidth);
		fullPanel.add(gridPanel);
		gridPanel.setLayout(null);

		boxes = new ArrayList<ArrayList<JTextField>>();
		for (int y = 0; y < numberOfRows; y++)
		{
			boxes.add(new ArrayList<JTextField>());
			for (int x = 0; x < numberOfRows; x++)
			{
				boxes.get(y).add(new JTextField());
				boxes.get(y).get(x).setBounds(padding + (x * widthBetweenTextBoxes), padding + (y * widthBetweenTextBoxes), textboxWidth, textboxWidth);
				boxes.get(y).get(x).setColumns(1);
				gridPanel.add(boxes.get(y).get(x));
			}
		}
	}
}
