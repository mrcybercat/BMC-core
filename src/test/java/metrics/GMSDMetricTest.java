package metrics;

import multi.converter.metrics.concrete.image.GMSDMetric;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GMSDMetricTest {
    double[][] input1 = {
            {203, 203, 203, 203, 203, 203, 000, 000, 203, 203, 203, 203},
            {203, 203, 203, 203, 203, 203, 000, 000, 203, 203, 203, 203},
            {203, 123, 203, 203, 203, 203, 000, 000, 203, 203, 203, 203},
            {203, 123, 203, 203, 203, 203, 000, 000, 203, 203, 203, 203},

            {203, 123, 203, 234, 234, 203, 000, 000, 203, 203, 203, 203},
            {123, 123, 234, 234, 234, 203, 000, 000, 203, 203, 203, 203},
            {123, 123, 203, 234, 203, 203, 000, 000, 203, 203, 203, 203},
            {203, 123, 203, 203, 203, 203, 000, 000, 89, 89, 203, 203},

            {203, 203, 203, 203, 203, 203, 000, 000, 89, 89, 89, 203},
            {203, 203, 203, 203, 203, 203, 000, 000, 89, 89, 89, 89},
            {203, 203, 203, 203, 203, 203, 000, 000, 89, 89, 89, 203},
            {203, 203, 203, 203, 203, 203, 000, 000, 89, 203, 203, 203}
    };
    double[][] input2 = {
            {203, 203, 203, 203, 203, 203, 000, 000, 203, 203, 234, 203},
            {203, 234, 203, 203, 89, 203, 000, 000, 203, 203, 234, 203},
            {203, 123, 203, 203, 203, 203, 000, 89, 203, 234, 203, 203},
            {203, 123, 89, 203, 203, 203, 000, 203, 203, 203, 203, 203},

            {203, 234, 203, 234, 234, 203, 203, 000, 203, 203, 203, 203},
            {123, 203, 234, 234, 89, 203, 000, 203, 203, 203, 203, 203},
            {123, 123, 203, 234, 203, 203, 000, 203, 203, 203, 203, 203},
            {203, 123, 203, 203, 203, 203, 203, 000, 89, 89, 203, 203},

            {203, 203, 203, 203, 203, 203, 000, 203, 89, 89, 89, 203},
            {203, 203, 203, 203, 203, 203, 203, 000, 89, 89, 89, 89},
            {203, 234, 203, 203, 203, 203, 234, 000, 89, 89, 89, 203},
            {203, 203, 203, 203, 203, 203, 000, 000, 89, 203, 203, 203}

    };
    @Test
    void calculateMetricControl() {
        GMSDMetric gmsd = new GMSDMetric();
        double score = gmsd.calculateMetric(input1, input1);

        assertEquals(0, score);
    }

    @Test
    void calculateMetricControl2() {
        GMSDMetric gmsd = new GMSDMetric();
        double score = gmsd.calculateMetric(input1, input2);
        double diff = Math.abs(0.16 - score);
        assertTrue(diff <= 0.01,
                String.format("Difference exceeds tolerance: %.2f", diff));
    }
}
