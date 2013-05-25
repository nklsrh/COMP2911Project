package controller.generate;

public class GeneratorTest {

	public static void main(String[] args) {
		Generator gen = new Generator();
		System.out.println(gen.puzzlePrettyPrint());
		
		gen.shufflePuzzle();
		System.out.println(gen.puzzlePrettyPrintByGroups());
	}
	
	
}
