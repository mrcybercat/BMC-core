package multi.converter.algorithm.steps.convertions;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.color.HVSData;
import multi.converter.data.color.RGBData;

public class ConvertRGBtoHVSStep extends AlgorithmStep<HVSData, RGBData> {
    @Override
    public HVSData performAlgorithmStep(RGBData source) throws UnableToPerformStepException {
        double[][] hue          = calculateHue(source);
        double[][] value        = calculateValue(source);
        double[][] saturation   = calculateSaturation(source);

        return HVSData.fromArrays(hue, value, saturation);
    }

    private double[][] calculateHue(RGBData source){
        double[][] hue = new double[source.getHeight()][source.getWidth()];
        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                hue[y][x] =
                        Math.acos((0.5 * (source.getRed()[y][x] - source.getGreen()[y][x] -  source.getBlue()[y][x]))
                                / Math.sqrt(Math.pow(source.getRed()[y][x] - source.getGreen()[y][x] , 2)
                                - (source.getRed()[y][x] - source.getBlue()[y][x]) * (source.getGreen()[y][x] - source.getBlue()[y][x])));
            }
        }
        return hue;
    }

    private double[][] calculateValue(RGBData source){
        double[][] value  = new double[source.getHeight()][source.getWidth()];
        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                value[y][x] = Math.max(source.getRed()[y][x], Math.max(source.getGreen()[y][x], source.getBlue()[y][x]));
            }
        }
        return value;
    }

    private double[][] calculateSaturation(RGBData source){
        double[][] saturation  = new double[source.getHeight()][source.getWidth()];
        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                double max =  Math.max(source.getRed()[y][x], Math.max(source.getGreen()[y][x], source.getBlue()[y][x]));
                double min =  Math.min(source.getRed()[y][x], Math.min(source.getGreen()[y][x], source.getBlue()[y][x]));

                saturation[y][x] = (max - min)/ max;
            }
        }
        return saturation;
    }
}
