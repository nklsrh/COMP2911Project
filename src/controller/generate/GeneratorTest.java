package controller.generate;

import model.Puzzle;

public class GeneratorTest {

	public static void main(String[] args) {
		Generator gen = new Generator();
		System.out.println(gen.puzzlePrettyPrintByGroups("row"));
		System.out.println(gen.puzzlePrettyPrintByGroups("col"));
		System.out.println("---------------------------\n");
		
		gen.shufflePuzzle();
		
		System.out.println("---------------------------\n");
		System.out.println(gen.puzzlePrettyPrintByGroups("row"));
		System.out.println(gen.puzzlePrettyPrintByGroups("col"));
		
		Puzzle puzzle = new Puzzle();
		puzzle = gen.packageUp(puzzle);
		System.out.println(puzzle.toString());
	}
	
	
}
