import controller.PuzzleControl;
import view.FrameMain;

public class SudokuGame {
	
	public static void run(int difficulty) {
		System.out.println("Hello Sudoku");
			
		PuzzleControl pc = new PuzzleControl();
		pc.createPuzzle();
		
		try {
			FrameMain frame = new FrameMain(pc.getPuzzle());
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		run(0);
	}

}
