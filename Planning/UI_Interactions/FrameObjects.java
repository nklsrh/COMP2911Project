package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.PuzzleControl;

import model.Puzzle;

public class FrameObjects {

	JPanel contentPane;
	JPanel fullPanel;
	JPanel keypadPanel;
	
	JPanel sidebarPanel;
	JTabbedPane tabs;
	JPanel tabTimer;
	JPanel tabStats;
	JPanel tabHints;
	JPanel gridPanel;
	
	
	private ArrayList<ArrayList<JButton>> cells;
	private ArrayList<ArrayList<JButton>> keypadButtons;

	private int numberOfRows;
	private int padding;
	private int textboxWidth;
	private int widthBetweenTextBoxes;
	private int totalWidthOfGrid;
	private int widthOfSidebar;
	private int widthOfKeypad;
	
	public void setBounds(FrameMain f, int width, int height)
	{
		f.setBounds(width, height, totalWidthOfGrid + widthOfSidebar + widthOfKeypad, totalWidthOfGrid + textboxWidth);
	}
	
	public void setFrameLayoutSizes(int numberOfRows, int padding, int textboxWidth, int widthBetweenTextBoxes, int widthOfSidebar, int widthOfKeypad)
	{
		this.numberOfRows = 9;
		this.padding = 6;
		this.textboxWidth = 28;
		this.widthBetweenTextBoxes = 31;
		this.widthOfSidebar = 300;
		this.widthOfKeypad = 300;
		this.totalWidthOfGrid = padding + (numberOfRows * widthBetweenTextBoxes) + textboxWidth;
	}
	
	public void createBasePanels(FrameMain f)
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));		
		
		fullPanel = new JPanel();
		fullPanel.setLayout(null);	
		
		contentPane.add(fullPanel);		
		f.setContentPane(contentPane);
	}
	
	public void createKeypad(FrameMain f)
	{
		keypadPanel = new JPanel();
		keypadPanel.setBackground(SystemColor.windowBorder);
		keypadPanel.setBounds(314, 6, 287, 297);
		fullPanel.add(keypadPanel);
		
		GridBagLayout gbl_keypadPanel = new GridBagLayout();
		gbl_keypadPanel.columnWidths = new int[] {95, 95, 95, 0};
		gbl_keypadPanel.rowHeights = new int[] {100, 100, 100, 0};
		gbl_keypadPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_keypadPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		keypadPanel.setLayout(gbl_keypadPanel);
		
		final ArrayList<ArrayList<JButton>> keypadButtons = new ArrayList<ArrayList<JButton>>();
		for (int y = 0; y < 3; y++)
		{
			final int thisY = y;
			keypadButtons.add(new ArrayList<JButton>());
			for (int x = 0; x < 3; x++)
			{
				final int thisX = x;
		    	final FrameMain fr = f;
		    	
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
					fr.keypadPressed(e, thisX, thisY, keypadButtons, cells);
				}
				});
			}
		}
	}
	
	public void createSidebar(FrameMain f)
	{
		sidebarPanel = new JPanel();
		sidebarPanel.setBackground(SystemColor.windowBorder);
		sidebarPanel.setBounds(totalWidthOfGrid + widthOfKeypad, 0, widthOfSidebar, totalWidthOfGrid);
		fullPanel.add(sidebarPanel);
		sidebarPanel.setLayout(null);
		
		tabs = new JTabbedPane(JTabbedPane.TOP);
		tabs.setBounds(0, 6, 288, 301);
		sidebarPanel.add(tabs);
		
		tabTimer = new JPanel();
		tabs.addTab("TIMER", null, tabTimer, null);
		
		JLabel lblNewLabel = new JLabel("TIME SINCE PUZZLE STARTED");
		tabTimer.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		tabStats = new JPanel();
		tabs.addTab("STATISTICS", null, tabStats, null);
		
		JLabel lblStats = new JLabel("STATISTICS");
		lblStats.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		tabStats.add(lblStats);
		lblStats.setVerticalAlignment(SwingConstants.TOP);
		
		tabHints = new JPanel();
		tabs.addTab("HINTS", null, tabHints, null);
		
		JLabel lblHint = new JLabel("HINTS");
		lblHint.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		tabHints.add(lblHint);
		lblHint.setVerticalAlignment(SwingConstants.TOP);
		
		gridPanel = new JPanel();
		gridPanel.setBounds(padding, padding, padding + (numberOfRows * widthBetweenTextBoxes) + textboxWidth, padding + (numberOfRows * widthBetweenTextBoxes) + textboxWidth);
		fullPanel.add(gridPanel);
		gridPanel.setLayout(null);
	}
	
	public void createCells(PuzzleControl puzzleControl)
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
				
				if (puzzleControl.getCell(y, x).isFixed())
				{
					cells.get(y).get(x).setForeground(Color.BLUE);
				}
				else
				{
					cells.get(y).get(x).setForeground(Color.BLACK);
				}
				
				gridPanel.add(cells.get(y).get(x));
				
				setCellPosition(y, x);
				
				cells.get(y).get(x).addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						
					}
			    });
				
				if (!puzzleControl.getCell(y, x).isEmpty())
				{
					cells.get(y).get(x).setText(Integer.toString(puzzleControl.getCell(y, x).getNumber()));
				}
				else
				{
					cells.get(y).get(x).setText("0");
				}
			}
		}
	}

	private void setCellPosition(int row, int col)
	{
		cells.get(row).get(col).setBounds(padding + (col * widthBetweenTextBoxes), padding + (row * widthBetweenTextBoxes), textboxWidth, textboxWidth);
	}
}
