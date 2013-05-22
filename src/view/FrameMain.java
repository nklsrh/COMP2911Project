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
		
		
		boxes = new ArrayList<ArrayList<JTextField>>();
		for (int y = 0; y < numberOfRows; y++)
		{
			final int thisY = y;
			boxes.add(new ArrayList<JTextField>());
			for (int x = 0; x < numberOfRows; x++)
			{
				final int thisX = x;
				boxes.get(y).add(new JTextField(1));
				boxes.get(y).get(x).setBounds(padding + (x * widthBetweenTextBoxes), padding + (y * widthBetweenTextBoxes), textboxWidth, textboxWidth);
				boxes.get(y).get(x).setColumns(1);
				gridPanel.add(boxes.get(y).get(x));
				final Puzzle pz = puzzle;
				boxes.get(y).get(x).addCaretListener(new CaretListener(){
			      public void caretUpdate(CaretEvent e) {
			    	  if (boxes.get(thisY).get(thisX).getText().length() > 0)
			    	  {
				    	pz.setCell(thisX, thisY, Integer.valueOf(boxes.get(thisY).get(thisX).getText()));
				        System.out.println(e + ", at " + thisX + "," + thisY);
			    	  }
			      }
			    });
				//System.out.println(puzzle.getCell(x, y));
				boxes.get(y).get(x).setText(Integer.toString(puzzle.getCell(x, y).getNumber()));
			}
		}
	}

}