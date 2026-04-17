package functions.math.integration;

import functions.math.trigonometric.Cos;
import functions.math.trigonometric.Sin;
import functions.math.trigonometric.Tan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class TanIntegrationTest {
    private static final double EPS = 1e-9;

    @Test
    void shouldCalculateTanUsingMocks() {
        Sin sin = mock(Sin.class);
        Cos cos = mock(Cos.class);

        when(sin.calc(1.0)).thenReturn(2.0);
        when(cos.calc(1.0)).thenReturn(4.0);

        Tan tan = new Tan(sin, cos);

        assertEquals(0.5, tan.calc(1.0), EPS);
        verify(sin).calc(1.0);
        verify(cos).calc(1.0);
    }

    @Test
    void shouldReturnNaNWhenCosIsTooSmall() {
        Sin sin = mock(Sin.class);
        Cos cos = mock(Cos.class);

        when(sin.calc(1.0)).thenReturn(2.0);
        when(cos.calc(1.0)).thenReturn(1e-12);

        Tan tan = new Tan(sin, cos);

        assertTrue(Double.isNaN(tan.calc(1.0)));
    }

    @Test
    void shouldReturnNaNWhenSinReturnsNaN() {
        Sin sin = mock(Sin.class);
        Cos cos = mock(Cos.class);

        when(sin.calc(1.0)).thenReturn(Double.NaN);
        when(cos.calc(1.0)).thenReturn(2.0);

        Tan tan = new Tan(sin, cos);

        assertTrue(Double.isNaN(tan.calc(1.0)));
    }

    @Test
    void shouldReturnNaNWhenCosReturnsNaN() {
        Sin sin = mock(Sin.class);
        Cos cos = mock(Cos.class);

        when(sin.calc(1.0)).thenReturn(2.0);
        when(cos.calc(1.0)).thenReturn(Double.NaN);

        Tan tan = new Tan(sin, cos);

        assertTrue(Double.isNaN(tan.calc(1.0)));
    }
}