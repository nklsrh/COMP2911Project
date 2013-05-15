package model;
/**
 * This is an abstract interface for the implementation of the Cell class which contains a number for the purposes
 * of a Sudoku game, and has coordinates for its position within a 3x3 Grid within a Sudoku puzzle.
 * @author Nicholas Ho, Ryan Tan, Nikhil Suresh
 */
public interface iCell {
	/**
	 * getNumber is called as a getter for the number contained in the cell (from 1-9), for the purposes of a 
	 * Sudoku game. If there is no contained number, it shall be 0.
	 * @return The <b>integer</b> number contained in the cell. If there is no number, return 0.
	 */
	public int getNumber();
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
	public int getColumnPos();
	/**
	 * getRow is called as a getter for the row (horizontal) position of the cell, with respect to the 3x3 grid 
	 * that it is contained in. Row numbers start from 0 at index position 0, and increment towards the right.
	 * 
	 * 0 | 1 | 2
	 * --+---+--
	 * 
	 * @return The <b>integer</b> row position of the cell, relative to the 3x3 grid containing it.
	 */
	public int getRowPos();
	/**
	 * getIndex is called as a getter for the index position of a cell within a region. They are as arranged:
	 * 
	 * 0 | 1 | 2
	 * --+---+--
	 * 3 | 4 | 5
	 * --+---+--
	 * 6 | 7 | 8
	 * 
	 * @return the index position of a particular cell.
	 */
	public int getIndex();
	/**
	 * isSolved is a boolean that determines if a cell has been solved. (Number has been revealed and is correct)
	 * @return <b>true</b> if the number has been revealed and is correct, and <b>false</b> if the number has not 
	 * been revealed OR the number has been guessed but is wrong.
	 */	
	public Boolean isSolved();
	/**
	 * isFilled is a boolean that determines if a cell has been filled (Number has been entered, not necessarily
	 * correct)
	 * @return <b>true</b> if the cell currently contains a number, and <b>false</b> if the number has not 
	 * been revealed OR the number has been guessed but is wrong.
	 */
	public Boolean isFilled();
	/**
	 * isWrong is a boolean that determines if a cell that has been filled has in fact been done so wrongly.
	 * @return <b>false</b> if the cell currently contains a number and isSolved is true. <b>true</b> otherwise.
	 */
	public Boolean isWrong();
}
