package controller.generate.old;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class SudokuGenerator {
	
	private ArrayList<GenCell> cells;
	private LinkedList<LinkedList<GenCell>> groups;
	private Random rand;
	
	public SudokuGenerator(String input, int randInsert){
		this.rand = new Random();

		this.cells = new ArrayList<GenCell>(81);
		this.groups = new LinkedList<LinkedList<GenCell>>();
//		int[] cellInts = new int[81]; // Integer array van input string
		
		// Get known values
//        int i = 0;
//        for(String substr : input.split(" ")) {
//                cellInts[i++] = Integer.parseInt(substr);
//        }
	
        int i=0;
		for(int j=0;  j < 81; j++){
//			cells.add(new GenCell(this, j/9, j%9, (j/3)%3 + (j/27)*3, cellInts[i++]));
			cells.add(new GenCell(this, j/9, j%9, (j/3)%3 + (j/27)*3, 0));
		}
		
		//built groups, 9 rows, 9 cols, 9 groups
		for(int j=0; j < 27; j++){
			this.groups.add(new LinkedList<GenCell>());
		}
		
		//Builds cells into the groups
		int curRow, curCol, curGrid;
		for(i=0;i < 81; i++){
			curRow = cells.get(i).row;
			curCol = cells.get(i).col;
			curGrid = cells.get(i).grid;
			this.groups.get(curRow).add(cells.get(i));
			this.groups.get(curCol+9).add(cells.get(i));
			this.groups.get(curGrid+18).add(cells.get(i));
		}
		//insert random number of valid values
		this.insertRandomValues(randInsert);
	}
	
	
	public void insertRandomValues(int rand_insert){
		
		for(int i=0; i<rand_insert; i++){
			boolean accept = true;
			int to_insert = rand.nextInt(9) + 1;
			int cell_num = rand.nextInt(81);
			GenCell target = cells.get(cell_num);
			
			if(target.value == 0){
				accept = check_groups(target.row, to_insert) && 
						check_groups(target.col + 9, to_insert) &&
						check_groups(target.grid + 18, to_insert);
				
				if(accept){
					target.setValue(to_insert);
				}else{
					i--;  //try again
				}
			}else{
				i--;  //try again
			}
			
		}
	}
	
	
	private boolean check_groups(int group_index ,int value){
		Iterator<GenCell> gcit = this.groups.get(group_index).iterator();
		while(gcit.hasNext()){
			if(gcit.next().value == value){
				return false;
			}
		}
		return true;
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
	
	
	private boolean find_unique(){
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
	private boolean done(){
		Iterator<GenCell> gc = cells.iterator();
		while(gc.hasNext()){
			if(gc.next().value == 0){
				return false;
			}
		}
		return true;
	}
	
	//checks if every row, col, and grid has unique values
	private boolean sound(){
		HashSet<Integer> done = new HashSet<Integer>();
		
		for(LinkedList<GenCell> grp : this.groups){
			for(GenCell cell : grp){
				if(cell.value != 0){
					if(done.contains(cell.value)){
						return false;
					}
					done.add(cell.value);
				}
			}
			done.clear();
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
	
	
	public String puzzlePrettyPrint() {
        String result = "";
        int i = 0;
        for (int j = 1; j < 10; j++) {
            for (int k = 1; k < 10; k++){
                result += " "+cells.get(i++).toString(); 
                if ((k % 3)== 0 && k < 9)
                    result += " |";
            }
            result += "\n";
            if ((j % 3)== 0 && j < 9)
                result += "-------+-------+-------\n";
        }
        return result;
    }
}
