package controller.generate;

/**
 * Square Class, simplified version of a cell with a different architecture purposed
 * of storing for row and col swaps.
 */
public class Square {
	
	/** The row. */
	public int row;
	
	/** The col. */
	public int col;
	
	/** The value. */
	public int value;
	
	/**
	 * Instantiates a new square of row, col and value.
	 *
	 * @param row the row
	 * @param col the col
	 * @param value the value
	 */
	public Square(int row, int col, int value){
		this.row = row;
		this.col = col;
		this.value = value;
	}
	
	
}
