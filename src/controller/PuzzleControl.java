package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.*;

public class PuzzleControl {
	private Puzzle puzzle;

	public void createPuzzle()
	{
		puzzle = createPuzzleAndSolution(populateSolutionFromArrayString(createPuzzleArrayString()), createMissingCells());			
	}
	
	public int[][] createMissingCells()
	{
		int[][] missingCells = {
				{2,3,4,5,6,7},				
				{1,7,6,6,7,5},
				{2,5,2,5},				
				{0,63,2,-10},
				{3,5,1,2},				
				{6,7,4},
				{3,5,1},				
				{1,2,5},
				{6,1,2}
		};
		return missingCells;
	}
	
	public ArrayList<String> createPuzzleArrayString()
	{
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
		
		return gridsStrings;
	}
	
	
	public Puzzle createPuzzleAndSolution(Puzzle s, int[][] missingCells)
	{
		Puzzle p = s;
		for (int y = 0; y < s.getRowList().size(); y++)
		{
			for (int i = 0; i < missingCells[y].length; i++)
			{
				p.setCellAsEmpty(y, missingCells[y][i]);
			}
		}	
		return p;
	}
	
	
	public Puzzle populateSolutionFromArrayString(ArrayList<String> gridsStrings)
	{
		Puzzle pz = new Puzzle();		
		Iterator<String> gsit = gridsStrings.iterator();
		int gridIndex = 0;
		
		while(gsit.hasNext()){
			String[] gridArray = gsit.next().split(" ");
			int n = 0;
			Grid curGrid = pz.getGrid(gridIndex);
			ArrayList<ArrayList<Cell>> table = curGrid.getGridTable();
			
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					table.get(i).get(j).setNumber(Integer.parseInt(gridArray[n]));
					table.get(i).get(j).setSolution(Integer.parseInt(gridArray[n]));
					n++;
				}
			}
			gridIndex++;
		}
		return pz;
	}
		
	
	public Cell getCell(int row, int column){
		return puzzle.getCell(row, column);
	}
	public void setCell(int row, int column, int value){
		puzzle.setCell(row, column, value);
	}
	public Grid getGrid(int gridIndex) {
		return puzzle.getGrid(gridIndex);
	}
	public ArrayList<Grid> getGridList(){
		return puzzle.getGridList();
	}	
	public ArrayList<Row> getRowList(){
		return puzzle.getRowList();
	}	
	public ArrayList<Column> getColumnList(){
		return puzzle.getColumnList();
	}
}
