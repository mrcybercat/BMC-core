package multi.converter;

import multi.converter.algorithm.AlgorithmType;

import java.util.Objects;

public record MetaData(
        String sourcePath,
        String outputPath,
        Integer UVDownScaleFactor,
        AlgorithmType type,
        Boolean enableMetrics) {

    public MetaData {
        Objects.requireNonNull(sourcePath);
        Objects.requireNonNull(outputPath);
        Objects.requireNonNull(UVDownScaleFactor);
        Objects.requireNonNull(type);
        Objects.requireNonNull(enableMetrics);
    }
}
