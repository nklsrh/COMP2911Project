package controller.generate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import model.Cell;
import model.Puzzle;
import model.Row;

public class Generator {
	
	private LinkedList<LinkedList<Square>> rowGroups;
	private LinkedList<LinkedList<Square>> colGroups;
	private RowsComparator rc;
	private ColsComparator cc;
	private Square[] cells;
	private Random rand;
	private final static int NUM_SHUFFLES = 5;
	
	public Generator(){
		rc = new RowsComparator();
		cc = new ColsComparator();
		this.rowGroups = new LinkedList<LinkedList<Square>>();
		this.colGroups = new LinkedList<LinkedList<Square>>();
		this.rand = new Random();
		this.cells = new Square[81];
		int[] cellInts = new int[81];
		
		//randomly select one of four puzzles
		String randPuzzle = PuzzleStore.getPuzzle();
		
		int i = 0;
	    for(String substr : randPuzzle.split(" ")) {
	    	cellInts[i++] = Integer.parseInt(substr);
	    }
		
	    i=0;
		for(int j=0;  j < 81; j++){
			cells[i] = new Square(j/9, j%9, cellInts[i++]);
		}
		
		//Add references to lists that contain the groups
        for (i=0; i < 9; i++) {
        	this.rowGroups.add(new LinkedList<Square>());
        }
        
        for (i=0; i < 9; i++) {
        	this.colGroups.add(new LinkedList<Square>());
        }
        
		int row, col;
		for(i=0; i<81; i++){
			row = cells[i].row;
			col = cells[i].col;
			this.rowGroups.get(row).add(cells[i]);
			this.colGroups.get(col).add(cells[i]);
		}
	}
	
	public void shufflePuzzle(){
		for(int i=0; i<NUM_SHUFFLES; i++){
			shuffleRowsInGrid();
			shuffleColsInGrid();
		}
		
		try {
			this.checkSanity();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	private void shuffleRowsInGrid(){
		//random 2 row ints
		int grid = rand.nextInt(3) * 3;
		int fst = rand.nextInt(3) + grid;
		int snd = rand.nextInt(3) + grid;
		while(fst == snd){
			snd = rand.nextInt(3) + grid;
		}
		
		LinkedList<Square> fstRows = this.rowGroups.get(fst); //gets first row
		LinkedList<Square> sndRows = this.rowGroups.get(snd); //gets second row
		
		//swap
		for(int i=0; i<9; i++){
			Square temp = fstRows.get(i);
			fstRows.set(i, sndRows.get(i));
			sndRows.set(i, temp);
			
			//change colNumbers first
			int tempCol = fstRows.get(i).col;
			fstRows.get(i).col = sndRows.get(i).col;
			sndRows.get(i).col = tempCol;
			
			//change rowNumbers first
			int tempRow = fstRows.get(i).row;
			fstRows.get(i).row = sndRows.get(i).row;
			sndRows.get(i).row = tempRow;
		}
		
		Iterator<LinkedList<Square>> colit = colGroups.iterator();
		while(colit.hasNext()){
			Collections.sort(colit.next(), rc);
		}
		
	}
	
	private void shuffleColsInGrid(){
		//random 2 row ints
		int grid = rand.nextInt(3) * 3;
		int fst = rand.nextInt(3) + grid;
		int snd = rand.nextInt(3) + grid;
		while(fst == snd){
			snd = rand.nextInt(3) + grid;
		}
		
		LinkedList<Square> fstCols = this.colGroups.get(fst); //gets first row
		LinkedList<Square> sndCols = this.colGroups.get(snd); //gets second row
		
		//swap
		for(int i=0; i<9; i++){
			//swap object
			Square temp = fstCols.get(i);
			fstCols.set(i, sndCols.get(i));
			sndCols.set(i, temp);
			
			//swap row
			int tempRow = fstCols.get(i).row;
			fstCols.get(i).row = sndCols.get(i).row;
			sndCols.get(i).row = tempRow;
			
			//swap col
			int tempCol = fstCols.get(i).col;
			fstCols.get(i).col = sndCols.get(i).col;
			sndCols.get(i).col = tempCol;
		}
		
		Iterator<LinkedList<Square>> rowit = rowGroups.iterator();
		while(rowit.hasNext()){
			Collections.sort(rowit.next(), cc);
		}
	}
	
	
	private void checkSanity() throws IllegalArgumentException{
		
		//checks every group has 1..9
		for(LinkedList<Square> row : rowGroups){
			LinkedList<Integer> required = getRequired();
			for(Square sq : row){
				required.remove(required.indexOf(sq.value));
			}
			if(!required.isEmpty()){
				throw new IllegalArgumentException("ROWS NOT SATISFIED");
			}
		}
		
		//checks every group has 1..9
		for(LinkedList<Square> col : colGroups){
			LinkedList<Integer> required = getRequired();
			for(Square sq : col){
				required.remove(required.indexOf(sq.value));
			}			
			if(!required.isEmpty()){
				throw new IllegalArgumentException("COLUMNS NOT SATISFIED");
			}
		}
		
	}
	
	private LinkedList<Integer> getRequired(){
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(int i=1; i<10; i++){
			list.add(i);
		}
		return list;
	}
	
	public Puzzle packageUp(Puzzle puzzle){
		//according to groups listing
		ArrayList<Row> puzzleRows = puzzle.getRowList();
		for(int i=0; i<puzzleRows.size(); i++){
			ArrayList<Cell> cellList = puzzleRows.get(i).getList();
			LinkedList<Square> sqList = rowGroups.get(i);
			for(int j=0; j<cellList.size(); j++){
				int number = sqList.get(j).value;
				//cellList.get(j).setNumber(number);	
				cellList.get(j).setSolution(number);
			}
		}
		
		return puzzle;
	}
	
	public String puzzlePrettyPrintByGroups(String type){
		String result = "";
		Iterator<LinkedList<Square>> git = null;
		if(type.equals("row")){
			git = rowGroups.iterator();
		}else if(type.equals("col")){
			git = colGroups.iterator();
		}
		
		if(git != null){
			int j = 1;
			while(git.hasNext()){
				
				Iterator<Square> cit = git.next().iterator();
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
