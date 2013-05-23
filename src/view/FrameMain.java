package view;

import model.Cell;
import model.Grid;
import model.Puzzle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import java.awt.GridLayout;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Font;
import java.awt.event.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class FrameMain extends JFrame {
	private JPanel contentPane;
	private ArrayList<ArrayList<JButton>> boxes;
	private ArrayList<ArrayList<JButton>> keypadButtons;
	
	private int numberOfRows;
	private int padding;
	private int textboxWidth;
	private int widthBetweenTextBoxes;
	private int totalWidthOfGrid;
	private int widthOfSidebar;
	private int widthOfKeypad;
	/**
	 * Contains coordinates for X,Y of last pressed cell button (so we know what cell to change)
	 * first element is X value (column)
	 */
	private int[] lastPressedCell;
	/**
	 * Create the frame.
	 */
	public FrameMain() {
		numberOfRows = 9;
		padding = 6;
		textboxWidth = 28;
		widthBetweenTextBoxes = 31;
		widthOfSidebar = 300;
		widthOfKeypad = 300;
		lastPressedCell = new int[2];
		lastPressedCell[0] = -1;
		lastPressedCell[1] = -1;
		
		totalWidthOfGrid = padding + (numberOfRows * widthBetweenTextBoxes) + textboxWidth;
		
		setResizable(false);
		setBackground(SystemColor.window);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, totalWidthOfGrid + widthOfSidebar + widthOfKeypad, totalWidthOfGrid + textboxWidth);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel fullPanel = new JPanel();
		contentPane.add(fullPanel);
		fullPanel.setLayout(null);
		
		JPanel keypadPanel = new JPanel();
		keypadPanel.setBackground(SystemColor.windowBorder);
		keypadPanel.setBounds(314, 6, 287, 297);
		fullPanel.add(keypadPanel);
		GridBagLayout gbl_keypadPanel = new GridBagLayout();
		gbl_keypadPanel.columnWidths = new int[] {95, 95, 95, 0};
		gbl_keypadPanel.rowHeights = new int[] {100, 100, 100, 0};
		gbl_keypadPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_keypadPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		keypadPanel.setLayout(gbl_keypadPanel);
		
		JPanel sidebarPanel = new JPanel();
		sidebarPanel.setBackground(SystemColor.windowBorder);
		sidebarPanel.setBounds(totalWidthOfGrid + widthOfKeypad, 0, widthOfSidebar, totalWidthOfGrid);
		fullPanel.add(sidebarPanel);
		sidebarPanel.setLayout(null);
		
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
		tabs.setBounds(0, 6, 288, 301);
		sidebarPanel.add(tabs);
		
		JPanel tabTimer = new JPanel();
		tabs.addTab("TIMER", null, tabTimer, null);
		
		JLabel lblNewLabel = new JLabel("TIME SINCE PUZZLE STARTED");
		tabTimer.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JPanel tabStats = new JPanel();
		tabs.addTab("STATISTICS", null, tabStats, null);
		
		JLabel lblStats = new JLabel("STATISTICS");
		lblStats.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		tabStats.add(lblStats);
		lblStats.setVerticalAlignment(SwingConstants.TOP);
		
		JPanel tabHints = new JPanel();
		tabs.addTab("HINTS", null, tabHints, null);
		
		JLabel lblHint = new JLabel("HINTS");
		lblHint.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		tabHints.add(lblHint);
		lblHint.setVerticalAlignment(SwingConstants.TOP);
		
		JPanel gridPanel = new JPanel();
		gridPanel.setBounds(padding, padding, padding + (numberOfRows * widthBetweenTextBoxes) + textboxWidth, padding + (numberOfRows * widthBetweenTextBoxes) + textboxWidth);
		fullPanel.add(gridPanel);
		gridPanel.setLayout(null);

		///////////////////////////////////////////////////////////////////////
		Puzzle puzzle = new Puzzle(1);
		ArrayList<String> gridsStrings = new ArrayList<String>(); 
		
		gridsStrings.add("1 7 4 3 9 6 8 5 2");
		gridsStrings.add("2 8 5 4 1 7 9 6 3");
		gridsStrings.add("3 9 6 5 2 8 1 7 4");
		
		gridsStrings.add("4 1 7 6 3 9 2 8 5");
		gridsStrings.add("5 2 8 7 4 1 3 9 6");
		gridsStrings.add("6 3 9 8 5 2 4 1 7");
		
		gridsStrings.add("7 4 1 9 6 3 5 2 8");
		gridsStrings.add("8 5 2 1 7 4 6 3 9");
		gridsStrings.add("9 6 3 2 8 5 7 4 1");
		
		Iterator<String> gsit = gridsStrings.iterator();
		int gridIndex = 0;
		
		while(gsit.hasNext()){
			String[] gridArray = gsit.next().split(" ");
			int n = 0;
			Grid curGrid = puzzle.getGrid(gridIndex);
			ArrayList<ArrayList<Cell>> table = curGrid.getGridTable();
			
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					table.get(i).get(j).setNumber(Integer.parseInt(gridArray[n]));
					n++;
				}
			}
			
			//puzzle.setGrid(gridIndex, table.get(gridIndex));
			
			gridIndex++;
		}

		/////////////////////////////////////////////////////////////////////////

		keypadButtons = new ArrayList<ArrayList<JButton>>();
		for (int y = 0; y < 3; y++)
		{
			final int thisY = y;
			keypadButtons.add(new ArrayList<JButton>());
			for (int x = 0; x < 3; x++)
			{
				final int thisX = x;
				final Puzzle pz = puzzle;
		    	
				keypadButtons.get(y).add(new JButton(String.valueOf(((y * 3) + (x + 1)))));
				GridBagConstraints gbc_button = new GridBagConstraints();
				gbc_button.fill = GridBagConstraints.BOTH;
				gbc_button.insets = new Insets(0, 0, 5, 5);
				gbc_button.gridx = x;
				gbc_button.gridy = y;
				keypadPanel.add(keypadButtons.get(y).get(x), gbc_button);
				keypadButtons.get(y).get(x).addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
			    	  if (keypadButtons.get(thisY).get(thisX).getText().length() > 0)
			    	  {
				    	pz.setCell(lastPressedCell[0], lastPressedCell[1], Integer.valueOf(keypadButtons.get(thisY).get(thisX).getText()));
				        System.out.println(lastPressedCell[0] + "," + lastPressedCell[1]);
				        boxes.get(lastPressedCell[1]).get(lastPressedCell[0]).setText(Integer.toString(pz.getCell(lastPressedCell[0], lastPressedCell[1]).getNumber()));
			    	  }
				}
				});
			}
		}
		
		boxes = new ArrayList<ArrayList<JButton>>();
		for (int y = 0; y < numberOfRows; y++)
		{
			final int thisY = y;
			boxes.add(new ArrayList<JButton>());
			for (int x = 0; x < numberOfRows; x++)
			{
				final int thisX = x;
				final Puzzle pz = puzzle;
				boxes.get(y).add(new JButton());
				boxes.get(y).get(x).setBounds(padding + (x * widthBetweenTextBoxes), padding + (y * widthBetweenTextBoxes), textboxWidth, textboxWidth);
				gridPanel.add(boxes.get(y).get(x));
				boxes.get(y).get(x).addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
				    	  lastPressedCell[0] = thisX;
				    	  lastPressedCell[1] = thisY;
					      System.out.println(e + ", at " + lastPressedCell[0] + "," + lastPressedCell[1]);
					}
			    });
				//System.out.println(puzzle.getCell(x, y));
				boxes.get(y).get(x).setText(Integer.toString(puzzle.getCell(x, y).getNumber()));
			}
		}
	}
}