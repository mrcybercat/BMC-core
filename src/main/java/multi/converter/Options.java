package multi.converter;

import multi.converter.algorithm.AlgorithmType;

import java.util.Objects;

public record Options(
        String sourcePath,
        String outputPath,
        Integer UVDownScaleFactor,
        AlgorithmType type,
        Boolean enableMetrics,
        MetaData metadata) {

    public Options {
        Objects.requireNonNull(sourcePath);
        Objects.requireNonNull(outputPath);
        Objects.requireNonNull(UVDownScaleFactor);
        Objects.requireNonNull(type);
        Objects.requireNonNull(enableMetrics);
        Objects.requireNonNull(metadata);
    }

    public static Options extractOptions(String[] args) {
        return new Options(
                "src\\main\\resources\\small.png",
                "src\\main\\resources\\yuv.jpeg",
                1,
                AlgorithmType.STANDARD_JPEG,
                false,
                new MetaData()
        );
    }

}
