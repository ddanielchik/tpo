package functions.math.integration;

import functions.math.trigonometric.Csc;
import functions.math.trigonometric.Sin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CscIntegrationTest {
    private static final double EPS = 1e-9;

    @Test
    void shouldCalculateCscUsingSinMock() {
        Sin sin = mock(Sin.class);
        when(sin.calc(1.0)).thenReturn(0.5);

        Csc csc = new Csc(sin);

        assertEquals(2.0, csc.calc(1.0), EPS);
        verify(sin).calc(1.0);
    }

    @Test
    void shouldReturnNaNWhenSinReturnsNaN() {
        Sin sin = mock(Sin.class);
        when(sin.calc(1.0)).thenReturn(Double.NaN);

        Csc csc = new Csc(sin);

        assertTrue(Double.isNaN(csc.calc(1.0)));
    }

    @Test
    void shouldReturnNaNWhenSinIsTooSmall() {
        Sin sin = mock(Sin.class);
        when(sin.calc(1.0)).thenReturn(1e-12);

        Csc csc = new Csc(sin);

        assertTrue(Double.isNaN(csc.calc(1.0)));
    }
}