package ua.kpi.tef.zu_test;

import org.junit.Assert;
import org.junit.Test;
import ua.kpi.tef.zu.Model;

/**
 * Created by Anton Domin on 2020-02-06
 */

public class NumberGameTest {

	private static final int TARGET_FLOOR = 0;
	private static final int TARGET_CEILING = 100;
	private static final long SATURATION_ITERATION_LIMIT = 99999L;

	@Test
	public void testTargetInBoundaries() {
		Model numberModel = new Model();

		numberModel.newTarget();

		int targetValue = numberModel.getTarget();

		if (targetValue < TARGET_FLOOR || targetValue > TARGET_CEILING) {
			Assert.fail();
		}
	}

	@Test
	public void testTargetReliablyFillsBoundaries() {
		Model numberModel = new Model();
		int targetValue;
		boolean rolledRealLow = false, rolledRealHigh = false;

		// let's assume _real_ low and high rolls to be within 1% off both ends
		int rollTolerance = (int) (TARGET_CEILING - TARGET_FLOOR) / 100;

		for (int i = 0; i < SATURATION_ITERATION_LIMIT; i++) {
			numberModel.newTarget();
			targetValue = numberModel.getTarget();

			if (targetValue <= (TARGET_FLOOR + rollTolerance)) {
				rolledRealLow = true;
			}

			if (targetValue >= (TARGET_CEILING - rollTolerance)) {
				rolledRealHigh = true;
			}
		}

		if (!rolledRealLow) {
			Assert.fail("Boundaries fail: no target numbers rolled below " + (TARGET_FLOOR + rollTolerance));
		}

		if (!rolledRealHigh) {
			Assert.fail("Boundaries fail: no target numbers rolled above " + (TARGET_CEILING - rollTolerance));
		}
	}

}
