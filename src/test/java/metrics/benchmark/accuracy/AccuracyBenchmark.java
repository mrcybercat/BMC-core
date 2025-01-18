package metrics.benchmark.accuracy;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AccuracyBenchmark implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        extensionContext.getTestMethod().ifPresent(m -> System.out.print(
                extensionContext.getDisplayName() + "\t:"));
    }
}
