package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import controller.generate.Generator;
import controller.statistics.Statistics;
import controller.timer.TimerLabel;

import model.*;

public class PuzzleControl {
	private static final int EASY_MISSING = 35;
	private static final int MEDIUM_MISSING = 50;
	private static final int HARD_MISSING = 70;
	private Puzzle puzzle;
	private TimerLabel timer;
	private Statistics statistics;
	
	public void createPuzzle(int difficulty)
	{
		Generator gen = new Generator();
		gen.shufflePuzzle();
		puzzle = new Puzzle();

		puzzle = createPuzzleAndSolution(gen.packageUp(puzzle), createMissingCells(difficulty));
		
		timer = new TimerLabel();
		statistics = new Statistics(difficulty);
		// old code with predetermined puzzles
//		puzzle = createPuzzleAndSolution(populateSolutionFromArrayString(createPuzzleArrayString()), createMissingCells());			
		System.out.println(puzzle.toString());
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
		return p;
	}
	
	
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
	 * A getter for a Grid object in the puzzle, given its index.
	 * @return the grid located at a certain index in the Puzzle.
	 */
	public Grid getGrid(int gridIndex) {
		return puzzle.getGrid(gridIndex);
	}
	
	/**
	 * A getter for the arrayList of grids that is used in initialisation of the Puzzle.
	 * @return the arrayList of Grid objects
	 */
	public ArrayList<Grid> getGridList(){
		return puzzle.getGridList();
	}	
	
	/**
	 * A getter for the arrayList of rows that is used in initialisation of the Puzzle.
	 * @return the arrayList of Row objects
	 */
	public ArrayList<Row> getRowList(){
		return puzzle.getRowList();
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
	 * @return true if the user has filled the cell correctly, false otherwise.
	 */
	public boolean checkNumberSolution(int row, int col)
	{
		return puzzle.getCell(row, col).checkNumberSolution();
	}
	
	/**
	 * Returns the first empty cell's coordinates and solution in an array
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
	
	public TimerLabel getTimer()
	{
		return timer;
	}
	
	public Statistics getStatistics()
	{
		return statistics;
	}
}
