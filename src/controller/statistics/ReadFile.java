package controller.statistics;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadFile {
	private Scanner console;
	
	public ReadFile() {
		try {
			console = new Scanner(new FileReader("./stats.txt"));
		}
		catch (FileNotFoundException e) {}
		catch (NoSuchElementException f) {}
	}
	
	public Scanner getConsole() {
		return console;
	}
}
