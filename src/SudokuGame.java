
import view.FrameMain;
/**
 * This class contains the main method for running the Sudoku game.
 * 
 * @author Ryan Tan, Nikhil Suresh and Nicholas Ho
 */
public class SudokuGame {
	
	/**
	 * this method initialises the GUI to launch the game, given a choice of difficulty
	 * 
	 * @param difficulty an int value to represent difficulty of the puzzle chosen by the player.<br>
	 * 1 == easy<br>
	 * 2 == medium<br>
	 * 3 == hard
	 */
	public static void run(int difficulty) {
		try {
			FrameMain frame = new FrameMain();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * executes the run method to begin the game.
	 */
	public static void main(String[] args) {
		run(0);
	}

}
