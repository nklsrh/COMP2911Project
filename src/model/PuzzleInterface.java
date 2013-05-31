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
	
	/**
	 * A getter for the arrayList of grids
	 * @return the arrayList of Grid objects
	 */
	public ArrayList<Grid> getGridList();
	
	/**
	 * A getter for the arrayList of rows.
	 * @return the arrayList of Row objects
	 */
	public ArrayList<Row> getRowList();
	
	/**
	 * A getter for the arrayList of columns.
	 * @return the arrayList of Column objects
	 */
	public ArrayList<Column> getColumnList();
	
	
	/**
	 * A getter for a Cell object in the puzzle, located at a given set of coordinates.
	 *
	 * @param row the row
	 * @param column the column
	 * @return the cell located at the given coordinates if it is present, and return null otherwise.
	 */
	public Cell getCell(int row, int column);

	
	/**
	 * A getter for a Grid object in the puzzle, given its index.
	 *
	 * @param gridIndex the grid index
	 * @return the grid located at a certain index in the Puzzle.
	 */
	public Grid getGrid(int gridIndex);
	
	
	/**
	 * Based on the x,y coordinates within the row and column, sets the cell
	 * up with the new value.
	 * 
	 * @param row the y-coordinate of the Cell to be set.
	 * @param column the x-coordinate of the Cell to be set.
	 * @param value the number that is to be given as the value of the cell.
	 */
	public void setCell(int row, int column, int value);
	
	
	/**
	 * This method sets a cell at a particular set of coordinates as empty 
	 * i.e. has no value, and all flags disabled.
	 * 
	 * @param row the y-coordinate of the Cell to be set as empty
	 * @param column the x-coordinate of the Cell to be set as empty
	 */
	public void setCellAsEmpty(int row, int column);
	
	
	/**
	 * A setter for the values of Cells in a Row object, given an index for 
	 * a Grid object and an arrayList of Cells containing the values for the row to be set.
	 * 
	 * @param index the position of the Grid whose values are to be set
	 * @param rowValues the values that are to be set to the Row.
	 */
	public void setRow(int index, ArrayList<Cell> rowValues);
}
