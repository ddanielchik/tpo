package functions.math.integration;

import functions.math.trigonometric.Cos;
import functions.math.trigonometric.Sec;
import functions.math.trigonometric.Sin;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecIntegrationTest {
    private static final double EPS = 1e-9;

    private final Sec sec = new Sec(new Cos(new Sin()));

    @ParameterizedTest(name = "sec integration ({0}) = {1}")
    @CsvFileSource(resources = "/integration/sec.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateSecWithCos(double x, double expected) {
        assertEquals(expected, sec.calc(x), EPS);
    }
}