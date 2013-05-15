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
<<<<<<< HEAD
=======
		this.isFixed = false;
>>>>>>> master
	}
	
	public boolean isFixed(){
		return isFixed;
	}
	
	@Override
	public int getNumber() {
		return number;
	}	
}
