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
	private FrameObjects f;
	
	/**
	 * Contains coordinates for X,Y of last pressed cell button (so we know what cell to change)
	 * first element is X value (column)
	 */
	private int[] lastPressedCell;
	private PuzzleControl puzzleControl;
	
	/**
	 * Create the frame.
	 */
	public FrameMain(PuzzleControl puzzleControl) 
	{
		this.puzzleControl = puzzleControl;
		
		f = new FrameObjects();
		setDefaultWindowLayout();
		f.setFrameLayoutSizes(9, 6, 28, 31, 300, 300);
		
		f.createBasePanels(this);
		f.createKeypad(this);

		lastPressedCell = new int[2];
		lastPressedCell[0] = -1;
		lastPressedCell[1] = -1;			

		/////////////////////////////////////////////////////////////////////////	
		
	}
	
	public void keypadPressed(ActionEvent e, int thisX, int thisY, ArrayList<ArrayList<JButton>> keypadButtons, ArrayList<ArrayList<JButton>> boxes) 
	{
//		System.out.println(keypadButtons.get(thisY).get(thisX).getText().length() + " " + lastPressedCell[0] + " " + lastPressedCell[1]);
		  if (keypadButtons.get(thisY).get(thisX).getText().length() > 0 && lastPressedCell[0] >= 0 && lastPressedCell[1] >= 0)
		  {
//		      System.out.println("COL:" + lastPressedCell[0] + ", ROW: " + lastPressedCell[1]);
		      
//		      System.out.println("CELL:"+pz.getCell(lastPressedCell[1], lastPressedCell[0]).getNumber() + " SET: " + Integer.valueOf(keypadButtons.get(thisY).get(thisX).getText()));

			  puzzleControl.setCell(lastPressedCell[1], lastPressedCell[0], Integer.valueOf(keypadButtons.get(thisY).get(thisX).getText()));

			  
//		      System.out.println("CELL:"+pz.getCell(lastPressedCell[1], lastPressedCell[0]).getNumber());
//		      System.out.println("SOLN:"+pz.getCell(lastPressedCell[1], lastPressedCell[0]).getSolution());
//		      
//		      System.out.println("COL:" + pz.getColumnList().get(lastPressedCell[0]));
//			  System.out.println("ROW:" + pz.getRowList().get(lastPressedCell[1]));
//			  System.out.println("GRID:" + pz.getGrid((lastPressedCell[1]/3 % 3 * 3 + lastPressedCell[0]/3 % 3 + 1) - 1));
			  
			  if (puzzleControl.getCell(lastPressedCell[1], lastPressedCell[0]).getNumber() != null)
				  boxes.get(lastPressedCell[1]).get(lastPressedCell[0]).setText(Integer.toString(puzzleControl.getCell(lastPressedCell[1], lastPressedCell[0]).getNumber()));
			  
			    if (puzzleControl.getCell(lastPressedCell[1], lastPressedCell[0]).getNumber() == puzzleControl.getCell(lastPressedCell[1],  lastPressedCell[0]).getSolution())
				{
					boxes.get(lastPressedCell[1]).get(lastPressedCell[0]).setForeground(Color.GREEN);
				}
			    else
			    {
					boxes.get(lastPressedCell[1]).get(lastPressedCell[0]).setForeground(Color.RED);
			    }
		  }
	}
	
	public void cellPressed(ActionEvent e, int thisX, int thisY)
	{
		if (!puzzleControl.getCell(thisY, thisX).isFixed())
    	{
			lastPressedCell[1] = thisY;
			lastPressedCell[0] = thisX;
    	}
	}

	
	private void setDefaultWindowLayout()
	{
		setResizable(false);
		setBackground(SystemColor.window);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(this, 300, 100);
	}
	
	
}