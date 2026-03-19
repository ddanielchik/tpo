package task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task_3.domain.LightPoint;
import task_3.domain.SpaceEnvironment;

import static org.junit.jupiter.api.Assertions.*;

class SpaceEnvironmentTest {

    @Test
    void shouldAddLightPoints() {
        SpaceEnvironment env = new SpaceEnvironment(true);

        env.addLightPoint(new LightPoint(100, true));
        env.addLightPoint(new LightPoint(200, true));

        Assertions.assertEquals(2, env.getLightPointsCount());
    }

    @Test
    void shouldThrowIfLightPointIsNull() {
        SpaceEnvironment env = new SpaceEnvironment(true);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> env.addLightPoint(null));
    }

    @Test
    void shouldReturnUnmodifiableList() {
        SpaceEnvironment env = new SpaceEnvironment(true);
        env.addLightPoint(new LightPoint(100, true));

        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> env.getLightPoints().add(new LightPoint(200, true)));
    }
}