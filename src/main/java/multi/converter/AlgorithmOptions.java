package multi.converter;

import multi.converter.algorithm.AlgorithmType;

import java.util.Objects;

public record AlgorithmOptions(
        String sourcePath,
        String outputPath,
        Integer UVDownScaleFactor,
        AlgorithmType type,
        Boolean enableMetrics) {

    public AlgorithmOptions {
        Objects.requireNonNull(sourcePath);
        Objects.requireNonNull(outputPath);
        Objects.requireNonNull(UVDownScaleFactor);
        Objects.requireNonNull(type);
        Objects.requireNonNull(enableMetrics);
    }

    public static AlgorithmOptions extractOptions(String[] args) {
        return new AlgorithmOptions(
                "src\\main\\resources\\small.png",
                "src\\main\\resources\\yuv.jpeg",
                1,
                AlgorithmType.STANDARD_JPEG,
                false
        );
    }

}
