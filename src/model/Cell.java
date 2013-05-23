package model;

public class Cell {
	
	private Integer number;
	private boolean isFixed;
	
	/**
	 * A constructor for a cell of a Sudoku game that is "non-changing" i.e. provided at the start of a game.
	 * @param number the number that shall be allocated to the cell that will already be present at the start
	 * of a Sudoku game.
	 */
	public Cell(int number) throws NullPointerException{

		this.number = number;
		this.isFixed = true;
	}
	
	/**
	 * A constructor for a cell of a Sudoku game that is "changing" i.e. designated by player input while a 
	 * game of Sudoku is being played.
	 */
	public Cell() {
		this.number = null;

		this.isFixed = false;
	}
	
	public boolean isFixed(){
		return isFixed;
	}
	
	public void setFixed(boolean set){
		this.isFixed = set;
	}
	
	public Integer getNumber() throws NullPointerException{
		return this.number;
	}
	
	public void setNumber(int num) throws NullPointerException{
		if(this.number != null){
			this.number = num;
		}
	}
	
	public void nullCell(){
		this.number = null;
	}
}
