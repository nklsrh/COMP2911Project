package model;

import java.util.ArrayList;
import java.util.Iterator;

public class TestPuzzleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Puzzle puzzle = new Puzzle(1);
		ArrayList<String> gridsStrings = new ArrayList<String>(); 
		
		gridsStrings.add("1 7 4 3 9 6 8 5 2");
		gridsStrings.add("2 8 5 4 1 7 9 6 3");
		gridsStrings.add("3 9 6 5 2 8 1 7 4");
		gridsStrings.add("4 1 7 6 3 9 2 8 5");
		gridsStrings.add("5 2 8 7 4 1 3 9 6");
		gridsStrings.add("6 3 9 8 5 2 4 1 7");
		gridsStrings.add("7 4 1 9 6 3 5 2 8");
		gridsStrings.add("8 5 2 1 7 4 6 3 9");
		gridsStrings.add("9 6 3 2 8 5 7 4 1");

		
		Iterator<String> gsit = gridsStrings.iterator();
		int gridIndex = 0;
		
		while(gsit.hasNext()){
			String[] gridArray = gsit.next().split(" ");
			int n = 0;
			Grid curGrid = puzzle.getGrid(gridIndex);
			ArrayList<ArrayList<Cell>> table = curGrid.getGridTable();
			
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					table.get(i).get(j).setNumber(Integer.parseInt(gridArray[n]));
					n++;
				}
			}
			gridIndex++;
		}
		
		
		
		Iterator<Grid> git = puzzle.getGridList().iterator();
		while(git.hasNext()){
			System.out.print(git.next().toString());
		}
		
		System.out.println();
		System.out.println("Rows:");
		
		Iterator<Row> rit = puzzle.getRowList().iterator();
		System.out.println(" -----------------------------------");
		while(rit.hasNext()){
			System.out.println(rit.next().toString());
			System.out.println(" -----------------------------------");
		}
		
		System.out.println();
		System.out.println("Columns:");
		
		Iterator<Column> cit = puzzle.getColumnList().iterator();
		System.out.println(" -----------------------------------");
		while(cit.hasNext()){
			System.out.println(cit.next().toString());
			System.out.println(" -----------------------------------");
		}
		
		
	}

}
