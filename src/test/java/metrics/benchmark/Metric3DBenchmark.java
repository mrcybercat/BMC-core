package metrics.benchmark;

import multi.converter.algorithm.steps.convertions.ConvertImageSequenceToCuboidDataStep;
import multi.converter.algorithm.steps.file.ExtractRGBDataFromImageSequenceStep;
import multi.converter.algorithm.steps.file.ReadImageSequenceFromFileStep;
import multi.converter.data.CuboidData;
import multi.converter.data.SourceFile;
import multi.converter.metrics.concrete.video3d.*;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class Metric3DBenchmark {
    SourceFile fileOriginal = new SourceFile("src\\main\\resources\\video.mp4");
    SourceFile fileAltered = new SourceFile("src\\main\\resources\\video.mp4");

    CuboidData dataOriginal = null;
    CuboidData dataAltered = null;

    @Setup(Level.Trial)
    public void setup() {
        dataOriginal = prepareData(fileOriginal);
        dataAltered = prepareData(fileAltered);
    }

    @Benchmark
    public void benchmarkGMSD() {
        GMSD3DMetric metric = new GMSD3DMetric();
        metric.calculateMetric(dataOriginal, dataAltered);
    }
    @Benchmark
    public void benchmarkSSIM() {
        SSIM3DMetric metric = new SSIM3DMetric();
        metric.calculateMetric(dataOriginal, dataAltered);
    }
    @Benchmark
    public void benchmarkUIQI() {
        UIQI3DMetric metric = new UIQI3DMetric();
        metric.calculateMetric(dataOriginal, dataAltered);
    }
    @Benchmark
    public void benchmarkMSE() {
        MSE3DMetric metric = new MSE3DMetric();
        metric.calculateMetric(dataOriginal, dataAltered);
    }
    @Benchmark
    public void benchmarkPNSR() {
        PNSR3DMetric metric = new PNSR3DMetric();
        metric.calculateMetric(dataOriginal, dataAltered);
    }

    private CuboidData prepareData(SourceFile file){
        ReadImageSequenceFromFileStep read = new ReadImageSequenceFromFileStep();
        ExtractRGBDataFromImageSequenceStep extractRGB = new ExtractRGBDataFromImageSequenceStep();
        ConvertImageSequenceToCuboidDataStep convert = new ConvertImageSequenceToCuboidDataStep();
        return convert.performAlgorithmStep(
                    extractRGB.performAlgorithmStep(
                        read.performAlgorithmStep(file)));
    }
}
