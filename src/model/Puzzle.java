package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Puzzle Class, used as an functional access of the model package, interfacing with sudoku elements
 * that requires storage would fetch from this class.
 * 
 * @author Ryan Tan, Nikhil Suresh and Nicholas Ho
 */
public class Puzzle implements PuzzleInterface {
	
	/** The Constant NUM_GRIDS. */
	public static final int NUM_GRIDS = 9;
	
	/** The Constant NUM_ROWS. */
	public static final int NUM_ROWS = 9;
	
	/** The Constant NUM_COLUMNS. */
	public static final int NUM_COLUMNS = 9;
	
	/** The grid store, an Arraylist of grids, used for easy look-through */
	private ArrayList<Grid> gridStore;
	
	/** The row store. likewise with the gridStore*/
	private ArrayList<Row> rowStore;
	
	/** The column store. likewise with the gridStore*/
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
	 * This method initializes the puzzle to a state where the inter-locking cells in the Rows, Columns 
	 * and Grid are tied to each other by referencing the same cells.
	 * 
	 */
	private void puzzleInit(){
		//make 9 grids
		for(int i=0; i < NUM_GRIDS; i++){
			gridStore.add(gridInit(i));
		}
		//make 9 rows
		for(int i=0; i < NUM_ROWS; i++){
			Row row = new Row();
			row = rowBuilder(row, i);
			rowStore.add(row);
		}
		//make 9 columns
		for(int i=0; i < NUM_COLUMNS; i++){
			Column column = new Column();
			column = columnBuilder(column, i);
			columnStore.add(column);
		}
	}
	
	/**
	 * Initializes and creates the grid cells that make up a Grid object, this is the first
	 * point where a new Cell(0) is created. The rest in building of row and column are referring to
	 * the grid index.
	 * 
	 * @param gridIndex the index of the particular Grid object that is being created.
	 * @return a fully-initialized empty Grid object.
	 */
	private Grid gridInit(int gridIndex){
		Grid grid = new Grid(gridIndex);
		
		for(int i=0; i<Grid.NUM_CELLS_PER_SIDE; i++) {
			grid.getGridTable().add(new ArrayList<Cell>());
			for(int j=0; j<Grid.NUM_CELLS_PER_SIDE; j++) {
				Cell cell = new Cell();
				cell.resetPossibilties();
				cell.setGrid(grid);
				grid.getGridTable().get(i).add(cell);
			}
		}
		return grid;
	}
	
	
	/**
	 * Takes in the row provided and the row's index and according to where the cell 
	 * is stored in the gridStore, it passes the cell reference from the grid to this 
	 * row. By arithmetic of how a square puzzle of equal side is made, this should be 
	 * referencing correctly. Assumes the gridStore has already been built
	 *
	 * @param gridStore The arraylist of Grids.
	 * @param row the row
	 * @param rowNumber the row that is being built/initialised
	 * @return a fully-initialised empty Row object
	 */
	private Row rowBuilder(Row row, int rowNumber){
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
				cell.setRow(row);
				row.addToList(cell);
			}
		}
		
		return row;
	}
	
	/**
	 * Likewise with the rowBuilder, due to less calculation it takes cell references 
	 * from the rowStore instead.
	 *
	 * @param column the column
	 * @param ColIndex the col index
	 * @return the column
	 */
	private Column columnBuilder(Column column, int ColIndex){
		
		Iterator<Row> rit = rowStore.iterator();
		while(rit.hasNext()){
			Cell cell = rit.next().getList().get(ColIndex);
			cell.setColumn(column);
			column.addToList(cell);
		}
		
		return column;
	}
	
	/**
	 * A getter for the arrayList of grids
	 * @return the arrayList of Grid objects
	 */
	@Override
	public ArrayList<Grid> getGridList(){
		return gridStore;
	}
	
	/**
	 * A getter for the arrayList of rows.
	 * @return the arrayList of Row objects
	 */
	@Override
	public ArrayList<Row> getRowList(){
		return rowStore;
	}
	
	/**
	 * A getter for the arrayList of columns.
	 * @return the arrayList of Column objects
	 */
	public ArrayList<Column> getColumnList(){
		return columnStore;
	}

	/**
	 * Based on the x,y coordinates within the row and column, sets the cell
	 * up with the new value.
	 * 
	 * @param row the y-coordinate of the Cell to be set.
	 * @param column the x-coordinate of the Cell to be set.
	 * @param value the number that is to be given as the value of the cell.
	 */
	@Override
	public void setCell(int row, int column, int value){
		if (value == -1){
			setCellAsEmpty(row, column);
		}else{
			if (row >= 0 && row < NUM_ROWS){
				rowStore.get(row).getList().get(column).setNumber(value);			
//				columnStore.get(column).getList().get(row).setNumber(value);
			}
		}
	}
	
	
	/**
	 * This method sets a cell at a particular set of coordinates as empty 
	 * i.e. has no value, and all flags disabled.
	 * 
	 * @param row the y-coordinate of the Cell to be set as empty
	 * @param column the x-coordinate of the Cell to be set as empty
	 */
	@Override
	public void setCellAsEmpty(int row, int column){
		if (row >= 0 && row < NUM_ROWS && column >= 0 && column < NUM_ROWS){
			rowStore.get(row).getList().get(column).nullCell();
//			columnStore.get(column).getList().get(row).nullCell();
		}
	}
	
	/**
	 * A getter for a Cell object in the puzzle, located at a given set of coordinates.
	 *
	 * @param row the row
	 * @param column the column
	 * @return the cell located at the given coordinates if it is present, and return null otherwise.
	 */
	@Override
	public Cell getCell(int row, int column){
		if (rowStore.get(row) != null){
			return rowStore.get(row).getList().get(column);
		}
		return null;
	}
	
	/**
	 * A getter for a Grid object in the puzzle, given its index.
	 *
	 * @param gridIndex the grid index
	 * @return the grid located at a certain index in the Puzzle.
	 */
	@Override
	public Grid getGrid(int gridIndex) {
		return gridStore.get(gridIndex);
	}
	
	/**
	 * A setter for the values of Cells in a Row object, given an index for 
	 * a Grid object and an arrayList of Cells containing the values for the row to be set.
	 * 
	 * @param index the position of the Grid whose values are to be set
	 * @param rowValues the values that are to be set to the Row.
	 */
	@Override
	public void setRow(int index, ArrayList<Cell> rowValues){
		for(int i = 0; i < Grid.NUM_CELLS_PER_SIDE; i++){
			gridStore.get(index).setCell(i, rowValues.get(i));
		}
	}
	
	/**
	 * This method copies the contents of the Puzzle into a string in a readable format
	 * for debugging purposes.
	 *
	 * @return the string
	 */
	@Override
	public String toString(){
	    String result = "";
	    
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
