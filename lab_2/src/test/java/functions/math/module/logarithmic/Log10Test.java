package functions.math.module.logarithmic;

import functions.math.logarithmic.Ln;
import functions.math.logarithmic.Log10;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Log10Test {
    private static final double EPS = 1e-9;

    private final Log10 log10 = new Log10(new Ln());

    @ParameterizedTest(name = "log10({0}) = {1}")
    @CsvFileSource(resources = "/module/logarithmic/log10.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateLog10(double x, double expected) {
        assertEquals(expected, log10.calc(x), EPS);
    }

    @Test
    void shouldReturnNaNForZero() {
        assertTrue(Double.isNaN(log10.calc(0.0)));
    }

    @Test
    void shouldReturnNaNForNegativeValue() {
        assertTrue(Double.isNaN(log10.calc(-1.0)));
    }

    @Test
    void shouldReturnNaNForNaN() {
        assertTrue(Double.isNaN(log10.calc(Double.NaN)));
    }

    @Test
    void shouldReturnNaNForPositiveInfinity() {
        assertTrue(Double.isNaN(log10.calc(Double.POSITIVE_INFINITY)));
    }
}