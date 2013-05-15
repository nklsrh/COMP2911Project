package model;

import java.util.ArrayList;

public class Puzzle implements PuzzleInterface {
<<<<<<< HEAD
	private final int NUM_COLUMNS = 9;
	private final int NUM_ROWS = 9;
	
	private int difficulty;
	private ArrayList <Grid> regions;
	
	public Puzzle(int difficulty) {
		this.difficulty = difficulty;
		regions = new ArrayList <Grid>(9);
	}
	
	@Override
	public Cell getCell(int row, int column) {
		int regionRow = row / 3;
		int regionColumn = column / 3;
		Grid region = getRegion(regionColumn, regionRow);		// Cell is in this region
		int cellRow = row - (regionRow * 3);
		int cellColumn = column - (regionColumn * 3);
		Cell cell = region.getCell(cellColumn, cellRow);
		
		return cell;
	}

	@Override
	public boolean isSolved() {
		boolean solved = true;
		
		for (int i = 0 ; i < NUM_COLUMNS ; i++) {
			for (int j = 0 ; j < NUM_ROWS ; j++) {
				if (getCell(j, i).isWrong())
					return false;
			}
		}
		return solved;
	}

=======
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
	
	
>>>>>>> master
	@Override
	public int getDifficulty() {
		return difficulty;
	}
	
<<<<<<< HEAD
	@Override
	public Grid getRegion(int column, int row) {
		Grid region = null;
		
		for (int i = 0 ; i < regions.size() ; i++) {
			if (regions.get(i).getColumnPos() == column && regions.get(i).getRowPos() == row)
				region = regions.get(i);
		}
		return region;
=======
//	@Override
	public Grid getRegion(int gridIndex) {
		return puzzelGridStore.get(gridIndex);
>>>>>>> master
	}

}
