package controller.statistics;


import java.io.File;
import java.io.IOException;


/**
 * This class handles all statistics that are present and displayed in the game.
 * 
 * @author Ryan Tan, Nikhil Suresh and Nicholas Ho
 */
public class Statistics
{	
	private int difficulty;
	private int actionCount;
	private int buttonCount;
	private int cheatCount;
	private int winCount;
	private int hintCount;
	private int progressCount;
	private int emptyBoxesCount;
	
	private String bestEasyTime;
	private String bestMediumTime;
	private String bestHardTime;
	private int numEasyGames;
	private int numMediumGames;
	private int numHardGames;
	private int totalCheat;
	private int numPuzzlesStarted;
	private int numPuzzlesFinished;
	
	private File file;
	
	private ReadFile acceptFile;
	private SaveFile saveFile;
	
	private static final int EASY = 1;
	private static final int MEDIUM = 2;
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
		winCount = 0;
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
		System.out.println(text);
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
	 */
	public void setDifficulty (int difficulty)
	{
		this.difficulty = difficulty;
	}
	
	/**
	 * Translates difficulty from an int to a String, where<br>
	 * 1 == Easy<br>
	 * 2 == Medium<br>
	 * 3 == Hard<br>
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
	
	public void setActionCount(int numActions)
	{
		actionCount = numActions;
	}
	
	public void setButtonCount(int numButtons)
	{
		buttonCount = numButtons;
	}
	
	public void setCheatCount(int numCheat)
	{
		cheatCount = numCheat;
	}
	
	public void setWinCount(int numWins)
	{
		winCount = numWins;
	}
	
	public void setHintCount(int numHints)
	{
		hintCount = numHints;
	}
	
	public void setProgressCount(int numProgress)
	{
		progressCount = numProgress;
	}
	
	public void setTotalEmptyBoxesCount(int numBoxes)
	{
		emptyBoxesCount = numBoxes;
	}
	
	public void setBestEasyTime(String bestEasyTime)
	{
		this.bestEasyTime = bestEasyTime; 
	}
	
	public void setBestMediumTime(String bestMediumTime)
	{
		this.bestMediumTime = bestMediumTime; 
	}
	
	public void setBestHardTime(String bestHardTime)
	{
		this.bestHardTime = bestHardTime;
	}
	
	public void setNumEasyGames(int numEasyGames)
	{
		this.numEasyGames = numEasyGames;
	}
	
	public void setNumMediumGames(int numMediumGames)
	{
		this.numMediumGames = numMediumGames;
	}
	
	public void setNumHardGames(int numHardGames)
	{
		this.numHardGames = numHardGames;
	}
	
	public void setTotalCheat(int totalCheat)
	{
		this.totalCheat = totalCheat;
	}
	
	public void setNumPuzzlesStarted(int numPuzzles)
	{
		numPuzzlesStarted = numPuzzles;
	}
	
	public void setNumPuzzlesFinished(int numPuzzles)
	{
		numPuzzlesFinished = numPuzzles;
	}
	
	public int getActionCount()
	{
		return actionCount;
	}
	
	public int getButtonCount()
	{
		return buttonCount;
	}
	
	public int getCheatCount()
	{
		return cheatCount;
	}
	
	public int getWinCount()
	{
		return winCount;
	}
	
	public int getHintCount()
	{
		return hintCount;
	}
	
	public int getProgressCount()
	{
		return progressCount;
	}
	
	public int getTotalEmptyBoxesCount()
	{
		return emptyBoxesCount;
	}
	
	public int getDifficulty()
	{
		return difficulty;
	}	
	
	public int getNumPuzzlesFinished()
	{
		return numPuzzlesFinished;
	}
	
	public int getNumPuzzlesStarted()
	{
		return numPuzzlesStarted;
	}
	
	public int getTotalCheat()
	{
		return totalCheat;
	}
	
	public int getNumHardGames()
	{
		return numHardGames;
	}
	
	public int getNumMediumGames()
	{
		return numMediumGames;
	}
	
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
