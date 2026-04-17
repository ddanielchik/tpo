package functions.math.integration;

import functions.math.trigonometric.Cos;
import functions.math.trigonometric.Sin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CosIntegrationTest {
    private static final double EPS = 1e-9;

    @Test
    void shouldCalculateCosUsingSinMock() {
        Sin sin = mock(Sin.class);

        when(sin.calc(Math.PI / 2 - 1.0)).thenReturn(0.5403023058681398);

        Cos cos = new Cos(sin);

        assertEquals(0.5403023058681398, cos.calc(1.0), EPS);
        verify(sin).calc(Math.PI / 2 - 1.0);
    }
}