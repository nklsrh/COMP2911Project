package controller;

import java.util.ArrayList;
import java.util.Iterator;

import controller.generate.Generator;

import model.*;

public class PuzzleControl {
	private Puzzle puzzle;
	
	
	public void createPuzzle()
	{
		Generator gen = new Generator();
		gen.shufflePuzzle();
		puzzle = new Puzzle();
		puzzle = createPuzzleAndSolution(gen.packageUp(puzzle), createMissingCells());
		
		// old code with predetermined puzzles
//		puzzle = createPuzzleAndSolution(populateSolutionFromArrayString(createPuzzleArrayString()), createMissingCells());			
		System.out.println(puzzle.toString());
	}
	
	public int[][] createMissingCells()
	{
		int[][] missingCells = {
				{2,3,4,5,6,7},				
				{1,7,6,6,7,5},
				{2,5,2,5},				
				{0,63,2,-10},	// got some cool test cases to ensure it doesn't break
				{3,5,1,2},				
				{6,7,4},
				{3,5,1},				
				{1,2,5},
				{6,1,2}
		};
		return missingCells;
	}
	
	public ArrayList<String> createPuzzleArrayString()
	{
		ArrayList<String> gridsStrings = new ArrayList<String>(); 
		
		gridsStrings.add("1 7 4 3 9 6 8 5 2");
		gridsStrings.add("2 8 5 4 1 7 9 6 3");
		gridsStrings.add("3 9 6 5 2 8 1 7 4");
		gridsStrings.add("4 1 7 6 3 9 2 8 5");
		gridsStrings.add("5 2 8 7 4 1 3 9 6");
		gridsStrings.add("6 3 9 8 5 2 4 1 7");
		gridsStrings.add("7 4 1 9 6 3 5 2 8");
		gridsStrings.add("8 5 2 1 7 4 6 3 9");
		gridsStrings.add("9 6 3 2 8 5 7 4 1");
		
		return gridsStrings;
	}
	
	
	public Puzzle createPuzzleAndSolution(Puzzle s, int[][] missingCells)
	{
		Puzzle p = s;
		for (int y = 0; y < s.getRowList().size(); y++)
		{
			for (int i = 0; i < missingCells[y].length; i++)
			{
				p.setCellAsEmpty(y, missingCells[y][i]);
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
				if (!puzzle.getCell(i, j).isFixed() && puzzle.getCell(i, j).isEmpty())
				{
					return new int[] {i, j, puzzle.getCell(i, j).getSolution()};
				}
			}
		}
		return null;
	}
}
