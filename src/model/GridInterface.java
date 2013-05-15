package model;

/**
 * This is an abstract interface for the implementation of the Region class which contains 9 cells in a 3x3 grid 
 * formation for the purposes of a Sudoku game. It has coordinates for its position within yet another 3x3 grid 
 * of Region objects. Together, the 9 regions with the cells that they contain make up the entire Sudoku Puzzle.
 * @author Nicholas Ho, Ryan Tan, Nikhil Suresh
 */
public interface GridInterface {
	/**
	 * addCell is called as a method during initialisation of the Sudoku game board, where cells are to be added
	 * to its parent region. A containing number, column, row and index positions as well as its "solved" status
	 * are required arguments.
	 * @param number the number to be contained in the cell (1-9). If empty, it is 0.
	 * @param column The numerical column position of the desired cell. Ranges from 0 to 2.
	 * @param row The numerical row position of the desired cell. Ranges from 0 to 2.
	 * @param index the numerical index position of the desired cell. Ranges from 0 to 8.
	 * @param isSolved A boolean, which is <b>true</b> if the cell contains a correct number and <b>false</b> 
	 * otherwise.
	 */
	
	public Cell getCell(int column, int row);
	/**
	 * getCell is called as an alternative getter for the cell that is located at a provided index, relative to 
	 * the <b>Region</b> that contains it.
	 * @param index the index of a particular cell
	 * @return The <b>Cell</b>, located at a provided index position.
	 */
	public Cell getCell(int index);
	
	
	public int getGridIndex();
	/**
	 * contains is called as a boolean to determine if a region contains a cell with a particular number.
	 * @return <b>true</b> if a cell exists within the region, that contains the requested number.
	 */
	public boolean contains(int number);
}
