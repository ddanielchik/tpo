package functions.math.logarithmic;

import functions.math.MathFunc;

public class Log3 implements MathFunc {
    private final LogN logN;

    public Log3(Ln ln) {
        this.logN = new LogN(ln, 3.0);
    }

    public double calc(double x) {
        return logN.calc(x);
    }
}