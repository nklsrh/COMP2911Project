package model;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Interface RowColumnInterface.
 * 
 * @author Ryan Tan, Nikhil Suresh and Nicholas Ho
 */
public interface RowColumnInterface {
	/**
	 * A getter for the arrayList of cells that is stored in a single Row or Column Object. 
	 * @return the arrayList of cells in the row or column.
	 */
	public ArrayList<Cell> getList();
	
	/**
	 * This method adds a cell to the list of cells contained in the Row or Column object.
	 *
	 * @param cell the cell
	 */
	public void addToList(Cell cell);
	
}
