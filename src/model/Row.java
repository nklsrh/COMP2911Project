package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Row implements RowColumnInterface{
	private ArrayList<Cell> rowCells;
	
	/**
	 * A constructor for a Row object, representing a single row in a Sudoku game that contains 9 "Cell"
	 * Objects. Creates a new arrayList which contains these nine Cells.
	 */
	public Row() {
		rowCells = new ArrayList<Cell>();
	}
	
	/**
	 * A getter for the arrayList of cells that is stored in a single Row Object. 
	 * @return the arrayList of cells in the row.
	 */
	@Override
	public ArrayList<Cell> getList() {
		return rowCells;
	}
	
	/**
	 * This method adds a cell to the list of cells contained in the Row object, if the rowCells arrayList
	 * is not full.
	 */
	@Override
	public void addToList(Cell cell) {
			rowCells.add(cell);
	}
	
	/**
	 * This method copies the contents of the rowCells arrayList into a string in a readable format.
	 */
	@Override
	public String toString() {
		StringBuffer string = new StringBuffer();
		string.append("| ");
		Iterator<Cell> rCit = rowCells.iterator();
		while(rCit.hasNext()){
			string.append(rCit.next().getNumber() + " | ");	
		}
		return string.toString();
	}
}
