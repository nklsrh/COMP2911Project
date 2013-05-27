package controller.generate;

import java.util.LinkedList;
import java.util.Random;
/**
 * This Class is used to store 4 predefined completed Sudoku puzzles which are randomly chosen to be used to 
 * generate a new Sudoku puzzle.
 * @author Ryan Tan, Nikhil Suresh, Nicholas Ho
 *
 */
public class PuzzleStore {
	
	/**
	 * A getter that randomly retrieves one of the 4 predefined completed Sudoku puzzles.
	 * @return one of the 4 predifined Sudoku puzzles.
	 */
	public static String getPuzzle(){
		Random rand = new Random();
		LinkedList<String> puzzles = new LinkedList<String>();  
		
		puzzles.add("3 4 1 9 7 6 2 8 5 " +
					"5 9 6 1 8 2 3 4 7 " +
					"8 2 7 5 4 3 1 9 6 " +
					"2 6 8 4 3 7 9 5 1 " +
					"4 3 9 2 1 5 6 7 8 " +
					"1 7 5 8 6 9 4 3 2 " +
					"6 8 2 7 9 4 5 1 3 " +
					"9 1 3 6 5 8 7 2 4 " +
					"7 5 4 3 2 1 8 6 9 ");
		
		puzzles.add("8 3 4 7 1 6 2 9 5 " +
					"7 9 6 3 2 5 8 4 1 " +
					"5 2 1 9 8 4 3 7 6 " +
					"6 4 5 2 7 9 1 3 8 " +
					"2 1 7 5 3 8 9 6 4 " +
					"3 8 9 6 4 1 7 5 2 " + 
					"9 5 2 1 6 7 4 8 3 " +
					"1 6 8 4 9 3 5 2 7 " +
					"4 7 3 8 5 2 6 1 9 ");
		
		puzzles.add("4 9 6 5 8 3 2 1 7 " +
					"2 1 7 9 4 6 5 3 8 " +
					"3 5 8 1 7 2 6 9 4 " +
					"6 2 9 4 3 1 8 7 5 " +
					"8 4 5 7 6 9 3 2 1 " +
					"1 7 3 2 5 8 9 4 6 " + 
					"9 6 2 8 1 4 7 5 3 " +
					"5 8 4 3 2 7 1 6 9 " +
					"7 3 1 6 9 5 4 8 2 ");
		
		puzzles.add("9 8 2 6 7 5 1 3 4 " +
					"5 6 4 3 1 8 9 2 7 " +
					"1 7 3 9 2 4 8 5 6 " +
					"2 4 1 5 3 9 7 6 8 " +
					"3 5 7 1 8 6 2 4 9 " +
					"8 9 6 2 4 7 3 1 5 " +
					"4 3 5 7 9 2 6 8 1 " +
					"6 1 9 8 5 3 4 7 2 " +
					"7 2 8 4 6 1 5 9 3 ");
		
		return puzzles.get(rand.nextInt(4));
	}
}
