package model;

import java.util.ArrayList;
import java.util.Iterator;


public class Puzzle implements PuzzleInterface {
	private static final int NUM_GRIDS = 9;
	private static final int NUM_ROWS = 9;
	private static final int NUM_COLUMNS = 9;
	
	private ArrayList<Grid> gridStore;
	private ArrayList<Row> rowStore;
	private ArrayList<Column> columnStore;
	
	/**
	 * Constructs an empty puzzle object given a difficulty setting, which is an <b>int</b>. initialises and
	 * enumerates the lists required to keep track of the <b>Grids</b>, <b>Rows</b> and <b>Columns</b> that 
	 * make up a Sudoku puzzle.
	 */
	public Puzzle(){
		gridStore = new ArrayList<Grid>();
		rowStore = new ArrayList<Row>();
		columnStore = new ArrayList<Column>();

		puzzleInit();
	}
	
	
	private void puzzleInit(){
		//make 9 grids
		for(int i=0; i < NUM_GRIDS; i++){
			gridStore.add(gridInit(i));
		}
		
		for(int i=0; i < NUM_ROWS; i++){
			Row row = new Row();
			row = rowBuilder(gridStore, row, i);
			rowStore.add(row);
		}
		
		for(int i=0; i < NUM_COLUMNS; i++){
			Column column = new Column();
			column = columnBuilder(rowStore, column, i);
			columnStore.add(column);
		}
	}
	
	//initialises & creates the grid cells
	private Grid gridInit(int gridIndex){
		Grid grid = new Grid(gridIndex);
		
		for(int i=0; i<Grid.NUM_CELLS_PER_SIDE; i++) {
			grid.getGridTable().add(new ArrayList<Cell>());
			for(int j=0; j<Grid.NUM_CELLS_PER_SIDE; j++) {
				grid.getGridTable().get(i).add(new Cell(0));

			}
		}
		return grid;
	}
	
	
	//rowIndex represent the Row number
	private Row rowBuilder(ArrayList<Grid> gridStore, Row row, int rowNumber){
		int numSide = Grid.NUM_CELLS_PER_SIDE; //number of rows on grid
		
		int firstGridNo = 0;
		int lastGridNo = 0;
		if(rowNumber >= 0 && rowNumber < 3){
			firstGridNo = 0;
			lastGridNo = 2;
		}else if(rowNumber >= 3 && rowNumber < 6){
			firstGridNo = 3;
			lastGridNo = 5;
		}else if(rowNumber >= 6 && rowNumber < 9){
			firstGridNo = 6;
			lastGridNo = 8;
		}
		
		
		//first 3 rows == grid 0-2
		int gridRowNumber = rowNumber % numSide;
		
		
		for(int gridNo=firstGridNo; gridNo < (lastGridNo+1); gridNo++){
			Grid curGrid = gridStore.get(gridNo);
			
			for(int j=0; j < numSide; j++){
				Cell cell = curGrid.getGridTable().get(gridRowNumber).get(j);
				if(gridNo >= 6 && gridNo < 8){
					System.out.println(j);
				}
				row.addToList(cell);
			}
		}
		
		return row;
	}
	
	private Column columnBuilder(ArrayList<Row> rowStore, Column column, int ColIndex){
		
		Iterator<Row> rit = rowStore.iterator();
		while(rit.hasNext()){
			Cell cell = rit.next().getList().get(ColIndex);
			column.addToList(cell);
		}
		
		return column;
	}
	
	public ArrayList<Grid> getGridList(){
		return gridStore;
	}
	
	public ArrayList<Row> getRowList(){
		return rowStore;
	}
	
	public ArrayList<Column> getColumnList(){
		return columnStore;
	}

	public void setCell(int row, int column, int value){
		if (row >= 0 && row < NUM_ROWS){
			rowStore.get(row).getList().get(column).setNumber(value);
			columnStore.get(column).getList().get(row).setNumber(value);
		}
	}
	
	public void setCellAsEmpty(int row, int column){
		if (row >= 0 && row < NUM_ROWS && column >= 0 && column < NUM_ROWS){
			rowStore.get(row).getList().get(column).nullCell();
			columnStore.get(column).getList().get(row).nullCell();
		}
	}
	
	public Cell getCell(int row, int column){
		if (rowStore.get(row) != null){
			return rowStore.get(row).getList().get(column);
		}
		return null;
	}
	
	@Override
	public Grid getGrid(int gridIndex) {
		return gridStore.get(gridIndex);
	}
	
	
	public void setRow(int index, ArrayList<Cell> rowValues){
		for(int i = 0; i < Grid.NUM_CELLS_PER_SIDE; i++){
			gridStore.get(index).setCell(i, rowValues.get(i));
		}
	}
}
