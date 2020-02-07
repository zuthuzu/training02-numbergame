package ua.kpi.tef.zu;

/**
 * Created by Anton Domin on 2020-02-06
 */

public class View {
	// phrasebook
	public static final String GUESS_INTRO = "Hello! Welcome to the game of 'Guess a number'.";
	public static final String RANGE_INFO_1 = "The number is somewhere between ";
	public static final String RANGE_INFO_2 = " and ";
	public static final String RANGE_INFO_3 = ".";
	public static final String INPUT_GUESS = "Cmon, make a guess: ";
	public static final String GUESS_NAN = "What? That's not even a number.";
	public static final String GUESS_WTF = "...Something went wrong here. Let's try to forget about this.";
	public static final String GUESS_BELOW_FLOOR = "Nooo, that's way too low. It can't be that low.";
	public static final String GUESS_BELOW_TARGET = "Nice try, but you need to get higher.";
	public static final String GUESS_ABOVE_TARGET = "Nice try, but you need to drop lower.";
	public static final String GUESS_ABOVE_CEILING = "Nooo, that's way too high. It can't be that high.";
	public static final String GUESS_BINGO = "Bingo! Congratulations.";
	public static final String INPUT_SIZE_1 = "It took you ";
	public static final String INPUT_SIZE_2 = " attempts to guess right.";
	public static final String INPUT_QUE = "Here is the complete story of your success: ";
	public static final String INPUT_QUE_SEPARATOR = ", ";
	public static final String INPUT_QUE_TERMINATOR = ".";

	public void printAndKeepLine(String message) {
		System.out.print(message + " ");
	}

	public void printAndEndLine(String message) {
		System.out.println(message);
	}
}
