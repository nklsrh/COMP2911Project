package model;

public class Cell {
	
	private Integer number;
	private boolean isFixed;
	
<<<<<<< HEAD
	/**
	 * A constructor for a cell of a Sudoku game that is "non-changing" i.e. provided at the start of a game.
	 * @param number the number that shall be allocated to the cell that will already be present at the start
	 * of a Sudoku game.
	 */
	public Cell(int number) throws NullPointerException{

=======
	//for non-changing cell
	public Cell(int number) {
>>>>>>> parent of fff3f8d... Deleted all model files
		this.number = number;
		this.isFixed = true;
	}
	
<<<<<<< HEAD
	/**
	 * A constructor for a cell of a Sudoku game that is "changing" i.e. designated by player input while a 
	 * game of Sudoku is being played.
	 */
	public Cell() {
		this.number = null;

=======
	//for a changing cell
	public Cell() {
		this.number = 0;
>>>>>>> parent of fff3f8d... Deleted all model files
		this.isFixed = false;
	}
	
	public boolean isFixed(){
		return isFixed;
	}
	
	public void setFixed(boolean set){
		this.isFixed = set;
	}
	
<<<<<<< HEAD
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
=======
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int num){
		this.number = num;
	}
	
	/* (non-Javadoc)
	 * Making sure we return something human-readable
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return Integer.toString(number);
>>>>>>> parent of fff3f8d... Deleted all model files
	}
}
