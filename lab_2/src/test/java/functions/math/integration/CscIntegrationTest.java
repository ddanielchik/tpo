package functions.math.integration;

import functions.math.trigonometric.Csc;
import functions.math.trigonometric.Sin;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CscIntegrationTest {
    private static final double EPS = 1e-9;

    private final Csc csc = new Csc(new Sin());

    @ParameterizedTest(name = "csc integration ({0}) = {1}")
    @CsvFileSource(resources = "/integration/csc.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateCscWithSin(double x, double expected) {
        assertEquals(expected, csc.calc(x), EPS);
    }
}