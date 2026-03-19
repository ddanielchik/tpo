package task3;

import org.example.task3.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;


public class FullScenarioTest {

    @Test
    void fullScenarioTest() {
        Engine engine = new Engine();
        engine.start();
        engine.makeWhistle();
        engine.makeRoar();

        SpaceCharacter ford = new SpaceCharacter("Ford");
        SpaceCharacter arthur = new SpaceCharacter("Arthur");

        SpaceEnvironment env = new SpaceEnvironment(true);
        env.addLightPoint(new LightPoint(100, true));

        EjectionEvent event = new EjectionEvent(List.of(ford, arthur), env);
        event.execute();

        Assertions.assertTrue(ford.isInOpenSpace());
        Assertions.assertTrue(arthur.isInOpenSpace());
    }
}
