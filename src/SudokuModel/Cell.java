package SudokuModel;

public class Cell implements iCell {
	private int number;
	private int columnPos;
	private int rowPos;
	private int index;
	private boolean isSolved;
	private boolean isWrong;
	private boolean isFilled;
	
	public Cell(int number, int columnPos, int rowPos, int index, boolean isSolved) {
		this.number = number;
		this.columnPos = columnPos;
		this.rowPos = rowPos;
		this.index = index;
		this.isSolved = isSolved;
		if (0 < number && number < 10)
			isFilled = true;
		else
			isFilled = false;
		if (isSolved)
			isWrong = false;
		else 
			isWrong = true;
	}
	
	@Override
	public int getNumber() {
		return number;
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
	public Boolean isSolved() {
		return isSolved;
	}
	
	@Override
	public Boolean isFilled() {
		return isFilled;
	}

	@Override
	public Boolean isWrong() {
		return isWrong;
	}	
}
