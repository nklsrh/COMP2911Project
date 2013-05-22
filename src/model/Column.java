package model;

import java.util.ArrayList;

public class Column implements RowColumnInterface{
	private ArrayList<Cell> columnCells;
	protected static final int NUMBER_OF_CELLS = 9;
	
	public Column() {
		columnCells = new ArrayList<Cell>();
	}
	
	public ArrayList<Cell> getList() {
		return columnCells;
	}
	
	@Override
	public void addToList(Cell cell) {
		if(columnCells.size() <= NUMBER_OF_CELLS){
			columnCells.add(cell);
		}
	}
	
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
