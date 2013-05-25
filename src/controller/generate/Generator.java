package controller.generate;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Generator {
	
	private LinkedList<LinkedList<Cells>> groups;
	private Cells[] cells;
	private Random rand;
	private static final int SHUFFLE_NUM = 5;
	
	public Generator(){
		this.groups = new LinkedList<LinkedList<Cells>>();
		this.rand = new Random();
		this.cells = new Cells[81];
		int[] cellInts = new int[81];
		
		//randomly select one of four puzzles
		String randPuzzle = PuzzleStore.getPuzzle();
		
		int i = 0;
	    for(String substr : randPuzzle.split(" ")) {
	    	cellInts[i++] = Integer.parseInt(substr);
	    }
		
	    i=0;
		for(int j=0;  j < 81; j++){
			cells[i] = new Cells(j/9, j%9, cellInts[i++]);
		}
		
		//Add references to lists that contain the groups
        for (i=0; i < 18; i++) {
        	this.groups.add(new LinkedList<Cells>());
        }
        
		int row, col;
		for(i=0; i<81; i++){
			row = cells[i].row;
			col = cells[i].col;
			this.groups.get(row).add(cells[i]);
			this.groups.get(col+9).add(cells[i]);
		}
	}
	
	public void shufflePuzzle(){
		for(int i=0; i < SHUFFLE_NUM; i++){
			shuffleRowsInGrid();
		}
	}
	
	private void shuffleRowsInGrid(){
		//random 2 row ints
		int grid = rand.nextInt(3)*3;
		int fst = rand.nextInt(3) + grid;
		int snd = rand.nextInt(3) + grid;
		while(fst == snd){
			snd = rand.nextInt(3) + grid;
		}
		
		LinkedList<Cells> fstRows = this.groups.get(fst);
		LinkedList<Cells> sndRows = this.groups.get(snd);
		//swaps
		for(int i=0; i<9; i++){
			Cells temp = fstRows.get(i);
			fstRows.remove(i);
			fstRows.add(i, sndRows.get(i));
			sndRows.remove(i);
			sndRows.add(i, temp);
			
			//change rowNums
			fstRows.get(i).row = snd;
			sndRows.get(i).row = fst;
		}
	}
	
	private void shuffleColsInGrid(){
		
	}
	
	private void shuffleGridRows(){
		
	}
	
	private void shuffleGridCols(){
		
	}
	
	public void restackList(){
		//restack the list cells[]
		//according to groups listing
	}
	
	private boolean checkSanity(){
		return false;
	}
	
	public String puzzlePrettyPrintByGroups(){
		String result = "";
		Iterator<LinkedList<Cells>> git = groups.iterator();
		int j = 1;
		while(git.hasNext()){
			
			Iterator<Cells> cit = git.next().iterator();
			int i = 1;
			while(cit.hasNext()){
				result += " "+cit.next().value;
				if((i%3) == 0 && i < 9){
					result += " |";
				}
				i++;
			}
			result += "\n";
			
			if((j%3) == 0 && j < 9)
				result += "-------+-------+-------\n";
			
			if(j == 9){
				break;
			}
			j++;
		}
		
		return result;
	}
	
	public String puzzlePrettyPrint(){
        String result = "";
        int i = 0;
        for (int j = 1; j < 10; j++) {
            for (int k = 1; k < 10; k++){
                result += " "+cells[i++].value; 
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
