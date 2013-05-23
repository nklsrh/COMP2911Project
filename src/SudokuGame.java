import controller.PuzzleControl;
import view.FrameMain;

public class SudokuGame {
	
	public static void run(int difficulty) {
			
		PuzzleControl pc = new PuzzleControl();
		pc.createPuzzle();
		
		try {
			FrameMain frame = new FrameMain(pc);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		run(0);
	}

}
