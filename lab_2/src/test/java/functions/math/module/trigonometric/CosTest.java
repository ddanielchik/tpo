package functions.math.module.trigonometric;

import functions.math.trigonometric.Cos;
import functions.math.trigonometric.Sin;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CosTest {
    private static final double EPS = 1e-9;

    private final Cos cos = new Cos(new Sin());

    @ParameterizedTest(name = "cos({0}) = {1}")
    @CsvFileSource(resources = "/module/trigonometric/cos.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateCos(double x, double expected) {
        assertEquals(expected, cos.calc(x), EPS);
    }
}