package functions.math.module.trigonometric;

import functions.math.trigonometric.Csc;
import functions.math.trigonometric.Sin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class CscTest {
    private static final double EPS = 1e-9;

    private final Csc csc = new Csc(new Sin());

    @ParameterizedTest(name = "csc({0}) = {1}")
    @CsvFileSource(resources = "/module/trigonometric/csc.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateCsc(double x, double expected) {
        assertEquals(expected, csc.calc(x), EPS);
    }

    @Test
    void shouldReturnNaNAtUndefinedPoint() {
        assertTrue(Double.isNaN(csc.calc(0.0)));
    }

    @Test
    void shouldReturnNaNForNaN() {
        assertTrue(Double.isNaN(csc.calc(Double.NaN)));
    }

    @Test
    void shouldReturnNaNForPositiveInfinity() {
        assertTrue(Double.isNaN(csc.calc(Double.POSITIVE_INFINITY)));
    }

    @Test
    void shouldReturnNaNWhenSinReturnsNaN() {
        Sin badSin = new Sin() {
            @Override
            public double calc(double x) {
                return Double.NaN;
            }
        };

        Csc csc = new Csc(badSin);
        assertTrue(Double.isNaN(csc.calc(1.0)));
    }

    @Test
    void shouldReturnNaNForNegativeInfinity() {
        assertTrue(Double.isNaN(csc.calc(Double.NEGATIVE_INFINITY)));
    }

    @Test
    void shouldReturnNaNAtZero() {
        assertTrue(Double.isNaN(csc.calc(0.0)));
    }

    @Test
    void shouldReturnNaNAtPi() {
        assertTrue(Double.isNaN(csc.calc(Math.PI)));
    }
}