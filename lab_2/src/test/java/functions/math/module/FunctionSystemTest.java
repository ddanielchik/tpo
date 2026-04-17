package functions.math.module;

import functions.math.FunctionSystem;
import functions.math.logarithmic.*;
import functions.math.trigonometric.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class FunctionSystemTest {
    private static final double EPS = 1e-9;

    private final FunctionSystem system = new FunctionSystem();

    @ParameterizedTest(name = "system({0}) = {1}")
    @CsvFileSource(resources = "/integration/function_system.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateFunctionSystem(double x, double expected) {
        assertEquals(expected, system.calc(x), EPS);
    }

    @Test
    void shouldReturnNaNForNaN() {
        assertTrue(Double.isNaN(system.calc(Double.NaN)));
    }

    @Test
    void shouldReturnNaNForInfinity() {
        assertTrue(Double.isNaN(system.calc(Double.POSITIVE_INFINITY)));
        assertTrue(Double.isNaN(system.calc(Double.NEGATIVE_INFINITY)));
    }

    @Test
    void shouldReturnNaNAtZero() {
        assertTrue(Double.isNaN(system.calc(0.0)));
    }

    @Test
    void shouldReturnNaNAtOne() {
        assertTrue(Double.isNaN(system.calc(1.0)));
    }

    @Test
    void shouldReturnNaNWhenTrigonometricValuesAreInvalid() {
        Sin badSin = new Sin() {
            @Override
            public double calc(double x) {
                return Double.NaN;
            }
        };

        Sin normalSin = new Sin();
        Cos cos = new Cos(normalSin);
        Tan tan = new Tan(normalSin, cos);
        Csc csc = new Csc(normalSin);
        Sec sec = new Sec(cos);

        Ln ln = new Ln();
        Log2 log2 = new Log2(ln);
        Log3 log3 = new Log3(ln);
        Log5 log5 = new Log5(ln);
        Log10 log10 = new Log10(ln);

        FunctionSystem system = new FunctionSystem(
                badSin, cos, tan, csc, sec,
                ln, log2, log3, log5, log10
        );

        assertTrue(Double.isNaN(system.calc(-1.0)));
    }

    @Test
    void shouldReturnNaNWhenLogarithmicValuesAreInvalid() {
        Sin sin = new Sin();
        Cos cos = new Cos(sin);
        Tan tan = new Tan(sin, cos);
        Csc csc = new Csc(sin);
        Sec sec = new Sec(cos);

        Ln ln = new Ln();
        Log2 badLog2 = new Log2(ln) {
            @Override
            public double calc(double x) {
                return Double.NaN;
            }
        };
        Log3 log3 = new Log3(ln);
        Log5 log5 = new Log5(ln);
        Log10 log10 = new Log10(ln);

        FunctionSystem system = new FunctionSystem(
                sin, cos, tan, csc, sec,
                ln, badLog2, log3, log5, log10
        );

        assertTrue(Double.isNaN(system.calc(2.0)));
    }
}