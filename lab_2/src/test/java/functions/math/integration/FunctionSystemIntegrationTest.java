package functions.math.integration;

import functions.math.FunctionSystem;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionSystemIntegrationTest {
    private static final double EPS = 1e-9;

    private final FunctionSystem system = new FunctionSystem();

    @ParameterizedTest(name = "system integration ({0}) = {1}")
    @CsvFileSource(resources = "/integration/function_system.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateFunctionSystem(double x, double expected) {
        assertEquals(expected, system.calc(x), EPS);
    }
}