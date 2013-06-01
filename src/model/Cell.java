package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Cell represents a single square within a sudoku grid, thereby this object is replicated 9*9=81
 * times within the sudoku grid. Cell being the main store of data relating to a puzzle game, implemented
 * an array of details such as the number itself, the generated solution, its gridIndex, and other details
 * that aid in the game-play.
 * @author Ryan Tan, Nikhil Suresh and Nicholas Ho
 */
public class Cell {
	
	private Integer number;
	private Integer solution;
	private boolean isFixed;
	private boolean isEmpty;
	private Row row;
	private Column column;
	private Grid grid;
	private ArrayList<Integer> possibilities = new ArrayList<Integer>();
	public Object resetPossibilties;
	
	/**
	 * A constructor for a cell of a Sudoku game that is "non-changing" i.e. provided at the start of a game.
	 * @param number: the number that shall be allocated to the cell
	 */
	public Cell() throws NullPointerException
	{
//		this.number = ;
//		this.solution = number;
		this.isFixed = true;
		this.isEmpty = false;
		resetPossibilties();
	}
	
	public Column getColumn(){
		return column;
	}
	
	public void setColumn(Column column){
		this.column = column;
	}
	
	public Row getRow(){
		return row;
	}
	
	public void setRow(Row row){
		this.row = row;
	}
	
	public Grid getGrid(){
		return grid;
	}
	
	
	public void setGrid(Grid grid){
		this.grid = grid;
	}
	
	/**
	 * A boolean for checking if a cell of a Sudoku game is non-changing/fixed
	 * 
	 * @return true if fixed, false otherwise
	 */
	public boolean isFixed(){
		return isFixed;
	}
	
	/**
	 * A boolean for checking if a cell of a Sudoku game is empty of a number. 
	 * Does not related to the "solution"
	 * 
	 * @return true if it is cleared of a set number, false otherwise.
	 */
	public boolean isEmpty(){
		return isEmpty;
	}
	
	/**
	 * A method to set a cell's isFixed flag to true
	 * 
	 * @param set determines if a particular cell is a fixed cell or not.
	 */
	public void setFixed(boolean set){
		this.isFixed = set;
	}
	
	/**
	 * A getter that returns the current number contained in a particular cell, 
	 * regardless of whether it is correct or wrong.
	 * 
	 * @return number contained in the cell.
	 */
	public Integer getNumber() throws NullPointerException{
		return this.number;
	}
	
	/**
	 * A getter that returns the correct solution to the number that should be contained
	 * in a particular cell.
	 * 
	 * @return correct solution of the cell
	 */
	public Integer getSolution() throws NullPointerException{
		return this.solution;
	}
	
	/**
	 * A setter that gives a number to a particular cell. This number is correct and is part of the solution
	 * to the Sudoku puzzle.
	 * 
	 * @param num the number solution to be added to the cell
	 * @throws NullPointerException
	 */
	public void setSolution(int num) throws NullPointerException{
		this.solution = num;
	}
	
	/**
	 * A setter that gives a number (not necessarily correct) to a particular cell. 
	 * If the cell used to be empty, its isEmpty flag is disabled to false.
	 * 
	 * @param num the number to be added to the cell.
	 */
	public void setNumber(int num) throws NullPointerException{

		if(this.number != null){
			addNeighbourPossibles(this.number);
		}
		
		this.isEmpty = false;
		this.number = num;
//		removePossibilties();
		removeNeighbourPossibles(num);		
	}
	
	
	/**
	 * This method compares the number placed by a user with the predefined solution of the Sudoku game, to
	 * see if the user has filled the cell with the correct number.
	 * @return true if the user has filled the cell correctly, false otherwise.
	 */
	public boolean checkNumberSolution()
	{
		boolean result = false;
		if (this.number == this.solution)
		{
			result = true;
		}
		else
		{
			result = false;
		}
		return result;
	}
	
	/**
	 * This method resets a cell such that it is completely empty.
	 */
	public void nullCell(){
		if(this.number != null){
			addNeighbourPossibles(this.number);
		}
		this.number = null;
		this.isEmpty = true;
		this.isFixed = false;
		resetPossibilties();		
	}

	/**
	 * Returns the list of possible numbers to UI. 
	 *
	 * @return the possibilities
	 */
	public ArrayList<Integer> getPossibilities(){
		return possibilities;
	}
	
	public void resetPossibilties(){
		this.possibilities = new ArrayList<Integer>();
		for(int i=1; i<10; i++){
			possibilities.add(i);
		}
	}
	
	public void addPossibilties(Integer value){
		if(!this.possibilities.contains(value)){
			this.possibilities.add(value);
//			this.possibilities.add(this.possibilities.indexOf(value), element)
		}
	}
	
	public void removePossibilties(int value){
		if(this.possibilities.contains(value)){
			this.possibilities.remove(possibilities.indexOf(value));
		}
	}
	
	/**
	 * Completely clears the list of possibilities.
	 */
	public void removePossibilties(){
		this.possibilities = new ArrayList<Integer>();
	}
	
	private void removeNeighbourPossibles(int num){
		Iterator<Cell> rit = row.getList().iterator();
		while(rit.hasNext()){
			Cell rowCell = rit.next();
			if(this != rowCell){
				rowCell.removePossibilties(num);
			}
		}
		
		Iterator<Cell> cit = column.getList().iterator();
		while(cit.hasNext()){
			Cell columnCell = cit.next();
			if(this != columnCell){
				columnCell.removePossibilties(num);
			}
		}
		
		Iterator<ArrayList<Cell>> git = grid.getGridTable().iterator();
		while(git.hasNext()){
			Iterator<Cell> gRow = git.next().iterator(); 
			while(gRow.hasNext()){
				Cell gridCell = gRow.next();
				if(this != gridCell){
					gridCell.removePossibilties(num);
				}
			}
		}
	}
	
	private void addNeighbourPossibles(int num){
		Iterator<Cell> rit = row.getList().iterator();
		while(rit.hasNext()){
			Cell rowCell = rit.next();
			if(this != rowCell){
				rowCell.addPossibilties(num);
			}
		}
		
		Iterator<Cell> cit = column.getList().iterator();
		while(cit.hasNext()){
			Cell columnCell = cit.next();
			if(this != columnCell){
				columnCell.addPossibilties(num);
			}
		}
		
		Iterator<ArrayList<Cell>> git = grid.getGridTable().iterator();
		while(git.hasNext()){
			Iterator<Cell> gRow = git.next().iterator(); 
			while(gRow.hasNext()){
				Cell gridCell = gRow.next();
				if(this != gridCell){
					gridCell.addPossibilties(num);
				}
			}
		}
	}
		
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		if (this.number != null)
		{
			return this.number.toString();
		}
		else
		{
			return "0";
		}
	}
}
