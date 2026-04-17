package functions.math.module.trigonometric;

import functions.math.trigonometric.Cos;
import functions.math.trigonometric.Sec;
import functions.math.trigonometric.Sin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class SecTest {
    private static final double EPS = 1e-9;

    private final Sec sec = new Sec(new Cos(new Sin()));

    @ParameterizedTest(name = "sec({0}) = {1}")
    @CsvFileSource(resources = "/module/trigonometric/sec.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateSec(double x, double expected) {
        assertEquals(expected, sec.calc(x), EPS);
    }

    @Test
    void shouldReturnNaNAtUndefinedPoint() {
        assertTrue(Double.isNaN(sec.calc(Math.PI / 2)));
    }
}