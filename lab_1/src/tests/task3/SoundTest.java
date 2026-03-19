package task3;

import org.example.task3.domain.Sound;
import org.example.task3.domain.SoundType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SoundTest {

    @Test
    void shouldCreateSound() {
        Sound sound = new Sound(SoundType.BUZZ, 1.0);

        Assertions.assertEquals(SoundType.BUZZ, sound.getType());
        Assertions.assertEquals(1.0, sound.getIntensity());
    }

    @Test
    void shouldTransformSound() {
        Sound sound = new Sound(SoundType.BUZZ, 1.0);

        sound.transformTo(SoundType.ROAR);

        Assertions.assertEquals(SoundType.ROAR, sound.getType());
    }

    @Test
    void shouldIncreaseIntensity() {
        Sound sound = new Sound(SoundType.BUZZ, 1.0);

        sound.increaseIntensity(2.0);

        Assertions.assertEquals(3.0, sound.getIntensity());
    }

    @Test
    void shouldThrowIfIntensityNegative() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Sound(SoundType.BUZZ, -1));
    }

    @Test
    void shouldThrowIfIncreaseNegative() {
        Sound sound = new Sound(SoundType.BUZZ, 1.0);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sound.increaseIntensity(-1));
    }
}