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
	
	public boolean isFixed(){
		return isFixed;
	}
	public boolean isEmpty(){
		return isEmpty;
	}
	
	public void setFixed(boolean set){
		this.isFixed = set;
	}
	
	public Integer getNumber() throws NullPointerException{
		return this.number;
	}
	
	public Integer getSolution() throws NullPointerException{
		return this.solution;
	}
	
	public void setNumber(int num) throws NullPointerException{
		this.number = num;
		this.isEmpty = false;
	}
	
	public void setSolution(int num) throws NullPointerException{
		this.solution = num;
	}
	
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
