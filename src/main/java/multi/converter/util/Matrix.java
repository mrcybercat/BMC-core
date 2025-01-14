package multi.converter.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Matrix<T> {
    private static final Object[][] EMPTY_ELEMENTDATA = {};

    private Object[][] values;

    public Matrix(T[][] values) {
        this.values = values;
    }

    // Nuh uh
    public Matrix() {
        this.values = EMPTY_ELEMENTDATA;
    }

    public T getValueAtXY(int x, int y) {
        if(values.length <= x || values[0].length <= y){
            return null;
        }
        return (T) values[y][x];
    }
}
