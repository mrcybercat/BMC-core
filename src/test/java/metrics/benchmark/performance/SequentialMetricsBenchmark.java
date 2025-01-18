package metrics.benchmark.performance;

import multi.converter.algorithm.steps.file.ExtractRGBDataFromImageSequenceStep;
import multi.converter.algorithm.steps.file.ReadImageSequenceFromFileStep;
import multi.converter.data.RGBSequence;
import multi.converter.data.SourceFile;
import multi.converter.metrics.concrete.videoSeq.*;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, warmups = 1)
@Warmup(iterations = 2)
@Measurement(iterations = 3)
public class SequentialMetricsBenchmark {
    SourceFile fileOriginal = new SourceFile("src\\main\\resources\\video.mp4");
    SourceFile fileAltered = new SourceFile("src\\main\\resources\\video.mp4");

    RGBSequence dataOriginal = null;
    RGBSequence dataAltered = null;

    @Setup(Level.Trial)
    public void setup() {
        dataOriginal = prepareData(fileOriginal);
        dataAltered = prepareData(fileAltered);
    }

    @Benchmark
    public void benchmarkGMSD() {
        GMSDSequentialMetric metric = new GMSDSequentialMetric();
        metric.calculateMetric(dataOriginal, dataAltered);
    }
    @Benchmark
    public void benchmarkSSIM() {
        SSIMSequentialMetric metric = new SSIMSequentialMetric();
        metric.calculateMetric(dataOriginal, dataAltered);
    }
    @Benchmark
    public void benchmarkUIQI() {
        UIQISequentialMetric metric = new UIQISequentialMetric();
        metric.calculateMetric(dataOriginal, dataAltered);
    }
    @Benchmark
    public void benchmarkMSE() {
        MSESequentialMetric metric = new MSESequentialMetric();
        metric.calculateMetric(dataOriginal, dataAltered);
    }
    @Benchmark
    public void benchmarkPNSR() {
        PNSRSequentialMetric metric = new PNSRSequentialMetric();
        metric.calculateMetric(dataOriginal, dataAltered);
    }

    private RGBSequence prepareData(SourceFile file){
        ReadImageSequenceFromFileStep read = new ReadImageSequenceFromFileStep();
        ExtractRGBDataFromImageSequenceStep extractRGB = new ExtractRGBDataFromImageSequenceStep();
        return extractRGB.performAlgorithmStep(
                        read.performAlgorithmStep(file));
    }
}
