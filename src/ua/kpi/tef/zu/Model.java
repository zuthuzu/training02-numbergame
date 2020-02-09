package ua.kpi.tef.zu;

import java.util.*;

/**
 * Created by Anton Domin on 2020-02-06
 */

public class Model {
	// The Constants
	private static final int STARTING_FLOOR = 0;
	private static final int STARTING_CEILING = 100;

	private int targetFloor;
	private int targetCeiling;

	private int targetValue;

	private Queue<Integer> userInputQue = new LinkedList<>();

	// there is really no reason to start without a ready number
	public Model() {
		newTarget();
	}

	public int getTargetFloor() {
		return targetFloor;
	}

	public int getTargetCeiling() {
		return targetCeiling;
	}

	/**
	 * Returns the game to its' starting state:<br>
	 * 1. Resets the allowed range of inputs to default values.<br>
	 * 2. Generates a new random target number within that range.<br>
	 * 3. Empties the history of valid player inputs.<br>
	 */
	public void newTarget() {
		targetFloor = STARTING_FLOOR;
		targetCeiling = STARTING_CEILING;

		targetValue = (int) (Math.random() * (targetCeiling - targetFloor - 1) + targetFloor + 1);

		//noinspection StatementWithEmptyBody
		while (userInputQue.poll() != null) ;
	}

	public int getTarget() {
		return targetValue;
	}

	/**
	 * Checks the number against the previously generated target number and a current range.<br>
	 * <br>
	 * If the number is inside the current allowed range, it logs the input.<br>
	 * <br>
	 * If the number is below the target, it becomes a new lower range bracket.<br>
	 * <br>
	 * If the number is above the target, it becomes a new higher range bracket.<br>
	 *
	 * @param  value an integer number that is to be checked
	 * @return a numeric code:<br>
	 * -2 : the number is below the allowed range,<br>
	 * -1 : the number is within the allowed range, but below the target number,<br>
	 * 0 : the number equals target number,<br>
	 * 1 : the number is with the allowed range, but above the target number,<br>
	 * 2 : the number is above the allowed range.
	 */
	public byte checkInput(int value) {

		if (value <= targetFloor) {
			return -2;
		}

		if (value >= targetCeiling) {
			return 2;
		}

		// we only log valid inputs
		userInputQue.add(value);

		if (value < targetValue) {
			targetFloor = value;
			return -1;
		}

		if (value > targetValue) {
			targetCeiling = value;
			return 1;
		}

		return 0; //at this point we can safely assume it equals targetValue
	}

	public int inputSize() {
		return userInputQue.size();
	}

	public Queue<Integer> getFullInputQue() {
		return userInputQue;
	}

}
