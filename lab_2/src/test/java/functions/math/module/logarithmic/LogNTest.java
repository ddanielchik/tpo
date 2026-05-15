package functions.math.module.logarithmic;

import functions.math.logarithmic.Ln;
import functions.math.logarithmic.LogN;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogNTest {

    @Test
    void shouldThrowWhenLnIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new LogN(null, 2.0));
    }

    @Test
    void shouldThrowForZeroBase() {
        assertThrows(IllegalArgumentException.class, () -> new LogN(new Ln(), 0.0));
    }

    @Test
    void shouldThrowForBaseOne() {
        assertThrows(IllegalArgumentException.class, () -> new LogN(new Ln(), 1.0));
    }

    @Test
    void shouldThrowForNegativeBase() {
        assertThrows(IllegalArgumentException.class, () -> new LogN(new Ln(), -2.0));
    }

    @Test
    void shouldThrowForNaNBase() {
        assertThrows(IllegalArgumentException.class, () -> new LogN(new Ln(), Double.NaN));
    }

    @Test
    void shouldThrowForInfiniteBase() {
        assertThrows(IllegalArgumentException.class, () -> new LogN(new Ln(), Double.POSITIVE_INFINITY));
    }

    @Test
    void shouldReturnNaNForZeroX() {
        LogN logN = new LogN(new Ln(), 2.0);
        assertTrue(Double.isNaN(logN.calc(0.0)));
    }

    @Test
    void shouldReturnNaNForNegativeX() {
        LogN logN = new LogN(new Ln(), 2.0);
        assertTrue(Double.isNaN(logN.calc(-1.0)));
    }

    @Test
    void shouldReturnNaNForNaNX() {
        LogN logN = new LogN(new Ln(), 2.0);
        assertTrue(Double.isNaN(logN.calc(Double.NaN)));
    }

    @Test
    void shouldReturnNaNForInfiniteX() {
        LogN logN = new LogN(new Ln(), 2.0);
        assertTrue(Double.isNaN(logN.calc(Double.POSITIVE_INFINITY)));
    }

    @Test
    void shouldReturnNaNWhenLnReturnsNaNForArgument() {
        Ln badLn = new Ln() {
            @Override
            public double calc(double x) {
                if (x == 2.0) {
                    return 1.0;
                }
                if (x == 5.0) {
                    return Double.NaN;
                }
                return super.calc(x);
            }
        };

        LogN logN = new LogN(badLn, 2.0);
        assertTrue(Double.isNaN(logN.calc(5.0)));
    }

    @Test
    void shouldThrowWhenLnBaseIsNaN() {
        Ln badLn = new Ln() {
            @Override
            public double calc(double x) {
                return Double.NaN;
            }
        };

        assertThrows(IllegalArgumentException.class, () -> new LogN(badLn, 2.0));
    }

    @Test
    void shouldThrowWhenLnBaseIsTooSmall() {
        Ln badLn = new Ln() {
            @Override
            public double calc(double x) {
                return 1e-20;
            }
        };

        assertThrows(IllegalArgumentException.class, () -> new LogN(badLn, 2.0));
    }
}