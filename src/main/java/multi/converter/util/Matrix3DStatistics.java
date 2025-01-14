package multi.converter.util;

public final class Matrix3DStatistics {

    private Matrix3DStatistics() {}

    public static strictfp  double meanSquaredError(double[][][] original, double[][][] altered){
        double mse = 0;
        for(int z = 0; z < original.length; z++){
            for (int y = 0; y < original[0].length; y++) {
                for (int x = 0; x < original[0][0].length; x++) {
                    mse +=  Math.pow((original[z][y][x] - altered[z][y][x]), 2);

                }
            }
        }
        mse = mse / (double) (original.length * original[0].length * original[0][0].length );
        return mse;
    }

    public static strictfp  double peakNoiseToSignalRation(double[][][] original, double[][][] altered){
        return 10 * Math.log10((255*255) / meanSquaredError(original, altered));
    }

    public static strictfp  double crossCovariance(double[][][] original, double[][][] altered){
        double mean1 = mean(original);
        double mean2 = mean(altered);

        double crossCovariance = 0;
        for(int z = 0; z < original.length; z++){
            for (int y = 0; y < original[0].length; y++) {
                for (int x = 0; x < original[0][0].length; x++) {
                    crossCovariance +=  (original[z][y][x] - mean1) * (altered[z][y][x] - mean2);
                }
            }
        }

        crossCovariance = crossCovariance / (double) (original.length * original[0].length * original[0][0].length - 1);
        return crossCovariance;
    }



