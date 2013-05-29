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
	
	public Statistics(int difficulty)
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
		System.out.println("numWins as an argument to setWinCount is " + numWins);
		winCount = numWins;
	}
	
	public void setHintCount(int numHints)
	{
		hintCount = numHints;
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
		System.out.println("winCount being returned for getWinCount is " + winCount);
		return winCount;
	}
	
	public int getHintCount()
	{
		return hintCount;
	}
	
	public int getDifficulty()
	{
		return difficulty;
	}
}
