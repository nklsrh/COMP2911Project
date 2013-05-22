package model;

import java.util.ArrayList;

public class Row {
	private ArrayList<Cell> rowCells;
	
	public Row(){
		rowCells = new ArrayList<Cell>();
	}
	
	public ArrayList<Cell> getRow(){
		return rowCells;
	}
	
	/* (non-Javadoc)
	 * Making sure we return something human-readable
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return rowCells.toString();
	}
	
}
