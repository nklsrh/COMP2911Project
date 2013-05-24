package controller.generate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class SudokuGenerator {
	
	ArrayList<GenCell> cells;
	LinkedList<LinkedList<GenCell>> groups;
	
	public SudokuGenerator(){
		this.cells = new ArrayList<GenCell>();
		this.groups = new LinkedList<LinkedList<GenCell>>();
		
		for(int i=0; i < 81; i++){
			cells.add(new GenCell(this, i/9, i%9, (i/3)%3 + (i/27)*3, 0));
		}
		
		//built groups, 9 rows, 9 cols, 9 groups
		for(int j=0; j < 27; j++){
			this.groups.add(new LinkedList<GenCell>());
		}
		
		//Builds cells into the groups
		int curRow, curCol, curGrid;
		for(int i=0;i < 81; i++){
			curRow = cells.get(i).row;
			curCol = cells.get(i).col;
			curGrid = cells.get(i).grid;
			this.groups.get(curRow).add(cells.get(i));
			this.groups.get(curCol+9).add(cells.get(i));
			this.groups.get(curGrid+18).add(cells.get(i));
		}
	}
	
	public void solve(){
		boolean updated = true;
		
		Iterator<GenCell> cit = cells.iterator();
		while(cit.hasNext()){
			GenCell next = cit.next();
			if(next.value > 0){
				this.remove(next.row, next.col, next.grid, next.value);
			}
		}
		
		do{
			updated = this.find_unique();
		}while(updated);
		
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
	
	public boolean find_unique(){
		boolean found = false;
		GenCell unique_cell = null;
		
		for(LinkedList<GenCell> grp : this.groups){
			for(int pos = 1; pos < 10; pos++){
				for(GenCell cell : grp){
					if(cell.possible.contains(pos)){
						if(unique_cell == null){
							unique_cell = cell;
						}else{
							break;
						}
					}
					if(unique_cell != null){
						found = true;
						unique_cell.setValue(pos);
					}
					if(this.done() && this.sound()){
						return false;
					}
				}
			}
		}
		return found;
	}
	
	
	//checks every cell if they already have a solution
	public boolean done(){
		Iterator<GenCell> gc = cells.iterator();
		while(gc.hasNext()){
			if(gc.next().value == 0){
				return false;
			}
		}
		return true;
	}
	
	//checks if every row, col, and grid has unique 1-9 values
	public boolean sound(){
		HashSet<Integer> done = new HashSet<Integer>();
		
		for(LinkedList<GenCell> grp : this.groups){
			for(GenCell cell : grp){
				if(cell.value != 0){
					if(done.contains(cell.value)){
						return false;
					}
				}
				done.clear();
			}
		}
		return true;
	}
	
	public String toString(){
		String result = "";
		Iterator<LinkedList<GenCell>> grps_it = groups.iterator();
		while(grps_it.hasNext()){
			Iterator<GenCell> gc_it = grps_it.next().iterator();
			while(gc_it.hasNext()){
				result += Integer.toString(gc_it.next().value) + " ";
			}
			result += "\n";
		}
		return result;
	}
}
