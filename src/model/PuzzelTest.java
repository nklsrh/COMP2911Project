package model;

import java.util.ArrayList;
import java.util.Iterator;

public class PuzzelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Puzzle puzzle = new Puzzle(1);
		ArrayList<String> grids = new ArrayList<String>(); 
		
		grids.add("1 7 4 3 9 6 8 5 2");
		grids.add("2 8 5 4 1 7 9 6 3");
		grids.add("3 9 6 5 2 8 1 7 4");
		grids.add("4 1 7 6 3 9 2 8 5");
		grids.add("5 2 8 7 4 1 3 9 6");
		grids.add("6 3 9 8 5 2 4 1 7");
		grids.add("7 4 1 9 6 3 5 2 8");
		grids.add("8 5 2 1 7 4 6 3 9");
		grids.add("9 6 3 2 8 5 7 4 1");
		
		Iterator<String> git = grids.iterator();
		while(git.hasNext()){
			String[] gridArray = git.next().split(" ");
			
		}
		
	}

}
