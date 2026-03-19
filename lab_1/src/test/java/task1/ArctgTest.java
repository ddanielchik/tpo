package task1;


import org.example.task1.Arctg;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class ArctgTest {

    @ParameterizedTest(name = "arctg({0})")
    @DisplayName("Check corner values")
    @ValueSource(doubles = {
            -999.9,
            -1.0000001,
            -1.0,
            -0.99,
            -0.5000001,
            -0.1,
            -0.0,
            0.0,
            0.1,
            0.5000001,
            0.99,
            1.0,
            1.0000001,
            999.9,
            Double.NaN,
            Double.NEGATIVE_INFINITY,
            Double.POSITIVE_INFINITY,
            Double.MIN_VALUE,
            -Double.MIN_VALUE,
            Double.MAX_VALUE,
            -Double.MAX_VALUE
    })
    void checkCornerDots(double param) {
        double expected = Math.atan(param);
        double actual = Arctg.arctg(param);

        assertAll(
                () -> assertEquals(expected, actual, 1e-6),
                () -> {
                    if (Double.doubleToRawLongBits(param) == Double.doubleToRawLongBits(-0.0d)) {
                        assertEquals(
                                Double.doubleToRawLongBits(expected),
                                Double.doubleToRawLongBits(actual),
                                "Sign of -0.0 must be preserved"
                        );
                    }
                }
        );
    }

    @ParameterizedTest(name = "arctg({0}) = {1}")
    @DisplayName("Check between dots [-1; +1]")
    @CsvFileSource(resources = "table_values.csv", numLinesToSkip = 1, delimiter = ';')
    void checkBetweenDotsMinus1And1(double x, double y) {
        assertAll(() -> assertEquals(y, Arctg.arctg(x), 1e-6));
    }

    @Test
    @DisplayName("Fuzzy testing")
    void checkRandomDots() {
        for (int i = 0; i < 1_000_000; i++) {
            double randomValue = ThreadLocalRandom.current().nextDouble(-1000.0, 1000.0);
            assertEquals(Math.atan(randomValue), Arctg.arctg(randomValue), 1e-6);
        }
    }

    @Test
    @DisplayName("Check convergence with small n (series truncation)")
    void checkSmallNConvergence() {
        double x = 0.1;
        assertEquals(Math.atan(x), Arctg.arctg(x, 1), 1e-1);
        assertEquals(Math.atan(x), Arctg.arctg(x, 3), 1e-6);
    }

    @Test
    @DisplayName("n must be positive")
    void checkNMustBePositive() {
        assertThrows(IllegalArgumentException.class, () -> Arctg.arctg(0.1, 0));
        assertThrows(IllegalArgumentException.class, () -> Arctg.arctg(0.1, -1));
    }
}