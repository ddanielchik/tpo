package functions.math.integration;

import functions.math.trigonometric.Cos;
import functions.math.trigonometric.Sec;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SecIntegrationTest {
    private static final double EPS = 1e-9;

    @Test
    void shouldCalculateSecUsingCosMock() {
        Cos cos = mock(Cos.class);
        when(cos.calc(1.0)).thenReturn(0.5);

        Sec sec = new Sec(cos);

        assertEquals(2.0, sec.calc(1.0), EPS);
        verify(cos).calc(1.0);
    }

    @Test
    void shouldReturnNaNWhenCosReturnsNaN() {
        Cos cos = mock(Cos.class);
        when(cos.calc(1.0)).thenReturn(Double.NaN);

        Sec sec = new Sec(cos);

        assertTrue(Double.isNaN(sec.calc(1.0)));
    }

    @Test
    void shouldReturnNaNWhenCosIsTooSmall() {
        Cos cos = mock(Cos.class);
        when(cos.calc(1.0)).thenReturn(1e-12);

        Sec sec = new Sec(cos);

        assertTrue(Double.isNaN(sec.calc(1.0)));
    }
}