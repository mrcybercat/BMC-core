package metrics.benchmark.accuracy;

import multi.converter.algorithm.steps.convertions.ConvertImageSequenceToCuboidDataStep;
import multi.converter.algorithm.steps.file.ExtractRGBDataFromImageSequenceStep;
import multi.converter.algorithm.steps.file.ReadImageSequenceFromFileStep;
import multi.converter.data.CuboidData;
import multi.converter.data.SourceFile;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.concrete.video3d.*;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AccuracyBenchmark.class)
public class Metrics3DAccuracyTest {
    static SourceFile fileWhite = new SourceFile("src\\main\\resources\\videos\\black2.mp4");
    static SourceFile fileBlack = new SourceFile("src\\main\\resources\\videos\\white.mp4");

    static SourceFile fileGardenOrig = new SourceFile("src\\main\\resources\\videos\\gardenOrig.mp4");
    static SourceFile fileGardenCompr1X = new SourceFile("src\\main\\resources\\videos\\gardenCompr1X.mp4");
    static SourceFile fileGardenCompr2X = new SourceFile("src\\main\\resources\\videos\\gardenCompr2X.mp4");
    static SourceFile fileGardenCompr3X = new SourceFile("src\\main\\resources\\videos\\gardenCompr3X.mp4");

    static SourceFile fileThunderOrig = new SourceFile("src\\main\\resources\\videos\\thunderOrig.mp4");
    static SourceFile fileThunderCompr1X = new SourceFile("src\\main\\resources\\videos\\thunderCompr1X.mp4");
    static SourceFile fileThunderCompr2X = new SourceFile("src\\main\\resources\\videos\\thunderCompr2X.mp4");
    static SourceFile fileThunderCompr3X = new SourceFile("src\\main\\resources\\videos\\thunderCompr3X.mp4");


    static CuboidData dataWhite = null;
    static CuboidData dataBlack = null;

    static CuboidData dataGardenOrig = null;
    static CuboidData dataGardenCompr1X = null;
    static CuboidData dataGardenCompr2X = null;
    static CuboidData dataGardenCompr3X = null;

    static CuboidData dataThunderOrig = null;
    static CuboidData dataThunderCompr1X = null;
    static CuboidData dataThunderCompr2X = null;
    static CuboidData dataThunderCompr3X = null;

    @BeforeAll
    public static void setup() {
        //dataWhite = prepareData(fileWhite);
        //dataBlack = prepareData(fileBlack);

        dataGardenOrig = prepareData(fileGardenOrig);
        dataGardenCompr1X = prepareData(fileGardenCompr1X);
        dataGardenCompr2X = prepareData(fileGardenCompr2X);
        dataGardenCompr3X = prepareData(fileGardenCompr3X);

        dataThunderOrig = prepareData(fileThunderOrig);
        dataThunderCompr1X = prepareData(fileThunderCompr1X);
        dataThunderCompr2X = prepareData(fileThunderCompr2X);
        dataThunderCompr3X = prepareData(fileThunderCompr3X);
    }

    @Ignore
    @Test
    public void accuracyGMSDWhiteVsBlack() {
        GMSD3DMetric metric = new GMSD3DMetric();
        printResult(dataWhite, dataBlack, metric);
    }
    @Test
    public void accuracyGMSDGardenSelf() {
        GMSD3DMetric metric = new GMSD3DMetric();
        printResult(dataGardenOrig, dataGardenOrig, metric);
    }
    @Test
    public void accuracyGMSDThunderSelf() {
        GMSD3DMetric metric = new GMSD3DMetric();
        printResult(dataThunderOrig, dataThunderOrig, metric);
    }
    @Test
    public void accuracyGMSDGardenX1() {
        GMSD3DMetric metric = new GMSD3DMetric();
        printResult(dataGardenOrig, dataGardenCompr1X, metric);
    }
    @Test
    public void accuracyGMSDGardenX2() {
        GMSD3DMetric metric = new GMSD3DMetric();
        printResult(dataGardenOrig, dataGardenCompr2X, metric);
    }
    @Test
    public void accuracyGMSDGardenX3() {
        GMSD3DMetric metric = new GMSD3DMetric();
        printResult(dataGardenOrig, dataGardenCompr3X, metric);
    }
    @Test
    public void accuracyGMSDYThunderX1() {
        GMSD3DMetric metric = new GMSD3DMetric();
        printResult(dataThunderOrig, dataThunderCompr1X, metric);
    }
    @Test
    public void accuracyGMSDYThunderX2() {
        GMSD3DMetric metric = new GMSD3DMetric();
        printResult(dataThunderOrig, dataThunderCompr2X, metric);
    }
    @Test
    public void accuracyGMSDYThunderX3() {
        GMSD3DMetric metric = new GMSD3DMetric();
        printResult(dataThunderOrig, dataThunderCompr3X, metric);
    }

