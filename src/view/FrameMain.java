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
	private Font font;
	private Font fontMed;
	private Font fontLight;
	private Font fontItalic;
	private Font fontThin;

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
		final JFrame thisFrame = this;
		
		PuzzleControl puzzleControl = new PuzzleControl();//TODO
		
		if (!puzzleControl.getStatistics().fileExists())
		{
			puzzleControl.getStatistics().makeFile();
		}
		puzzleControl.getStatistics().readFile();
		
		setResizable(false);
		setBackground(SystemColor.window);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 580);
		
		contentPane = new JPanel();
		contentPane.setBackground(colorBackground);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		File font_file_light = new File("assets/Roboto-Regular.ttf");
		File font_file_thin = new File("assets/Roboto-Thin.ttf");
		File font_file_reg = new File("assets/Roboto-Light.ttf");
		File font_file_med = new File("assets/Roboto-Italic.ttf");
		File font_file_ita = new File("assets/Roboto-BoldItalic.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, font_file_reg);
			fontLight = Font.createFont(Font.TRUETYPE_FONT, font_file_light);
			fontMed = Font.createFont(Font.TRUETYPE_FONT, font_file_med);
			fontItalic = Font.createFont(Font.TRUETYPE_FONT, font_file_ita);
			fontThin = Font.createFont(Font.TRUETYPE_FONT, font_file_thin);
		} catch (Exception e1) {
			font = Font.getFont("Lucida Sans");
			fontLight = Font.getFont("Lucida Sans");
			fontMed = Font.getFont("Lucida Sans");
			fontItalic = Font.getFont("Lucida Sans");
			fontThin = Font.getFont("Lucida Sans");
		}
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{484, 0};
		gbl_contentPane.rowHeights = new int[] {15, 35, 35, 135, 100, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(colorBackground);
		GridBagConstraints gbc_panelHeader = new GridBagConstraints();
		gbc_panelHeader.fill = GridBagConstraints.BOTH;
		gbc_panelHeader.insets = new Insets(0, 0, 5, 0);
		gbc_panelHeader.gridx = 0;
		gbc_panelHeader.gridy = 0;
		contentPane.add(panelHeader, gbc_panelHeader);
		GridBagLayout gbl_panelHeader = new GridBagLayout();
		gbl_panelHeader.columnWidths = new int[] {484, 0};
		gbl_panelHeader.rowHeights = new int[] {77, 47, 30, 0};
		gbl_panelHeader.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelHeader.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelHeader.setLayout(gbl_panelHeader);
		
		JLabel lblTitle = new JLabel("Sudoku");
		lblTitle.setFont(fontThin.deriveFont(54f));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridheight = 2;
		gbc_lblTitle.fill = GridBagConstraints.BOTH;
		gbc_lblTitle.insets = new Insets(0, 0, 30, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		panelHeader.add(lblTitle, gbc_lblTitle);
		
		JLabel lblSubtitle = new JLabel("by GROUP 04");
		lblSubtitle.setFont(fontMed.deriveFont(17f));
		lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblSubtitle = new GridBagConstraints();
		gbc_lblSubtitle.insets = new Insets(0, 0, 12, 0);
		gbc_lblSubtitle.fill = GridBagConstraints.BOTH;
		gbc_lblSubtitle.gridx = 0;
		gbc_lblSubtitle.gridy = 1;
		panelHeader.add(lblSubtitle, gbc_lblSubtitle);
		
		JLabel lblInstruction = new JLabel("SELECT MODE");
		lblInstruction.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstruction.setFont(fontItalic.deriveFont(23f));
		GridBagConstraints gbc_lblInstruction = new GridBagConstraints();
		gbc_lblInstruction.insets = new Insets(0, 0, 6, 0);
		gbc_lblInstruction.gridx = 0;
		gbc_lblInstruction.gridy = 2;
		panelHeader.add(lblInstruction, gbc_lblInstruction);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(colorBackground);
		GridBagConstraints gbc_panelButtons = new GridBagConstraints();
		gbc_panelButtons.fill = GridBagConstraints.BOTH;
		gbc_panelButtons.insets = new Insets(0, 0, 5, 0);
		gbc_panelButtons.gridx = 0;
		gbc_panelButtons.gridy = 1;
		contentPane.add(panelButtons, gbc_panelButtons);
		panelButtons.setLayout(new GridLayout(1, 3, 10, 0));
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		final JPanel panelButtonEasy = new JPanel();
		panelButtonEasy.setToolTipText("Easy mode contains fewer cells to fill, and cells usually have very few possibilities. Best for beginners.");
		panelButtonEasy.setBackground(new Color(255, 255, 255));
		panelButtons.add(panelButtonEasy);
		GridBagLayout gbl_panelButtonEasy = new GridBagLayout();
		gbl_panelButtonEasy.columnWidths = new int[] {154, 0};
		gbl_panelButtonEasy.rowHeights = new int[] {50, 90, 0};
		gbl_panelButtonEasy.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelButtonEasy.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelButtonEasy.setLayout(gbl_panelButtonEasy);
		
		final JPanel panelButtonEasyHeader = new JPanel();
		FlowLayout fl_panelButtonEasyHeader = (FlowLayout) panelButtonEasyHeader.getLayout();
		fl_panelButtonEasyHeader.setAlignment(FlowLayout.LEFT);
		panelButtonEasyHeader.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_panelButtonEasyHeader = new GridBagConstraints();
		gbc_panelButtonEasyHeader.fill = GridBagConstraints.BOTH;
		gbc_panelButtonEasyHeader.insets = new Insets(0, 0, 5, 0);
		gbc_panelButtonEasyHeader.gridx = 0;
		gbc_panelButtonEasyHeader.gridy = 0;
		panelButtonEasy.add(panelButtonEasyHeader, gbc_panelButtonEasyHeader);
		
		JLabel lblEasy = new JLabel("Easy");				
		lblEasy.setFont(fontThin.deriveFont(32f));
		lblEasy.setHorizontalAlignment(SwingConstants.LEFT);
		panelButtonEasyHeader.add(lblEasy);
		
		final JPanel panelButtonEasyStats = new JPanel();
		panelButtonEasyStats.setBackground(colorEasy);
		GridBagConstraints gbc_panelButtonEasyStats = new GridBagConstraints();
		gbc_panelButtonEasyStats.fill = GridBagConstraints.BOTH;
		gbc_panelButtonEasyStats.gridx = 0;
		gbc_panelButtonEasyStats.gridy = 1;
		panelButtonEasy.add(panelButtonEasyStats, gbc_panelButtonEasyStats);
		panelButtonEasyStats.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblBestTime = new JLabel("Fastest time");
		lblBestTime.setFont(font.deriveFont(22f));
		lblBestTime.setHorizontalAlignment(SwingConstants.LEFT);
		panelButtonEasyStats.add(lblBestTime);
		
		JLabel panelButtonEasyFastestTime = new JLabel(puzzleControl.getStatistics().getBestEasyTime());
		panelButtonEasyFastestTime.setFont(fontMed.deriveFont(42f));
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
				goToFrame(new FrameGame(thisFrame, 1));
			}
		});
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JPanel panelButtonMedium = new JPanel();
		panelButtonMedium.setToolTipText("Medium mode is slightly more complex, with less pre-filled cells and more possibilities for each cell. This also means some puzzles may have multiple solutions.");
		panelButtonMedium.setBackground(new Color(255, 255, 255));
		panelButtons.add(panelButtonMedium);
		GridBagLayout gbl_panelButtonMedium = new GridBagLayout();
		gbl_panelButtonMedium.columnWidths = new int[]{154, 0};
		gbl_panelButtonMedium.rowHeights = new int[] {50, 90, 0};
		gbl_panelButtonMedium.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelButtonMedium.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelButtonMedium.setLayout(gbl_panelButtonMedium);
		
		final JPanel panelButtonMediumHeader = new JPanel();
		FlowLayout fl_panelButtonMediumHeader = (FlowLayout) panelButtonMediumHeader.getLayout();
		fl_panelButtonMediumHeader.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelButtonMediumHeader = new GridBagConstraints();
		gbc_panelButtonMediumHeader.fill = GridBagConstraints.BOTH;
		gbc_panelButtonMediumHeader.insets = new Insets(0, 0, 5, 0);
		gbc_panelButtonMediumHeader.gridx = 0;
		gbc_panelButtonMediumHeader.gridy = 0;
		panelButtonMedium.add(panelButtonMediumHeader, gbc_panelButtonMediumHeader);
		panelButtonMediumHeader.setBackground(Color.WHITE);
		
		// used to be JLabel panelButtonMediumHeaderText
		JLabel panelButtonMediumHeaderText = new JLabel("Medium");
		panelButtonMediumHeaderText.setFont(fontThin.deriveFont(32f));
		panelButtonMediumHeaderText.setHorizontalAlignment(SwingConstants.LEFT);
		panelButtonMediumHeader.add(panelButtonMediumHeaderText);
		
		JPanel panelButtonMediumStats = new JPanel();
		GridBagConstraints gbc_panelButtonMediumStats = new GridBagConstraints();
		gbc_panelButtonMediumStats.fill = GridBagConstraints.BOTH;
		gbc_panelButtonMediumStats.gridx = 0;
		gbc_panelButtonMediumStats.gridy = 1;
		panelButtonMedium.add(panelButtonMediumStats, gbc_panelButtonMediumStats);
		panelButtonMediumStats.setBackground(colorMedium);
		panelButtonMediumStats.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel panelButtonMediumTimeHeader = new JLabel("Fastest time");
		panelButtonMediumTimeHeader.setFont(font.deriveFont(22f));
		panelButtonMediumTimeHeader.setHorizontalAlignment(SwingConstants.LEFT);
		panelButtonMediumStats.add(panelButtonMediumTimeHeader);
		
		JLabel panelButtonMediumFastestTime = new JLabel(puzzleControl.getStatistics().getBestMediumTime());
		panelButtonMediumFastestTime.setFont(fontMed.deriveFont(42f));
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
				goToFrame(new FrameGame(thisFrame, 2));
			}
		});
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JPanel panelButtonHard = new JPanel();
		panelButtonHard.setToolTipText("Hard mode is for expert Sudoku solvers.\r\nVery few cells have been pre-filled, meaning there may be a high number of possible solutions. \r\nThis also means each empty cell may have many legal possibilities.");
		panelButtonHard.setBackground(Color.WHITE);
		panelButtons.add(panelButtonHard);
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
		GridBagConstraints gbc_panelButtonHardHeader = new GridBagConstraints();
		gbc_panelButtonHardHeader.fill = GridBagConstraints.BOTH;
		gbc_panelButtonHardHeader.insets = new Insets(0, 0, 5, 0);
		gbc_panelButtonHardHeader.gridx = 0;
		gbc_panelButtonHardHeader.gridy = 0;
		panelButtonHard.add(panelButtonHardHeader, gbc_panelButtonHardHeader);
		
		JLabel lblHard = new JLabel("Hard");
		lblHard.setFont(fontThin.deriveFont(32f));
		lblHard.setHorizontalAlignment(SwingConstants.LEFT);
		panelButtonHardHeader.add(lblHard);
		
		final JPanel panelButtonHardStats = new JPanel();
		panelButtonHardStats.setBackground(colorHard);
		GridBagConstraints gbc_panelButtonHardStats = new GridBagConstraints();
		gbc_panelButtonHardStats.fill = GridBagConstraints.BOTH;
		gbc_panelButtonHardStats.gridx = 0;
		gbc_panelButtonHardStats.gridy = 1;
		panelButtonHard.add(panelButtonHardStats, gbc_panelButtonHardStats);
		panelButtonHardStats.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblFastestTime_1 = new JLabel("Fastest time");
		lblFastestTime_1.setFont(font.deriveFont(22f));
		lblFastestTime_1.setHorizontalAlignment(SwingConstants.LEFT);
		panelButtonHardStats.add(lblFastestTime_1);
		
		JLabel panelButtonHardFastestTime = new JLabel(puzzleControl.getStatistics().getBestHardTime());
		panelButtonHardFastestTime.setFont(fontMed.deriveFont(42f));
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
				goToFrame(new FrameGame(thisFrame, 3));
			}
		});
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JPanel panelWhitespace = new JPanel();
		panelWhitespace.setBackground(new Color(234,234,234));
		FlowLayout fl_panelWhitespace = (FlowLayout) panelWhitespace.getLayout();
		fl_panelWhitespace.setVgap(0);
		fl_panelWhitespace.setHgap(0);
		GridBagConstraints gbc_panelWhitespace = new GridBagConstraints();
		gbc_panelWhitespace.fill = GridBagConstraints.BOTH;
		gbc_panelWhitespace.insets = new Insets(0, 0, 5, 0);
		gbc_panelWhitespace.gridx = 0;
		gbc_panelWhitespace.gridy = 2;
		contentPane.add(panelWhitespace, gbc_panelWhitespace);
		
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
		lblNewLabel.setFont(font.deriveFont(17f));
		panelStatsMode.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblStatsFavouriteMode = new JLabel(puzzleControl.getStatistics().findFavouriteDifficulty());
		lblStatsFavouriteMode.setVerticalAlignment(SwingConstants.TOP);
		lblStatsFavouriteMode.setFont(fontLight.deriveFont(32f));
		panelStatsMode.add(lblStatsFavouriteMode);
		lblStatsFavouriteMode.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelStatsCompletion = new JPanel();
		panelStatsCompletion.setBackground(Color.WHITE);
		panelStatsTables.add(panelStatsCompletion);
		panelStatsCompletion.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Puzzles Completed");
		lblNewLabel_1.setFont(font.deriveFont(17f));
		panelStatsCompletion.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblStatsCompletion = new JLabel(String.valueOf(puzzleControl.getStatistics().getNumPuzzlesFinished()));
		lblStatsCompletion.setVerticalAlignment(SwingConstants.TOP);
		lblStatsCompletion.setFont(fontLight.deriveFont(32f));
		panelStatsCompletion.add(lblStatsCompletion);
		lblStatsCompletion.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelStatsAutofills = new JPanel();
		panelStatsAutofills.setBackground(new Color(255, 255, 255));
		panelStatsTables.add(panelStatsAutofills);
		panelStatsAutofills.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Autofills used");
		lblNewLabel_2.setFont(font.deriveFont(17f));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panelStatsAutofills.add(lblNewLabel_2);
		
		JLabel lblStatsAutofills = new JLabel(String.valueOf(puzzleControl.getStatistics().getTotalCheat()));
		lblStatsAutofills.setVerticalAlignment(SwingConstants.TOP);
		lblStatsAutofills.setFont(fontLight.deriveFont(32f));
		lblStatsAutofills.setHorizontalAlignment(SwingConstants.CENTER);
		panelStatsAutofills.add(lblStatsAutofills);
		
		JPanel panelWhitespace2 = new JPanel();
		GridBagConstraints gbc_panelWhitespace2 = new GridBagConstraints();
		gbc_panelWhitespace2.fill = GridBagConstraints.BOTH;
		gbc_panelWhitespace2.gridx = 0;
		gbc_panelWhitespace2.gridy = 4;
		contentPane.add(panelWhitespace2, gbc_panelWhitespace2);
	}
	
	private void goToFrame(JFrame frame)
	{
		frame.setVisible(true);
		setVisible(false);
	}
}