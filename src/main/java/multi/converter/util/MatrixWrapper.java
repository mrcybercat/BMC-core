package multi.converter.util;

import java.util.Arrays;

public final class MatrixWrapper {

    private MatrixWrapper() {}

    public static Byte[][] wrapPrimitive(byte[][] primitiveMatrix) {
        return Arrays.stream(primitiveMatrix)
                .map(row -> {
                    Byte[] boxedRow = new Byte[row.length];
                    for (int i = 0; i < row.length; i++) {
                        boxedRow[i] = row[i]; // Autoboxing byte to Byte
                    }
                    return boxedRow;
                })
                .toArray(Byte[][]::new);
    }

    public static Short[][] wrapPrimitive(short[][] primitiveMatrix) {
        return Arrays.stream(primitiveMatrix)
                .map(row -> {
                    Short[] boxedRow = new Short[row.length];
                    for (int i = 0; i < row.length; i++) {
                        boxedRow[i] = row[i]; // Autoboxing byte to Byte
                    }
                    return boxedRow;
                })
                .toArray(Short[][]::new);
    }

    public static Integer[][] wrapPrimitive(int[][] primitiveMatrix) {
        return Arrays.stream(primitiveMatrix)
                .map(row -> {
                    Integer[] boxedRow = new Integer[row.length];
                    for (int i = 0; i < row.length; i++) {
                        boxedRow[i] = row[i]; // Autoboxing byte to Byte
                    }
                    return boxedRow;
                })
                .toArray(Integer[][]::new);
    }

    public static Float[][] wrapPrimitive(float[][] primitiveMatrix) {
        return Arrays.stream(primitiveMatrix)
                .map(row -> {
                    Float[] boxedRow = new Float[row.length];
                    for (int i = 0; i < row.length; i++) {
                        boxedRow[i] = row[i]; // Autoboxing byte to Byte
                    }
                    return boxedRow;
                })
                .toArray(Float[][]::new);
    }

    public static Double[][] wrapPrimitive(double[][] primitiveMatrix) {
        return Arrays.stream(primitiveMatrix)
                .map(row -> {
                    Double[] boxedRow = new Double[row.length];
                    for (int i = 0; i < row.length; i++) {
                        boxedRow[i] = row[i]; // Autoboxing byte to Byte
                    }
                    return boxedRow;
                })
                .toArray(Double[][]::new);
    }

    public static Boolean[][] wrapPrimitive(boolean[][] primitiveMatrix) {
        return Arrays.stream(primitiveMatrix)
                .map(row -> {
                    Boolean[] boxedRow = new Boolean[row.length];
                    for (int i = 0; i < row.length; i++) {
                        boxedRow[i] = row[i]; // Autoboxing byte to Byte
                    }
                    return boxedRow;
                })
                .toArray(Boolean[][]::new);
    }
}
