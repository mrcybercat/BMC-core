package multi.converter.data;

import java.io.File;

public class SourceFile extends File implements DataClass {
    public SourceFile(String pathname) {
        super(pathname);
    }
}
