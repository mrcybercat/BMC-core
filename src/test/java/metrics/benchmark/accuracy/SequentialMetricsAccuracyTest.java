package metrics.benchmark.accuracy;

import multi.converter.algorithm.steps.file.ExtractRGBDataFromImageSequenceStep;
import multi.converter.algorithm.steps.file.ReadImageSequenceFromFileStep;
import multi.converter.data.RGBSequence;
import multi.converter.data.SourceFile;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.concrete.videoSeq.*;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AccuracyBenchmark.class)
public class SequentialMetricsAccuracyTest {

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


    static RGBSequence dataWhite = null;
    static RGBSequence dataBlack = null;

    static RGBSequence dataGardenOrig = null;
    static RGBSequence dataGardenCompr1X = null;
    static RGBSequence dataGardenCompr2X = null;
    static RGBSequence dataGardenCompr3X = null;

    static RGBSequence dataThunderOrig = null;
    static RGBSequence dataThunderCompr1X = null;
    static RGBSequence dataThunderCompr2X = null;
    static RGBSequence dataThunderCompr3X = null;

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
        GMSDSequentialMetric metric = new GMSDSequentialMetric();
        printResult(dataWhite, dataBlack, metric);
    }
    @Test
    public void accuracyGMSDGardenSelf() {
        GMSDSequentialMetric metric = new GMSDSequentialMetric();
        printResult(dataGardenOrig, dataGardenOrig, metric);
    }
    @Test
    public void accuracyGMSDThunderSelf() {
        GMSDSequentialMetric metric = new GMSDSequentialMetric();
        printResult(dataThunderOrig, dataThunderOrig, metric);
    }
    @Test
    public void accuracyGMSDGardenX1() {
        GMSDSequentialMetric metric = new GMSDSequentialMetric();
        printResult(dataGardenOrig, dataGardenCompr1X, metric);
    }
    @Test
    public void accuracyGMSDGardenX2() {
        GMSDSequentialMetric metric = new GMSDSequentialMetric();
        printResult(dataGardenOrig, dataGardenCompr2X, metric);
    }
    @Test
    public void accuracyGMSDGardenX3() {
        GMSDSequentialMetric metric = new GMSDSequentialMetric();
        printResult(dataGardenOrig, dataGardenCompr3X, metric);
    }
    @Test
    public void accuracyGMSDYThunderX1() {
        GMSDSequentialMetric metric = new GMSDSequentialMetric();
        printResult(dataThunderOrig, dataThunderCompr1X, metric);
    }
    @Test
    public void accuracyGMSDYThunderX2() {
        GMSDSequentialMetric metric = new GMSDSequentialMetric();
        printResult(dataThunderOrig, dataThunderCompr2X, metric);
    }
    @Test
    public void accuracyGMSDYThunderX3() {
        GMSDSequentialMetric metric = new GMSDSequentialMetric();
        printResult(dataThunderOrig, dataThunderCompr3X, metric);
    }

    @Ignore
    @Test
    public void accuracySSIMWhiteVsBlack() {
        SSIMSequentialMetric metric = new SSIMSequentialMetric();
        printResult(dataWhite, dataBlack, metric);
    }
    @Test
    public void accuracySSIMGardenSelf() {
        SSIMSequentialMetric metric = new SSIMSequentialMetric();
        printResult(dataGardenOrig, dataGardenOrig, metric);
    }
    @Test
    public void accuracySSIMThunderSelf() {
        SSIMSequentialMetric metric = new SSIMSequentialMetric();
        printResult(dataThunderOrig, dataThunderOrig, metric);
    }
    @Test
    public void accuracySSIMGardenX1() {
        SSIMSequentialMetric metric = new SSIMSequentialMetric();
        printResult(dataGardenOrig, dataGardenCompr1X, metric);
    }
    @Test
    public void accuracySSIMGardenX2() {
        SSIMSequentialMetric metric = new SSIMSequentialMetric();
        printResult(dataGardenOrig, dataGardenCompr2X, metric);
    }
    @Test
    public void accuracySSIMGardenX3() {
        SSIMSequentialMetric metric = new SSIMSequentialMetric();
        printResult(dataGardenOrig, dataGardenCompr3X, metric);
    }
    @Test
    public void accuracySSIMYThunderX1() {
        SSIMSequentialMetric metric = new SSIMSequentialMetric();
        printResult(dataThunderOrig, dataThunderCompr1X, metric);
    }
    @Test
    public void accuracySSIMYThunderX2() {
        SSIMSequentialMetric metric = new SSIMSequentialMetric();
        printResult(dataThunderOrig, dataThunderCompr2X, metric);
    }
    @Test
    public void accuracySSIMYThunderX3() {
        SSIMSequentialMetric metric = new SSIMSequentialMetric();
        printResult(dataThunderOrig, dataThunderCompr3X, metric);
    }

    @Ignore
    @Test
    public void accuracyUIQIWhiteVsBlack() {
        UIQISequentialMetric metric = new UIQISequentialMetric();
        printResult(dataWhite, dataBlack, metric);
    }
    @Test
    public void accuracyUIQIGardenSelf() {
        UIQISequentialMetric metric = new UIQISequentialMetric();
        printResult(dataGardenOrig, dataGardenOrig, metric);
    }
    @Test
    public void accuracyUIQIThunderSelf() {
        UIQISequentialMetric metric = new UIQISequentialMetric();
        printResult(dataThunderOrig, dataThunderOrig, metric);
    }
    @Test
    public void accuracyUIQIGardenX1() {
        UIQISequentialMetric metric = new UIQISequentialMetric();
        printResult(dataGardenOrig, dataGardenCompr1X, metric);
    }
    @Test
    public void accuracyUIQIGardenX2() {
        UIQISequentialMetric metric = new UIQISequentialMetric();
        printResult(dataGardenOrig, dataGardenCompr2X, metric);
    }
    @Test
    public void accuracyUIQIGardenX3() {
        UIQISequentialMetric metric = new UIQISequentialMetric();
        printResult(dataGardenOrig, dataGardenCompr3X, metric);
    }
    @Test
    public void accuracyUIQIYThunderX1() {
        UIQISequentialMetric metric = new UIQISequentialMetric();
        printResult(dataThunderOrig, dataThunderCompr1X, metric);
    }
    @Test
    public void accuracyUIQIYThunderX2() {
        UIQISequentialMetric metric = new UIQISequentialMetric();
        printResult(dataThunderOrig, dataThunderCompr2X, metric);
    }
    @Test
    public void accuracyUIQIYThunderX3() {
        UIQISequentialMetric metric = new UIQISequentialMetric();
        printResult(dataThunderOrig, dataThunderCompr3X, metric);
    }

    @Ignore
    @Test
    public void accuracyMSEWhiteVsBlack() {
        MSESequentialMetric metric = new MSESequentialMetric();
        printResult(dataWhite, dataBlack, metric);
    }
    @Test
    public void accuracyMSEGardenSelf() {
        MSESequentialMetric metric = new MSESequentialMetric();
        printResult(dataGardenOrig, dataGardenOrig, metric);
    }
    @Test
    public void accuracyMSEThunderSelf() {
        MSESequentialMetric metric = new MSESequentialMetric();
        printResult(dataThunderOrig, dataThunderOrig, metric);
    }
    @Test
    public void accuracyMSEGardenX1() {
        MSESequentialMetric metric = new MSESequentialMetric();
        printResult(dataGardenOrig, dataGardenCompr1X, metric);
    }
    @Test
    public void accuracyMSEGardenX2() {
        MSESequentialMetric metric = new MSESequentialMetric();
        printResult(dataGardenOrig, dataGardenCompr2X, metric);
    }
    @Test
    public void accuracyMSEGardenX3() {
        MSESequentialMetric metric = new MSESequentialMetric();
        printResult(dataGardenOrig, dataGardenCompr3X, metric);
    }
    @Test
    public void accuracyMSEYThunderX1() {
        MSESequentialMetric metric = new MSESequentialMetric();
        printResult(dataThunderOrig, dataGardenCompr3X, metric);
    }
    @Test
    public void accuracyMSEYThunderX2() {
        MSESequentialMetric metric = new MSESequentialMetric();
        printResult(dataThunderOrig, dataGardenCompr3X, metric);
    }
    @Test
    public void accuracyMSEYThunderX3() {
        MSESequentialMetric metric = new MSESequentialMetric();
        printResult(dataThunderOrig, dataThunderCompr3X, metric);
    }

    @Ignore
    @Test
    public void accuracyPNSRWhiteVsBlack() {
        PNSRSequentialMetric metric = new PNSRSequentialMetric();
        printResult(dataWhite, dataBlack, metric);
    }
    @Test
    public void accuracyPNSRGardenSelf() {
        PNSRSequentialMetric metric = new PNSRSequentialMetric();
        printResult(dataGardenOrig, dataGardenOrig, metric);
    }
    @Test
    public void accuracyPNSRThunderSelf() {
        PNSRSequentialMetric metric = new PNSRSequentialMetric();
        printResult(dataThunderOrig, dataThunderOrig, metric);
    }
    @Test
    public void accuracyPNSRGardenX1() {
        PNSRSequentialMetric metric = new PNSRSequentialMetric();
        printResult(dataGardenOrig, dataGardenCompr1X, metric);
    }
    @Test
    public void accuracyPNSRGardenX2() {
        PNSRSequentialMetric metric = new PNSRSequentialMetric();
        printResult(dataGardenOrig, dataGardenCompr2X, metric);
    }
    @Test
    public void accuracyPNSRGardenX3() {
        PNSRSequentialMetric metric = new PNSRSequentialMetric();
        printResult(dataGardenOrig, dataGardenCompr3X, metric);
    }
    @Test
    public void accuracyPNSRThunderX1() {
        PNSRSequentialMetric metric = new PNSRSequentialMetric();
        printResult(dataThunderOrig, dataThunderCompr1X, metric);
    }
    @Test
    public void accuracyPNSRThunderX2() {
        PNSRSequentialMetric metric = new PNSRSequentialMetric();
        printResult(dataThunderOrig, dataThunderCompr2X, metric);
    }
    @Test
    public void accuracyPNSRThunderX3() {
        PNSRSequentialMetric metric = new PNSRSequentialMetric();
        printResult(dataThunderOrig, dataThunderCompr3X, metric);
    }

    private void printResult(RGBSequence original, RGBSequence altered, Metric<RGBSequence> metric) {
        System.out.println(metric.getType() + ":\t" +  metric.calculateMetric(original, altered));
    }

    private static RGBSequence prepareData(SourceFile file){
        ReadImageSequenceFromFileStep read = new ReadImageSequenceFromFileStep();
        ExtractRGBDataFromImageSequenceStep extractRGB = new ExtractRGBDataFromImageSequenceStep();
        return extractRGB.performAlgorithmStep(
                read.performAlgorithmStep(file));
    }
}
