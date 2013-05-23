package model;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Iterator;

=======
>>>>>>> parent of fff3f8d... Deleted all model files

public class Puzzle implements PuzzleInterface {
	private static final int NUM_GRIDS = 9;
	private static final int NUM_ROWS = 9;
	private static final int NUM_COLUMNS = 9;
	
	private ArrayList<Grid> gridStore;
	private ArrayList<Row> rowStore;
<<<<<<< HEAD
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
=======
	private int difficulty;
	
	
	public Puzzle(int difficulty) {
		this.difficulty = difficulty;
		gridStore = new ArrayList<Grid>();
		rowStore = new ArrayList<Row>();
		puzzelInit();
	}
	
	//initialises & creates the grids
	private void puzzelInit(){
>>>>>>> parent of fff3f8d... Deleted all model files
		//make 9 grids
		for(int i=0; i < NUM_GRIDS; i++){
			gridStore.add(gridInit(i));
		}
<<<<<<< HEAD
		
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
=======
		//System.out.println(gridStore);
		
		rowInit();
>>>>>>> parent of fff3f8d... Deleted all model files
	}
	
	//initialises & creates the grid cells
	private Grid gridInit(int gridIndex){
		Grid grid = new Grid(gridIndex);
		
<<<<<<< HEAD
		for(int i=0; i<Grid.NUM_CELLS_PER_SIDE; i++) {
			grid.getGridTable().add(new ArrayList<Cell>());
			for(int j=0; j<Grid.NUM_CELLS_PER_SIDE; j++) {
				grid.getGridTable().get(i).add(new Cell());

=======
		for(int i=0; i<3; i++){
			grid.getGridTable().add(new ArrayList<Cell>());
			for(int j=0; j<3; j++){
				grid.getGridTable().get(i).add(new Cell(0));
>>>>>>> parent of fff3f8d... Deleted all model files
			}
		}
		return grid;
	}
	
	
<<<<<<< HEAD
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
			if (rowStore.get(row) != null){
				if (rowStore.get(row).getList().get(column) != null){
					rowStore.get(row).getList().get(column).setNumber(value);
				}
			}
		}
	}
	
	public Cell getCell(int row, int column){
		if (rowStore.get(row) != null){
			if (rowStore.get(row).getList().get(column) != null){
				return rowStore.get(row).getList().get(column);
			}
		}
		return null;
=======
	private void rowInit(){
		for(int i = 0; i < NUM_ROWS; i++){	
			rowStore.add(rowBuilder(gridStore, i));
		}
		
	}
	
	//rowIndex represent the Row number
	private Row rowBuilder(ArrayList<Grid> gridStore, int rowNumber){
		Row row = new Row();
		int numSide = Grid.NUM_GRID_ROW; //number of rows
		int lower = (rowNumber/numSide);
		int upper = lower + numSide;
		
		//first 3 rows == grid 0-2
		for(int i = lower; i < upper; i++){
			Grid curGrid = gridStore.get(i);
			for(int j = 0; j < numSide; j++){
				Cell cell = curGrid.getGridTable().get(rowNumber % numSide).get(j);
				row.getRow().add(cell);
			}
		}
		return row;
	}
	
	@Override
	public Cell getCell(int row, int column){
		System.out.println(row + "," + column + "  " + rowStore);
		if (rowStore.get(row) != null)
		{
			if (rowStore.get(row).getRow().get(column) != null)
			{
				return rowStore.get(row).getRow().get(column);
			}
		}
		return null;
	}

	public void setCell(int row, int column, int value){
		System.out.println("BEFORE: " + row + "," + column + "  " + rowStore.get(row).getRow());
		if (row >= 0 && row < NUM_ROWS)
		{
			if (rowStore.get(row) != null)
			{
				if (rowStore.get(row).getRow().get(column) != null)
				{
					rowStore.get(row).getRow().get(column).setNumber(value);
				}
			}
		}
		System.out.println("AFTER: " + row + "," + column + "  " + rowStore.get(row).getRow());
	}
	
	@Override
	public int getDifficulty() {
		return difficulty;
>>>>>>> parent of fff3f8d... Deleted all model files
	}
	
	@Override
	public Grid getGrid(int gridIndex) {
		return gridStore.get(gridIndex);
	}
	
<<<<<<< HEAD
	public void setGrid(int index, ArrayList<Cell> gridValues){
		for(int i = 0; i < Grid.NUM_CELLS_PER_SIDE; i++){
			gridStore.get(index).setCell(i, gridValues.get(i));
		}
	}
}
=======
	public ArrayList<Grid> getGridList(){
		return gridStore;
	}
	
	public void setGrid(int index, ArrayList<Cell> gridValues)
	{
		for(int i = 0; i < gridStore.get(index).NUM_GRID_ROW; i++)
		{
			gridStore.get(index).setCell(i, gridValues.get(i));
		}
	}

}
>>>>>>> parent of fff3f8d... Deleted all model files
