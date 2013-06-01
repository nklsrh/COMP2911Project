package controller.statistics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveFile {
	private String path;
	private boolean appendToFile;
	
	public SaveFile(String filePath, boolean appendToFile) {
		path = filePath;
		this.appendToFile = appendToFile;	
	}
	
	public void writeToFile(String text) throws IOException {
		FileWriter write = new FileWriter(path, appendToFile);
		PrintWriter print = new PrintWriter(write);
		
		print.println(text);
		print.close();
	}
	
}
