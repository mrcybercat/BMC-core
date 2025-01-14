package multi.converter.algorithm.steps;

import multi.converter.data.DataClass;

import java.awt.image.ColorModel;

/**
 * Represents an abstract step in an algorithm. Each step transforms a source data object
 * into a target data object.
 *
 * @param <T> the type of the target data class
 * @param <S> the type of the source data class
 *
 * @see multi.converter.algorithm.Algorithm
 * @see ColorModel
 */
public abstract class AlgorithmStep<T extends DataClass, S extends DataClass> {

    /**
     * Performs the operation defined by the algorithm step on the given source data.
     *
     * @param source the source data object
     * @return the result of the step as an object of type T
     * @throws UnableToPerformStepException if the step fails to execute
     */
    public abstract T performAlgorithmStep(S source) throws UnableToPerformStepException;
}
