package functions.math.module.trigonometric;

import functions.math.trigonometric.Cos;
import functions.math.trigonometric.Sin;
import functions.math.trigonometric.Tan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class TanTest {
    private static final double EPS = 1e-9;

    private final Sin sin = new Sin();
    private final Cos cos = new Cos(sin);
    private final Tan tan = new Tan(sin, cos);

    @ParameterizedTest(name = "tan({0}) = {1}")
    @CsvFileSource(resources = "/module/trigonometric/tan.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateTan(double x, double expected) {
        assertEquals(expected, tan.calc(x), EPS);
    }

    @Test
    void shouldReturnNaNForNaN() {
        assertTrue(Double.isNaN(tan.calc(Double.NaN)));
    }

    @Test
    void shouldReturnNaNForPositiveInfinity() {
        assertTrue(Double.isNaN(tan.calc(Double.POSITIVE_INFINITY)));
    }

    @Test
    void shouldReturnNaNForNegativeInfinity() {
        assertTrue(Double.isNaN(tan.calc(Double.NEGATIVE_INFINITY)));
    }

    @Test
    void shouldReturnNaNWhenCosIsTooSmall() {
        assertTrue(Double.isNaN(tan.calc(Math.PI / 2)));
    }

    @Test
    void shouldReturnNaNWhenSinReturnsNaN() {
        Sin badSin = new Sin() {
            @Override
            public double calc(double x) {
                return Double.NaN;
            }
        };

        Tan badTan = new Tan(badSin, cos);
        assertTrue(Double.isNaN(badTan.calc(1.0)));
    }

    @Test
    void shouldReturnNaNWhenCosReturnsNaN() {
        Cos badCos = new Cos(new Sin()) {
            @Override
            public double calc(double x) {
                return Double.NaN;
            }
        };

        Tan badTan = new Tan(new Sin(), badCos);
        assertTrue(Double.isNaN(badTan.calc(1.0)));
    }
}