package multi.converter.data;

public class DataBatch implements DataClass {
    int[] arrY;
    int[] arrU;
    int[] arrV;

    public DataBatch(int[] arrY, int[] arrU, int[] arrV) {
        this.arrY = arrY;
        this.arrU = arrU;
        this.arrV = arrV;
    }

    public int[] getArrU() {
        return arrU;
    }

    public int[] getArrV() {
        return arrV;
    }

    public int[] getArrY() {
        return arrY;
    }
}
