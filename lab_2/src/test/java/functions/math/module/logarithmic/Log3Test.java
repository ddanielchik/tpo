package functions.math.module.logarithmic;

import functions.math.logarithmic.Ln;
import functions.math.logarithmic.Log3;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Log3Test {
    private static final double EPS = 1e-9;

    private final Log3 log3 = new Log3(new Ln());

    @ParameterizedTest(name = "log3({0}) = {1}")
    @CsvFileSource(resources = "/module/logarithmic/log3.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateLog3(double x, double expected) {
        assertEquals(expected, log3.calc(x), EPS);
    }
}