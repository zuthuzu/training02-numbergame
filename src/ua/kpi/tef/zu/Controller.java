package ua.kpi.tef.zu;

import java.util.Scanner;

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
		int recentInput;
		byte lookupCode;
		boolean gameIsWon = false;
		boolean inputIsRepeated = false;

		model.newTarget();

		view.printMessage(View.GUESS_INTRO);
		view.printMessage(View.RANGE_INFO_1 + model.getTargetFloor() + View.RANGE_INFO_2 + model.getTargetCeiling() + View.RANGE_INFO_3);

		do {
			recentInput = inputIntValueWithScanner(sc);
			inputIsRepeated = model.inputIsRepeated(recentInput);
			lookupCode = model.checkInput(recentInput);

			switch (lookupCode) {
				case (0):
					view.printMessage(View.GUESS_BINGO);
					gameIsWon = true;
					break;
				case (-1):
					view.printMessage(View.GUESS_BELOW_TARGET);
					break;
				case (1):
					view.printMessage(View.GUESS_ABOVE_TARGET);
					break;
				case (-2):
					view.printMessage(View.GUESS_BELOW_FLOOR);
					break;
				case (2):
					view.printMessage(View.GUESS_ABOVE_CEILING);
					break;
				default:
					view.printMessage(View.GUESS_WTF);
					break;
			}

			if (inputIsRepeated) {
				view.printMessage(View.GUESS_REPEAT);
			}
		} while (!gameIsWon);

		//we can only get here by guessing right
		view.printMessage(View.INPUT_SIZE_1 + model.inputSize() + View.INPUT_SIZE_2);
		view.printMessage(View.INPUT_QUE + model.inputDump());

	}

	// The Utility methods
	public int inputIntValueWithScanner(Scanner sc) {
		view.printMessage(View.INPUT_GUESS);
		while (!sc.hasNextInt()) {
			view.printMessage(View.GUESS_NAN);
			view.printMessage(View.INPUT_GUESS);
			sc.next();
		}
		return sc.nextInt();
	}

}
