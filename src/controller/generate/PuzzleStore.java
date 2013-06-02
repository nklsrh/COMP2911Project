package controller.generate;

import java.util.LinkedList;
import java.util.Random;
// TODO: Auto-generated Javadoc
/**
 * This Class is used to store 10 predefined completed Sudoku puzzles which are randomly 
 * chosen to be used to generate a new Sudoku puzzle.
 * @author Ryan Tan, Nikhil Suresh, Nicholas Ho
 *
 */
public class PuzzleStore {
	
	/**
	 * A getter that randomly retrieves one of the 10 predefined completed Sudoku puzzles.
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
		
		puzzles.add("2 3 1 4 9 6 8 7 5 " +
					"9 7 5 8 3 1 6 4 2 " +
					"4 8 6 7 2 5 1 9 3 " +
					"6 1 8 2 5 7 4 3 9 " +
					"7 2 3 9 6 4 5 8 1 " +
					"5 9 4 3 1 8 2 6 7 " +
					"3 6 9 1 8 2 7 5 4 " +
					"1 5 7 6 4 9 3 2 8 " +
					"8 4 2 5 7 3 9 1 6 ");
		
		puzzles.add("9 8 6 3 2 7 1 5 4 " +
					"1 5 2 4 6 8 7 9 3 " +
					"7 3 4 1 9 5 8 2 6 " +
					"8 9 3 6 5 4 2 1 7 " +
					"5 4 1 7 3 2 6 8 9 " +
					"2 6 7 9 8 1 3 4 5 " +
					"3 2 8 5 4 6 9 7 1 " +
					"4 1 9 8 7 3 5 6 2 " +
					"6 7 5 2 1 9 4 3 8 ");
		
		puzzles.add("6 5 8 9 4 2 7 1 3 " +
					"7 2 1 5 3 8 6 4 9 " +
					"9 4 3 1 6 7 8 5 2 " +
					"5 3 7 4 9 6 2 8 1 " +
					"1 6 2 8 7 3 4 9 5 " +
					"8 9 4 2 5 1 3 7 6 " +
					"2 8 6 7 1 9 5 3 4 " +
					"4 7 9 3 2 5 1 6 8 " +
					"3 1 5 6 8 4 9 2 7 ");
		
		puzzles.add("8 2 5 7 6 1 9 3 4 " +
					"1 3 4 2 5 9 7 8 6 " +
					"9 7 6 8 3 4 2 1 5 " +
					"4 5 9 1 8 6 3 2 7 " +
					"6 1 3 4 2 7 8 5 9 " +
					"2 8 7 3 9 5 4 6 1 " +
					"3 9 1 5 4 2 6 7 8 " +
					"5 6 2 9 7 8 1 4 3 " +
					"7 4 8 6 1 3 5 9 2 ");
		
		puzzles.add("7 5 9 4 6 8 2 1 3 " +
					"3 8 4 9 1 2 7 6 5 " +
					"6 1 2 7 3 5 4 9 8 " +
					"2 4 3 6 9 1 8 5 7 " +
					"9 7 1 5 8 4 3 2 6 " +
					"5 6 8 3 2 7 9 4 1 " +
					"1 9 7 8 4 6 5 3 2 " +
					"4 2 5 1 7 3 6 8 9 " +
					"8 3 6 2 5 9 1 7 4 ");
		
		puzzles.add("7 3 9 2 5 8 6 1 4 " +
					"5 1 8 7 6 4 3 9 2 " +
					"2 4 6 1 3 9 8 7 5 " +
					"3 2 7 9 4 5 1 6 8 " +
					"6 8 5 3 1 2 9 4 7 " +
					"1 9 4 6 8 7 5 2 3 " +
					"4 7 1 8 9 3 2 5 6 " +
					"8 6 2 5 7 1 4 3 9 " +
					"9 5 3 4 2 6 7 8 1 ");
		
		return puzzles.get(rand.nextInt(puzzles.size()));
	}
}
