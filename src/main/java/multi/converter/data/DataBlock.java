package multi.converter.data;

import java.util.Arrays;

public class DataBlock {
    private int size = 8;
    private double[][] block;

    public DataBlock(int size) {
        this.size = size;
        this.block = new double[size][size];
        Arrays.stream(block).forEach(a -> Arrays.fill(a, 0));
    }

    public DataBlock(double[][] block) {
        if(block.length != block[0].length){
            throw new IllegalArgumentException();
        }
        this.size = block.length;
        this.block = block;
    }

    public DataBlock() {
        this.block = new double[size][size];
        Arrays.stream(block).forEach(a -> Arrays.fill(a, 0));
    }

    public int getSize() {
        return size;
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

    @Override
    public String toString() {
        return "DataBlock{" +
                "block=" + Arrays.deepToString(block) +
                '}';
    }
}
