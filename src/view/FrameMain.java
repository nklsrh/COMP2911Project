package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.PuzzleControl;

import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.awt.FlowLayout;

/**
 * The class that represents the GUI of the Sudoku puzzle game.
 * @author Nikhil Suresh, Ryan Tan, Nicholas Ho
 *
 */
public class FrameMain extends JFrame {
	private Font fontRegular;
	private Font fontLight;
	private Font fontThin;
	private Font fontItalic;
	private Font fontBoldItalic;

	private final Color colorBackground = new Color(240,240,240); 	
	private final Color colorSelected = new Color(149,235,255);
	
	private final Color colorEasy = new Color(169,232,218);
	private final Color colorMedium = new Color(247,239,125);	
	private final Color colorHard = new Color(249,205,185);
	
	private JPanel contentPane;

	/**
	 * Creates the frame of the GUI being used to display the Sudoku board and other supplementary information
	 * in an attractive and accessible manner
	 */
	public FrameMain() {
		
		PuzzleControl puzzleControl = new PuzzleControl();//TODO
		
		setResizable(false);
		setBackground(SystemColor.window);
		setBounds(100, 100, 500, 580);
		
		contentPane = new JPanel();
		contentPane.setBackground(colorBackground);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		File fontFileReg = new File("assets/Roboto-Regular.ttf");
		File fontFileThin = new File("assets/Roboto-Thin.ttf");
		File fontFileLight = new File("assets/Roboto-Light.ttf");
		File fontFileIta = new File("assets/Roboto-Italic.ttf");
		File fontFileBoldIta = new File("assets/Roboto-BoldItalic.ttf");
		try {
			fontRegular = Font.createFont(Font.TRUETYPE_FONT, fontFileReg);
			fontThin = Font.createFont(Font.TRUETYPE_FONT, fontFileThin);
			fontLight = Font.createFont(Font.TRUETYPE_FONT, fontFileLight);
			fontItalic = Font.createFont(Font.TRUETYPE_FONT, fontFileIta);
			fontBoldItalic = Font.createFont(Font.TRUETYPE_FONT, fontFileBoldIta);
		} catch (Exception e1) {
			fontRegular = Font.getFont("Lucida Sans");
			fontThin = Font.getFont("Lucida Sans");
			fontLight = Font.getFont("Lucida Sans");
			fontItalic = Font.getFont("Lucida Sans");
			fontBoldItalic = Font.getFont("Lucida Sans");
		}
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{484, 0};
		gbl_contentPane.rowHeights = new int[] {15, 35, 35, 135, 100, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		setupFrame(puzzleControl);
	}
	
	public void setupFrame(PuzzleControl puzzleControl)
	{		
		if (!puzzleControl.getStatistics().fileExists())
		{
			puzzleControl.getStatistics().makeFile();
		}
		puzzleControl.getStatistics().readFile();
				
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(colorBackground);
		
		GridBagConstraints gridBagC = new GridBagConstraints();
		gridBagC.fill = GridBagConstraints.BOTH;
		gridBagC.insets = new Insets(0, 0, 5, 0);
		gridBagC.gridx = 0;
		gridBagC.gridy = 0;
		contentPane.add(panelHeader, gridBagC);

		GridBagLayout gbl_panelHeader = new GridBagLayout();
		gbl_panelHeader.columnWidths = new int[] {484, 0};
		gbl_panelHeader.rowHeights = new int[] {77, 47, 30, 0};
		gbl_panelHeader.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelHeader.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelHeader.setLayout(gbl_panelHeader);
		
		createHeaderObject(panelHeader);
		createButtonObjects(puzzleControl);
		createStatsObjects(puzzleControl);
	}
	
	private void createHeaderObject(JPanel panelHeader)
	{
		JLabel lblTitle = new JLabel("Sudoku");
		lblTitle.setFont(fontThin.deriveFont(54f));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		GridBagConstraints gridBagC = new GridBagConstraints();
		gridBagC.gridheight = 2;
		gridBagC.fill = GridBagConstraints.BOTH;
		gridBagC.insets = new Insets(0, 0, 30, 0);
		gridBagC.gridx = 0;
		gridBagC.gridy = 0;
		panelHeader.add(lblTitle, gridBagC);
		
		JLabel lblSubtitle = new JLabel("by GROUP 04");
		lblSubtitle.setFont(fontItalic.deriveFont(17f));
		lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		gridBagC.insets = new Insets(0, 0, 12, 0);
		gridBagC.fill = GridBagConstraints.BOTH;
		gridBagC.gridx = 0;
		gridBagC.gridy = 1;
		panelHeader.add(lblSubtitle, gridBagC);
		
		JLabel lblInstruction = new JLabel("SELECT MODE");
		lblInstruction.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstruction.setFont(fontBoldItalic.deriveFont(23f));
		gridBagC.insets = new Insets(0, 0, 6, 0);
		gridBagC.gridx = 0;
		gridBagC.gridy = 2;
		panelHeader.add(lblInstruction, gridBagC);
	}
	private void createButtonObjects(PuzzleControl puzzleControl)
	{
		final FrameMain thisFrame = this;
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(colorBackground);		

		GridBagLayout gbl_panelButtonEasy = new GridBagLayout();
		gbl_panelButtonEasy.columnWidths = new int[] {154, 0};
		gbl_panelButtonEasy.rowHeights = new int[] {50, 90, 0};
		gbl_panelButtonEasy.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelButtonEasy.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};		
		
		GridBagConstraints gridBagC = new GridBagConstraints();
		gridBagC.fill = GridBagConstraints.BOTH;
		gridBagC.insets = new Insets(0, 0, 5, 0);
		gridBagC.gridx = 0;
		gridBagC.gridy = 1;
		panelButtons.setLayout(new GridLayout(1, 3, 10, 0));		
		
		contentPane.add(panelButtons, gridBagC);
		
		final JPanel panelButtonEasy = new JPanel();
		panelButtonEasy.setToolTipText("Easy mode contains fewer cells to fill, and cells usually have very few possibilities. Best for beginners.");
		panelButtonEasy.setBackground(new Color(255, 255, 255));
		panelButtonEasy.setLayout(gbl_panelButtonEasy);		
		
		final JPanel panelButtonEasyHeader = new JPanel();
		FlowLayout fl_panelButtonEasyHeader = (FlowLayout) panelButtonEasyHeader.getLayout();
		fl_panelButtonEasyHeader.setAlignment(FlowLayout.LEFT);
		panelButtonEasyHeader.setBackground(new Color(255, 255, 255));
		
		gridBagC.fill = GridBagConstraints.BOTH;
		gridBagC.insets = new Insets(0, 0, 5, 0);
		gridBagC.gridx = 0;
		gridBagC.gridy = 0;
		panelButtonEasy.add(panelButtonEasyHeader, gridBagC);
		
		JLabel lblEasy = new JLabel("Easy");				
		lblEasy.setFont(fontThin.deriveFont(32f));
		lblEasy.setHorizontalAlignment(SwingConstants.LEFT);
		panelButtonEasyHeader.add(lblEasy);
		
		final JPanel panelButtonEasyStats = new JPanel();
		panelButtonEasyStats.setBackground(colorEasy);
		panelButtonEasyStats.setLayout(new GridLayout(0, 1, 0, 0));

		gridBagC.fill = GridBagConstraints.BOTH;
		gridBagC.gridx = 0;
		gridBagC.gridy = 1;
		panelButtonEasy.add(panelButtonEasyStats, gridBagC);		
		
		JLabel lblBestTime = new JLabel("Fastest time");
		lblBestTime.setFont(fontLight.deriveFont(22f));
		lblBestTime.setHorizontalAlignment(SwingConstants.LEFT);
		panelButtonEasyStats.add(lblBestTime);
		
		JLabel panelButtonEasyFastestTime = new JLabel(puzzleControl.getStatistics().getBestEasyTime());
		panelButtonEasyFastestTime.setFont(fontItalic.deriveFont(42f));
		panelButtonEasyFastestTime.setHorizontalAlignment(SwingConstants.LEFT);
		panelButtonEasyStats.add(panelButtonEasyFastestTime);
		
		panelButtonEasy.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				panelButtonEasyHeader.setBackground(colorSelected);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelButtonEasyHeader.setBackground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				goToGame(new FrameGame(thisFrame, 1));
			}
		});
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JPanel panelButtonMedium = new JPanel();
		panelButtonMedium.setToolTipText("Medium mode is slightly more complex, with less pre-filled cells and more possibilities for each cell. This also means some puzzles may have multiple solutions.");
		panelButtonMedium.setBackground(new Color(255, 255, 255));
		
		GridBagLayout gbl_panelButtonMedium = new GridBagLayout();
		gbl_panelButtonMedium.columnWidths = new int[]{154, 0};
		gbl_panelButtonMedium.rowHeights = new int[] {50, 90, 0};
		gbl_panelButtonMedium.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelButtonMedium.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelButtonMedium.setLayout(gbl_panelButtonMedium);
		
		final JPanel panelButtonMediumHeader = new JPanel();
		panelButtonMediumHeader.setBackground(Color.WHITE);
		FlowLayout fl_panelButtonMediumHeader = (FlowLayout) panelButtonMediumHeader.getLayout();
		fl_panelButtonMediumHeader.setAlignment(FlowLayout.LEFT);

		gridBagC.fill = GridBagConstraints.BOTH;
		gridBagC.insets = new Insets(0, 0, 5, 0);
		gridBagC.gridx = 0;
		gridBagC.gridy = 0;
		panelButtonMedium.add(panelButtonMediumHeader, gridBagC);

		JLabel panelButtonMediumHeaderText = new JLabel("Medium");
		panelButtonMediumHeaderText.setFont(fontThin.deriveFont(32f));
		panelButtonMediumHeaderText.setHorizontalAlignment(SwingConstants.LEFT);
		panelButtonMediumHeader.add(panelButtonMediumHeaderText);
		
		JPanel panelButtonMediumStats = new JPanel();
		panelButtonMediumStats.setBackground(colorMedium);
		panelButtonMediumStats.setLayout(new GridLayout(0, 1, 0, 0));

		gridBagC.fill = GridBagConstraints.BOTH;
		gridBagC.gridx = 0;
		gridBagC.gridy = 1;
		panelButtonMedium.add(panelButtonMediumStats, gridBagC);
		
		JLabel panelButtonMediumTimeHeader = new JLabel("Fastest time");
		panelButtonMediumTimeHeader.setFont(fontLight.deriveFont(22f));
		panelButtonMediumTimeHeader.setHorizontalAlignment(SwingConstants.LEFT);
		panelButtonMediumStats.add(panelButtonMediumTimeHeader);
		
		JLabel panelButtonMediumFastestTime = new JLabel(puzzleControl.getStatistics().getBestMediumTime());
		panelButtonMediumFastestTime.setFont(fontItalic.deriveFont(42f));
		panelButtonMediumFastestTime.setHorizontalAlignment(SwingConstants.LEFT);
		panelButtonMediumStats.add(panelButtonMediumFastestTime);
		
		panelButtonMedium.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				panelButtonMediumHeader.setBackground(colorSelected);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelButtonMediumHeader.setBackground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				goToGame(new FrameGame(thisFrame, 2));
			}
		});
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JPanel panelButtonHard = new JPanel();
		panelButtonHard.setToolTipText("Hard mode is for expert Sudoku solvers.\r\nVery few cells have been pre-filled, meaning there may be a high number of possible solutions. \r\nThis also means each empty cell may have many legal possibilities.");
		panelButtonHard.setBackground(Color.WHITE);
		
		GridBagLayout gbl_panelButtonHard = new GridBagLayout();
		gbl_panelButtonHard.columnWidths = new int[]{154, 0};
		gbl_panelButtonHard.rowHeights = new int[] {50, 90, 0};
		gbl_panelButtonHard.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelButtonHard.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelButtonHard.setLayout(gbl_panelButtonHard);
		
		final JPanel panelButtonHardHeader = new JPanel();
		FlowLayout fl_panelButtonHardHeader = (FlowLayout) panelButtonHardHeader.getLayout();
		fl_panelButtonHardHeader.setAlignment(FlowLayout.LEFT);
		panelButtonHardHeader.setBackground(Color.WHITE);
		
		gridBagC.fill = GridBagConstraints.BOTH;
		gridBagC.insets = new Insets(0, 0, 5, 0);
		gridBagC.gridx = 0;
		gridBagC.gridy = 0;
		panelButtonHard.add(panelButtonHardHeader, gridBagC);
		
		JLabel lblHard = new JLabel("Hard");
		lblHard.setFont(fontThin.deriveFont(32f));
		lblHard.setHorizontalAlignment(SwingConstants.LEFT);
		panelButtonHardHeader.add(lblHard);
		
		final JPanel panelButtonHardStats = new JPanel();
		panelButtonHardStats.setBackground(colorHard);
		panelButtonHardStats.setLayout(new GridLayout(0, 1, 0, 0));

		gridBagC.fill = GridBagConstraints.BOTH;
		gridBagC.gridx = 0;
		gridBagC.gridy = 1;
		panelButtonHard.add(panelButtonHardStats, gridBagC);
		
		JLabel lblFastestTime_1 = new JLabel("Fastest time");
		lblFastestTime_1.setFont(fontLight.deriveFont(22f));
		lblFastestTime_1.setHorizontalAlignment(SwingConstants.LEFT);
		panelButtonHardStats.add(lblFastestTime_1);
		
		JLabel panelButtonHardFastestTime = new JLabel(puzzleControl.getStatistics().getBestHardTime());
		panelButtonHardFastestTime.setFont(fontItalic.deriveFont(42f));
		panelButtonHardFastestTime.setHorizontalAlignment(SwingConstants.LEFT);
		panelButtonHardStats.add(panelButtonHardFastestTime);
		panelButtonHard.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				panelButtonHardHeader.setBackground(colorSelected);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelButtonHardHeader.setBackground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				goToGame(new FrameGame(thisFrame, 3));
			}
		});
		
		panelButtons.add(panelButtonEasy);
		panelButtons.add(panelButtonMedium);		
		panelButtons.add(panelButtonHard);
	}
	
	private void createStatsObjects(PuzzleControl puzzleControl)
	{
		JPanel panelStats = new JPanel();
		panelStats.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_panelStats = new GridBagConstraints();
		gbc_panelStats.insets = new Insets(0, 0, 5, 0);
		gbc_panelStats.fill = GridBagConstraints.BOTH;
		gbc_panelStats.gridx = 0;
		gbc_panelStats.gridy = 3;
		contentPane.add(panelStats, gbc_panelStats);
		panelStats.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel panelStatsContent = new JPanel();
		panelStatsContent.setBackground(new Color(255, 255, 255));
		panelStats.add(panelStatsContent);
		panelStatsContent.setLayout(new BorderLayout(0, 0));
		
		JLabel lblStatisticsHeader = new JLabel("Statistics");
		lblStatisticsHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatisticsHeader.setFont(fontThin.deriveFont(37f));
		panelStatsContent.add(lblStatisticsHeader, BorderLayout.NORTH);
		
		JPanel panelStatsTables = new JPanel();
		panelStatsContent.add(panelStatsTables, BorderLayout.CENTER);
		panelStatsTables.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panelStatsMode = new JPanel();
		panelStatsMode.setBackground(new Color(255, 255, 255));
		panelStatsTables.add(panelStatsMode);
		panelStatsMode.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Favourite Mode");
		lblNewLabel.setFont(fontLight.deriveFont(17f));
		panelStatsMode.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblStatsFavouriteMode = new JLabel(puzzleControl.getStatistics().findFavouriteDifficulty());
		lblStatsFavouriteMode.setVerticalAlignment(SwingConstants.TOP);
		lblStatsFavouriteMode.setFont(fontRegular.deriveFont(32f));
		panelStatsMode.add(lblStatsFavouriteMode);
		lblStatsFavouriteMode.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelStatsCompletion = new JPanel();
		panelStatsCompletion.setBackground(Color.WHITE);
		panelStatsTables.add(panelStatsCompletion);
		panelStatsCompletion.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Puzzles Completed");
		lblNewLabel_1.setFont(fontLight.deriveFont(17f));
		panelStatsCompletion.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblStatsCompletion = new JLabel(String.valueOf(puzzleControl.getStatistics().getNumPuzzlesFinished()));
		lblStatsCompletion.setVerticalAlignment(SwingConstants.TOP);
		lblStatsCompletion.setFont(fontRegular.deriveFont(32f));
		panelStatsCompletion.add(lblStatsCompletion);
		lblStatsCompletion.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelStatsAutofills = new JPanel();
		panelStatsAutofills.setBackground(new Color(255, 255, 255));
		panelStatsTables.add(panelStatsAutofills);
		panelStatsAutofills.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Autofills used");
		lblNewLabel_2.setFont(fontLight.deriveFont(17f));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panelStatsAutofills.add(lblNewLabel_2);
		
		JLabel lblStatsAutofills = new JLabel(String.valueOf(puzzleControl.getStatistics().getTotalCheat()));
		lblStatsAutofills.setVerticalAlignment(SwingConstants.TOP);
		lblStatsAutofills.setFont(fontRegular.deriveFont(32f));
		lblStatsAutofills.setHorizontalAlignment(SwingConstants.CENTER);
		panelStatsAutofills.add(lblStatsAutofills);
	}
	
	private void goToGame(FrameGame frame)
	{
		frame.setVisible(true);
		this.dispose();
	}
}