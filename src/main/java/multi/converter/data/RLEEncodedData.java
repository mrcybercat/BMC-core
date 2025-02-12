package multi.converter.data;

public class RLEEncodedData implements DataClass{
    String code;

    public RLEEncodedData(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
