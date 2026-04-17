package functions.math.integration;

import functions.math.FunctionSystem;
import functions.math.logarithmic.Ln;
import functions.math.logarithmic.Log10;
import functions.math.logarithmic.Log2;
import functions.math.logarithmic.Log3;
import functions.math.logarithmic.Log5;
import functions.math.trigonometric.Cos;
import functions.math.trigonometric.Csc;
import functions.math.trigonometric.Sec;
import functions.math.trigonometric.Sin;
import functions.math.trigonometric.Tan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FunctionSystemIntegrationTest {
    private static final double EPS = 1e-9;

    @Test
    void shouldCalculateNegativeBranchUsingMocks() {
        Sin sin = mock(Sin.class);
        Cos cos = mock(Cos.class);
        Tan tan = mock(Tan.class);
        Csc csc = mock(Csc.class);
        Sec sec = mock(Sec.class);

        Ln ln = mock(Ln.class);
        Log2 log2 = mock(Log2.class);
        Log3 log3 = mock(Log3.class);
        Log5 log5 = mock(Log5.class);
        Log10 log10 = mock(Log10.class);

        when(sin.calc(-1.0)).thenReturn(1.0);
        when(cos.calc(-1.0)).thenReturn(2.0);
        when(tan.calc(-1.0)).thenReturn(3.0);
        when(csc.calc(-1.0)).thenReturn(1.0);
        when(sec.calc(-1.0)).thenReturn(5.0);

        FunctionSystem system = new FunctionSystem(
                sin, cos, tan, csc, sec,
                ln, log2, log3, log5, log10
        );

        double expected = (((2.0 * 2.0) - 1.0) * ((3.0 - 1.0) * (3.0 - 1.0))) + 5.0;
        assertEquals(expected, system.calc(-1.0), EPS);
    }

    @Test
    void shouldCalculatePositiveBranchUsingMocks() {
        Sin sin = mock(Sin.class);
        Cos cos = mock(Cos.class);
        Tan tan = mock(Tan.class);
        Csc csc = mock(Csc.class);
        Sec sec = mock(Sec.class);

        Ln ln = mock(Ln.class);
        Log2 log2 = mock(Log2.class);
        Log3 log3 = mock(Log3.class);
        Log5 log5 = mock(Log5.class);
        Log10 log10 = mock(Log10.class);

        when(ln.calc(2.0)).thenReturn(2.0);
        when(log2.calc(2.0)).thenReturn(3.0);
        when(log3.calc(2.0)).thenReturn(4.0);
        when(log5.calc(2.0)).thenReturn(5.0);
        when(log10.calc(2.0)).thenReturn(20.0);

        FunctionSystem system = new FunctionSystem(
                sin, cos, tan, csc, sec,
                ln, log2, log3, log5, log10
        );

        double left = (((5.0 * 20.0) * 4.0) + 20.0) * ((5.0 * 5.0) * 2.0);
        double denominator = 20.0 - 2.0 * 3.0;
        double right = (3.0 + 2.0 + 4.0) / denominator;
        double expected = left * right;

        assertEquals(expected, system.calc(2.0), EPS);
    }

    @Test
    void shouldReturnNaNWhenTrigonometricValuesAreInvalid() {
        Sin sin = mock(Sin.class);
        Cos cos = mock(Cos.class);
        Tan tan = mock(Tan.class);
        Csc csc = mock(Csc.class);
        Sec sec = mock(Sec.class);

        Ln ln = mock(Ln.class);
        Log2 log2 = mock(Log2.class);
        Log3 log3 = mock(Log3.class);
        Log5 log5 = mock(Log5.class);
        Log10 log10 = mock(Log10.class);

        when(sin.calc(-1.0)).thenReturn(Double.NaN);
        when(cos.calc(-1.0)).thenReturn(1.0);
        when(tan.calc(-1.0)).thenReturn(1.0);
        when(csc.calc(-1.0)).thenReturn(1.0);
        when(sec.calc(-1.0)).thenReturn(1.0);

        FunctionSystem system = new FunctionSystem(
                sin, cos, tan, csc, sec,
                ln, log2, log3, log5, log10
        );

        assertTrue(Double.isNaN(system.calc(-1.0)));
    }

    @Test
    void shouldReturnNaNWhenLogarithmicValuesAreInvalid() {
        Sin sin = mock(Sin.class);
        Cos cos = mock(Cos.class);
        Tan tan = mock(Tan.class);
        Csc csc = mock(Csc.class);
        Sec sec = mock(Sec.class);

        Ln ln = mock(Ln.class);
        Log2 log2 = mock(Log2.class);
        Log3 log3 = mock(Log3.class);
        Log5 log5 = mock(Log5.class);
        Log10 log10 = mock(Log10.class);

        when(ln.calc(2.0)).thenReturn(1.0);
        when(log2.calc(2.0)).thenReturn(Double.NaN);
        when(log3.calc(2.0)).thenReturn(1.0);
        when(log5.calc(2.0)).thenReturn(1.0);
        when(log10.calc(2.0)).thenReturn(1.0);

        FunctionSystem system = new FunctionSystem(
                sin, cos, tan, csc, sec,
                ln, log2, log3, log5, log10
        );

        assertTrue(Double.isNaN(system.calc(2.0)));
    }

    @Test
    void shouldReturnNaNWhenDenominatorIsTooSmall() {
        Sin sin = mock(Sin.class);
        Cos cos = mock(Cos.class);
        Tan tan = mock(Tan.class);
        Csc csc = mock(Csc.class);
        Sec sec = mock(Sec.class);

        Ln ln = mock(Ln.class);
        Log2 log2 = mock(Log2.class);
        Log3 log3 = mock(Log3.class);
        Log5 log5 = mock(Log5.class);
        Log10 log10 = mock(Log10.class);

        when(ln.calc(2.0)).thenReturn(1.0);
        when(log2.calc(2.0)).thenReturn(3.0);
        when(log3.calc(2.0)).thenReturn(1.0);
        when(log5.calc(2.0)).thenReturn(1.0);
        when(log10.calc(2.0)).thenReturn(6.0); // denominator = 6 - 2*3 = 0

        FunctionSystem system = new FunctionSystem(
                sin, cos, tan, csc, sec,
                ln, log2, log3, log5, log10
        );

        assertTrue(Double.isNaN(system.calc(2.0)));
    }

    @Test
    void shouldReturnNaNForNaNArgument() {
        FunctionSystem system = new FunctionSystem();
        assertTrue(Double.isNaN(system.calc(Double.NaN)));
    }

    @Test
    void shouldReturnNaNForPositiveInfinityArgument() {
        FunctionSystem system = new FunctionSystem();
        assertTrue(Double.isNaN(system.calc(Double.POSITIVE_INFINITY)));
    }

    @Test
    void shouldReturnNaNForNegativeInfinityArgument() {
        FunctionSystem system = new FunctionSystem();
        assertTrue(Double.isNaN(system.calc(Double.NEGATIVE_INFINITY)));
    }
}