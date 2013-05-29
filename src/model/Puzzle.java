package model;

import java.util.ArrayList;
import java.util.Iterator;


public class Puzzle implements PuzzleInterface {
	public static final int NUM_GRIDS = 9;
	public static final int NUM_ROWS = 9;
	public static final int NUM_COLUMNS = 9;
	
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
	
	/**
	 * This method initialises the puzzle to a state where the Sudoku structures (9x9 puzzle, 9 3x3 grids etc)
	 * are constructed, and is ready to take in numbers such that a full Sudoku puzzle can be created.
	 * 9 each of Grid, Row and Column objects are initialised to represent the 9 Grids, 9 Rows and 9 Columns
	 * that make up a Sudoku puzzle.
	 */
	private void puzzleInit(){
		//make 9 grids
		for(int i=0; i < NUM_GRIDS; i++){
			gridStore.add(gridInit(i));
		}
		//make 9 rows
		for(int i=0; i < NUM_ROWS; i++){
			Row row = new Row();
			row = rowBuilder(gridStore, row, i);
			rowStore.add(row);
		}
		//make 9 columns
		for(int i=0; i < NUM_COLUMNS; i++){
			Column column = new Column();
			column = columnBuilder(rowStore, column, i);
			columnStore.add(column);
		}
	}
	
	/**
	 * Initialises and creates the grid cells that make up a Grid object.
	 * @param gridIndex the index of the particular Grid object that is being created.
	 * @return a fully-initialised empty Grid object.
	 */
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
	
	
	/**
	 * Initialises and creates the cells that make up a Row object, by using the gridStore arrayList
	 * of Grid objects. a rowNumber is taken in to identify the position of the Row that is being
	 * created.
	 * @param gridStore The arraylist of Grids.
	 * @param row 
	 * @param rowNumber the row that is being built/initialised
	 * @return a fully-initialised empty Row object
	 */
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
				row.addToList(cell);
			}
		}
		
		return row;
	}
	
	/**
	 * Initialises and creates the cells that make up a Column object, by using the gridStore arrayList
	 * of Grid objects. a rowNumber is taken in to identify the position of the Column that is being
	 * created.
	 * @param rowStore
	 * @param column
	 * @param ColIndex
	 * @return
	 */
	private Column columnBuilder(ArrayList<Row> rowStore, Column column, int ColIndex){
		
		Iterator<Row> rit = rowStore.iterator();
		while(rit.hasNext()){
			Cell cell = rit.next().getList().get(ColIndex);
			column.addToList(cell);
		}
		
		return column;
	}
	
	/**
	 * A getter for the arrayList of grids that is used in initialisation of the Puzzle.
	 * @return the arrayList of Grid objects
	 */
	public ArrayList<Grid> getGridList(){
		return gridStore;
	}
	
	/**
	 * A getter for the arrayList of rows that is used in initialisation of the Puzzle.
	 * @return the arrayList of Row objects
	 */
	public ArrayList<Row> getRowList(){
		return rowStore;
	}
	
	/**
	 * A getter for the arrayList of columns that is used in initialisation of the Puzzle.
	 * @return the arrayList of Column objects
	 */
	public ArrayList<Column> getColumnList(){
		return columnStore;
	}

	/**
	 * A setter which defines a particular cell in the puzzle. The cell is located via row and column index 
	 * coordinates that are taken in as arguments. The number given to the cell is "value" that is taken in
	 * as well.
	 * @param row the y-coordinate of the Cell to be set.
	 * @param column the x-coordinate of the Cell to be set.
	 * @param value the number that is to be given as the value of the cell.
	 * @param isPlayerSetting Is it changing upon player interaction, or are we editing beforehand
	 */
	public void setCell(int row, int column, int value){
		if (row >= 0 && row < NUM_ROWS){
			rowStore.get(row).getList().get(column).setNumber(value);			
			columnStore.get(column).getList().get(row).setNumber(value);
			updatePossibilities(row, column, value);
		}
	}
	
	public void updatePossibilities(int row, int column, int value)
	{		
		// loop through the rest of the ROW AND COLUMN and remove the new value from possibilities
		for (int i = 0; i < NUM_ROWS; i++)
		{
			if (i != column)
			{
				rowStore.get(row).getList().get(i).removeFromPossibilities(value);
			}
			if (i != row)
			{
				columnStore.get(column).getList().get(i).removeFromPossibilities(value);
			}
		}
		// loop through the rest of the COLUMN and remove the new value from possibilities
		for (int i = 0; i < NUM_ROWS; i++)
		{
			if (i != row)
			{
				// do same as above, but for gridStore
				//gridStore.get(column).getList().get(i).removeFromPossibilities(value);
			}
		}
	}
	
	/**
	 * This method sets a cell at a particular set of coordinates as empty i.e. has no value, and all flags
	 * disabled.
	 * @param row the y-coordinate of the Cell to be set as empty
	 * @param column the x-coordinate of the Cell to be set as empty
	 */
	public void setCellAsEmpty(int row, int column){
		if (row >= 0 && row < NUM_ROWS && column >= 0 && column < NUM_ROWS){
			rowStore.get(row).getList().get(column).nullCell();
			columnStore.get(column).getList().get(row).nullCell();
		}
	}
	
	/**
	 * A getter for a Cell object in the puzzle, located at a given set of coordinates
	 * @return the cell located at the given coordinates if it is present, and return null otherwise.
	 */
	public Cell getCell(int row, int column){
		if (rowStore.get(row) != null){
			return rowStore.get(row).getList().get(column);
		}
		return null;
	}
	
	/**
	 * A getter for a Grid object in the puzzle, given its index.
	 * @return the grid located at a certain index in the Puzzle.
	 */
	@Override
	public Grid getGrid(int gridIndex) {
		return gridStore.get(gridIndex);
	}
	
	/**
	 * A setter for the values of Cells in a Row object, given an index for a Grid object and an arrayList
	 * of Cells containing the values for the row to be set.
	 * @param index the position of the Grid whose values are to be set
	 * @param rowValues the values that are to be set to the Row.
	 */
	public void setRow(int index, ArrayList<Cell> rowValues){
		for(int i = 0; i < Grid.NUM_CELLS_PER_SIDE; i++){
			gridStore.get(index).setCell(i, rowValues.get(i));
		}
	}
	
	/**
	 * This method copies the contents of the Puzzle into a string in a readable format.
	 */
	@Override
	public String toString(){
	    String result = "In Rows:\n";
	    
	    int j = 1;
	    Iterator<Row> rit = rowStore.iterator();
	    while(rit.hasNext()){
	    	Iterator<Cell> cit = rit.next().getList().iterator();
	    	int i = 1;
	    	while(cit.hasNext()){
	    		result += " "+cit.next().getSolution();
	    		if((i%3) == 0 && i < 9){
					result += " |";
				}
				i++;
	    	}
	    	result += "\n";
			if((j%3) == 0 && j < 9)
				result += "-------+-------+-------\n";
			if(j == 9){
				break;
			}
			j++;
	    }
	    return result;
	}
}
