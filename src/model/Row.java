package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Row implements RowColumnInterface{
	private ArrayList<Cell> rowCells;
	
	
	public Row() {
		rowCells = new ArrayList<Cell>();
	}
	
	@Override
	public ArrayList<Cell> getList() {
		return rowCells;
	}
	
	@Override
	public void addToList(Cell cell) {
			rowCells.add(cell);
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
