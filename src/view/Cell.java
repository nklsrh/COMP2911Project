package view;
/**
 * This is an abstract interface for the implementation of the Cell class which contains a number for the purposes
 * of a Sudoku game, and has coordinates for its position within a 3x3 Grid within a Sudoku puzzle.
 * @author Nicholas Ho, Ryan Tan, Nikhil Suresh
 */
public interface Cell {
	/**
	 * getNumber is called as a getter for the number contained in the cell, for the purposes of a Sudoku game.
	 * @return The <b>integer</b> number contained in the cell.
	 */
	public int getNumber();
	/**
	 * getColumn is called as a getter for the column position of the cell, with respect to the 3x3 grid that it
	 * is contained in.
	 * @return The <b>integer</b> column position of the cell, relative to the 3x3 grid containing it.
	 */
	public int getColumnPos();
	/**
	 * getRow is called as a getter for the row position of the cell, with respect to the 3x3 grid that it is 
	 * contained in.
	 * @return The <b>integer</b> row position of the cell, relative to the 3x3 grid containing it.
	 */
	public int getRowPos();
	/**
	 * isSolved is a boolean that determines if a cell has been solved. (Number has been revealed and is correct)
	 * @return <b>true</b> if the number has been revealed and is correct, and <b>false</b> if the number has not 
	 * been revealed OR the number has been guessed but is wrong.
	 */
	public Boolean isSolved();
}
