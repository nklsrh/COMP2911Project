import controller.PuzzleControl;
import view.FrameMain;

public class SudokuGame {
	
	public static void run(int difficulty) {
		try {
			FrameMain frame = new FrameMain();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		run(0);
	}

}
