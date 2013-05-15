package model;

import java.util.ArrayList;

public class Column {
	
	private ArrayList<Cell> columnCells;
	
	public Column(){
		columnCells = new ArrayList<Cell>();
	}
	
	public ArrayList<Cell> getColumn(){
		return columnCells;
	}
	
	public void addCell(int index, Cell cell){
		columnCells.add(index, cell);
	}
}
