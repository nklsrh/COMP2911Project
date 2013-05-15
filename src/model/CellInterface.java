package model;
/**
 * This is an abstract interface for the implementation of the Cell class which contains a number for the purposes
 * of a Sudoku game, and has coordinates for its position within a 3x3 Grid within a Sudoku puzzle.
 * @author Nicholas Ho, Ryan Tan, Nikhil Suresh
 */
public interface CellInterface {
	/**
	 * getNumber is called as a getter for the number contained in the cell (from 1-9), for the purposes of a 
	 * Sudoku game. If there is no contained number, it shall be 0.
	 * @return The <b>integer</b> number contained in the cell. If there is no number, return 0.
	 */
	public int getNumber();
	
	
	public boolean isFixed();
	
}
