package multi.converter.algorithm.directors;

import multi.converter.Options;
import multi.converter.algorithm.Algorithm;

/**
 * {@code AlgorithmDirector} defines a contract for creating an algorithm
 * with a specified structure and steps.
 */
public interface AlgorithmDirector {

    /**
     * Defines the algorithm using the provided builder and options.
     *
     * @param builder the builder to construct the algorithm
     * @param options the options that may contact data relevant to the
     *                algorithm execution or alter the algorithm structure
     * @return the constructed Algorithm
     */
    Algorithm defineAlgorithm(Algorithm.AlgorithmBuilder builder, Options options);
}
