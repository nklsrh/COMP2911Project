package view;

import model.Cell;
import model.Grid;
import model.Puzzle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.PuzzleControl;

import java.awt.GridLayout;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * The class that represents the GUI of the Sudoku puzzle game.
 * @author Nikhil Suresh, Ryan Tan, Nicholas Ho
 *
 */
public class FrameMain extends JFrame {
	private JPanel contentPane;
	private ArrayList<ArrayList<JButton>> cells;
	private ArrayList<ArrayList<JButton>> keypadButtons;
	private JLabel hintLabel;
	private JButton btnAutofill;
	
	private int numberOfRows;
	private int padding;
	private int textboxWidth;
	private int widthBetweenTextBoxes;
	private int totalWidthOfGrid;
	private int widthOfSidebar;
	private int widthOfKeypad;
	private int numActions;
	/**
	 * Contains coordinates for X,Y of last pressed cell button (so we know what cell to change)
	 * first element is X value (column)
	 */
	private int[] lastPressedCell;
	/**
	 * Creates the frame of the GUI being used to display the Sudoku board and other supplementary information
	 * in an attractive and accessible manner
	 */
	public FrameMain(PuzzleControl puzzleControl) {
		numberOfRows = 9;
		padding = 0; //6
		textboxWidth = 42; // 28
		widthBetweenTextBoxes = 51; // 31
		widthOfSidebar = 300;
		widthOfKeypad = 300;
		lastPressedCell = new int[2];
		lastPressedCell[0] = -1;
		lastPressedCell[1] = -1;
		numActions = 0;
		
		totalWidthOfGrid = padding + (numberOfRows * widthBetweenTextBoxes) + textboxWidth;
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
		
		//////////////////////////////////////////////
		
		JPanel keypadPanel = new JPanel();
		//keypadPanel.setBackground(SystemColor.windowBorder);
		keypadPanel.setBounds(totalWidthOfGrid + padding, 0, 287, 297); //314 - padding
		fullPanel.add(keypadPanel);
		GridBagLayout gbl_keypadPanel = new GridBagLayout();
		gbl_keypadPanel.columnWidths = new int[] {95, 95, 95, 0};
		gbl_keypadPanel.rowHeights = new int[] {100, 100, 100, 0};
		gbl_keypadPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_keypadPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		keypadPanel.setLayout(gbl_keypadPanel);
		
		//////////////////////////////////////////////////////////////////////////////////////
		
		JPanel sidebarPanel = new JPanel();
		//sidebarPanel.setBackground(SystemColor.windowBorder);
		sidebarPanel.setBounds(totalWidthOfGrid + widthOfKeypad - padding, -padding, widthOfSidebar, totalWidthOfGrid);
		fullPanel.add(sidebarPanel);
		sidebarPanel.setLayout(null);
		
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
		tabs.setBounds(0, padding, 288, 301);
		sidebarPanel.add(tabs);
		
		///////////////////////////////////////////////////////////
		
		JPanel tabHints = new JPanel();
		tabs.addTab("HINTS", null, tabHints, null);
		tabHints.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel lblHint = new JLabel("HINTS");
		lblHint.setHorizontalAlignment(SwingConstants.CENTER);
		lblHint.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		tabHints.add(lblHint);
		
		hintLabel = new JLabel("No hints revealed");
		hintLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		hintLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tabHints.add(hintLabel);
		
		JButton btnGetHint = new JButton("Get hint");
		btnGetHint.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		final PuzzleControl pz = puzzleControl;
		btnGetHint.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				loadHint(lastPressedCell[1], lastPressedCell[0], pz);
			}
		});
		tabHints.add(btnGetHint);
		
		btnAutofill = new JButton("Autofill next empty cell");
		btnAutofill.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnAutofill.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				autoFill(pz);
			}
		});
		tabHints.add(btnAutofill);
		
		/////////////////////////////////////////////////////////////
		
		JPanel tabTimer = new JPanel();
		tabs.addTab("TIMER", null, tabTimer, null);
		
		JLabel lblNewLabel = new JLabel("TIME SINCE PUZZLE STARTED");
		tabTimer.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		//Timer stuff
		JLabel timerExplanation = new JLabel("Minutes:Seconds");
		timerExplanation.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		tabTimer.add(timerExplanation);
		
		TimerLabel timer = new TimerLabel();
		timer.setHorizontalAlignment(SwingConstants.CENTER);
		timer.setVerticalAlignment(SwingConstants.BOTTOM);
		timer.setFont(new Font("Lucida Grande", Font.PLAIN, 70));
		tabTimer.add(timer);
		
		
		////////////////////////////////////////////////////////////////
		
		JPanel tabStats = new JPanel();
		tabs.addTab("STATISTICS", null, tabStats, null);
		
		JLabel lblStats = new JLabel("STATISTICS");
		lblStats.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		tabStats.add(lblStats);
		lblStats.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel actionStats = new JLabel("Number of actions performed: " + numActions);
		actionStats.setHorizontalAlignment(SwingConstants.LEFT);
		actionStats.setVerticalAlignment(SwingConstants.CENTER);
		actionStats.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		tabStats.add(actionStats);
		
		//////////////////////////////////////////////////////////////////
		
		JPanel gridPanel = new JPanel();
		gridPanel.setBounds(padding, padding, padding + (numberOfRows * widthBetweenTextBoxes) + textboxWidth, padding + (numberOfRows * widthBetweenTextBoxes) + textboxWidth);
		fullPanel.add(gridPanel);
		gridPanel.setLayout(new GridLayout(9, 9));

		/////////////////////////////////////////////////////////////////////////

		setupKeypad(keypadPanel, puzzleControl);
		
		///////////////////////////////////////////////////////////////////////////
		
		setupCells(gridPanel, puzzleControl);
	}
	
	private void cellClicked(int thisX, int thisY, PuzzleControl pz)
	{
		if (lastPressedCell[0] >= 0 && lastPressedCell[1] >= 0)
		{
			setCellFont(lastPressedCell[1], lastPressedCell[0], false);
		}
		if (!pz.getCell(thisY, thisX).isFixed())
    	{
			lastPressedCell[1] = thisY;
			lastPressedCell[0] = thisX;
			setCellFont(thisY, thisX, true);
    	}
		numActions++;
		this.validate();
	}
	
	private void keyPressed(int thisX, int thisY, PuzzleControl pz)
	{
//		System.out.println(keypadButtons.get(thisY).get(thisX).getText().length() + " " + lastPressedCell[0] + " " + lastPressedCell[1]);
		  if (keypadButtons.get(thisY).get(thisX).getText().length() > 0 && lastPressedCell[0] >= 0 && lastPressedCell[1] >= 0)
		  {
//		      System.out.println("COL:" + lastPressedCell[0] + ", ROW: " + lastPressedCell[1]);		  
			  pz.setCell(lastPressedCell[1], lastPressedCell[0], Integer.valueOf(keypadButtons.get(thisY).get(thisX).getText()));

//			  System.out.println("COL:" + pz.getColumnList().get(lastPressedCell[0]));
//			  System.out.println("ROW:" + pz.getRowList().get(lastPressedCell[1]));
//			  System.out.println("GRID:" + pz.getGrid((lastPressedCell[1]/3 % 3 * 3 + lastPressedCell[0]/3 % 3 + 1) - 1));
			  
		  		if (pz.getCell(lastPressedCell[1], lastPressedCell[0]).getNumber() != null)
				  cells.get(lastPressedCell[1]).get(lastPressedCell[0]).setText(Integer.toString(pz.getCell(lastPressedCell[1], lastPressedCell[0]).getNumber()));
			  
		  		setDEBUGCellColour(lastPressedCell[1],lastPressedCell[0], pz);
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

				keypadButtons.get(y).add(new JButton(String.valueOf(((y * 3) + (x + 1)))));	// set value of number according to position (like telephone buttons
				keypadButtons.get(y).get(x).setFont(new Font("Lucida Grande", Font.BOLD, 36));	
				
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
				
				cells.get(y).add(new JButton("0"));
				cells.get(y).get(x).setFont(new Font("Lucida Grande", Font.PLAIN, 16));				
//				cells.get(y).get(x).setBounds(padding + (x * widthBetweenTextBoxes), padding + (y * widthBetweenTextBoxes), textboxWidth, textboxWidth);

				GridBagConstraints gbc_button = new GridBagConstraints();
				gbc_button.fill = GridBagConstraints.BOTH;
				gbc_button.insets = new Insets(0,0,0,0);
				gbc_button.gridx = x;
				gbc_button.gridy = y;
				
				setCellNumber(y, x, puzzleControl);		
				
				cells.get(y).get(x).addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						cellClicked(thisX, thisY, pz);
						setCellNumber(thisY, thisX, pz);
					}
			    });
				
				gridPanel.add(cells.get(y).get(x), gbc_button);
