package controller.generate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import model.Cell;
import model.Puzzle;
import model.Row;

/**
 *  Generator Class, This is a Sudoku puzzle generator. It creates a full puzzle through 
 *  transformations of rows and columns across grids of stored sudoku puzzles. 
 *  Idea taken from [http://ljkrakauer.com/Sudoku/transformations.htm]. 
 *  To not have confusion between java files, this generator is separate from the sudoku 
 *  playing classes, the only interaction is the Generator.packageUp(Puzzle) function which 
 *  takes in a puzzle and fill in the square. Cells in Sudoku are named Squares here to not 
 *  cause confusion, being the same but different. 
 *  
 *  Initialization is done by first filling up an empty puzzle with one of the ten stored 
 *  puzzles. The 81 square are placed into 18 groups of 9 rows and 9 columns as references, 
 *  one square Swap operations are done by swapping either the row or column index per turn, then a 
 *  Collection.sort operation is applied to align the square within the other group.
 *  
 *  @author Ryan Tan
 */
public class Generator {
	
	/** The row groups. */
	private LinkedList<LinkedList<Square>> rowGroups;
	
	/** The col groups. */
	private LinkedList<LinkedList<Square>> colGroups;
	
	/** The rc for sorting squares according to row index. */
	private RowsComparator rc;
	
	/** The cc for sorting squares according to column index. */
	private ColsComparator cc;
	
	/** The squares . */
	private Square[] squares;
	
	/** The random number generator for nextInt()s. */
	private Random rand;
	
	/** The Constant NUM_SHUFFLES for the fixed number of row and shuffles 
	 * applied to a puzzle. */
	private final static int NUM_SHUFFLES = 25;
	
	/**
	 * Instantiates a new generator, creates the new squares 
	 * and stores references into the row and column lists.
	 */
	public Generator(){
		rc = new RowsComparator();
		cc = new ColsComparator();
		this.rowGroups = new LinkedList<LinkedList<Square>>();
		this.colGroups = new LinkedList<LinkedList<Square>>();
		this.rand = new Random();
		this.squares = new Square[81];
		int[] squareInts = new int[81];
		
		//randomly select one of ten puzzles
		String randPuzzle = PuzzleStore.getPuzzle();
		
		int i = 0;
	    for(String substr : randPuzzle.split(" ")) {
	    	squareInts[i++] = Integer.parseInt(substr);
	    }
		
	    i=0;
		for(int j=0;  j < 81; j++){
			squares[i] = new Square(j/9, j%9, squareInts[i++]);
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
			row = squares[i].row;
			col = squares[i].col;
			this.rowGroups.get(row).add(squares[i]);
			this.colGroups.get(col).add(squares[i]);
		}
	}
	
	/**
	 * Shuffles the puzzle first in rows and then grids by the 
	 * constant number of shuffles. Checks sanity of board values after. 
	 */
	public void shufflePuzzle(){
		for(int i=0; i<NUM_SHUFFLES; i++){
			shuffleRowsInGrid();
			shuffleColsInGrid();
		}
		
		//sanity checker to check if values do not clash
		//from the numerous swaps
		try {
			this.checkSanity();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Shuffle rows between the 3 rows within a grid. It swaps the 
	 * row square objects first, updates the row & col indexes and then 
	 * it sorts the columns after according to the index.
	 */
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
		
		//swaps
		for(int i=0; i<9; i++){
			Square temp = fstRows.get(i);
			fstRows.set(i, sndRows.get(i));
			sndRows.set(i, temp);
			
			//change colNumbers
			int tempCol = fstRows.get(i).col;
			fstRows.get(i).col = sndRows.get(i).col;
			sndRows.get(i).col = tempCol;
			
			//change rowNumbers to coincide with the squares
			int tempRow = fstRows.get(i).row;
			fstRows.get(i).row = sndRows.get(i).row;
			sndRows.get(i).row = tempRow;
		}
		
		//Sorts the 
		Iterator<LinkedList<Square>> colit = colGroups.iterator();
		while(colit.hasNext()){
			Collections.sort(colit.next(), rc);
		}
		
	}
	
	/**
	 * Shuffle columns between the 3 rows within a grid. It swaps the 
	 * row square objects first, updates the row & col indexes and then 
	 * it sorts the row after according to the index.
	 */
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
	
	
	/**
	 * Check sanity, if every group of row and column has the square 
	 * numbers from 1..9. Sudoku generate is correct in all cases thus far, but breaking 
	 * the program would be essential to prevent an invalid sudoku. Thus throwing an 
	 * IllegalArguementException exception. 
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 */
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
	
	/**
	 * Helper function for checkSanity(), populates the "Required" list from
	 * 1..9 so it wont have to be done twice.
	 *
	 * @return the required
	 */
	private LinkedList<Integer> getRequired(){
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(int i=1; i<10; i++){
			list.add(i);
		}
		return list;
	}
	
	/**
	 * Packages up a puzzle object and fills all cell solutions.  
	 *
	 * @param puzzle the puzzle
	 * @return the puzzle
	 */
	public Puzzle packageUp(Puzzle puzzle){
		//according to groups listing
		ArrayList<Row> puzzleRows = puzzle.getRowList();
		for(int i=0; i<puzzleRows.size(); i++){
			ArrayList<Cell> cellList = puzzleRows.get(i).getList();
			LinkedList<Square> sqList = rowGroups.get(i);
			for(int j=0; j<cellList.size(); j++){
				int number = sqList.get(j).value;
				cellList.get(j).setSolution(number);
			}
		}
		return puzzle;
	}
	
	/**
	 * Puzzle pretty prints only row or col groups. Used for printing out
	 * and checking if the row puzzle rotates to col puzzle.
	 *
	 * @param type the type
	 * @return the string
	 */
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
}
