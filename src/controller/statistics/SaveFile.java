package controller.statistics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class produces a file writer that takes in a filepath. This represents the save location of the 
 * file to be written and writes the file, given a string of text to be input into the file.
 * 
 * @author Ryan Tan, Nikhil Suresh and Nicholas Ho
 */
public class SaveFile {
	
	/** The path. */
	private String path;
	
	/** The append to file. */
	private boolean appendToFile;
	
	/**
	 * Instantiates a new save file.
	 *
	 * @param filePath the file path
	 * @param appendToFile for our purposes, appendToFile is always false, so that the file is overwritten
	 * every time.
	 */
	public SaveFile(String filePath, boolean appendToFile) {
		path = filePath;
		this.appendToFile = appendToFile;	
	}
	
	/**
	 * This method writes a string to the newly-produced file.
	 *
	 * @param text the contents to be written to the file.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeToFile(String text) throws IOException {
		FileWriter write = new FileWriter(path, appendToFile);
		PrintWriter print = new PrintWriter(write);
		
		print.println(text);
		print.close();
	}
	
}
