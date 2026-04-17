package functions.math.integration;

import functions.math.logarithmic.Ln;
import functions.math.logarithmic.Log10;
import functions.math.logarithmic.Log2;
import functions.math.logarithmic.Log3;
import functions.math.logarithmic.Log5;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LogNIntegrationTest {
    private static final double EPS = 1e-9;

    private final Ln ln = new Ln();
    private final Log2 log2 = new Log2(ln);
    private final Log3 log3 = new Log3(ln);
    private final Log5 log5 = new Log5(ln);
    private final Log10 log10 = new Log10(ln);

    @ParameterizedTest(name = "log integration ({0})")
    @CsvFileSource(resources = "/integration/logn.csv", numLinesToSkip = 1, delimiter = ';')
    void shouldCalculateLogsWithLn(
            double x,
            double expectedLog2,
            double expectedLog3,
            double expectedLog5,
            double expectedLog10
    ) {
        assertAll(
                () -> assertEquals(expectedLog2, log2.calc(x), EPS),
                () -> assertEquals(expectedLog3, log3.calc(x), EPS),
                () -> assertEquals(expectedLog5, log5.calc(x), EPS),
                () -> assertEquals(expectedLog10, log10.calc(x), EPS)
        );
    }
}