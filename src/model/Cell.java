package model;

public class Cell {
	
	private Integer number;
	private boolean isFixed;
	
	//for non-changing cell
	public Cell(int number) {
		this.number = number;
		this.isFixed = true;
	}
	
	//for a changing cell
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
