package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Column implements RowColumnInterface{
	private ArrayList<Cell> columnCells;
	
	
	public Column() {
		columnCells = new ArrayList<Cell>();
	}
	
	public ArrayList<Cell> getList() {
		return columnCells;
	}
	
	@Override
	public void addToList(Cell cell) {
		columnCells.add(cell);
	}
	
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
