package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import controller.generate.Generator;
import controller.statistics.Statistics;
import controller.timer.TimerLabel;

import model.*;

/**
 * The Class PuzzleControl.
 * 
 * @author Ryan Tan, Nikhil Suresh and Nicholas Ho
 */
public class PuzzleControl {
	
	/** The Constant EASY_MISSING, number of empty cells. */
	private static final int EASY_MISSING = 35;
	
	/** The Constant MEDIUM_MISSING, number of empty cells. */
	private static final int MEDIUM_MISSING = 43;
	
	/** The Constant HARD_MISSING, number of empty cells. */
	private static final int HARD_MISSING = 55;
	
	/** The puzzle stored */
	private Puzzle puzzle;
	
	/** The timer running */
	private TimerLabel timer;
	
	/** The statistics Object. */
	private Statistics statistics;
	
	/**
	 * Instantiates a new puzzle control.
	 */
	public PuzzleControl()
	{
		statistics = new Statistics();
	}
	
	/**
	 * Creates the puzzle, using the generator first instantiates it, 
	 * shuffles and packages it all up by setting all cell values
	 *
	 * @param difficulty the difficulty
	 */
	public void createPuzzle(int difficulty)
	{
		Generator gen = new Generator();
		gen.shufflePuzzle();

		puzzle = new Puzzle();
		puzzle = createPuzzleAndSolution(gen.packageUp(puzzle), createMissingCells(difficulty));
		
		timer = new TimerLabel();
		statistics.setDifficulty(difficulty);
		
		// DEBUG
		System.out.println(puzzle.toString());
	}
	
	/**
	 * Load save file.
	 */
	public void loadSaveFile()
	{
		if (!getStatistics().fileExists())
		{
			getStatistics().makeFile();
		}
		getStatistics().readFile();		
	}
	
