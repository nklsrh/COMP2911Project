package model;

public class Cell implements CellInterface {
	
	private Integer number;
	private boolean isFixed;
	
	//for non-changing cell
	public Cell(int number) {
		this.number = number;
		this.isFixed = true;
	}
	
	//for a changing cell
	public Cell() {
		this.number = null;
		this.isFixed = false;
	}
	
	@Override
	public boolean isFixed(){
		return isFixed;
	}
	
	@Override
	public int getNumber() {
		return number;
	}	
}
