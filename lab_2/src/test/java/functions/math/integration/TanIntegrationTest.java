package functions.math.integration;

import functions.math.trigonometric.Cos;
import functions.math.trigonometric.Sin;
import functions.math.trigonometric.Tan;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TanIntegrationTest {
    private static final double EPS = 1e-9;

    private final Sin sin = new Sin();
    private final Cos cos = new Cos(sin);
    private final Tan tan = new Tan(sin, cos);

    @ParameterizedTest(name = "tan integration ({0}) = {1}")
    @CsvFileSource(resources = "/integration/tan.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateTanWithSinAndCos(double x, double expected) {
        assertEquals(expected, tan.calc(x), EPS);
    }
}