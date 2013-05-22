package model;

public class Cell {
	
	private Integer number;
	private boolean isFixed;
	
	/**
	 * A constructor for a cell of a Sudoku game that is "non-changing" i.e. provided at the start of a game.
	 * @param number the number that shall be allocated to the cell that will already be present at the start
	 * of a Sudoku game.
	 */
	public Cell(int number) {
		this.number = number;
		this.isFixed = true;
	}
	
	/**
	 * A constructor for a cell of a Sudoku game that is "changing" i.e. designated by player input while a 
	 * game of Sudoku is being played.
	 */
	public Cell() {
		this.number = 0;
		this.isFixed = false;
	}
	
	public boolean isFixed(){
		return isFixed;
	}
	
	public void setFixed(boolean set){
		this.isFixed = set;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int num){
		this.number = num;
	}
}
