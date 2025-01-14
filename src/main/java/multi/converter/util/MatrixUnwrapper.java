package multi.converter.util;

import java.util.Arrays;

public final class MatrixUnwrapper {

    private MatrixUnwrapper() {}

    public static byte[][] unwrapPrimitive(Byte[][] matrix) {
        return Arrays.stream(matrix)
                .map(row -> {
                    byte[] primitiveRow = new byte[row.length];
                    for (int i = 0; i < row.length; i++) {
                        primitiveRow[i] = row[i];
                    }
                    return primitiveRow;
                })
                .toArray(byte[][]::new);
    }

    public static short[][] unwrapPrimitive(Short[][] matrix) {
        return Arrays.stream(matrix)
                .map(row -> {
                    short[] primitiveRow = new short[row.length];
                    for (int i = 0; i < row.length; i++) {
                        primitiveRow[i] = row[i];
                    }
                    return primitiveRow;
                })
                .toArray(short[][]::new);
    }


    public static int[][] unwrapPrimitive(Integer[][] matrix) {
        return Arrays.stream(matrix)
                .map(row -> {
                    int[] primitiveRow = new int[row.length];
                    for (int i = 0; i < row.length; i++) {
                        primitiveRow[i] = row[i];
                    }
                    return primitiveRow;
                })
                .toArray(int[][]::new);
    }

    public static long[][] unwrapPrimitive(Long[][] byteArray) {
        return Arrays.stream(byteArray)
                .map(row -> {
                    long[] primitiveRow = new long[row.length];
                    for (int i = 0; i < row.length; i++) {
                        primitiveRow[i] = row[i];
                    }
                    return primitiveRow;
                })
                .toArray(long[][]::new);
    }

    public static float[][] unwrapPrimitive(Float[][] byteArray) {
        return Arrays.stream(byteArray)
                .map(row -> {
                    float[] primitiveRow = new float[row.length];
                    for (int i = 0; i < row.length; i++) {
                        primitiveRow[i] = row[i];
                    }
                    return primitiveRow;
                })
                .toArray(float[][]::new);
    }

    public static double[][] unwrapPrimitive(Double[][] byteArray) {
        return Arrays.stream(byteArray)
                .map(row -> {
                    double[] primitiveRow = new double[row.length];
                    for (int i = 0; i < row.length; i++) {
                        primitiveRow[i] = row[i];
                    }
                    return primitiveRow;
                })
                .toArray(double[][]::new);
    }

    public static boolean[][] unwrapPrimitiveBoolean(Boolean[][] byteArray) {
        return Arrays.stream(byteArray)
                .map(row -> {
                    boolean[] primitiveRow = new boolean[row.length];
                    for (int i = 0; i < row.length; i++) {
                        primitiveRow[i] = row[i];
                    }
                    return primitiveRow;
                })
                .toArray(boolean[][]::new);
    }

}
