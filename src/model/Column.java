package model;

import java.util.ArrayList;

public class Column implements RowColumnInterface{
	private ArrayList<Cell> columnCells;
	protected static final int NUMBER_OF_CELLS = 9;
	
	/**
	 * A constructor for a Column object, representing a single column in a Sudoku game that contains 9 "Cell"
	 * Objects. Creates a new arrayList which contains these nine Cells.
	 */
	public Column() {
		columnCells = new ArrayList<Cell>();
	}
	
	/**
	 * A getter for the arrayList of cells that is stored in a single Column Object. 
	 * @return the arrayList of cells in the column.
	 */
	public ArrayList<Cell> getList() {
		return columnCells;
	}
	
	/**
	 * This method adds a cell to the list of cells contained in the Column object, if the columnCells arrayList
	 * is not full.
	 */
	@Override
	public void addToList(Cell cell) {
		if(columnCells.size() <= NUMBER_OF_CELLS){
			columnCells.add(cell);
		}
	}
	
	/**
	 * This method copies the contents of the columnCells arrayList into a string in a readable format.
	 */
	@Override
	public String toString() {
		StringBuffer string = new StringBuffer();
		string.append("| ");
		if(!columnCells.isEmpty()) {
			for(int i=0; i< NUMBER_OF_CELLS; i++) {
				string.append(columnCells.get(i).getNumber() + " | ");	
			}
		}
		return string.toString();

	}
}
