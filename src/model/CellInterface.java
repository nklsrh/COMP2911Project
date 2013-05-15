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
<<<<<<< HEAD
	/**
	 * getColumn is called as a getter for the column (vertical) position of the cell, with respect to the 3x3 grid 
	 * that it is contained in. column numbers start from 0 at index position 0, and increment downwards.
	 * 
	 * 0 |
	 * --+
	 * 1 |
	 * --+
	 * 2 |
	 * 
	 * @return The <b>integer</b> column position of the cell, relative to the 3x3 grid containing it.
	 */
=======
	
>>>>>>> master
	
	public boolean isFixed();
	
}
