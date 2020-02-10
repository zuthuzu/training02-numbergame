package ua.kpi.tef.zu;

import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Anton Domin on 2020-02-06
 */

public class Controller {

	// Constructor
	private Model model;
	private View view;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	// The Work method
	public void startNumberGame() {
		Scanner sc = new Scanner(System.in);

		model.newTarget();

		view.printAndEndLine(View.GUESS_INTRO);

		coreInputLoop(sc);

		//we can only get here by guessing right
		view.printAndEndLine(View.INPUT_SIZE_1 + model.inputSize() + View.INPUT_SIZE_2);
		view.printAndEndLine(View.INPUT_QUE + inputQueDump());

	}

	private void coreInputLoop(Scanner sc) {
		int recentInput;
		boolean gameIsWon;
		do {
			view.printAndEndLine(View.RANGE_INFO_1 + model.getTargetFloor() + View.RANGE_INFO_2 + model.getTargetCeiling() + View.RANGE_INFO_3);

			recentInput = inputIntValueWithScanner(sc);

			gameIsWon = isGameWon(model.checkInput(recentInput));
		} while (!gameIsWon);
	}

	private boolean isGameWon(byte lookupCode) {
		boolean gameIsWon = false;

		switch (lookupCode) {
			case (0):
				view.printAndKeepLine(View.GUESS_BINGO);
				gameIsWon = true;
				break;
			case (-1):
				view.printAndKeepLine(View.GUESS_BELOW_TARGET);
				break;
			case (1):
				view.printAndKeepLine(View.GUESS_ABOVE_TARGET);
				break;
			case (-2):
				view.printAndKeepLine(View.GUESS_BELOW_FLOOR);
				break;
			case (2):
				view.printAndKeepLine(View.GUESS_ABOVE_CEILING);
				break;
			default:
				view.printAndKeepLine(View.GUESS_WTF);
				break;
		}
		return gameIsWon;
	}

	// The Utility methods
	public int inputIntValueWithScanner(Scanner sc) {
		view.printAndKeepLine(View.INPUT_GUESS);
		while (!sc.hasNextInt()) {
			view.printAndEndLine(View.GUESS_NAN);
			view.printAndKeepLine(View.INPUT_GUESS);
			sc.next();
		}
		return sc.nextInt();
	}

	public String inputQueDump() {
		StringBuilder result = new StringBuilder();
		Queue<Integer> userInputQue;

		userInputQue = model.getFullInputQue();

		while (userInputQue.peek() != null) {
			result.append(userInputQue.poll());
			result.append(userInputQue.peek() != null? View.INPUT_QUE_SEPARATOR: View.INPUT_QUE_TERMINATOR);
		}
		return result.toString();
	}

}
