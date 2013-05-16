package model;

import java.util.ArrayList;

public class Puzzle implements PuzzleInterface {
	private static final int NUM_GRIDS = 9;
	private static final int NUM_ROWS = 9;
	private static final int NUM_COLUMNS = 9;
	
	private ArrayList<Grid> gridStore;
	private ArrayList<Row> rowStore;
	private int difficulty;
	
	
	public Puzzle(int difficulty) {
		this.difficulty = difficulty;
		gridStore = new ArrayList<Grid>();
		rowStore = new ArrayList<Row>();
		puzzleInit();
	}
	
	//initialises & creates the grids
	private void puzzleInit(){
		//make 9 grids
		for(int i=0; i < NUM_GRIDS; i++){
			gridStore.add(gridInit(i));
		}
		
		rowInit();
	}
	
	//initialises & creates the grid cells
	private Grid gridInit(int gridIndex){
		Grid grid = new Grid(gridIndex);
		
		for(int i=0; i<Grid.NUM_GRID_SIDE; i++){
			grid.getGridTable().add(new ArrayList<Cell>());
			for(int j=0; j<Grid.NUM_GRID_SIDE; j++){
				grid.getGridTable().get(i).add(new Cell());
			}
		}
		return grid;
	}
	
	
	private void rowInit(){
		for(int i=0; i < NUM_ROWS; i++){
			Row row = new Row();
			row = rowBuilder(gridStore, row, i);
			rowStore.add(row);
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
