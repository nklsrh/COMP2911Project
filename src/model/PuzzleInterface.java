package model;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * This is an abstract interface for the implementation of the Puzzle class. Programming by Contract,
 * this interface is the contract for the expected methods, getters and setters to tie Model with the other 
 * packages of View and Controller with expected methods during dev.
 * 
 * @author Nicholas Ho, Ryan Tan, Nikhil Suresh
 */
public interface PuzzleInterface {
	
	
	public ArrayList<Grid> getGridList();
	
	public ArrayList<Row> getRowList();
	
	public ArrayList<Column> getColumnList();
	
	public void setCell(int row, int column, int value);
	
	public void updatePossibilities(int row, int column, int value);
	
	public void setCellAsEmpty(int row, int column);
	
	public void setRow(int index, ArrayList<Cell> rowValues);
	
	/**
	 * getCell is called as a getter for the cell that is located at a provided row and column position, 
	 * relative to the <b>puzzle</b> that contains it.
	 * @param row The numerical row position of the desired cell. Ranges from 0 to 8.
	 * @param column The numerical column position of the desired cell. Ranges from 0 to 8.
	 * @return The <b>Cell</b> column position of the cell, relative to the 3x3 grid containing it.
	 */
	public Cell getCell(int row, int column);

	
	/**
	 * A getter for a Grid object in the puzzle, given its index.
	 *
	 * @param gridIndex the grid index
	 * @return the grid located at a certain index in the Puzzle.
	 */
	public Grid getGrid(int gridIndex);
}
