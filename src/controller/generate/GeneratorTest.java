package controller.generate;

public class GeneratorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SudokuGenerator sudoku = new SudokuGenerator();
		
		System.out.println(sudoku.tableOut());
		sudoku.solve();
		System.out.println(sudoku.tableOut());
	}

}
