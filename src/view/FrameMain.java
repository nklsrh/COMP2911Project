package view;

import model.Cell;
import model.Grid;
import model.Puzzle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import controller.PuzzleControl;

import java.awt.GridLayout;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Font;
import java.awt.event.*;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class FrameMain extends JFrame {
	private JPanel contentPane;
	private ArrayList<ArrayList<JButton>> cells;
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
	public FrameMain(PuzzleControl puzzleControl) {
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

		/////////////////////////////////////////////////////////////////////////

		setupKeypad(keypadPanel, puzzleControl);
		
		///////////////////////////////////////////////////////////////////////////
		
		setupCells(gridPanel, puzzleControl);
	}

	private void cellClicked(int thisX, int thisY, PuzzleControl pz)
	{
		if (!pz.getCell(thisY, thisX).isFixed())
    	{
			lastPressedCell[1] = thisY;
			lastPressedCell[0] = thisX;
    	}
	}
	
	private void keyPressed(int thisX, int thisY, PuzzleControl pz)
	{
		System.out.println(keypadButtons.get(thisY).get(thisX).getText().length() + " " + lastPressedCell[0] + " " + lastPressedCell[1]);
		  if (keypadButtons.get(thisY).get(thisX).getText().length() > 0 && lastPressedCell[0] >= 0 && lastPressedCell[1] >= 0)
		  {
		      System.out.println("COL:" + lastPressedCell[0] + ", ROW: " + lastPressedCell[1]);		  
			  pz.setCell(lastPressedCell[1], lastPressedCell[0], Integer.valueOf(keypadButtons.get(thisY).get(thisX).getText()));

			  System.out.println("COL:" + pz.getColumnList().get(lastPressedCell[0]));
			  System.out.println("ROW:" + pz.getRowList().get(lastPressedCell[1]));
			  System.out.println("GRID:" + pz.getGrid((lastPressedCell[1]/3 % 3 * 3 + lastPressedCell[0]/3 % 3 + 1) - 1));
			  
		  		if (pz.getCell(lastPressedCell[1], lastPressedCell[0]).getNumber() != null)
				  cells.get(lastPressedCell[1]).get(lastPressedCell[0]).setText(Integer.toString(pz.getCell(lastPressedCell[1], lastPressedCell[0]).getNumber()));
			  
			    if (pz.getCell(lastPressedCell[1], lastPressedCell[0]).getNumber() == pz.getCell(lastPressedCell[1],  lastPressedCell[0]).getSolution())
				{
					cells.get(lastPressedCell[1]).get(lastPressedCell[0]).setForeground(Color.GREEN);
				}
			    else
			    {
					cells.get(lastPressedCell[1]).get(lastPressedCell[0]).setForeground(Color.BLACK);
			    }
		  }
	}
	
	private void setupKeypad(JPanel keypadPanel, PuzzleControl puzzleControl)
	{
		keypadButtons = new ArrayList<ArrayList<JButton>>();
		for (int y = 0; y < 3; y++)
		{
			final int thisY = y;
			keypadButtons.add(new ArrayList<JButton>());
			for (int x = 0; x < 3; x++)
			{
				final int thisX = x;
				final PuzzleControl pz = puzzleControl;
		    	
				keypadButtons.get(y).add(new JButton(String.valueOf(((y * 3) + (x + 1)))));	// set value of number according to position (like telephone buttons)
				
				GridBagConstraints gbc_button = new GridBagConstraints();
				gbc_button.fill = GridBagConstraints.BOTH;
				gbc_button.insets = new Insets(0, 0, 5, 5);
				gbc_button.gridx = x;
				gbc_button.gridy = y;
				
				keypadButtons.get(y).get(x).addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						keyPressed(thisX, thisY, pz);
					}
				});
				
				keypadPanel.add(keypadButtons.get(y).get(x), gbc_button);
			}
		}
	}
	
	private void setupCells(JPanel gridPanel, PuzzleControl puzzleControl)
	{
		cells = new ArrayList<ArrayList<JButton>>();
		for (int y = 0; y < numberOfRows; y++)
		{
			final int thisY = y;
			cells.add(new ArrayList<JButton>());
			for (int x = 0; x < numberOfRows; x++)
			{
				final int thisX = x;
				final PuzzleControl pz = puzzleControl;
				
				cells.get(y).add(new JButton());				
				cells.get(y).get(x).setBounds(padding + (x * widthBetweenTextBoxes), padding + (y * widthBetweenTextBoxes), textboxWidth, textboxWidth);
				setCellColour(y, x, puzzleControl);					
				
				cells.get(y).get(x).addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						cellClicked(thisX, thisY, pz);
						setCellColour(thisY, thisX, pz);
					}
			    });
				
				gridPanel.add(cells.get(y).get(x));
				
			}
		}	
	}
	
	private void setCellColour(int row, int col, PuzzleControl puzzleControl)
	{
		if (puzzleControl.getCell(row, col).isFixed())
		{
			cells.get(row).get(col).setForeground(Color.CYAN);
		}
		else
		{
			cells.get(row).get(col).setForeground(Color.BLACK);
		}	

		if (!puzzleControl.getCell(row, col).isEmpty())
		{
			cells.get(row).get(col).setText(Integer.toString(puzzleControl.getCell(row, col).getNumber()));
		}
		else
		{
			cells.get(row).get(col).setText("0");
		}
	}
}