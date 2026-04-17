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
}