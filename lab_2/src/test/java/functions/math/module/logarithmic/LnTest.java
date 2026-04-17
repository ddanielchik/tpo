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
}