package multi.converter.data;

public class DataBlock {
    double[][] block;

    public DataBlock() {
        this.block = new double[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
    }

    public double[][] getBlock() {
        return block;
    }

    public void setBlockValueOnXY(byte value, int x, int y) {
        this.block[y][x] = value;
    }

    public double getBlockValueOnXY(int x, int y){
        return block[y][x];
    }



}
