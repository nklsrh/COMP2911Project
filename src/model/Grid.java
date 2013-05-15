package model;

import java.util.ArrayList;

public class Grid implements GridInterface {
<<<<<<< HEAD
	private int columnPos;
	private int rowPos;
	private int index;
	private ArrayList <Cell> cells;
	
	public Grid(int columnPos, int rowPos, int index) {
		cells = new ArrayList <Cell>(9);
		this.columnPos = columnPos;
		this.rowPos = rowPos;
		this.index = index;
	}
	
	@Override
	public void addCell(int number, int column, int row, int index,	boolean isSolved) {
		Cell cell = new Cell(number, column, row, index, isSolved);
		
		if (!cells.contains(cell))
			cells.add(index, cell);
	}
	
	@Override
	public Cell getCell(int column, int row) {
		Cell cell = null;
		
		for (int i = 0 ; i < cells.size() ; i++) {
			if (cells.get(i).getColumnPos() == column && cells.get(i).getRowPos() == row)
				cell = cells.get(i);
		}
		return cell;
	}

	@Override
	public Cell getCell(int index) {	
		return cells.get(index);
	}
	
	@Override
	public int getColumnPos() {
		return columnPos;
	}

	@Override
	public int getRowPos() {
		return rowPos;
	}

	@Override
	public int getIndex() {
		return index;
	}
	
	@Override
	public boolean contains(int number) {
		for (int i = 0 ; i < cells.size() ; i++)
		{
			if (cells.get(i).getNumber() == number)
				return true;
		}
		return false;
=======
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
>>>>>>> master
	}
}
