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
	private SaveFile saveFile;
	
	public void makeFile(String text)
	{
		saveFile = new SaveFile("C:/Users/Nicholas/Desktop/stats.txt", false);
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
