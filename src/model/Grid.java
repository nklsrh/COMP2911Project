package model;

import java.util.ArrayList;

public class Grid {
	protected static final int NUM_GRID_ROW = 3;
	protected static final int NUM_GRID_COLUMN = 3;
	
	private int gridIndex;
	private ArrayList<ArrayList<Cell>> gridTable;
	
	public Grid(int gridIndex) {
		this.gridTable = new ArrayList<ArrayList<Cell>>();
		this.gridIndex = gridIndex;
	}
	
	public ArrayList<ArrayList<Cell>> getGridTable(){
		return gridTable;
	}
	
	public int getGridIndex(){
		return gridIndex;
	}
	
	public Cell getCell(int column, int row) {
		return gridTable.get(row).get(column);
	}
	
	public Cell getCell(int index){
		for (int i = 0; i < (index+1) / NUM_GRID_ROW; i++){
			for (int j = 0; j < (index+1) % NUM_GRID_COLUMN; j++){
				return gridTable.get(i).get(j);
			}
		}
		return null;
	}
	
	/**
	 * Actually sets a certain grid cell to a cell value
	 * @param index
	 * @param value
	 */
	public void setCell(int index, Cell value){
		for (int i = 0; i < (index+1) / NUM_GRID_ROW; i++){
			for (int j = 0; j < (index+1) % NUM_GRID_COLUMN; j++){
				gridTable.get(i).set(j, value);
			}
		}
	}
	
	
	public boolean contains(int number){
		boolean doesIt = false;
		for (int i = 0 ; i < gridTable.size() ; i++){
			for (int j = 0 ; j < gridTable.get(i).size() ; j++){
				if (gridTable.get(i).get(j).getNumber() == number){
					doesIt = true;
				}
			}
		}
		return doesIt;
	}
	
	@Override
	public String toString(){
		StringBuffer string = new StringBuffer();
		string.append("-------------\n");
		for(int i=0; i< NUM_GRID_ROW; i++){
			string.append("| ");
			for(int j=0; j< NUM_GRID_COLUMN; j++){
				string.append(gridTable.get(i).get(j).getNumber() + " | ");
			}
			string.append("\n-------------\n");
		}
		string.append("\n");
		return string.toString();
	}
}