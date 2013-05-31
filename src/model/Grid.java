package model;

import java.util.ArrayList;

/**
 * The Grid Column, contains numeral references to cells reflecting to a single grid of a sudoku puzzle.
 * A 9*9 puzzle thus has 9 Grids and is stored in the index of 0-8, reading left to right. 
 * Note: the reference is duplicated in the other Rows, Column.
 */
public class Grid {
	
	/** The Constant NUM_CELLS_PER_SIDE. */
	protected static final int NUM_CELLS_PER_SIDE = 3;
	
	/** The grid index. */
	private int gridIndex;
	
	/** The grid table. */
	private ArrayList<ArrayList<Cell>> gridTable;
	
	/**
	 * Constructor instantiates a new grid and the grid index.
	 *
	 * @param gridIndex The index of this particular Grid, ranging 0-8.
	 */
	public Grid(int gridIndex) {
		this.gridTable = new ArrayList<ArrayList<Cell>>();
		this.gridIndex = gridIndex;
	}
	
	/**
	 * A getter for the double arrayList that contains the 9 Cell objects.
	 * 
	 * @return the gridTable 2-d arrayList which is a list of lists containing the Cells contained in the grid.
	 */
	public ArrayList<ArrayList<Cell>> getGridTable(){
		return gridTable;
	}
	
	/**
	 * A getter for the grid's index within the Sudoku puzzle.
	 * 
	 * @return the index of the Grid object.
	 */
	public int getGridIndex(){
		return gridIndex;
	}
	
	/**
	 * A getter that retrieves a particular Cell within the Grid, given a column index and row index.
	 * @param column the x-coordinate of the Cell within the Grid, from 0-2.
	 * @param row the y-coordinate of the Cell within the Grid, from 0-2.
	 * @return the Cell contained in the Grid object, at specific coordinates specified by <b>column</b> and
	 * <b>row</b>. 
	 */
	public Cell getCell(int column, int row) {
		return gridTable.get(row).get(column);
	}
	
	/**
	 * A getter that retrieves a particular Cell within a Grid, given an index of the Cell.
	 * @param index the positional index of the Cell object within the Grid, from 0-8, in the orientation
	 * 012<br>345<br>678
	 * @return the Cell contained in the Grid object, at a specific index position specified by <b>index</b>
	 */
	public Cell getCell(int index){
		for (int i = 0; i < (index+1) / NUM_CELLS_PER_SIDE; i++){
			for (int j = 0; j < (index+1) % NUM_CELLS_PER_SIDE; j++){
				return gridTable.get(i).get(j);
			}
		}
		return null;
	}
	
	/**
	 * A setter that sets a certain grid cell to a cell value, given the Cell's index and value to be added.
	 * 
	 * @param index the positional index of a Cell within the Grid.
	 * @param value the number of the Cell to be stored in the Grid
	 */
	public void setCell(int index, Cell value){
		for (int i = 0; i < (index+1) / NUM_CELLS_PER_SIDE; i++){
			for (int j = 0; j < (index+1) % NUM_CELLS_PER_SIDE; j++){
				gridTable.get(i).set(j, value);
			}
		}
	}
	
	/**
	 * This method takes in a number from 1-9 and checks if the Grid object 
	 * contains a Cell which contains this number.
	 * 
	 * @param number the number whose presence in the Grid is to be checked.
	 * @return true if the grid contains such a number, false otherwise.
	 */
	public boolean contains(int number){
		boolean doesIt = false;
		for (int i = 0 ; i < gridTable.size() ; i++){
			for (int j = 0 ; j < gridTable.get(i).size() ; j++){
				if (gridTable.get(i).get(j).getNumber() == number){
					doesIt = true;
				}
			}
		}
		return doesIt;
	}
	
	/**
	 * This method copies the contents of the gridTable 2-d arraylist in a string 
	 * in a readable format.
	 *
	 * @return the string
	 */
	@Override
	public String toString(){
		StringBuffer string = new StringBuffer();

		string.append(" -----------\n");
		for(int i=0; i< NUM_CELLS_PER_SIDE; i++){		// iterate through rows
			string.append("| ");
			for(int j=0; j< NUM_CELLS_PER_SIDE; j++){	// iterate through columns
				string.append(gridTable.get(i).get(j).getNumber() + " | ");
			}
			string.append("\n -----------\n");
		}
		string.append("\n");
		return string.toString();
	}
}

