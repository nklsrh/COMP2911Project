package controller.generate.old;


import java.util.HashSet;

public class GenCell {
	
	public SudokuGenerator sudoku;
	public final int row;
	public final int col;
	public final int grid;
	public int value = 0;
	
	public HashSet<Integer> possible;
	
	public GenCell(SudokuGenerator sudoku, int r, int c, int g, int val){
		this.sudoku = sudoku;
		this.row = r;
		this.col = c;
		this.grid = g;
		this.possible = new HashSet<Integer>();
		
		//load all possibilities
		if(val == 0){
			for(int i = 1; i < 10; i++){
				this.possible.add(i);
			}
			this.value = 0;
		}else{
			this.value = val;
		}
	}
	
	//elemination technique to find number
	public boolean remove(int r, int c, int g, int n){
		if(this.value == 0 && (this.row == r || this.col == c || this.grid == g)){
			this.possible.remove(n);
			if(this.possible.size() == 1){
				this.setValue(this.possible.iterator().next()); //wipe out 
				this.possible.clear();
				return true;
			}
		}
		return false;
	}
	
	public void setValue(int value){
		this.value = value;
		this.possible.clear();
		this.sudoku.remove(this.row, this.col, this.grid, value);
	}
	
	public String toString(){
		return ((Integer) this.value).toString();
	}
	
}
