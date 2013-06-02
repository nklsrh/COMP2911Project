package controller.statistics;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class produces a file reader that takes in a .txt file in the project directory containing
 * saved game statistics. This allows statistics on the welcome screen to persist between each launch
 * of the game.
 * 
 * @author Ryan Tan, Nikhil Suresh and Nicholas Ho
 */
public class ReadFile {
	private Scanner console;
	
	/**
	 * This method reads the stats.txt file located in the project directory, if it is present.
	 */
	public ReadFile() {
		try {
			console = new Scanner(new FileReader("./stats.txt"));
		}
		catch (FileNotFoundException e) {}
		catch (NoSuchElementException f) {}
	}
	
	/**
	 * This is a getter for the scanner contents of stats.txt
	 * 
	 * @return Scanner object containing information from stats.txt
	 */
	public Scanner getConsole() {
		return console;
	}
}
