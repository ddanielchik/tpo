package task3;

import org.example.task3.domain.EjectionEvent;
import org.example.task3.domain.SpaceCharacter;
import org.example.task3.domain.SpaceEnvironment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EjectionEventTest {

    @Test
    void shouldEjectAllCharacters() {
        SpaceCharacter ford = new SpaceCharacter("Ford");
        SpaceCharacter arthur = new SpaceCharacter("Arthur");

        SpaceEnvironment env = new SpaceEnvironment(true);

        EjectionEvent event = new EjectionEvent(List.of(ford, arthur), env);

        event.execute();

        assertTrue(ford.isInOpenSpace());
        assertTrue(arthur.isInOpenSpace());
        assertTrue(event.isCompleted());
    }

    @Test
    void shouldThrowIfExecutedTwice() {
        SpaceCharacter ford = new SpaceCharacter("Ford");
        SpaceEnvironment env = new SpaceEnvironment(true);

        EjectionEvent event = new EjectionEvent(List.of(ford), env);

        event.execute();

        assertThrows(IllegalStateException.class, event::execute);
    }

    @Test
    void shouldThrowIfCharactersListIsEmpty() {
        SpaceEnvironment env = new SpaceEnvironment(true);

        assertThrows(IllegalArgumentException.class,
                () -> new EjectionEvent(List.of(), env));
    }

    @Test
    void shouldThrowIfCharactersListIsNull() {
        SpaceEnvironment env = new SpaceEnvironment(true);

        assertThrows(IllegalArgumentException.class,
                () -> new EjectionEvent(null, env));
    }

    @Test
    void shouldThrowIfCharacterIsNullInsideList() {
        SpaceEnvironment env = new SpaceEnvironment(true);
        List<SpaceCharacter> characters = java.util.Arrays.asList(new SpaceCharacter("Ford"), null);

        assertThrows(IllegalArgumentException.class,
                () -> new EjectionEvent(characters, env));
    }

    @Test
    void shouldThrowIfEnvironmentIsNull() {
        SpaceCharacter ford = new SpaceCharacter("Ford");

        assertThrows(NullPointerException.class,
                () -> new EjectionEvent(List.of(ford), null));
    }
}
