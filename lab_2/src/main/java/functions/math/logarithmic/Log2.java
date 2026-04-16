package functions.math.logarithmic;

import functions.math.MathFunc;

public class Log2 implements MathFunc {
    private final LogN logN;

    public Log2(Ln ln) {
        this.logN = new LogN(ln, 2.0);
    }

    public double calc(double x) {
        return logN.calc(x);
    }
}