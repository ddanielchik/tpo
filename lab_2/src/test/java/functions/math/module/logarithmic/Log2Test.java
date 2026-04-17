package functions.math.module.logarithmic;

import functions.math.logarithmic.Ln;
import functions.math.logarithmic.Log2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Log2Test {
    private static final double EPS = 1e-9;

    private final Log2 log2 = new Log2(new Ln());

    @ParameterizedTest(name = "log2({0}) = {1}")
    @CsvFileSource(resources = "/module/logarithmic/log2.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateLog2(double x, double expected) {
        assertEquals(expected, log2.calc(x), EPS);
    }
}