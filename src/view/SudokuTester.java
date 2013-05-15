package view;

import java.awt.FlowLayout;

import javax.swing.*;

public class SudokuTester {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello Sudoku");
			
		try {
			FrameMain frame = new FrameMain();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
