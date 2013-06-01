package controller.statistics;

import java.io.File;
import java.io.IOException;

import view.FrameMain;

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
	private SaveFile saveFile;
	
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
		saveFile = new SaveFile("./stats.txt", false);
	}
	
	public void makeFile(String text)
	{
		try {
			saveFile.writeToFile(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setDifficulty (int difficulty)
	{
		this.difficulty = difficulty;
	}
	
	public String difficultyToString()
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
	
	public String getBestHardTime()
	{
		return bestHardTime;
	}
	
	public String getBestEasyTime()
	{
		return bestEasyTime;
	}
	
	public String getBestMediumTime()
	{
		return bestMediumTime;
	}
}
