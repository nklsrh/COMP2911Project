package model;

import java.util.ArrayList;

public class Region implements iRegion {
	private int columnPos;
	private int rowPos;
	private int index;
	private ArrayList <Cell> cells;
	
	public Region(int columnPos, int rowPos, int index) {
		cells = new ArrayList <Cell>(9);
		this.columnPos = columnPos;
		this.rowPos = rowPos;
		this.index = index;
	}
	
	@Override
	public void addCell(int number, int column, int row, int index,	boolean isSolved) {
		Cell cell = new Cell(number, column, row, index, isSolved);
		
		if (!cells.contains(cell))
			cells.add(index, cell);
	}
	
	@Override
	public Cell getCell(int column, int row) {
		Cell cell = null;
		
		for (int i = 0 ; i < cells.size() ; i++) {
			if (cells.get(i).getColumnPos() == column && cells.get(i).getRowPos() == row)
				cell = cells.get(i);
		}
		return cell;
	}

	@Override
	public Cell getCell(int index) {	
		return cells.get(index);
	}
	
	@Override
	public int getColumnPos() {
		return columnPos;
	}

	@Override
	public int getRowPos() {
		return rowPos;
	}

	@Override
	public int getIndex() {
		return index;
	}
	
	@Override
	public boolean contains(int number) {
		for (int i = 0 ; i < cells.size() ; i++)
		{
			if (cells.get(i).getNumber() == number)
				return true;
		}
		return false;
	}
}
