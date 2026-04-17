package functions.math.module.logarithmic;

import functions.math.logarithmic.Ln;
import functions.math.logarithmic.Log5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Log5Test {
    private static final double EPS = 1e-9;

    private final Log5 log5 = new Log5(new Ln());

    @ParameterizedTest(name = "log5({0}) = {1}")
    @CsvFileSource(resources = "/module/logarithmic/log5.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateLog5(double x, double expected) {
        assertEquals(expected, log5.calc(x), EPS);
    }

    @Test
    void shouldReturnNaNForZero() {
        assertTrue(Double.isNaN(log5.calc(0.0)));
    }

    @Test
    void shouldReturnNaNForNegativeValue() {
        assertTrue(Double.isNaN(log5.calc(-1.0)));
    }

    @Test
    void shouldReturnNaNForNaN() {
        assertTrue(Double.isNaN(log5.calc(Double.NaN)));
    }

    @Test
    void shouldReturnNaNForPositiveInfinity() {
        assertTrue(Double.isNaN(log5.calc(Double.POSITIVE_INFINITY)));
    }
}