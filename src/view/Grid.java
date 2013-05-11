package view;

/**
 * This is an abstract interface for the implementation of the Grid class which contains 9 cells in a 3x3 grid 
 * formation for the purposes of a Sudoku game. It has coordinates for its position within yet another 3x3 Grid 
 * of Grid objects. Together, the 9 grids with the cells that they contain make up the entire Sudoku Puzzle.
 * @author Nicholas Ho, Ryan Tan, Nikhil Suresh
 */
public interface Grid {
	/**
	 * getCell is called as a getter for the cell that is located at a provided row and column position,
	 * relative to the <b>grid</b> that contains it.
	 * @param row The numerical row position of the desired cell. Ranges from 0 to 2.
	 * @param column The numerical column position of the desired cell. Ranges from 0 to 2.
	 * @return The <b>Cell</b>, located at a provided row and column.
	 */
	public Cell getCell(int row, int column);
	/**
	 * getColumn is called as a getter for the column position of the grid, with respect to the 3x3 grid of Grid
	 * objects that it is contained in.
	 * @return The <b>integer</b> column position of the grid, relative to the 3x3 grid of <b>Grids</b> containing it.
	 */
	public int getColumnPos();
	/**
	 * getRow is called as a getter for the row position of the cell, with respect to the 3x3 grid that it is 
	 * contained in.
	 * @return The <b>integer</b> row position of the grid, relative to the 3x3 grid of <b>Grids</b> containing it.
	 */
	public int getRowPos();
}
