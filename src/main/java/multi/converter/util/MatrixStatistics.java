package multi.converter.util;

public final class MatrixStatistics {

    private MatrixStatistics() {}


    public static strictfp double meanSquaredError(double[][] original, double[][] altered){
        double mse = 0;
        for (int y = 0; y < original.length; y++) {
            for (int x = 0; x < original[0].length; x++) {
                mse +=  Math.pow((original[y][x] - altered[y][x]), 2);
            }
        }
        mse = mse / (double) (original.length * original[0].length);
        return mse;
    }

    public static strictfp  double peakNoiseToSignalRation(double[][] original, double[][] altered){
        return 10 * Math.log10((255*255) / meanSquaredError(original, altered));
    }

    public static strictfp  double crossCovariance(double[][] original, double[][] altered){
        double mean1 = mean(original);
        double mean2 = mean(altered);

        double crossCovariance = 0;
        for (int y = 0; y < original.length; y++) {
            for (int x = 0; x < original[0].length; x++) {
                crossCovariance +=  (original[y][x] - mean1) * (altered[y][x] - mean2);
            }
        }
        crossCovariance = crossCovariance / (double) (original.length * original[0].length - 1);
        return crossCovariance;
    }



    public static strictfp double[][] convolve(double[][] matrix, double[][] kernel) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int kernelRows = kernel.length;
        int kernelCols = kernel[0].length;

        // Be mindful of center
        // requesr additional testing
        // Should be 0 on size 1, 1 on size 3 ???
        int kCenterX = (int) (Math.ceil(kernelCols / 2.0)) - 1;
        int kCenterY = (int) (Math.ceil(kernelRows / 2.0)) - 1;

        double[][] output = new double[rows][cols];
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                double sum = 0.0;
                for (int m = 0; m < kernelRows; m++) {
                    for (int n = 0; n < kernelCols; n++) {
                        int x = j + n - kCenterX;
                        int y = i + m - kCenterY;

                        if (x >= 0 && x < cols && y >= 0 && y < rows) {
                            sum += matrix[y][x] * kernel[m][n];
                        }
                    }
                }

                output[i][j] = sum;
            }
        }

        return output;
    }

    public static strictfp  double[][] averagingFilter(int size){
        double[][] filter = new double[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                filter[i][j] = (double) 1 / (double) (size*size);
            }
        }
        return filter;
    }

    public static strictfp  double mean(double[][] original){
        double mean = 0;
        for (int y = 0; y < original.length; y++) {
            for (int x = 0; x < original[0].length; x++) {
                mean += original[y][x];
            }
        }
        mean = mean / (double) (original.length * original[0].length);
        return mean;
    }

    public static strictfp  double variance(double[][] matrix){
        double var = 0;
        double mean = mean(matrix);

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                var += Math.pow(matrix[y][x] - mean, 2);
            }
        }
        var = var / (double) (matrix.length * matrix[0].length);
        return var;
    }

    public static strictfp double standardDeviation(double[][] matrix){
        return Math.sqrt(variance(matrix));
    }


    public static double[][] downScale(double[][] matrix, int downscaleFactor){
        double[][] newMatrix = new double[matrix.length/downscaleFactor][matrix[0].length/downscaleFactor];

        for (int y = 0; y < matrix.length / downscaleFactor; y++) {
            for (int x = 0; x < matrix[0].length / downscaleFactor; x++) {
                newMatrix[y][x]  = matrix[y * downscaleFactor][x * downscaleFactor];
            }
        }
        return newMatrix;
    }

    public static double[][] pow(double[][] matrix, int factor){
        double[][] newMatrix = new double[matrix.length][matrix[0].length];

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                newMatrix[y][x]  = Math.pow(matrix[y][x], factor);
            }
        }
        return newMatrix;
    }

    public static double[][] sqrt(double[][] matrix){
        double[][] newMatrix = new double[matrix.length][matrix[0].length];

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                newMatrix[y][x]  = Math.sqrt(matrix[y][x]);
            }
        }
        return newMatrix;
    }

    public static double[][] sum(double[][] matrix1, double[][] matrix2){
        if(matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length){
            return null;
        }
        double[][] newMatrix = new double[matrix1.length][matrix1[0].length];
        for (int y = 0; y < matrix1.length; y++) {
            for (int x = 0; x < matrix1[0].length; x++) {
                newMatrix[y][x]  = matrix1[y][x] +  matrix2[y][x];
            }
        }
        return newMatrix;
    }

    public static double[][] divide(double[][] matrix1, double[][] matrix2){
        if(matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length){
            return null;
        }
        double[][] newMatrix = new double[matrix1.length][matrix1[0].length];
        for (int y = 0; y < matrix1.length; y++) {
            for (int x = 0; x < matrix1[0].length; x++) {
                newMatrix[y][x]  = matrix1[y][x] /  matrix2[y][x];
            }
        }
        return newMatrix;
    }

    public static double[][] multiply(double[][] a, double[][] b){//a[m][n], b[n][p]
        if(a.length == 0) return new double[0][0];
        if(a[0].length != b.length) return null; //invalid dims

        int n = a[0].length;
        int m = a.length;
        int p = b[0].length;
        double[][] ans = new double[m][p];

        for(int i = 0;i < m;i++){
            for(int j = 0;j < p;j++){
                for(int k = 0;k < n;k++){
                    ans[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return ans;
    }

    public static double[][] multiplyPlain(double[][] matrix1, double[][] matrix2){//a[m][n], b[n][p]
        if(matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length){
            return null;
        }
        double[][] newMatrix = new double[matrix1.length][matrix1[0].length];
        for (int y = 0; y < matrix1.length; y++) {
            for (int x = 0; x < matrix1[0].length; x++) {
                newMatrix[y][x]  = matrix1[y][x] *  matrix2[y][x];
            }
        }
        return newMatrix;
    }

    public static double[][] multiplyConst(double[][] matrix, double number){//a[m][n], b[n][p]
        double[][] newMatrix = new double[matrix.length][matrix[0].length];

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                newMatrix[y][x]  = matrix[y][x] * number;
            }
        }
        return newMatrix;
    }


    public static double[][] addConst(double[][] matrix, double number){//a[m][n], b[n][p]
        double[][] newMatrix = new double[matrix.length][matrix[0].length];

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                newMatrix[y][x]  = matrix[y][x] + number;
            }
        }
        return newMatrix;
    }

}
