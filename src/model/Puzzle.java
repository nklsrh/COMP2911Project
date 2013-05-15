package model;

import java.util.ArrayList;

public class Puzzle implements PuzzleInterface {
	private static final int NUM_GRIDS = 9;
	private static final int NUM_COLUMNS = 9;
	private static final int NUM_ROWS = 9;
	
	private int difficulty;
	private ArrayList<Grid> puzzelGridStore;
	
	public Puzzle(int difficulty) {
		this.difficulty = difficulty;
		puzzelGridStore = new ArrayList<Grid>();
		puzzelInit();
	}
	
	//initialises & creates the grids
	private void puzzelInit(){
		//make 9 grids
		for(int i=0; i < NUM_GRIDS; i++){
			puzzelGridStore.add(gridInit(i));
		}
	}
	
	//initialises & creates the grid cells
	private Grid gridInit(int gridIndex){
		Grid grid = new Grid(gridIndex);
		
		for(int i=0; i<3; i++){
			grid.getGridTable().add(new ArrayList<Cell>());
			for(int j=0; j<3; j++){
				grid.getGridTable().get(i).add(new Cell());
			}
		}
		return grid;
	}
	
	@Override
	public Cell getCell(int row, int column){
		return null;
	}
	
	@Override
	public int getDifficulty() {
		return difficulty;
	}
	
//	@Override
	public Grid getGrid(int gridIndex) {
		return puzzelGridStore.get(gridIndex);
	}


}
