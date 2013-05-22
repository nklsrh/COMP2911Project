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
	private int difficulty;
	
	/**
	 * Constructs an empty puzzle object given a difficulty setting, which is an <b>int</b>. initialises and
	 * enumerates the lists required to keep track of the <b>Grids</b>, <b>Rows</b> and <b>Columns</b> that 
	 * make up a Sudoku puzzle.
	 * @param difficulty is the numerical difficulty of the puzzle, lower = easier, higher = harder
	 */
	public Puzzle(int difficulty){
		this.difficulty = difficulty;
		gridStore = new ArrayList<Grid>();
		rowStore = new ArrayList<Row>();
		columnStore = new ArrayList<Column>();

		puzzleInit();
	}
	
	//initialises & creates the grids
	private void puzzleInit(){
		//make 9 grids
		for(int i=0; i < NUM_GRIDS; i++){
			gridStore.add(gridInit(i));
		}
		
		rowInit();
		columnInit();
	}
	
	//initialises & creates the grid cells
	private Grid gridInit(int gridIndex){
		Grid grid = new Grid(gridIndex);
		
		for(int i=0; i<Grid.NUM_CELLS_PER_SIDE; i++) {
			grid.getGridTable().add(new ArrayList<Cell>());
			for(int j=0; j<Grid.NUM_CELLS_PER_SIDE; j++) {
				grid.getGridTable().get(i).add(new Cell());
			}
		}
		return grid;
	}
	
	
	private void rowInit() {
		for(int i=0; i < NUM_ROWS; i++){
			Row row = new Row();
			row = rowBuilder(gridStore, row, i);
			rowStore.add(row);
		}
		
	}
	
	private void columnInit() {
		for(int i=0; i < NUM_COLUMNS; i++){
			Column column = new Column();
			column = columnBuilder(rowStore, column, i);
			columnStore.add(column);
		}
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
	
	@Override
	public Cell getCell(int row, int column){
		return null;
	}
	
	@Override
	public int getDifficulty() {
		return difficulty;
	}
	
	@Override
	public Grid getGrid(int gridIndex) {
		return gridStore.get(gridIndex);
	}
}
