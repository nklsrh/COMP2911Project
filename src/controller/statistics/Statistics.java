package controller.statistics;

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
}
