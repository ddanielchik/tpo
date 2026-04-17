package functions.math.module.trigonometric;

import functions.math.trigonometric.Sin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class SinTest {
    private static final double EPS = 1e-9;

    private final Sin sin = new Sin();

    @ParameterizedTest(name = "sin({0}) = {1}")
    @CsvFileSource(resources = "/module/trigonometric/sin.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateSin(double x, double expected) {
        assertEquals(expected, sin.calc(x), EPS);
    }

    @Test
    void shouldReturnNaNForNaN() {
        assertTrue(Double.isNaN(sin.calc(Double.NaN)));
    }

    @Test
    void shouldReturnNaNForInfinity() {
        assertTrue(Double.isNaN(sin.calc(Double.POSITIVE_INFINITY)));
        assertTrue(Double.isNaN(sin.calc(Double.NEGATIVE_INFINITY)));
    }

    @Test
    void shouldThrowForInvalidN() {
        assertThrows(IllegalArgumentException.class, () -> sin.calc(1.0, 0));
    }

    @Test
    void shouldNormalizeLargePositiveArgument() {
        Sin sin = new Sin();
        assertEquals(Math.sin(10.0), sin.calc(10.0), 1e-9);
    }

    @Test
    void shouldNormalizeArgumentGreaterThanPi() {
        Sin sin = new Sin();
        assertEquals(Math.sin(4.0), sin.calc(4.0), 1e-9);
    }

    @Test
    void shouldReturnNaNForPositiveInfinity() {
        assertTrue(Double.isNaN(sin.calc(Double.POSITIVE_INFINITY)));
    }

    @Test
    void shouldReturnNaNForNegativeInfinity() {
        assertTrue(Double.isNaN(sin.calc(Double.NEGATIVE_INFINITY)));
    }

    @Test
    void shouldThrowForZeroN() {
        assertThrows(IllegalArgumentException.class, () -> sin.calc(1.0, 0));
    }

    @Test
    void shouldThrowForNegativeN() {
        assertThrows(IllegalArgumentException.class, () -> sin.calc(1.0, -5));
    }

    @Test
    void shouldReturnZeroForNegativeZero() {
        assertEquals(-0.0, sin.calc(-0.0), 1e-9);
    }

    @Test
    void shouldCalculateForNegativeArgument() {
        assertEquals(Math.sin(-4.0), sin.calc(-4.0), 1e-9);
    }

    @Test
    void shouldStopBecauseOfIterationLimit() {
        double result = sin.calc(1.0, 1);
        assertFalse(Double.isNaN(result));
    }
}