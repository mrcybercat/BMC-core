package multi.converter.data;

import multi.converter.metrics.abstractions.MetricTarget;

public class CuboidData implements DataClass, MetricTarget {
    double[][][] matrix3d;

    public CuboidData(double[][][] matrix3d) {
        this.matrix3d = matrix3d;
    }

    public double[][][] get3DMatrix(){
        return matrix3d;
    }
}
/*
    public CuboidData(double[][]... matrices){
        this.matrix3d = new double[matrices.length][matrices[0].length][matrices[0][0].length];
        for(int i=0; i < matrices.length; i++){
            matrix3d[i] = matrices[i];
        }
    }
 */
