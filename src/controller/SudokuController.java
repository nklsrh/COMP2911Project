package controller;

import model.Cell;
import model.Column;
import model.Row;
import model.Puzzle;
import model.Grid;

public class SudokuController {
	private Puzzle puzzle;
	
	public SudokuController(int difficulty) {
		this.puzzle = new Puzzle(difficulty);
	}
}
