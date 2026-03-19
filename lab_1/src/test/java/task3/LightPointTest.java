package task3;

import org.example.task3.domain.LightPoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LightPointTest {

    @Test
    void shouldCreateLightPoint() {
        LightPoint point = new LightPoint(100, true);

        assertEquals(100, point.getBrightness());
        assertTrue(point.isGlowing());
    }

    @Test
    void shouldThrowIfBrightnessNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new LightPoint(-1, true));
    }
}