	/**
	 * Creates the missing cells.
	 * Difficulty ranked according:
	 * 	1: Easy
	 *  2: Medium
	 *  3: Hard
	 * @param difficulty the difficulty
	 * @return the linked list
	 */
	public LinkedList<LinkedList<Integer>> createMissingCells(int difficulty){
		LinkedList<LinkedList<Integer>> resultMissings = new LinkedList<LinkedList<Integer>>();
		Random rand = new Random();
		
		int cellsMissing = 0;
		if(difficulty == 3){
			cellsMissing = HARD_MISSING;
		}else if(difficulty == 2){
			cellsMissing = MEDIUM_MISSING;
		}else{
			cellsMissing = EASY_MISSING;
		}
		
		//build possible numbers
		for(int i=0; i<puzzle.getRowList().size(); i++){
			resultMissings.add(new LinkedList<Integer>());
		}
		
		while(cellsMissing > 0){
			
			Iterator<LinkedList<Integer>> rit = resultMissings.iterator(); 
			while(rit.hasNext()){
				if(cellsMissing <= 0){
					break;
				}
				
				int missing_rand = rand.nextInt(9);
				LinkedList<Integer> next = rit.next();
				if(!next.contains(missing_rand)){
					next.add(missing_rand);
					cellsMissing--;
				}
			}
		}
		
		return resultMissings;
	}
	
	
	/**
	 * Creates the puzzle and solution, references the missingCells to 
	 * point to which cell needs to be made empty.
	 *
	 * @param s the s
	 * @param missingCells the missing cells
	 * @return the puzzle
	 */
	public Puzzle createPuzzleAndSolution(Puzzle s, LinkedList<LinkedList<Integer>> missingCells)
	{
		Puzzle p = s;
		for (int y = 0; y < s.getRowList().size(); y++)
		{
			for (int i = 0; i < missingCells.get(y).size(); i++)
			{
				p.setCellAsEmpty(y, missingCells.get(y).get(i));
			}
		}	
		// need 2 loops coz we got 2 traversals through the grid, one before empty cells, one after
		for (int y = 0; y < s.getRowList().size(); y++)
		{
			for (int x = 0; x < s.getRowList().size(); x++)
			{
				if (!p.getCell(y, x).isEmpty())
				{
					p.setCell(y, x, p.getCell(y, x).getSolution());
				}
			}
		}	
		return p;
	}
	
	
	/**
	 * Populate solution from array string, used in testing to populate a 
	 * fixed puzzle string of 81 spaced values representing a sudoku puzzle.
	 *
	 * @param gridsStrings the grids strings
	 * @return the puzzle
	 */
	public Puzzle populateSolutionFromArrayString(ArrayList<String> gridsStrings)
	{
		Puzzle pz = new Puzzle();		
		Iterator<String> gsit = gridsStrings.iterator();
		int gridIndex = 0;
		
		while(gsit.hasNext()){
			String[] gridArray = gsit.next().split(" ");
			int n = 0;
			Grid curGrid = pz.getGrid(gridIndex);
			ArrayList<ArrayList<Cell>> table = curGrid.getGridTable();
			
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					table.get(i).get(j).setNumber(Integer.parseInt(gridArray[n]));
					table.get(i).get(j).setSolution(Integer.parseInt(gridArray[n]));
					n++;
				}
			}
			gridIndex++;
		}
		return pz;
	}
		
	/**
	 * A getter that retrieves a cell from the <b>Model</b>, given its coordinates. 
	 * @param row y-coordinates of the cell to be retrieved
	 * @param column x-coordinates of the cell to be retrieved
	 * @return the cell at the given row and column.
	 */
	public Cell getCell(int row, int column){
		return puzzle.getCell(row, column);
	}
	
	/**
	 * A setter that sets a value to a cell from the <b>Model</b>, given its coordinates and a value. 
	 * @param row y-coordinates of the cell to be set
	 * @param column x-coordinates of the cell to be set
	 * @param value the value of the cell to be set
	 * @return the cell at the given row and column.
	 */
	public void setCell(int row, int column, int value){
		puzzle.setCell(row, column, value);
	}
		
	
	
	/**
	 * A getter for the arrayList of columns that is used in initialisation of the Puzzle.
	 * @return the arrayList of Column objects
	 */
	public ArrayList<Column> getColumnList(){
		return puzzle.getColumnList();
	}
	
	/**
	 * This method compares the number placed by a user with the predefined solution of the Sudoku game, to
	 * see if the user has filled the cell with the correct number.
	 *
	 * @param row the row
	 * @param col the col
	 * @return true if the user has filled the cell correctly, false otherwise.
	 */
	public boolean checkNumberSolution(int row, int col)
	{
		return puzzle.getCell(row, col).checkNumberSolution();
	}
	
	/**
	 * Returns the first empty cell's coordinates and solution in an array.
	 *
	 * @return An array [i,j,k] that corresponds to the [row,col] of the cell, as well as [k] which is the solution
	 */
	public int[] getFirstEmptyCellCoordinates()
	{
		for (int i = 0; i < puzzle.getRowList().size(); i++)
		{
			for (int j = 0; j < puzzle.getColumnList().size(); j++)
			{
				if (!puzzle.getCell(i, j).isFixed() && !puzzle.getCell(i, j).checkNumberSolution())
				{
					return new int[] {i, j, puzzle.getCell(i, j).getSolution()};
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns the first empty cell with a single possible solution, used for autofilling the Sudoku Board,
	 * As AutoFill is made like a human, cell with remaining single possibles fill up that last one into the 
	 * cell's number.
	 *
	 * @return An array [i,j,k] that corresponds to the [row,col] of the cell, as well as [k] which is the solution
	 */
	public int[] getFirstCellWithSinglePossible()
	{
		for (int i = 0; i < puzzle.getRowList().size(); i++)
		{
			for (int j = 0; j < puzzle.getColumnList().size(); j++)
			{
				if (!puzzle.getCell(i, j).isFixed() && puzzle.getCell(i, j).getPossibilities().size() == 1)
				{
					if (puzzle.getCell(i, j).getPossibilities().get(0) != puzzle.getCell(i, j).getNumber())
					{
						return new int[] {i, j, puzzle.getCell(i, j).getPossibilities().get(0)};
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Gets the current game time duration
	 *
	 * @return the timer
	 */
	public TimerLabel getTimer()
	{
		return timer;
	}
	
	/**
	 * Returns the statistics of the game.
	 *
	 * @return the statistics
	 */
	public Statistics getStatistics()
	{
		return statistics;
	}
	
	
	/**
	 * Checks if
	 * - all cells are filled in the board
	 * - all rows are filled with 1-9
	 * - all columns are filled with 1-9
	 * - all grids are filled with 1-9.
	 *
	 * @return true, if successful
	 */
	public boolean boardIsValid(){
		//check grid is valid
		Iterator<Grid> git = puzzle.getGridList().iterator();
		while(git.hasNext()){
			LinkedList<Integer> possibles = fillPossibles(Puzzle.NUM_GRIDS);
			
			Iterator<ArrayList<Cell>> cit = git.next().getGridTable().iterator();
			while(cit.hasNext()){
				for(Cell cell : cit.next()){
					if(cell.getNumber() != null){
						possibles = removeFromPossibles(cell.getNumber(), possibles);
					}
				}
			}
			
			if(!possibles.isEmpty()){
				return false;
			}
		}
		
		Iterator<Row> rit = puzzle.getRowList().iterator();
		while(rit.hasNext()){
			LinkedList<Integer> possibles = fillPossibles(Puzzle.NUM_ROWS);
			
			Iterator<Cell> cit = rit.next().getList().iterator();
			while(cit.hasNext()){
				possibles = removeFromPossibles(cit.next().getNumber(), possibles);
			}
			
			if(!possibles.isEmpty()){
				return false;
			}
		}
		
		Iterator<Column> colit = puzzle.getColumnList().iterator();
		while(colit.hasNext()){
			LinkedList<Integer> possibles = fillPossibles(Puzzle.NUM_COLUMNS);
			
			Iterator<Cell> cit = colit.next().getList().iterator();
			while(cit.hasNext()){
				possibles = removeFromPossibles(cit.next().getNumber(), possibles);
			}
			if(!possibles.isEmpty()){
				return false;
			}
		}
		//check with helper function
		return boardCheckFilled();
	}
	
	/**
	 * Helper function, fills possibles according to the provided size.
	 * Used for checking if all grids, rows and cols are valid for a 
	 * WIN scenario
	 *
	 * @param total the total
	 * @return the linked list
	 */
	private LinkedList<Integer> fillPossibles(int total){
		LinkedList<Integer> result = new LinkedList<Integer>();
		for(int i=1; i<=total; i++){
			result.add(i);
		}
		return result;
	}
	
	
	/**
	 * 
	 * Helper function, removes from possibles, thus checking empty.
	 * Used for checking if all grids, rows and cols are valid for a 
	 * WIN scenario
	 *
	 * @param number the number
	 * @param possibles the possibles
	 * @return the linked list
	 */
	private LinkedList<Integer> removeFromPossibles(int number, LinkedList<Integer> possibles){
		int index = possibles.indexOf(number);
		if(index > -1){
			possibles.remove(index);
		}
		return possibles;
	}
	
	/**
	 * Checks that every cell in the board is filled, 
	 * all 81 without nulls.
	 *
	 * @return true, if successful
	 */
	private boolean boardCheckFilled(){
		//check using rowStore
		int totalCells = Puzzle.NUM_ROWS * Puzzle.NUM_COLUMNS;
		
		Iterator<Row> rit = puzzle.getRowList().iterator();
		while(rit.hasNext()){
			Iterator<Cell> cit = rit.next().getList().iterator();
			while(cit.hasNext()){
				if(cit.next().getNumber() != null){
					totalCells--;
				}
			}
		}
		//check if all are filled (not null)
		if(totalCells == 0){
			return true;
		}else{
			return false;
		}
	}
}
