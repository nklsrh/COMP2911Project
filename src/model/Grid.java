package model;

import java.util.ArrayList;

public class Grid implements GridInterface {
	
	private int gridIndex;
	private ArrayList<ArrayList<Cell>> grid;
	
	public Grid(int gridIndex) {
		this.grid = new ArrayList<ArrayList<Cell>>();
		this.gridIndex = gridIndex;
	}
	
//	@Override
//	public void addCell(int number, int column, int row, int index,	boolean isSolved) {
//		Cell cell = new Cell(number, column, row, index, isSolved);
//		
//		if (!cells.contains(cell))
//			cells.add(index, cell);
//	}
	
	public int getGridIndex(){
		return gridIndex;
	}
	
	@Override
	public Cell getCell(int column, int row) {
		return grid.get(row).get(column);
	}
	
	@Override
	public Cell getCell(int index){
		for (int i = 0; i < (index+1)/3; i++){
			for (int j = 0; j < (index+1)%3; j++){
				return grid.get(i).get(j);
			}
		}
		return null;
	}
	
	@Override
	public boolean contains(int number){
		boolean doesIt = false;
		for (int i = 0 ; i < grid.size() ; i++){
			for (int j = 0 ; j < grid.get(i).size() ; j++){
				if (grid.get(i).get(j).getNumber() == number){
					doesIt = true;
				}
			}
		}
		return doesIt;
	}
}
