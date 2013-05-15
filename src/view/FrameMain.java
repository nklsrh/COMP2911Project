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
	
	/**
	 * Create the frame.
	 */
	public FrameMain() {
		numberOfRows = 9;
		padding = 6;
		textboxWidth = 28;
		widthBetweenTextBoxes = 31;
		
		setResizable(false);
		setBackground(SystemColor.window);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 800, 480);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel fullPanel = new JPanel();
		contentPane.add(fullPanel);
		fullPanel.setLayout(null);
		
		JPanel sidebarPanel = new JPanel();
		sidebarPanel.setBackground(SystemColor.windowBorder);
		sidebarPanel.setBounds(padding + (numberOfRows * widthBetweenTextBoxes) + textboxWidth, padding, 291, 436);
		fullPanel.add(sidebarPanel);
		sidebarPanel.setLayout(null);
		
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
