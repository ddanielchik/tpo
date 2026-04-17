package functions.math.module.logarithmic;

import functions.math.logarithmic.Ln;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class LnTest {
    private static final double EPS = 1e-9;

    private final Ln ln = new Ln();

    @ParameterizedTest(name = "ln({0}) = {1}")
    @CsvFileSource(resources = "/module/logarithmic/ln.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateLn(double x, double expected) {
        assertEquals(expected, ln.calc(x), EPS);
    }

    @Test
    void shouldReturnNaNForInvalidX() {
        assertTrue(Double.isNaN(ln.calc(0.0)));
        assertTrue(Double.isNaN(ln.calc(-1.0)));
    }

    @Test
    void shouldStopBecauseOfIterationLimit() {
        Ln ln = new Ln();
        assertFalse(Double.isNaN(ln.calc(2.0, 1)));
    }

    @Test
    void shouldReturnZeroForOne() {
        assertEquals(0.0, ln.calc(1.0), 1e-9);
    }

    @Test
    void shouldReturnNaNForZero() {
        assertTrue(Double.isNaN(ln.calc(0.0)));
    }

    @Test
    void shouldReturnNaNForNegativeValue() {
        assertTrue(Double.isNaN(ln.calc(-1.0)));
    }

    @Test
    void shouldReturnNaNForNaN() {
        assertTrue(Double.isNaN(ln.calc(Double.NaN)));
    }

    @Test
    void shouldReturnNaNForPositiveInfinity() {
        assertTrue(Double.isNaN(ln.calc(Double.POSITIVE_INFINITY)));
    }

    @Test
    void shouldReturnNaNForNegativeInfinity() {
        assertTrue(Double.isNaN(ln.calc(Double.NEGATIVE_INFINITY)));
    }

    @Test
    void shouldThrowForZeroN() {
        assertThrows(IllegalArgumentException.class, () -> ln.calc(2.0, 0));
    }

    @Test
    void shouldThrowForNegativeN() {
        assertThrows(IllegalArgumentException.class, () -> ln.calc(2.0, -1));
    }

    @Test
    void shouldCalculateForValueCloseToOne() {
        assertEquals(Math.log(1.0001), ln.calc(1.0001), 1e-9);
    }
}