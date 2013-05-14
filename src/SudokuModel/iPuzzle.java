package SudokuModel;

/**
 * This is an abstract interface for the implementation of the Puzzle class which contains 9 grids of cells, 
 * arranged in a 3x3 formation. These grids themselves contain 9 cells, arranged in a 3x3 formation as well.
 * Together, they compose the structure of a standard 9x9-cell Sudoku puzzle.
 * @author Nicholas Ho, Ryan Tan, Nikhil Suresh
 */
public interface iPuzzle {
	/**
	 * getCell is called as a getter for the cell that is located at a provided row and column position, 
	 * relative to the <b>puzzle</b> that contains it.
	 * @param row The numerical row position of the desired cell. Ranges from 0 to 8.
	 * @param column The numerical column position of the desired cell. Ranges from 0 to 8.
	 * @return The <b>Cell</b> column position of the cell, relative to the 3x3 grid containing it.
	 */
	public Cell getCell(int row, int column);
	/**
	 * getDifficulty is called as a getter for the difficulty of a particular puzzle.
	 * @return numerical <b>integer</b> value for difficulty of the puzzle.
	 */
	public int getDifficulty();
	/**
	 * isSolved is a boolean that determines if an entire puzzle has been solved.
	 * @return <b>true</b> if all cells have been correctly guessed and/or revealed. <b>false</b> if not all cells
	 * have been correctly guessed and/or revealed. 
	 */
	public boolean isSolved();
	/**
	 * getRegion is called as a getter for the region that is located at a provided row and column position,
	 * relative to the <b>Puzzle</b> that contains it.
	 * @param row The numerical row position of the desired cell. Ranges from 0 to 2.
	 * @param column The numerical column position of the desired cell. Ranges from 0 to 2.
	 * @return The <b>Cell</b>, located at a provided row and column.
	 */
	public Region getRegion(int column, int row);
}
