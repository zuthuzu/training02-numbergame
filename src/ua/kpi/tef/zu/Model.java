package ua.kpi.tef.zu;

import java.util.*;

/**
 * Created by Anton Domin on 2020-02-06
 */

public class Model {
	// The Constants
	private static final int TARGET_FLOOR = 0;
	private static final int TARGET_CEILING = 100;

	private int targetValue;
	private Queue<Integer> userInputsQue = new LinkedList<>();
	private Set<Integer> userInputsSet = new HashSet<>();

	// there is really no reason to start without a ready number
	public Model() {
		newTarget();
	}

	public int getTargetFloor() {
		return TARGET_FLOOR;
	}

	public int getTargetCeiling() {
		return TARGET_CEILING;
	}

	public void newTarget() {
		targetValue = (int) (Math.random() * (TARGET_CEILING - TARGET_FLOOR) + TARGET_FLOOR);

		userInputsSet.clear();

		while (userInputsQue.poll() != null) {
		}
	}

	public int getTarget() {
		return targetValue;
	}

	public byte checkInput(int value) {

		if (value < TARGET_FLOOR) {
			return -2;
		}

		if (value > TARGET_CEILING) {
			return 2;
		}

		// we only log valid inputs
		userInputsQue.add(value);
		userInputsSet.add(value);

		if (value < targetValue) {
			return -1;
		}

		if (value > targetValue) {
			return 1;
		}

		return 0; //at this point we can safely assume it equals targetValue
	}

	public boolean inputIsRepeated(int value) {
		return userInputsSet.contains(value);
	}

	public int inputSize() {
		return userInputsQue.size();
	}

	public String inputDump() {
		String result = "";
		while (userInputsQue.peek() != null) {
			result = result + userInputsQue.poll();

			if (userInputsQue.peek() != null) {
				result = result + View.INPUT_QUE_SEPARATOR;
			} else {
				result = result + View.INPUT_QUE_TERMINATOR;
			}
		}
		return result;
	}

}
