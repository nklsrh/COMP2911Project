package controller.generate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import model.Puzzle;

public class SudokuGenerator {
	
	ArrayList<GenCell> cells;
	LinkedList<LinkedList<GenCell>> groups;
	
	public SudokuGenerator(){
		this.cells = new ArrayList<GenCell>(81);
		this.groups = new LinkedList<LinkedList<GenCell>>();
		
		
	}
	
	public boolean remove(int r, int c, int g, int n){
		boolean result = false;
		Iterator<GenCell> gcit = cells.iterator();
		while(gcit.hasNext()){
			GenCell gc = gcit.next();
			if(gc.remove(r, c, g, n)){
				this.remove(gc.row, gc.col, gc.grid, gc.value);
				result = true;
			}
		}
		
		return result;
	}
	
}
