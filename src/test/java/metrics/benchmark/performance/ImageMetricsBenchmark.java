package metrics.benchmark.performance;

import multi.converter.algorithm.steps.file.ExtractRGBFromImageStep;
import multi.converter.algorithm.steps.file.ReadImageFromAFileStep;
import multi.converter.data.SourceFile;
import multi.converter.data.color.RGBData;
import multi.converter.metrics.concrete.image.*;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, warmups = 1)
@Warmup(iterations = 2)
@Measurement(iterations = 3)
public class ImageMetricsBenchmark {
    SourceFile fileOriginal = new SourceFile("src\\main\\resources\\img.png");
    SourceFile fileAltered = new SourceFile("src\\main\\resources\\img.png");

    RGBData dataOriginal = null;
    RGBData dataAltered = null;

    @Setup(Level.Trial)
    public void setup() {
        dataOriginal = prepareData(fileOriginal);
        dataAltered = prepareData(fileAltered);
    }

    @Benchmark
    public void benchmarkGMSD() {
        GMSDMetric metric = new GMSDMetric();
        metric.calculateMetric(dataOriginal, dataAltered);
    }
    @Benchmark
    public void benchmarkSSIM() {
        SSIMMetric metric = new SSIMMetric();
        metric.calculateMetric(dataOriginal, dataAltered);
    }
    @Benchmark
    public void benchmarkUIQI() {
        UIQIMetric metric = new UIQIMetric();
        metric.calculateMetric(dataOriginal, dataAltered);
    }
    @Benchmark
    public void benchmarkMSE() {
        MSEMetric metric = new MSEMetric();
        metric.calculateMetric(dataOriginal, dataAltered);
    }
    @Benchmark
    public void benchmarkPNSR() {
        PNSRMetric metric = new PNSRMetric();
        metric.calculateMetric(dataOriginal, dataAltered);
    }

    private RGBData prepareData(SourceFile file){
        ReadImageFromAFileStep read = new ReadImageFromAFileStep();
        ExtractRGBFromImageStep extractRGB = new ExtractRGBFromImageStep();
        return extractRGB.performAlgorithmStep(
                read.performAlgorithmStep(file));
    }
}
