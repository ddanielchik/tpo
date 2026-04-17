package functions.math.module.logarithmic;

import functions.math.logarithmic.Ln;
import functions.math.logarithmic.Log3;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Log3Test {
    private static final double EPS = 1e-9;

    private final Log3 log3 = new Log3(new Ln());

    @ParameterizedTest(name = "log3({0}) = {1}")
    @CsvFileSource(resources = "/module/logarithmic/log3.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateLog3(double x, double expected) {
        assertEquals(expected, log3.calc(x), EPS);
    }

    @Test
    void shouldReturnNaNForZero() {
        assertTrue(Double.isNaN(log3.calc(0.0)));
    }

    @Test
    void shouldReturnNaNForNegativeValue() {
        assertTrue(Double.isNaN(log3.calc(-1.0)));
    }

    @Test
    void shouldReturnNaNForNaN() {
        assertTrue(Double.isNaN(log3.calc(Double.NaN)));
    }

    @Test
    void shouldReturnNaNForPositiveInfinity() {
        assertTrue(Double.isNaN(log3.calc(Double.POSITIVE_INFINITY)));
    }
}