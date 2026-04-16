package functions.math.logarithmic;

import functions.math.MathFunc;

public class Log5 implements MathFunc {
    private final LogN logN;

    public Log5(Ln ln) {
        this.logN = new LogN(ln, 5.0);
    }

    public double calc(double x) {
        return logN.calc(x);
    }
}