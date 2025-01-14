package multi.converter.algorithm;


import multi.converter.data.DataClass;
import multi.converter.algorithm.steps.AlgorithmStep;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Algorithm} class describes an algorithm
 * composed of a series of algorithm steps.
 *
 * @param <T> the type of the final result of the algorithm
 * @param <S> the type of the input data to the algorithm
 *
 *  @see AlgorithmStep
 */

public class Algorithm<T extends DataClass, S extends DataClass> {

    private List<AlgorithmStep> steps;

    /**
     * Constructs an {@code Algorithm} with the specified builder.
     *
     * @param builder the builder containing the steps of the algorithm
     */
    public Algorithm(AlgorithmBuilder builder) {
        this.steps = builder.steps;
    }

    /**
     * Executes the algorithm on the given source data, applying all steps in sequence.
     *
     * @param source the initial input data for the algorithm
     * @return the final result after all steps have been applied
     */
    public T executeAlgorithm(S source){
        DataClass data = source;
        for(AlgorithmStep step : steps){
            data = step.performAlgorithmStep(data);
        }
        return (T) data;
    }

    /**
     * The {@code AlgorithmBuilder} is a builder class
     * for creating an Algorithm with a sequence of steps.
     */
    public static class AlgorithmBuilder{
        private List<AlgorithmStep> steps;

        private AlgorithmBuilder() {
            this.steps = new ArrayList<>();
        }

        /**
         * Creates a new instance of the AlgorithmBuilder.
         *
         * @return a new AlgorithmBuilder instance
         */
        public static AlgorithmBuilder newInstance() {
            return new AlgorithmBuilder();
        }

        /**
         * Adds a single step to the algorithm.
         *
         * @param step the algorithm step to add
         * @return the builder instance
         */
        public AlgorithmBuilder addStep(AlgorithmStep step) {
            this.steps.add(step);
            return this;
        }

        /**
         * Adds multiple steps to the algorithm.
         *
         * @param steps the algorithm steps to add
         * @return the builder instance
         */
        public AlgorithmBuilder addSteps(AlgorithmStep... steps) {
            this.steps.addAll(List.of(steps));
            return this;
        }

        /**
         * Builds and returns the Algorithm instance.
         *
         * @return the constructed Algorithm
         */
        public Algorithm getAlgorithm() {
            return new Algorithm(this);
        }
    }
}