//				gridPanel.add(cells.get(y).get(x));
				
				if ((x+1) % 3 == 0)
				{
					JSeparator js = new JSeparator(SwingConstants.VERTICAL);
					js.setPreferredSize(new Dimension(1,1));
					gbc_button.weighty = 1;
					gridPanel.add(js, gbc_button);
				}	
			}
//			if ((y+1) % 3 == 0)
//			{
//				JSeparator js = new JSeparator(SwingConstants.VERTICAL);
//				gridPanel.add(js);
//			}
		}	
	}
	
	private void setCellFont(int row, int col, boolean isBold)
	{
		if (isBold)
		{
			cells.get(row).get(col).setFont(new Font(cells.get(row).get(col).getFont().getName(), 
					Font.BOLD, 
					cells.get(row).get(col).getFont().getSize()));
		}
		else
		{
			cells.get(row).get(col).setFont(new Font(cells.get(row).get(col).getFont().getName(), 
					Font.PLAIN,
					cells.get(row).get(col).getFont().getSize()));
		}
	}
	private void setCellNumber(int row, int col, PuzzleControl puzzleControl)
	{
		if (!puzzleControl.getCell(row, col).isEmpty())
		{
			cells.get(row).get(col).setText(Integer.toString(puzzleControl.getCell(row, col).getNumber()));
		}
		else
		{
			cells.get(row).get(col).setText("");
		}
		setCellColour(row, col, puzzleControl);
	}
	
	private void setCellColour(int row, int col, PuzzleControl puzzleControl)
	{
		if (puzzleControl.getCell(row, col).isFixed())
		{
			//cells.get(row).get(col).setForeground(Color.GRAY);
		}
		else
		{
			//cells.get(row).get(col).setForeground(Color.BLACK);
			// Leaving this here for inspiration
			//cells.get(row).get(col).setText("<html><body bgcolor=blue>test</body></html>");
			cells.get(row).get(col).setBackground(Color.BLACK);
		}		
	}
	private void setDEBUGCellColour(int row, int col, PuzzleControl puzzleControl)
	{
		if (puzzleControl.checkNumberSolution(row, col))
		{
			//cells.get(row).get(col).setForeground(Color.GREEN);
			cells.get(row).get(col).setBackground(Color.GREEN);
		}
		else
		{
			//cells.get(row).get(col).setForeground(Color.RED);
			cells.get(row).get(col).setBackground(Color.RED);
	    }		
	}
	
	private void loadHint(int row, int col, PuzzleControl puzzleControl) 
	{
		if (row >= 0 && col >= 0)
		{
			if (puzzleControl.getCell(row, col).checkNumberSolution())
			{
				hintLabel.setText("Cell is correctly filled");
			}
			else
			{
				hintLabel.setText("Solution: " + Integer.toString(puzzleControl.getCell(row, col).getSolution()));
			}
		}
		else
		{
			hintLabel.setText("No hint available");
		}	
	}
	
	private void autoFill(PuzzleControl puzzleControl)
	{
		int[] values = puzzleControl.getFirstEmptyCellCoordinates();
		if (values != null)
		{
			puzzleControl.setCell(values[0], values[1], values[2]);
			cellClicked(values[0], values[1], puzzleControl);
			setCellNumber(values[0], values[1], puzzleControl);
		}
		else
		{
			btnAutofill.setEnabled(false);
		}
	}
}