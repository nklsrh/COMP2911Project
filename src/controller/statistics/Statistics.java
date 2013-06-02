package controller.statistics;


import java.io.File;
import java.io.IOException;


// TODO: Auto-generated Javadoc
/**
 * This class handles all statistics that are present and displayed in the game.
 * 
 * @author Ryan Tan, Nikhil Suresh and Nicholas Ho
 */
public class Statistics
{	
	
	/** The difficulty. */
	private int difficulty;
	
	/** The action count. */
	private int actionCount;
	
	/** The button count. */
	private int buttonCount;
	
	/** The cheat count. */
	private int cheatCount;
	
	/** The hint count. */
	private int hintCount;
	
	/** The progress count. */
	private int progressCount;
	
	/** The empty boxes count. */
	private int emptyBoxesCount;
	
	/** The best easy time. */
	private String bestEasyTime;
	
	/** The best medium time. */
	private String bestMediumTime;
	
	/** The best hard time. */
	private String bestHardTime;
	
	/** The num easy games. */
	private int numEasyGames;
	
	/** The num medium games. */
	private int numMediumGames;
	
	/** The num hard games. */
	private int numHardGames;
	
	/** The total cheat. */
	private int totalCheat;
	
	/** The num puzzles started. */
	private int numPuzzlesStarted;
	
	/** The num puzzles finished. */
	private int numPuzzlesFinished;
	
	/** The file. */
	private File file;
	
	/** The accept file. */
	private ReadFile acceptFile;
	
	/** The save file. */
	private SaveFile saveFile;
	
	/** The Constant EASY. */
	private static final int EASY = 1;
	
	/** The Constant MEDIUM. */
	private static final int MEDIUM = 2;
	
	/** The Constant HARD. */
	private static final int HARD = 3;
	
	/**
	 * This constructor initialises all integer values to 0, and times to an arbitrarily large number.
	 */
	public Statistics()
	{
		difficulty = 0;
		actionCount = 0;
		buttonCount = 0;
		cheatCount = 0;
		hintCount = 0;
		progressCount = 0;
		emptyBoxesCount = 0;
		bestEasyTime = "99:00";
		bestMediumTime = "99:00";
		bestHardTime = "99:00";
		numEasyGames = 0;
		numMediumGames = 0;
		numHardGames = 0;
		totalCheat = 0;
		numPuzzlesStarted = 0;
		numPuzzlesFinished = 0;
		file = new File("./stats.txt");
		saveFile = new SaveFile("./stats.txt", false);
	}
	
	/**
	 * A boolean method that checks if the stats.txt file exists in the project directory.
	 * 
	 * @return true if the file exists, false if it is missing.
	 */
	public boolean fileExists()
	{
		if (file.exists())
			return true;
		else
			return false;
	}
	
	/**
	 * This method takes all information in the saved stats.txt file and initialises all statistics with
	 * the saved values.
	 */
	public void readFile()
	{
		acceptFile = new ReadFile();
		bestEasyTime = acceptFile.getConsole().next();
		bestMediumTime = acceptFile.getConsole().next();
		bestHardTime = acceptFile.getConsole().next();
		numEasyGames = acceptFile.getConsole().nextInt();
		numMediumGames = acceptFile.getConsole().nextInt();
		numHardGames = acceptFile.getConsole().nextInt();
		totalCheat = acceptFile.getConsole().nextInt();
		numPuzzlesStarted = acceptFile.getConsole().nextInt();
		numPuzzlesFinished = acceptFile.getConsole().nextInt();
	}
	
