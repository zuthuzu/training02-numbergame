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
	private Queue<Integer> userInputsQue = new LinkedList<>();

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

	public void newTarget() {
		targetFloor = STARTING_FLOOR;
		targetCeiling = STARTING_CEILING;

		targetValue = (int) (Math.random() * (targetCeiling - targetFloor - 1) + targetFloor + 1);

		while (userInputsQue.poll() != null) ;
	}

	public int getTarget() {
		return targetValue;
	}

	public byte checkInput(int value) {

		if (value <= targetFloor) {
			return -2;
		}

		if (value >= targetCeiling) {
			return 2;
		}

		// we only log valid inputs
		userInputsQue.add(value);

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
		return userInputsQue.size();
	}

	public String inputDump() {
		String result = "";
		while (userInputsQue.peek() != null) {
			result += userInputsQue.poll();

			if (userInputsQue.peek() != null) {
				result += View.INPUT_QUE_SEPARATOR;
			} else {
				result += View.INPUT_QUE_TERMINATOR;
			}
		}
		return result;
	}

}
