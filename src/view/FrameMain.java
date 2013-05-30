package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.PuzzleControl;
import controller.timer.TimerLabel;

import java.awt.GridLayout;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.FlowLayout;
import java.awt.CardLayout;

/**
 * The class that represents the GUI of the Sudoku puzzle game.
 * @author Nikhil Suresh, Ryan Tan, Nicholas Ho
 *
 */
public class FrameMain extends JFrame {
	private Font font;
	private Font fontMed;
	private Font fontLight;
	
	private JPanel contentPane;
	private ArrayList<ArrayList<JButton>> cells;
	private ArrayList<ArrayList<JButton>> keypadButtons;
	private JLabel hintLabel;
	private JLabel actionStats;
	private JLabel buttonStats;
	private JLabel winStats;
	private JLabel cheatStats;
	private JLabel hintStats;
	private JLabel progressStats;
	
	private JPanel tabHints;
	private JPanel tabTimer;
	private JPanel tabStats;
	private JPanel sidebarBottomPanel;
	
	private JButton btnAutofill;
	private JButton btnHints;
	private JButton btnTimer;
	private JButton btnStats;
	
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
		
	private void startNewGame(PuzzleControl puzzleControl)
	{		
		puzzleControl.createPuzzle(1);
		setupFrame(puzzleControl);
		
		lastPressedCell = new int[2];
		lastPressedCell[0] = -1;
		lastPressedCell[1] = -1;
	}
	/**
	 * Creates the frame of the GUI being used to display the Sudoku board and other supplementary information
	 * in an attractive and accessible manner
	 */
	public FrameMain() {
		
		numberOfRows = 9;
		padding = 0; //6
		textboxWidth = 42; // 28
		widthBetweenTextBoxes = 51; // 31
		widthOfSidebar = 300;
		widthOfKeypad = 300;
		
		totalWidthOfGrid = padding + (numberOfRows * widthBetweenTextBoxes) + textboxWidth;
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		
		setResizable(false);
		setBackground(SystemColor.window);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, totalWidthOfGrid + widthOfSidebar + widthOfKeypad + 50, totalWidthOfGrid + textboxWidth);

