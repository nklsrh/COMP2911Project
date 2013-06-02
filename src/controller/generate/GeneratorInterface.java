package controller.generate;

import model.Puzzle;

// TODO: Auto-generated Javadoc
/**
 * The Interface for the GeneratorInterface.
 */
public interface GeneratorInterface {
	
	/**
	 * Packages up a puzzle object and fills all cell solutions.
	 *
	 * @param puzzle the puzzle
	 * @return the puzzle
	 */
	public Puzzle packageUp(Puzzle puzzle);
	
}
