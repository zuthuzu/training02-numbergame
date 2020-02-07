package ua.kpi.tef.zu_test;

import org.junit.Assert;
import org.junit.Test;
import ua.kpi.tef.zu.Model;

/**
 * Created by Anton Domin on 2020-02-06
 */

public class NumberGameTest {

	private static final int SATURATION_ITERATION_LIMIT = 99999;

	private int targetFloor;
	private int targetCeiling;

	@Test
	public void testTargetReliablyFillsBoundaries() {
		Model numberModel = new Model();

		targetFloor = numberModel.getTargetFloor();
		targetCeiling = numberModel.getTargetCeiling();

		int targetValue;
		boolean rolledRealLow = false, rolledRealHigh = false;

		// let's assume _real_ low and high rolls to be within 1% off both ends
		int rollTolerance = Math.max(((targetCeiling - targetFloor) / 100), 1);

		for (int i = 0; i < SATURATION_ITERATION_LIMIT; i++) {
			numberModel.newTarget();
			targetValue = numberModel.getTarget();

			if (targetValue <= targetFloor || targetValue >= targetCeiling) {
				Assert.fail("Target (" + targetValue + ") is outside of bounds");
				return;
			}

			if (targetValue <= (targetFloor + rollTolerance)) {
				rolledRealLow = true;
			}

			if (targetValue >= (targetCeiling - rollTolerance)) {
				rolledRealHigh = true;
			}
		}

		if (!rolledRealLow) {
			Assert.fail("Boundaries fail: no target numbers rolled at " + (targetFloor + rollTolerance) + " or below");
		}

		if (!rolledRealHigh) {
			Assert.fail("Boundaries fail: no target numbers rolled at " + (targetCeiling - rollTolerance) + " or above");
		}
	}

}
