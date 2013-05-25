package controller.generate.old;

public class GeneratorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SudokuGenerator sudoku = new SudokuGenerator();
		
		System.out.println(sudoku.puzzlePrint());
		sudoku.solve();
		System.out.println(sudoku.puzzlePrint());
	}

}
