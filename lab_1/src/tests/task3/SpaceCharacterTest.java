package task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task_3.domain.SpaceCharacter;

import static org.junit.jupiter.api.Assertions.*;

class SpaceCharacterTest {

    @Test
    void shouldCreateCharacter() {
        SpaceCharacter character = new SpaceCharacter("Ford");

        Assertions.assertEquals("Ford", character.getName());
        Assertions.assertFalse(character.isInOpenSpace());
    }

    @Test
    void shouldEjectCharacter() {
        SpaceCharacter character = new SpaceCharacter("Arthur");

        character.ejectToOpenSpace();

        Assertions.assertTrue(character.isInOpenSpace());
    }

    @Test
    void shouldThrowIfNameIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SpaceCharacter(""));
    }
}