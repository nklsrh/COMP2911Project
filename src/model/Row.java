package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Row implements RowColumnInterface{
	private ArrayList<Cell> rowCells;
	protected static final int NUMBER_OF_CELLS = 9;
	
	public Row() {
		rowCells = new ArrayList<Cell>();
	}
	
	@Override
	public ArrayList<Cell> getList() {
		return rowCells;
	}
	
	@Override
	public void addToList(Cell cell) {
		if(rowCells.size() <= NUMBER_OF_CELLS){
			rowCells.add(cell);
		}
	}
	
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