    public static strictfp double[][][] convolve(double[][][] matrix, double[][][] kernel) {
        int depth = matrix.length;
        int rows = matrix[0].length;
        int cols = matrix[0][0].length;

        int kernelDepth = kernel.length;
        int kernelRows = kernel[0].length;
        int kernelCols = kernel[0][0].length;

        int kCenterZ = (int) (Math.ceil(kernelCols / 2.0)) - 1;
        int kCenterX = (int) (Math.ceil(kernelCols / 2.0)) - 1;
        int kCenterY = (int) (Math.ceil(kernelRows / 2.0)) - 1;

        double[][][] output = new double[depth][rows][cols];

        for (int z = 0; z < depth; z++) {
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    double sum = 0.0;

                    for (int dz = 0; dz < kernelDepth; dz++) {
                        for (int dy = 0; dy < kernelRows; dy++) {
                            for (int dx = 0; dx < kernelCols; dx++) {
                                int zz = z + dz - kCenterZ;
                                int yy = y + dy - kCenterY;
                                int xx = x + dx - kCenterX;

                                if (zz >= 0 && zz < depth && yy >= 0 && yy < rows && xx >= 0 && xx < cols) {
                                    sum += matrix[zz][yy][xx] * kernel[dz][dy][dx];
                                }
                            }
                        }
                    }

                    output[z][y][x] = sum;
                }
            }
        }

        return output;
    }

    public static strictfp  double[][][] averagingFilter(int size){
        double[][][] filter = new double[size][size][size];

        for(int k = 0; k < size; k++){
            for(int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){
                    filter[k][i][j] = (double) 1 / (double) (size*size*size);
                }
            }
        }

        return filter;
    }

    public static strictfp double mean(double[][][] original){
        double mean = 0;
        for(int z = 0; z < original.length; z++){
            for (int y = 0; y < original[0].length; y++) {
                for (int x = 0; x < original[0][0].length; x++) {
                    mean += original[z][y][x];
                }
            }
        }
        mean = mean / (double) (original.length * original[0].length *  original[0][0].length);
        return mean;
    }

    public static strictfp double variance(double[][][] matrix){
        double var = 0;
        double mean = mean(matrix);
        for(int z = 0; z < matrix.length; z++){
            for (int y = 0; y < matrix[0].length; y++) {
                for (int x = 0; x < matrix[0][0].length; x++) {
                    var += Math.pow(matrix[z][y][x] - mean, 2);
                }
            }
        }

        var = var / (double) (matrix.length * matrix[0].length);
        return var;
    }

    public static strictfp double standardDeviation(double[][][] matrix){
        return Math.sqrt(variance(matrix));
    }


    public static double[][][] downScale(double[][][] matrix, int downscaleFactor){
        double[][][] newMatrix = new double[matrix.length/downscaleFactor][matrix[0].length/downscaleFactor][matrix[0][0].length/downscaleFactor];

        for(int z = 0; z < matrix.length / downscaleFactor; z++){
            for (int y = 0; y < matrix[0].length / downscaleFactor; y++) {
                for (int x = 0; x < matrix[0][0].length / downscaleFactor; x++) {
                    newMatrix[z][y][x]  = matrix[z * downscaleFactor][y * downscaleFactor][x * downscaleFactor];
                }
            }
        }

        return newMatrix;
    }

    public static double[][][] pow(double[][][] matrix, int factor){
        double[][][] newMatrix = new double[matrix.length][matrix[0].length][matrix[0][0].length];

        for (int z = 0; z < matrix.length; z++) {
            for (int y = 0; y < matrix[0].length; y++) {
                for (int x = 0; x < matrix[0][0].length; x++) {
                    newMatrix[z][y][x]  = Math.pow(matrix[z][y][x], factor);
                }
            }
        }

        return newMatrix;
    }

    public static double[][][] sqrt(double[][][] matrix){
        double[][][] newMatrix = new double[matrix.length][matrix[0].length][matrix[0][0].length];

        for (int z = 0; z < matrix.length; z++) {
            for (int y = 0; y < matrix[0].length; y++) {
                for (int x = 0; x < matrix[0][0].length; x++) {
                    newMatrix[z][y][x]  = Math.sqrt(matrix[z][y][x]);
                }
            }
        }
        return newMatrix;
    }

    public static double[][][] sum(double[][][] matrix1, double[][][] matrix2){
        if(matrix1.length != matrix2.length ||
           matrix1[0].length != matrix2[0].length ||
           matrix1[0][0].length != matrix2[0][0].length){
            return null;
        }
        double[][][] newMatrix = new double[matrix1.length][matrix1[0].length][matrix1[0][0].length];

        for (int z = 0; z < matrix1.length; z++) {
            for (int y = 0; y < matrix1[0].length; y++) {
                for (int x = 0; x < matrix1[0][0].length; x++) {
                    newMatrix[z][y][x] = matrix1[z][y][x] + matrix2[z][y][x];
                }
            }
        }
        return newMatrix;
    }

    public static double[][][] divide(double[][][] matrix1, double[][][] matrix2){
        if(matrix1.length != matrix2.length ||
                matrix1[0].length != matrix2[0].length ||
                matrix1[0][0].length != matrix2[0][0].length){
            return null;
        }
        double[][][] newMatrix = new double[matrix1.length][matrix1[0].length][matrix1[0][0].length];

        for (int z = 0; z < matrix1.length; z++) {
            for (int y = 0; y < matrix1[0].length; y++) {
                for (int x = 0; x < matrix1[0][0].length; x++) {
                    newMatrix[z][y][x] = matrix1[z][y][x] / matrix2[z][y][x];
                }
            }
        }
        return newMatrix;
    }

    public static double[][][] multiplyPlain(double[][][] matrix1, double[][][] matrix2){//a[m][n], b[n][p]
        if(matrix1.length != matrix2.length ||
                matrix1[0].length != matrix2[0].length ||
                matrix1[0][0].length != matrix2[0][0].length){
            return null;
        }
        double[][][] newMatrix = new double[matrix1.length][matrix1[0].length][matrix1[0][0].length];

        for (int z = 0; z < matrix1.length; z++) {
            for (int y = 0; y < matrix1[0].length; y++) {
                for (int x = 0; x < matrix1[0][0].length; x++) {
                    newMatrix[z][y][x] = matrix1[z][y][x] * matrix2[z][y][x];
                }
            }
        }
        return newMatrix;
    }

    public static double[][][] multiplyConst(double[][][] matrix, double number){//a[m][n], b[n][p]
        double[][][] newMatrix = new double[matrix.length][matrix[0].length][matrix[0][0].length];

        for (int z = 0; z < matrix.length; z++) {
            for (int y = 0; y < matrix[0].length; y++) {
                for (int x = 0; x < matrix[0][0].length; x++) {
                    newMatrix[z][y][x]  = matrix[z][y][x] * number;
                }
            }
        }

        return newMatrix;
    }


    public static double[][][] addConst(double[][][] matrix, double number){//a[m][n], b[n][p]
        double[][][] newMatrix = new double[matrix.length][matrix[0].length][matrix[0][0].length];

        for (int z = 0; z < matrix.length; z++) {
            for (int y = 0; y < matrix[0].length; y++) {
                for (int x = 0; x < matrix[0][0].length; x++) {
                    newMatrix[z][y][x]  = matrix[z][y][x] + number;
                }
            }
        }

        return newMatrix;
    }

}
