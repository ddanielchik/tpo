package org.example.task3.tests;

import org.example.task3.domain.Engine;
import org.junit.jupiter.api.Test;

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
    void shouldThrowIfNotRunning() {
        Engine engine = new Engine();

        assertThrows(IllegalStateException.class, engine::makeRoar);
    }
}