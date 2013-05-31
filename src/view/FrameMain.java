package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.PuzzleControl;
import controller.timer.TimerLabel;

import java.awt.GridLayout;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.FlowLayout;
import java.awt.CardLayout;

/**
 * The class that represents the GUI of the Sudoku puzzle game.
 * @author Nikhil Suresh, Ryan Tan, Nicholas Ho
 *
 */
public class FrameMain extends JFrame {
	private Font font;
	private Font fontMed;
	private Font fontLight;
	private Color nicholasisgay;
	
	private JPanel contentPane;

	/**
	 * Creates the frame of the GUI being used to display the Sudoku board and other supplementary information
	 * in an attractive and accessible manner
	 */
	public FrameMain() {

		setResizable(false);
		setBackground(SystemColor.window);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		
		contentPane = new JPanel();
		contentPane.setBackground(nicholasisgay);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnStartGame = new JButton("Start game");
		btnStartGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameGame f = new FrameGame();
				f.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(btnStartGame);
	}
}