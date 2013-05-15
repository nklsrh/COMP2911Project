package model;

import java.util.ArrayList;

public class Grid implements GridInterface {
	private static final int NUM_GRID_WIDTH = 3;
	private static final int NUM_GRID_HEIGHT = 3;
	
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
	
	@Override
	public Cell getCell(int column, int row) {
		return gridTable.get(row).get(column);
	}
	
	@Override
	public Cell getCell(int index){
		for (int i = 0; i < (index+1)/3; i++){
			for (int j = 0; j < (index+1)%3; j++){
				return gridTable.get(i).get(j);
			}
		}
		return null;
	}
	
	@Override
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
}
