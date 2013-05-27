package model;

public class Cell {
	
	private Integer number;
	private Integer solution;
	private boolean isFixed;
	private boolean isEmpty;
	
	/**
	 * A constructor for a cell of a Sudoku game that is "non-changing" i.e. provided at the start of a game.
	 * @param number the number that shall be allocated to the cell that will already be present at the start
	 * of a Sudoku game.
	 */
	public Cell(int number) throws NullPointerException
	{
		this.number = number;
		this.solution = number;
		this.isFixed = true;
		this.isEmpty = false;
	}
	
	/**
	 * A constructor for a cell of a Sudoku game that is "changing" i.e. designated by player input while a 
	 * game of Sudoku is being played.
	 */
	public Cell() {
		this.number = null;
		this.solution = null;
		this.isFixed = false;
		this.isEmpty = true;
	}
	
	/**
	 * A boolean for checking if a cell of a Sudoku game is non-changing/fixed
	 * @return true if fixed, false otherwise
	 */
	public boolean isFixed(){
		return isFixed;
	}
	
	/**
	 * A boolean for checking if a cell of  a Sudoku game is empty.
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty(){
		return isEmpty;
	}
	
	/**
	 * A method to set a cell's isFixed flag to true
	 * @param set determines if a particular cell is a fixed cell or not.
	 */
	public void setFixed(boolean set){
		this.isFixed = set;
	}
	
	/**
	 * A getter that returns the number contained in a particular cell, regardless of whether it is correct or
	 * wrong.
	 * @return number contained in the cell.
	 */
	public Integer getNumber() throws NullPointerException{
		return this.number;
	}
	
	/**
	 * A getter that returns the correct solution to the number that should be contained in a particular cell.
	 * @return correct solution of the cell
	 */
	public Integer getSolution() throws NullPointerException{
		return this.solution;
	}
	
	/**
	 * A setter that gives a number (not necessarily correct) to a particular cell. If the cell used to be 
	 * empty, its isEmpty flag is disabled to false.
	 * @param num the number to be added to the cell.
	 */
	public void setNumber(int num) throws NullPointerException{
		this.number = num;
		this.isEmpty = false;
	}
	
	/**
	 * A setter that gives a number to a particular cell. This number is correct and is part of the solution
	 * to the Sudoku puzzle.
	 * @param num the number solution to be added to the cell
	 * @throws NullPointerException
	 */
	public void setSolution(int num) throws NullPointerException{
		this.solution = num;
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
		this.number = null;
		this.isEmpty = true;
		this.isFixed = false;
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
