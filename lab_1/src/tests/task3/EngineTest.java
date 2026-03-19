package task3;

import org.junit.jupiter.api.Test;
import task_3.domain.Engine;
import task_3.domain.SoundType;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    @Test
    void shouldStartEngine() {
        Engine engine = new Engine();

        engine.start();

        assertTrue(engine.isRunning());
    }

    @Test
    void shouldStopEngine() {
        Engine engine = new Engine();

        engine.start();
        engine.stop();

        assertFalse(engine.isRunning());
    }

    @Test
    void shouldMakeWhistle() {
        Engine engine = new Engine();
        engine.start();

        engine.makeWhistle();

        assertEquals(SoundType.WHISTLE, engine.getSound().getType());
    }

    @Test
    void shouldMakeRoar() {
        Engine engine = new Engine();
        engine.start();

        engine.makeRoar();

        assertEquals(SoundType.ROAR, engine.getSound().getType());
    }

    @Test
    void shouldThrowIfNotRunning() {
        Engine engine = new Engine();

        assertThrows(IllegalStateException.class, engine::makeRoar);
    }
}