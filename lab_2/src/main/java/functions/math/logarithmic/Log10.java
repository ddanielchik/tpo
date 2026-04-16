package functions.math.logarithmic;

import functions.math.MathFunc;

public class Log10 implements MathFunc {
    private final LogN logN;

    public Log10(Ln ln) {
        this.logN = new LogN(ln, 10.0);
    }

    public double calc(double x) {
        return logN.calc(x);
    }
}