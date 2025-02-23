import multi.converter.util.MatrixStatistics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixStatisticsTest {
    double[][] input = {
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

    @Test
    void convolution2x2KernelTest() {
        double[][] kernel = {
                {0.25, 0.25},
                {0.25, 0.25},
        };

        double[][] expected = {
                {203.0000,203.0000,203.0000,203.0000,203.0000,101.5000,0,101.5000,203.0000,203.0000,203.0000,101.5000},
                {183.0000,183.0000,203.0000,203.0000,203.0000,101.5000,0,101.5000,203.0000,203.0000,203.0000,101.5000},
                {163.0000,163.0000,203.0000,203.0000,203.0000,101.5000,0,101.5000,203.0000,203.0000,203.0000,101.5000},
                {163.0000,163.0000,210.7500,218.5000,210.7500,101.5000,0,101.5000,203.0000,203.0000,203.0000,101.5000},

                {143.0000,170.7500,226.2500,234.0000,218.5000,101.5000,0,101.5000,203.0000,203.0000,203.0000,101.5000},
                {123.0000,170.7500,226.2500,226.2500,210.7500,101.5000,0,101.5000,203.0000,203.0000,203.0000,101.5000},
                {143.0000,163.0000,210.7500,210.7500,203.0000,101.5000,0,73.0000,146.0000,174.5000,203.0000,101.5000},
                {183.0000,183.0000,203.0000,203.0000,203.0000,101.5000,0,44.5000,89.0000,117.5000,174.5000,101.5000},

                {203.0000,203.0000,203.0000,203.0000,203.0000,101.5000,0,44.5000,89.0000,89.0000,117.5000,73.0000},
                {203.0000,203.0000,203.0000,203.0000,203.0000,101.5000,0,44.5000,89.0000,89.0000,117.5000,73.0000},
                {203.0000,203.0000,203.0000,203.0000,203.0000,101.5000,0,44.5000,117.5000,146.0000,174.5000,101.5000},
                {101.5000,101.5000,101.5000,101.5000,101.5000,50.7500,0,22.2500,73.0000,101.5000,101.5000,50.7500}
        };

        double[][] actual = MatrixStatistics.convolve(input, kernel);

        System.out.println("Expected:");
        print2D(expected);

        System.out.println("Actual:");
        print2D(actual);

        assertArraysClose(expected, actual, 1.0);
    }

    @Test
    void convolution3x3KernelTest() {
        // array needs to be read backwards for some reason
        double[][] kernel = {
                {-0.3333, 0, 0.3333},
                {-0.3333, 0, 0.3333},
                {-0.3333, 0, 0.3333}
        };

        double[][] expected = {
                {135.3333,0,0,0,0,-135.3333,-135.3333,135.3333,135.3333,0,0,-135.3333},
                {176.3333,0,26.6667,0,0,-203.0000,-203.0000,203.0000,203.0000,0,0,-203.0000},
                {149.6667,0,53.3333,0,0,-203.0000,-203.0000,203.0000,203.0000,0,0,-203.0000},
                {123.0000,0,90.3333,10.3333,-10.3333,-213.3333,-203.0000,203.0000,203.0000,0,0,-203.0000},

                {123.0000,37.0000,100.6667,10.3333,-20.6667,-223.6667,-203.0000,203.0000,203.0000,0,0,-203.0000},
                {123.0000,63.6667,111.0000,10.3333,-31.0000,-223.6667,-203.0000,203.0000,203.0000,0,0,-203.0000},
                {123.0000,63.6667,100.6667,0,-20.6667,-213.3333,-203.0000,165.0000,165.0000,38.0000,38.0000,-203.0000},
                {149.6667,26.6667,63.6667,0,-10.3333,-203.0000,-203.0000,127.0000,127.0000,38.0000,76.0000,-165.0000},

                {176.3333,0,26.6667,0,0,-203.0000,-203.0000,89.0000,89.0000,38.0000,76.0000,-127.0000},
                {203.0000,0,0,0,0,-203.0000,-203.0000,89.0000,89.0000,0.0000,76.0000,-89.0000},
                {203.0000,0,0,0,0,-203.0000,-203.0000,89.0000,127.0000,38.0000,38.0000,-127.0000},
                {135.3333,0,0,0,0,-135.3333,-135.3333,59.3333,97.3333,38.0000,38.0000,-97.3333}
        };

        double[][] actual = MatrixStatistics.convolve(input, kernel);

        System.out.println("Expected:");
        print2D(expected);

        System.out.println("Actual:");
        print2D(actual);

        assertArraysClose(expected, actual, 0.1);
    }

    public static void print2D(double mat[][])
    {
        // Loop through all rows
        for (double[] row : mat)
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }

    private static void assertArraysClose(double[][] expected, double[][] actual, double tolerance) {
        assertTrue(expected.length == actual.length, "Number of rows should match");
        for (int i = 0; i < expected.length; i++) {
            assertTrue(expected[i].length == actual[i].length, "Row " + i + " should have the same length");
            for (int j = 0; j < expected[i].length; j++) {
                double diff = Math.abs(expected[i][j] - actual[i][j]);
                assertTrue(diff <= tolerance,
                        String.format("Difference at position [%d][%d] exceeds tolerance: %.2f", i, j, diff));
            }
        }
    }
}
