package functions.math.integration;

import functions.math.FunctionSystem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class FunctionSystemIntegrationTest {
    private static final double EPS = 1e-9;

    private final FunctionSystem system = new FunctionSystem();

    @ParameterizedTest(name = "system integration ({0}) = {1}")
    @CsvFileSource(resources = "/integration/function_system.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateFunctionSystem(double x, double expected) {
        assertEquals(expected, system.calc(x), EPS);
    }

    @Test
    void shouldReturnNaNForNaN() {
        assertTrue(Double.isNaN(system.calc(Double.NaN)));
    }

    @Test
    void shouldReturnNaNForPositiveInfinity() {
        assertTrue(Double.isNaN(system.calc(Double.POSITIVE_INFINITY)));
    }

    @Test
    void shouldReturnNaNForNegativeInfinity() {
        assertTrue(Double.isNaN(system.calc(Double.NEGATIVE_INFINITY)));
    }

    @Test
    void shouldReturnNaNAtZero() {
        assertTrue(Double.isNaN(system.calc(0.0)));
    }

    @Test
    void shouldReturnNaNAtOne() {
        assertTrue(Double.isNaN(system.calc(1.0)));
    }

    @Test
    void shouldCalculateNegativeBranch() {
        assertFalse(Double.isNaN(system.calc(-1.0)));
    }

    @Test
    void shouldCalculatePositiveBranch() {
        assertFalse(Double.isNaN(system.calc(2.0)));
    }
}