    @Ignore
    @Test
    public void accuracySSIMWhiteVsBlack() {
        SSIM3DMetric metric = new SSIM3DMetric();
        printResult(dataWhite, dataBlack, metric);
    }
    @Test
    public void accuracySSIMGardenSelf() {
        SSIM3DMetric metric = new SSIM3DMetric();
        printResult(dataGardenOrig, dataGardenOrig, metric);
    }
    @Test
    public void accuracySSIMThunderSelf() {
        SSIM3DMetric metric = new SSIM3DMetric();
        printResult(dataThunderOrig, dataThunderOrig, metric);
    }
    @Test
    public void accuracySSIMGardenX1() {
        SSIM3DMetric metric = new SSIM3DMetric();
        printResult(dataGardenOrig, dataGardenCompr1X, metric);
    }
    @Test
    public void accuracySSIMGardenX2() {
        SSIM3DMetric metric = new SSIM3DMetric();
        printResult(dataGardenOrig, dataGardenCompr2X, metric);
    }
    @Test
    public void accuracySSIMGardenX3() {
        SSIM3DMetric metric = new SSIM3DMetric();
        printResult(dataGardenOrig, dataGardenCompr3X, metric);
    }
    @Test
    public void accuracySSIMYThunderX1() {
        SSIM3DMetric metric = new SSIM3DMetric();
        printResult(dataThunderOrig, dataThunderCompr1X, metric);
    }
    @Test
    public void accuracySSIMYThunderX2() {
        SSIM3DMetric metric = new SSIM3DMetric();
        printResult(dataThunderOrig, dataThunderCompr2X, metric);
    }
    @Test
    public void accuracySSIMYThunderX3() {
        SSIM3DMetric metric = new SSIM3DMetric();
        printResult(dataThunderOrig, dataThunderCompr3X, metric);
    }

    @Ignore
    @Test
    public void accuracyUIQIWhiteVsBlack() {
        UIQI3DMetric metric = new UIQI3DMetric();
        printResult(dataWhite, dataBlack, metric);
    }
    @Test
    public void accuracyUIQIGardenSelf() {
        UIQI3DMetric metric = new UIQI3DMetric();
        printResult(dataGardenOrig, dataGardenOrig, metric);
    }
    @Test
    public void accuracyUIQIThunderSelf() {
        UIQI3DMetric metric = new UIQI3DMetric();
        printResult(dataThunderOrig, dataThunderOrig, metric);
    }
    @Test
    public void accuracyUIQIGardenX1() {
        UIQI3DMetric metric = new UIQI3DMetric();
        printResult(dataGardenOrig, dataGardenCompr1X, metric);
    }
    @Test
    public void accuracyUIQIGardenX2() {
        UIQI3DMetric metric = new UIQI3DMetric();
        printResult(dataGardenOrig, dataGardenCompr2X, metric);
    }
    @Test
    public void accuracyUIQIGardenX3() {
        UIQI3DMetric metric = new UIQI3DMetric();
        printResult(dataGardenOrig, dataGardenCompr3X, metric);
    }
    @Test
    public void accuracyUIQIYThunderX1() {
        UIQI3DMetric metric = new UIQI3DMetric();
        printResult(dataThunderOrig, dataThunderCompr1X, metric);
    }
    @Test
    public void accuracyUIQIYThunderX2() {
        UIQI3DMetric metric = new UIQI3DMetric();
        printResult(dataThunderOrig, dataThunderCompr2X, metric);
    }
    @Test
    public void accuracyUIQIYThunderX3() {
        UIQI3DMetric metric = new UIQI3DMetric();
        printResult(dataThunderOrig, dataThunderCompr3X, metric);
    }

