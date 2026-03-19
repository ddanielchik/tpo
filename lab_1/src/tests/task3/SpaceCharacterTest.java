package task3;

import org.example.task3.domain.SpaceCharacter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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