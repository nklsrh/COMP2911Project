package model;

import java.util.ArrayList;

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
		
		for(int i=0; i<Grid.NUM_GRID_SIDE; i++) {
			grid.getGridTable().add(new ArrayList<Cell>());
			for(int j=0; j<Grid.NUM_GRID_SIDE; j++) {
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
			column = columnBuilder(gridStore, column, i);
			columnStore.add(column);
		}
	}
	
	//rowIndex represent the Row number
	private Row rowBuilder(ArrayList<Grid> gridStore, Row row, int rowNumber){
		int numSide = Grid.NUM_GRID_SIDE; //number of rows
		int lower = (rowNumber/numSide);
		int upper = lower + numSide;
		
		//first 3 rows == grid 0-2
		for(int i=lower; i < upper; i++){
			Grid curGrid = gridStore.get(i);
			for(int j=0; j < Grid.NUM_GRID_SIDE; j++){
				Cell cell = curGrid.getGridTable().get(rowNumber % numSide).get(j);
				row.getRow().add(cell);
			}
		}
		return row;
	}
	
	//columnIndex represent the Column number
	private Column columnBuilder(ArrayList<Grid> gridStore, Column column, int colNumber){
		int numSide = Grid.NUM_GRID_SIDE; //number of rows
		int lower = (colNumber/numSide);
		int upper = lower + numSide;
		
		//first 3 rows == grid 0-2
		for(int i=lower; i < upper; i++){
			Grid curGrid = gridStore.get(i);
			for(int j=0; j < Grid.NUM_GRID_SIDE; j++){
				Cell cell = curGrid.getGridTable().get(colNumber % numSide).get(j);
				column.getColumn().add(cell);
			}
		}
		return column;
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
	
	public ArrayList<Grid> getGridList(){
		return gridStore;
	}
	
	public ArrayList<Row> getRowList(){
		return rowStore;
	}
}
