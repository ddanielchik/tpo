package functions.math.integration;

import functions.math.trigonometric.Cos;
import functions.math.trigonometric.Sin;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CosIntegrationTest {
    private static final double EPS = 1e-9;

    private final Cos cos = new Cos(new Sin());

    @ParameterizedTest(name = "cos integration ({0}) = {1}")
    @CsvFileSource(resources = "/integration/cos.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateCosWithSin(double x, double expected) {
        assertEquals(expected, cos.calc(x), EPS);
    }
}