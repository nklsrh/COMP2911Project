package model;

/**
 * This is an abstract interface for the implementation of the Puzzle class which contains 9 grids of cells, 
 * arranged in a 3x3 formation. These grids themselves contain 9 cells, arranged in a 3x3 formation as well.
 * Together, they compose the structure of a standard 9x9-cell Sudoku puzzle.
 * @author Nicholas Ho, Ryan Tan, Nikhil Suresh
 */
public interface PuzzleInterface {
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
	 * @return the grid located at a certain index in the Puzzle.
	 */
	public Grid getGrid(int gridIndex);
}