    @Ignore
    @Test
    public void accuracyMSEWhiteVsBlack() {
        MSE3DMetric metric = new MSE3DMetric();
        printResult(dataWhite, dataBlack, metric);
    }
    @Test
    public void accuracyMSEGardenSelf() {
        MSE3DMetric metric = new MSE3DMetric();
        printResult(dataGardenOrig, dataGardenOrig, metric);
    }
    @Test
    public void accuracyMSEThunderSelf() {
        MSE3DMetric metric = new MSE3DMetric();
        printResult(dataThunderOrig, dataThunderOrig, metric);
    }
    @Test
    public void accuracyMSEGardenX1() {
        MSE3DMetric metric = new MSE3DMetric();
        printResult(dataGardenOrig, dataGardenCompr1X, metric);
    }
    @Test
    public void accuracyMSEGardenX2() {
        MSE3DMetric metric = new MSE3DMetric();
        printResult(dataGardenOrig, dataGardenCompr2X, metric);
    }
    @Test
    public void accuracyMSEGardenX3() {
        MSE3DMetric metric = new MSE3DMetric();
        printResult(dataGardenOrig, dataGardenCompr3X, metric);
    }
    @Test
    public void accuracyMSEYThunderX1() {
        MSE3DMetric metric = new MSE3DMetric();
        printResult(dataThunderOrig, dataGardenCompr3X, metric);
    }
    @Test
    public void accuracyMSEYThunderX2() {
        MSE3DMetric metric = new MSE3DMetric();
        printResult(dataThunderOrig, dataGardenCompr3X, metric);
    }
    @Test
    public void accuracyMSEYThunderX3() {
        MSE3DMetric metric = new MSE3DMetric();
        printResult(dataThunderOrig, dataThunderCompr3X, metric);
    }

    @Ignore
    @Test
    public void accuracyPNSRWhiteVsBlack() {
        PNSR3DMetric metric = new PNSR3DMetric();
        printResult(dataWhite, dataBlack, metric);
    }
    @Test
    public void accuracyPNSRGardenSelf() {
        PNSR3DMetric metric = new PNSR3DMetric();
        printResult(dataGardenOrig, dataGardenOrig, metric);
    }
    @Test
    public void accuracyPNSRThunderSelf() {
        PNSR3DMetric metric = new PNSR3DMetric();
        printResult(dataThunderOrig, dataThunderOrig, metric);
    }
    @Test
    public void accuracyPNSRGardenX1() {
        PNSR3DMetric metric = new PNSR3DMetric();
        printResult(dataGardenOrig, dataGardenCompr1X, metric);
    }
    @Test
    public void accuracyPNSRGardenX2() {
        PNSR3DMetric metric = new PNSR3DMetric();
        printResult(dataGardenOrig, dataGardenCompr2X, metric);
    }
    @Test
    public void accuracyPNSRGardenX3() {
        PNSR3DMetric metric = new PNSR3DMetric();
        printResult(dataGardenOrig, dataGardenCompr3X, metric);
    }
    @Test
    public void accuracyPNSRThunderX1() {
        PNSR3DMetric metric = new PNSR3DMetric();
        printResult(dataThunderOrig, dataThunderCompr1X, metric);
    }
    @Test
    public void accuracyPNSRThunderX2() {
        PNSR3DMetric metric = new PNSR3DMetric();
        printResult(dataThunderOrig, dataThunderCompr2X, metric);
    }
    @Test
    public void accuracyPNSRThunderX3() {
        PNSR3DMetric metric = new PNSR3DMetric();
        printResult(dataThunderOrig, dataThunderCompr3X, metric);
    }

    private void printResult(CuboidData original, CuboidData altered, Metric<CuboidData> metric) {
        System.out.println(metric.getType() + ":\t" +  metric.calculateMetric(original, altered));
    }

    private static CuboidData prepareData(SourceFile file){
        ReadImageSequenceFromFileStep read = new ReadImageSequenceFromFileStep();
        ExtractRGBDataFromImageSequenceStep extractRGB = new ExtractRGBDataFromImageSequenceStep();
        ConvertImageSequenceToCuboidDataStep convert = new ConvertImageSequenceToCuboidDataStep();
        return convert.performAlgorithmStep(
                extractRGB.performAlgorithmStep(
                        read.performAlgorithmStep(file)));
    }
}
