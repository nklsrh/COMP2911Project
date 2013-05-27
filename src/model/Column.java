package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Column implements RowColumnInterface{
	private ArrayList<Cell> columnCells;
	
	
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
		columnCells.add(cell);
	}
	
	/**
	 * This method copies the contents of the columnCells arrayList into a string in a readable format.
	 */
	@Override
	public String toString() {
		StringBuffer string = new StringBuffer();
		string.append("| ");
		Iterator<Cell> rCit = columnCells.iterator();
		while(rCit.hasNext()){
			string.append(rCit.next().getNumber() + " | ");	
		}
		return string.toString();
	}
}