	/**
	 * This method gathers all relevant statistics information that requires saving, and writes it to 
	 * a text file as a String, where the data is separated by spaces for easy reading.
	 */
	public void makeFile()
	{
		String text = getBestEasyTime()+" "+
				   	  getBestMediumTime()+" "+
				      getBestHardTime()+" "+
				      getNumEasyGames()+" "+
				      getNumMediumGames()+" "+
				      getNumHardGames()+" "+
				      getTotalCheat()+" "+
				      getNumPuzzlesStarted()+" "+
				      getNumPuzzlesFinished();
		try {
			saveFile.writeToFile(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method calculates a value representing the number of games started versus the number of games
	 * finished, as a percentage value.
	 * 
	 * @return player's percentage success in completing the puzzles
	 */
	public int calculateCompletionRate()
	{
		int percentage = 100;
		
		if (numPuzzlesFinished != 0)
			percentage = numPuzzlesStarted * 100 / numPuzzlesFinished;
		
		return percentage;
	}
	
	/**
	 * A method that simply calculates the total number of times that a player has used the autofill
	 * button. 
	 */
	public void calculateTotalCheat()
	{		
		totalCheat = totalCheat + cheatCount;
	}
	
	/**
	 * A method that determines which difficulty is the most-played mode by comparing the number of games
	 * played of each difficulty.
	 * 
	 * @return the most-played difficulty mode, in String format. If all games are played equally, returns
	 * "none"
	 */
	public String findFavouriteDifficulty()
	{
		int favouriteDifficulty = 0;
		String favDiffString = null;
		
		if (numEasyGames >= numMediumGames && numEasyGames >= numHardGames)
			favouriteDifficulty = EASY;
		else if (numMediumGames >= numEasyGames && numMediumGames >= numHardGames)
			favouriteDifficulty = MEDIUM;
		else if (numHardGames >= numEasyGames && numHardGames >= numMediumGames)
			favouriteDifficulty = HARD;
		
		favDiffString = difficultyToString(favouriteDifficulty);
		
		if (numEasyGames == numMediumGames && numMediumGames == numHardGames)
			favDiffString = "None";
		
		return favDiffString;	
	}
	
	/**
	 * Sets the difficulty field, based on difficulty chosen by the player.	 *
	 *
	 * @param difficulty the new difficulty
	 */
	public void setDifficulty (int difficulty)
	{
		this.difficulty = difficulty;
	}
	
	/**
	 * Translates difficulty from an int to a String, where<br>
	 * 1 == Easy<br>
	 * 2 == Medium<br>
	 * 3 == Hard<br>.
	 *
	 * @param difficulty the difficulty, in int format.
	 * @return the difficulty, in String format.
	 */
	public String difficultyToString(int difficulty)
	{
		String difficultyString = null;
		
		if (difficulty == 1)
			difficultyString = "Easy";
		else if (difficulty == 2)
			difficultyString = "Medium";
		else
			difficultyString = "Hard";
		
		return difficultyString;
	}
	
	/**
	 * A variant of the difficultyToString method that does not take in arguments.<br>
	 * Translates difficulty from an int to a String, where<br>
	 * 1 == Easy<br>
	 * 2 == Medium<br>
	 * 3 == Hard<br>
	 * 
	 * @return the difficulty, in String format.
	 */
	public String difficultyToString()
	{
		String difficultyString = null;
		
		if (this.difficulty == 1)
			difficultyString = "Easy";
		else if (this.difficulty == 2)
			difficultyString = "Medium";
		else
			difficultyString = "Hard";
		
		return difficultyString;
	}
	
	/**
	 * Sets the action count.
	 *
	 * @param numActions the new action count
	 */
	public void setActionCount(int numActions)
	{
		actionCount = numActions;
	}
	
	/**
	 * Sets the button count.
	 *
	 * @param numButtons the new button count
	 */
	public void setButtonCount(int numButtons)
	{
		buttonCount = numButtons;
	}
	
	/**
	 * Sets the cheat count.
	 *
	 * @param numCheat the new cheat count
	 */
	public void setCheatCount(int numCheat)
	{
		cheatCount = numCheat;
	}
	
	/**
	 * Sets the hint count.
	 *
	 * @param numHints the new hint count
	 */
	public void setHintCount(int numHints)
	{
		hintCount = numHints;
	}
	
	/**
	 * Sets the progress count.
	 *
	 * @param numProgress the new progress count
	 */
	public void setProgressCount(int numProgress)
	{
		progressCount = numProgress;
	}
	
	/**
	 * Sets the total empty boxes count.
	 *
	 * @param numBoxes the new total empty boxes count
	 */
	public void setTotalEmptyBoxesCount(int numBoxes)
	{
		emptyBoxesCount = numBoxes;
	}
	
	/**
	 * Sets the best easy time.
	 *
	 * @param bestEasyTime the new best easy time
	 */
	public void setBestEasyTime(String bestEasyTime)
	{
		this.bestEasyTime = bestEasyTime; 
	}
	
	/**
	 * Sets the best medium time.
	 *
	 * @param bestMediumTime the new best medium time
	 */
	public void setBestMediumTime(String bestMediumTime)
	{
		this.bestMediumTime = bestMediumTime; 
	}
	
	/**
	 * Sets the best hard time.
	 *
	 * @param bestHardTime the new best hard time
	 */
	public void setBestHardTime(String bestHardTime)
	{
		this.bestHardTime = bestHardTime;
	}
	
	/**
	 * Sets the num easy games.
	 *
	 * @param numEasyGames the new num easy games
	 */
	public void setNumEasyGames(int numEasyGames)
	{
		this.numEasyGames = numEasyGames;
	}
	
	/**
	 * Sets the num medium games.
	 *
	 * @param numMediumGames the new num medium games
	 */
	public void setNumMediumGames(int numMediumGames)
	{
		this.numMediumGames = numMediumGames;
	}
	
	/**
	 * Sets the num hard games.
	 *
	 * @param numHardGames the new num hard games
	 */
	public void setNumHardGames(int numHardGames)
	{
		this.numHardGames = numHardGames;
	}
	
	/**
	 * Sets the total cheat.
	 *
	 * @param totalCheat the new total cheat
	 */
	public void setTotalCheat(int totalCheat)
	{
		this.totalCheat = totalCheat;
	}
	
	/**
	 * Sets the num puzzles started.
	 *
	 * @param numPuzzles the new num puzzles started
	 */
	public void setNumPuzzlesStarted(int numPuzzles)
	{
		numPuzzlesStarted = numPuzzles;
	}
	
	/**
	 * Sets the num puzzles finished.
	 *
	 * @param numPuzzles the new num puzzles finished
	 */
	public void setNumPuzzlesFinished(int numPuzzles)
	{
		numPuzzlesFinished = numPuzzles;
	}
	
	/**
	 * Gets the action count.
	 *
	 * @return the action count
	 */
	public int getActionCount()
	{
		return actionCount;
	}
	
	/**
	 * Gets the button count.
	 *
	 * @return the button count
	 */
	public int getButtonCount()
	{
		return buttonCount;
	}
	
	/**
	 * Gets the cheat count.
	 *
	 * @return the cheat count
	 */
	public int getCheatCount()
	{
		return cheatCount;
	}
	
	/**
	 * Gets the hint count.
	 *
	 * @return the hint count
	 */
	public int getHintCount()
	{
		return hintCount;
	}
	
	/**
	 * Gets the progress count.
	 *
	 * @return the progress count
	 */
	public int getProgressCount()
	{
		return progressCount;
	}
	
	/**
	 * Gets the total empty boxes count.
	 *
	 * @return the total empty boxes count
	 */
	public int getTotalEmptyBoxesCount()
	{
		return emptyBoxesCount;
	}
	
	/**
	 * Gets the difficulty.
	 *
	 * @return the difficulty
	 */
	public int getDifficulty()
	{
		return difficulty;
	}	
	
	/**
	 * Gets the num puzzles finished.
	 *
	 * @return the num puzzles finished
	 */
	public int getNumPuzzlesFinished()
	{
		return numPuzzlesFinished;
	}
	
	/**
	 * Gets the num puzzles started.
	 *
	 * @return the num puzzles started
	 */
	public int getNumPuzzlesStarted()
	{
		return numPuzzlesStarted;
	}
	
	/**
	 * Gets the total cheat.
	 *
	 * @return the total cheat
	 */
	public int getTotalCheat()
	{
		return totalCheat;
	}
	
	/**
	 * Gets the num hard games.
	 *
	 * @return the num hard games
	 */
	public int getNumHardGames()
	{
		return numHardGames;
	}
	
	/**
	 * Gets the num medium games.
	 *
	 * @return the num medium games
	 */
	public int getNumMediumGames()
	{
		return numMediumGames;
	}
	
	/**
	 * Gets the num easy games.
	 *
	 * @return the num easy games
	 */
	public int getNumEasyGames()
	{
		return numEasyGames;
	}
	
	/**
	 * Gets the fastest time that the player has spent finishing a Hard puzzle.
	 * 
	 * @return the fastest time spent to complete a Hard puzzle, "---" if non-existent
	 */
	public String getBestHardTime()
	{
		if (bestHardTime.equals("99:00"))
			return "---";
		else
			return bestHardTime;
	}
	
	/**
	 * Gets the fastest time that the player has spent finishing an Easy puzzle.
	 * 
	 * @return the fastest time spent to complete an Easy puzzle, "---" if non-existent
	 */
	public String getBestEasyTime()
	{
		if (bestEasyTime.equals("99:00"))
			return "---";
		else
			return bestEasyTime;
	}
	
	/**
	 * Gets the fastest time that the player has spent finishing a Medium puzzle.
	 * 
	 * @return the fastest time spent to complete a Medium puzzle, "---" if non-existent
	 */
	public String getBestMediumTime()
	{
		if (bestMediumTime.equals("99:00"))
			return "---";
		else
			return bestMediumTime;
	}
}
