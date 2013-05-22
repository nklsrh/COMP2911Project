package model;

import java.util.ArrayList;

public class Row {
	private ArrayList<Cell> rowCells;
	protected static final int NUM_ROW_SIDE = 9;
	
	public Row(){
		rowCells = new ArrayList<Cell>();
	}
	
	public ArrayList<Cell> getRow(){
		return rowCells;
	}
	
	
	@Override
	public String toString(){
		StringBuffer string = new StringBuffer();
		string.append("| ");
		if(!rowCells.isEmpty()){
			for(int i=0; i< NUM_ROW_SIDE; i++){
				string.append(rowCells.get(i).getNumber() + " | ");	
			}
		}
		return string.toString();
	}
}
