package functions.math.integration;

import functions.math.logarithmic.Ln;
import functions.math.logarithmic.LogN;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LogNIntegrationTest {
    private static final double EPS = 1e-9;

    @Test
    void shouldCalculateLogNUsingLnMock() {
        Ln ln = mock(Ln.class);

        when(ln.calc(2.0)).thenReturn(0.6931471805599453); // ln(base)
        when(ln.calc(8.0)).thenReturn(2.0794415416798357); // ln(x)

        LogN logN = new LogN(ln, 2.0);

        assertEquals(3.0, logN.calc(8.0), EPS);
        verify(ln).calc(2.0);
        verify(ln).calc(8.0);
    }

    @Test
    void shouldReturnNaNWhenLnReturnsNaNForX() {
        Ln ln = mock(Ln.class);

        when(ln.calc(2.0)).thenReturn(0.6931471805599453);
        when(ln.calc(8.0)).thenReturn(Double.NaN);

        LogN logN = new LogN(ln, 2.0);

        assertTrue(Double.isNaN(logN.calc(8.0)));
    }

    @Test
    void shouldThrowWhenLnReturnsNaNForBase() {
        Ln ln = mock(Ln.class);
        when(ln.calc(2.0)).thenReturn(Double.NaN);

        assertThrows(IllegalArgumentException.class, () -> new LogN(ln, 2.0));
    }
}