		PuzzleControl puzzleControl = new PuzzleControl();
		startNewGame(puzzleControl);
	}
	
	private void setupFrame(PuzzleControl puzzleControl)
	{
		final PuzzleControl pz = puzzleControl;
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel fullPanel = new JPanel();
		fullPanel.setBackground(new Color(238,238,238));
		contentPane.add(fullPanel);
		fullPanel.setLayout(null);
		
		File font_file_light = new File("assets/Roboto-Regular.ttf");
		File font_file_reg = new File("assets/Roboto-Light.ttf");
		File font_file_med = new File("assets/Roboto-Medium.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, font_file_reg);
			fontLight = Font.createFont(Font.TRUETYPE_FONT, font_file_light);
			fontMed = Font.createFont(Font.TRUETYPE_FONT, font_file_med);
		} catch (Exception e1) {
			font = Font.getFont("Lucida Sans");
			fontLight = Font.getFont("Lucida Sans");
			fontMed = Font.getFont("Lucida Sans");
		}
		
		//////////////////////////////////////////////////////////////////////////
		
		JPanel keypadPanel = new JPanel();
		keypadPanel.setBackground(Color.WHITE);
		//keypadPanel.setBackground(SystemColor.windowBorder);
		keypadPanel.setBounds(totalWidthOfGrid + padding + 25, 0, 287, 400); //314 - padding, 297 - height
		fullPanel.add(keypadPanel);
		
		GridBagLayout gbl_keypadPanel = new GridBagLayout();
		gbl_keypadPanel.columnWidths = new int[] {100, 100, 100, 100};
		gbl_keypadPanel.rowHeights = new int[] {100, 100, 100, 100};
		gbl_keypadPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_keypadPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		keypadPanel.setLayout(gbl_keypadPanel);

		
		//////////////////////////////////////////////////////////////////////////////////////
		
		JPanel sidebarPanel = new JPanel();
		sidebarPanel.setBackground(Color.WHITE);
		//sidebarPanel.setBackground(SystemColor.windowBorder);
		sidebarPanel.setBounds(826, 0, widthOfSidebar, totalWidthOfGrid);
		fullPanel.add(sidebarPanel);
		
		GridBagLayout gbl_sidebarPanel = new GridBagLayout();
		gbl_sidebarPanel.columnWidths = new int[] {0, 0};
		gbl_sidebarPanel.rowHeights = new int[] {0, 0};
		gbl_sidebarPanel.columnWeights = new double[]{1};
		gbl_sidebarPanel.rowWeights = new double[]{0.1, 0.9};
		sidebarPanel.setLayout(gbl_sidebarPanel);
		
		//////////////////////////////////////////////////////////////////////////////////////////

		JPanel sidebarTopPanel = new JPanel();
		sidebarTopPanel.setBackground(new Color(238,238,238));
		GridBagConstraints gbc_sidebarTopPanel = new GridBagConstraints();
		gbc_sidebarTopPanel.fill = GridBagConstraints.BOTH;
		gbc_sidebarTopPanel.insets = new Insets(0, 0, 5, 0);
		gbc_sidebarTopPanel.gridx = 0;
		gbc_sidebarTopPanel.gridy = 0;
		sidebarPanel.add(sidebarTopPanel, gbc_sidebarTopPanel);		
		
		btnHints = new JButton("Hints");
		btnHints.setBackground(new Color(238,238,238));
		btnHints.setFont(fontMed.deriveFont(24f));
		btnHints.setBorder(BorderFactory.createEmptyBorder());	
		btnHints.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setTab(0);
			}
		});
		
		btnTimer = new JButton("Timer");
		btnTimer.setBackground(new Color(238,238,238));
		btnTimer.setFont(font.deriveFont(24f));
		btnTimer.setBorder(BorderFactory.createEmptyBorder());	
		btnTimer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setTab(1);
			}
		});
		
		btnStats = new JButton("Stats");
		btnStats.setBackground(new Color(238,238,238));
		btnStats.setFont(font.deriveFont(24f));
		btnStats.setBorder(BorderFactory.createEmptyBorder());	
		btnStats.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setTab(2);
			}
		});
		sidebarTopPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		sidebarTopPanel.add(btnHints);
		sidebarTopPanel.add(btnTimer);
		sidebarTopPanel.add(btnStats);
		
		TimerLabel timer = puzzleControl.getTimer();
		timer.setVerticalAlignment(SwingConstants.BOTTOM);
		timer.setFont(font.deriveFont(84f));
		
		sidebarBottomPanel = new JPanel();
		sidebarBottomPanel.setBackground(Color.GRAY);
		GridBagConstraints gbc_sidebarBottomPanel = new GridBagConstraints();
		gbc_sidebarBottomPanel.fill = GridBagConstraints.BOTH;
		gbc_sidebarBottomPanel.gridx = 0;
		gbc_sidebarBottomPanel.gridy = 1;
		sidebarPanel.add(sidebarBottomPanel, gbc_sidebarBottomPanel);		
		sidebarBottomPanel.setLayout(new CardLayout(0, 0));
		
		///////////////////////////////////////////////////////////
		
		tabHints = new JPanel();
		tabHints.setName("tabHints");
		tabHints.setBackground(Color.WHITE);
		//tabs.addTab("HINTS", null, tabHints, null);
		sidebarBottomPanel.add(tabHints, "name_1369870411837636000");
		tabHints.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel lblHint = new JLabel("HINTS");
		lblHint.setBackground(Color.WHITE);
		lblHint.setHorizontalAlignment(SwingConstants.CENTER);
		lblHint.setFont(fontLight.deriveFont(30f));
		tabHints.add(lblHint);
		
		hintLabel = new JLabel("No hints revealed");
		hintLabel.setBackground(Color.WHITE);
		hintLabel.setFont(font.deriveFont(30f));
		hintLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tabHints.add(hintLabel);
		
		JButton btnGetHint = new JButton("Get hint");
		btnGetHint.setBackground(new Color(251,251,251));
		btnGetHint.setFont(font.deriveFont(24f));
		btnGetHint.setBorder(BorderFactory.createEmptyBorder());	
		btnGetHint.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				loadHint(lastPressedCell[1], lastPressedCell[0], pz);
			}
		});
		tabHints.add(btnGetHint);
		
		btnAutofill = new JButton("Autofill a cell");
		btnAutofill.setBackground(new Color(248,248,248));
		btnAutofill.setFont(font.deriveFont(24f));
		btnAutofill.setEnabled(true);
		btnAutofill.setBorder(BorderFactory.createEmptyBorder());		
		btnAutofill.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				autoFill(pz);
			}
		});
		tabHints.add(btnAutofill);
		
		/////////////////////////////////////////////////////////////
		
		tabTimer = new JPanel();
		tabTimer.setName("tabTimer");
		tabTimer.setBackground(Color.WHITE);
		sidebarBottomPanel.add(tabTimer, "name_1369870411857197000");
		//tabs.addTab("TIMER", null, tabTimer, null);
		
		JLabel lblNewLabel = new JLabel("ELAPSED TIME");
		lblNewLabel.setFont(fontLight.deriveFont(30f));
		tabTimer.add(lblNewLabel);
		
		
		//Timer stuff
		JLabel timerExplanation = new JLabel("Minutes : Seconds");
		timerExplanation.setFont(font.deriveFont(24f));
		tabTimer.add(timerExplanation);
		tabTimer.add(timer);		
		
		////////////////////////////////////////////////////////////////
		
		tabStats = new JPanel();
		tabStats.setName("tabStats");
		tabStats.setBackground(Color.WHITE);
		sidebarBottomPanel.add(tabStats, "name_1369870411886906000");
		//tabs.addTab("STATISTICS", null, tabStats, null);
		
		JLabel lblStats = new JLabel("STATISTICS");
		lblStats.setFont(fontLight.deriveFont(30f));
		lblStats.setVerticalAlignment(SwingConstants.TOP);
		tabStats.add(lblStats);
		
		JLabel difficultyStats = new JLabel("Difficulty chosen: " + puzzleControl.getStatistics().difficultyToString());
		difficultyStats.setHorizontalAlignment(SwingConstants.LEFT);
		difficultyStats.setVerticalAlignment(SwingConstants.CENTER);
		difficultyStats.setFont(font.deriveFont(17f));
		tabStats.add(difficultyStats);
		
		actionStats = new JLabel("Number of actions performed: " + puzzleControl.getStatistics().getActionCount());
		actionStats.setHorizontalAlignment(SwingConstants.LEFT);
		actionStats.setVerticalAlignment(SwingConstants.CENTER);
		actionStats.setFont(font.deriveFont(17f));
		tabStats.add(actionStats);
		
		buttonStats = new JLabel("Number of buttons pressed: " + puzzleControl.getStatistics().getButtonCount());
		buttonStats.setHorizontalAlignment(SwingConstants.LEFT);
		buttonStats.setVerticalAlignment(SwingConstants.CENTER);
		buttonStats.setFont(font.deriveFont(17f));
		tabStats.add(buttonStats);
		
		winStats = new JLabel("Number of wins this session: " + puzzleControl.getStatistics().getWinCount());
		winStats.setHorizontalAlignment(SwingConstants.LEFT);
		winStats.setVerticalAlignment(SwingConstants.CENTER);
		winStats.setFont(font.deriveFont(17f));
		tabStats.add(winStats);
		
		cheatStats = new JLabel("Number of times you've cheated: " + puzzleControl.getStatistics().getCheatCount());
		cheatStats.setHorizontalAlignment(SwingConstants.LEFT);
		cheatStats.setVerticalAlignment(SwingConstants.CENTER);
		cheatStats.setFont(font.deriveFont(17f));
		tabStats.add(cheatStats);
		
		hintStats = new JLabel("Number of hints requested: " + puzzleControl.getStatistics().getHintCount());
		hintStats.setHorizontalAlignment(SwingConstants.LEFT);
		hintStats.setVerticalAlignment(SwingConstants.CENTER);
		hintStats.setFont(font.deriveFont(17f));
		tabStats.add(hintStats);
		
		progressStats = new JLabel("Your progress: " + puzzleControl.getStatistics().getProgressCount() + " correct");
		progressStats.setHorizontalAlignment(SwingConstants.LEFT);
		progressStats.setVerticalAlignment(SwingConstants.CENTER);
		progressStats.setFont(font.deriveFont(17f));
		tabStats.add(progressStats);
		
		//////////////////////////////////////////////////////////////////
		
		JPanel gridPanel = new JPanel();
		gridPanel.setBackground(new Color(238,238,238));
		gridPanel.setBounds(15 + padding, padding, padding + (numberOfRows * widthBetweenTextBoxes) + textboxWidth, padding + (numberOfRows * widthBetweenTextBoxes) + textboxWidth);
		fullPanel.add(gridPanel);
		gridPanel.setLayout(new GridLayout(9, 9));

		/////////////////////////////////////////////////////////////////////////
		
		setupKeypad(keypadPanel, puzzleControl);
		
		JButton btnClearCell = new JButton("CLEAR");
		btnClearCell.setBackground(new Color(251,251,251));
		btnClearCell.setFont(font.deriveFont(24f));
		btnClearCell.setBorder(BorderFactory.createEmptyBorder());	
		btnClearCell.setVisible(false);
		btnClearCell.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				clearCell(lastPressedCell[1], lastPressedCell[0], pz);
			}
		});
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 3;
		keypadPanel.add(btnClearCell, gbc_btnNewButton);
		
		///////////////////////////////////////////////////////////////////////////
		
		setupCells(gridPanel, puzzleControl);
	}
	
	private void setTab(int tabIndex)
	{
		switch(tabIndex)
		{
			case 0:
				tabHints.setVisible(true);
				tabTimer.setVisible(false);
				tabStats.setVisible(false);
				btnHints.setFont(fontMed.deriveFont(24f));
				tabTimer.setFont(font.deriveFont(24f));
				tabStats.setFont(font.deriveFont(24f));
				break;
			case 1:
				tabHints.setVisible(false);
				tabTimer.setVisible(true);
				tabStats.setVisible(false);
				btnHints.setFont(font.deriveFont(24f));
				tabTimer.setFont(fontMed.deriveFont(24f));
				tabStats.setFont(font.deriveFont(24f));
				break;
			case 2:	
				tabHints.setVisible(false);
				tabTimer.setVisible(false);
				tabStats.setVisible(true);
				btnHints.setFont(font.deriveFont(24f));
				tabTimer.setFont(font.deriveFont(24f));
				tabStats.setFont(fontMed.deriveFont(24f));
				break;
		}		
	}
	/**
	 * @param thisX
	 * @param thisY
	 * @param pz
	 */
	private void cellClicked(int row, int col, PuzzleControl pz)
	{
		if (lastPressedCell[0] >= 0 && lastPressedCell[1] >= 0)
		{
			setCellFont(lastPressedCell[1], lastPressedCell[0], false);
		}
		if (!pz.getCell(row, col).isFixed())
    	{
			lastPressedCell[1] = row;
			lastPressedCell[0] = col;
			setCellFont(row, col, true);
    	}
		incrementNumButtons(pz, 1);
		buttonStats.setText("Number of buttons pressed: " + pz.getStatistics().getButtonCount());
	}
	
	/**
	 * @param thisX
	 * @param thisY
	 * @param pz
	 */
	private void keyPressed(int row, int col, PuzzleControl pz)
	{
		if (keypadButtons.get(row).get(col).getText().length() > 0 && lastPressedCell[0] >= 0 && lastPressedCell[1] >= 0)
		{  
			pz.setCell(lastPressedCell[1], lastPressedCell[0], Integer.valueOf(keypadButtons.get(row).get(col).getText()));

			if (pz.getCell(lastPressedCell[1], lastPressedCell[0]).getNumber() != null)
			{
				cells.get(lastPressedCell[1]).get(lastPressedCell[0]).setText(Integer.toString(pz.getCell(lastPressedCell[1], lastPressedCell[0]).getNumber()));
			}
			  
			setDEBUGCellColour(lastPressedCell[1],lastPressedCell[0], pz);
			  
			checkIfPuzzleComplete(pz);
		}
		  
		incrementNumActions(pz, 1);
		incrementNumButtons(pz, 1);
		actionStats.setText("Number of actions performed: " + pz.getStatistics().getActionCount());
		buttonStats.setText("Number of buttons pressed: " + pz.getStatistics().getButtonCount());
	}
	
	/**
	 * @param keypadPanel
	 * @param puzzleControl
	 */
	private void setupKeypad(JPanel keypadPanel, PuzzleControl puzzleControl)
	{
		keypadButtons = new ArrayList<ArrayList<JButton>>();
		for (int y = 0; y < 3; y++)
		{
			final int row = y;
			keypadButtons.add(new ArrayList<JButton>());
			for (int x = 0; x < 3; x++)
			{
				final int col = x;
				final PuzzleControl pz = puzzleControl;
				
				keypadButtons.get(row).add(new JButton(String.valueOf(((row * 3) + (col + 1)))));	// set value of number according to position (like telephone buttons
				keypadButtons.get(row).get(col).setFont(font.deriveFont(40f));	
				keypadButtons.get(row).get(col).setSize(50, 50);
				keypadButtons.get(row).get(col).setBackground(Color.WHITE);
				keypadButtons.get(row).get(col).setForeground(new Color(100,100,100));
				keypadButtons.get(row).get(col).setBorder(BorderFactory.createEmptyBorder());
				
				GridBagConstraints gbc_button = new GridBagConstraints();
				gbc_button.fill = GridBagConstraints.BOTH;
				gbc_button.insets = new Insets(0, 0, 5, 5);
				gbc_button.gridx = col;
				gbc_button.gridy = row;
				
				keypadButtons.get(row).get(col).addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						keyPressed(row, col, pz);
					}
				});
				
				keypadPanel.add(keypadButtons.get(row).get(col), gbc_button);
			}
		}
	}
	
	/**
	 * @param gridPanel
	 * @param puzzleControl
	 */
	private void setupCells(JPanel gridPanel, PuzzleControl puzzleControl)
	{
		cells = new ArrayList<ArrayList<JButton>>();
		for (int y = 0; y < numberOfRows; y++)
		{
			final int row = y;
			cells.add(new ArrayList<JButton>());
			for (int x = 0; x < numberOfRows; x++)
			{
				final int col = x;
				final PuzzleControl pz = puzzleControl;
				
				cells.get(row).add(new JButton("0"));
				cells.get(row).get(col).setFont(font.deriveFont(16f));				
				cells.get(row).get(col).setBounds(padding + (col * widthBetweenTextBoxes), padding + (row * widthBetweenTextBoxes), textboxWidth, textboxWidth);
				cells.get(row).get(col).setBorder(BorderFactory.createEmptyBorder());
				
				GridBagConstraints gbc_button = new GridBagConstraints();
				gbc_button.fill = GridBagConstraints.BOTH;
				gbc_button.insets = new Insets(5,5,5,5);
				gbc_button.gridx = col;
				gbc_button.gridy = row;
				
				setCellNumber(row, col, puzzleControl);		
				
				cells.get(row).get(col).addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						cellClicked(row, col, pz);
						setCellNumber(row, col, pz);
					}
			    });
				
				//setCellColour(row, col, puzzleControl);				
				gridPanel.add(cells.get(y).get(x), gbc_button);
			}
		}	
	}
	
	/**
	 * @param row
	 * @param col
	 * @param isBold
	 */
	private void setCellFont(int row, int col, boolean isBold)
	{
		if (isBold)
		{
			cells.get(row).get(col).setFont(font.deriveFont(32f));
		}
		else
		{
			cells.get(row).get(col).setFont(font.deriveFont(19f));
		}
	}
	/**
	 * @param row
	 * @param col
	 * @param puzzleControl
	 */
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
	
	/**
	 * @param row
	 * @param col
	 * @param puzzleControl
	 */
	private void setCellColour(int row, int col, PuzzleControl puzzleControl)
	{
		if (puzzleControl.getCell(row, col).isFixed())
		{
			System.out.println("F: " + row + ", " + col);
			cells.get(row).get(col).setEnabled(false);
			cells.get(row).get(col).setForeground(Color.DARK_GRAY);	
			cells.get(row).get(col).setBackground(new Color(238, 238, 238));	// gray
		}
		else
		{
			System.out.println("SETWHITE: " + row + ", " + col);
			cells.get(row).get(col).setBackground(Color.WHITE);			
			incrementTotalEmptyBoxes(puzzleControl, 1);
		}		
	}
	
	/**
	 * @param row
	 * @param col
	 * @param puzzleControl
	 */
	private void setDEBUGCellColour(int row, int col, PuzzleControl puzzleControl)
	{
		if (puzzleControl.checkNumberSolution(row, col))
		{
			cells.get(row).get(col).setBackground(new Color(150,255,147));	// green, but a nicer shade
			
			incrementNumProgress(puzzleControl, 1);
			progressStats.setText("Your progress: " + puzzleControl.getStatistics().getProgressCount() + " correct");
		}
		else
		{
			cells.get(row).get(col).setBackground(new Color(255,171,171));	// red, but a nicer shade
	    }		
	}
	
	/**
	 * @param row
	 * @param col
	 * @param puzzleControl
	 */
	private void loadHint(int row, int col, PuzzleControl puzzleControl) 
	{
		if (row >= 0 && col >= 0)
		{
			// changed this to make it more fair, users cannot just simply get solutions but only possibilities
			// and even that only if they've not filled in a cell, so the chance of reaching a dead end is larger now
			if (!puzzleControl.getCell(row, col).isEmpty())
			{
				hintLabel.setText("Cell already filled");
			}
			else
			{
				hintLabel.setText("Possible: " + puzzleControl.getCell(row, col).getPossibilities());
			}
			incrementNumHints(puzzleControl, 1);
		}
		else
		{
			hintLabel.setText("No hint available");
		}	
		
		hintStats.setText("Hints requested: " + puzzleControl.getStatistics().getHintCount());
	}
	
	/**
	 * @param puzzleControl
	 */
	private void autoFill(PuzzleControl puzzleControl)
	{
		int[] values = puzzleControl.getFirstEmptyCellCoordinates();
		if (values != null)
		{
			puzzleControl.setCell(values[0], values[1], values[2]);
			cellClicked(values[0], values[1], puzzleControl);
			setCellNumber(values[0], values[1], puzzleControl);
	  		setDEBUGCellColour(values[0], values[1], puzzleControl);
	  		if (checkIfPuzzleComplete(puzzleControl))
	  		{
	  			if (checkIfPuzzleComplete(puzzleControl))
		  		{
					btnAutofill.setEnabled(false);
		  		}
	  		}
			incrementNumCheat(puzzleControl, 1);
			cheatStats.setText("Number of autofills: " + puzzleControl.getStatistics().getCheatCount());
		}
		else
		{
			btnAutofill.setEnabled(false);
		}
	}
	
	/**
	 * @param puzzleControl
	 * @return
	 */
	private boolean checkIfPuzzleComplete(PuzzleControl puzzleControl)
	{
		if (puzzleControl.boardIsValid())
		{
			incrementNumWins(puzzleControl, 1);
			winStats.setText("Wins: " + puzzleControl.getStatistics().getWinCount());
			
			JOptionPane.showMessageDialog(this, "A WINNER IS YOU");
			startNewGame(puzzleControl);
			return true;
		}
		return false;
	}

	private void clearCell(int row, int col, PuzzleControl pz)
	{
		if (lastPressedCell[0] >= 0 && lastPressedCell[1] >= 0)
		{
			pz.setCell(row, col, -1);
			setCellNumber(row, col, pz);
		}
	}
	
	/**
	 * Extensible setters woot
	 * @param pz
	 * @param value
	 */
	public void incrementNumActions(PuzzleControl pz, int value)
	{
		pz.getStatistics().setActionCount(pz.getStatistics().getActionCount() + value);
	}

	public void incrementNumButtons(PuzzleControl pz, int value)
	{
		pz.getStatistics().setButtonCount(pz.getStatistics().getButtonCount() + value);
	}
	
	public void incrementNumCheat(PuzzleControl pz, int value)
	{
		pz.getStatistics().setCheatCount(pz.getStatistics().getCheatCount() + value);
	}

	public void incrementNumWins(PuzzleControl pz, int value)
	{
		pz.getStatistics().setWinCount(pz.getStatistics().getWinCount() + value);
	}

	public void incrementNumHints(PuzzleControl pz, int value)
	{
		pz.getStatistics().setHintCount(pz.getStatistics().getHintCount() + value);
	}	
	
	public void incrementNumProgress(PuzzleControl pz, int value)
	{
		pz.getStatistics().setProgressCount(pz.getStatistics().getProgressCount() + value);
	}	
	
	public void incrementTotalEmptyBoxes(PuzzleControl pz, int value)
	{
		pz.getStatistics().setProgressCount(pz.getStatistics().getTotalEmptyBoxesCount() + value);
	}	
}