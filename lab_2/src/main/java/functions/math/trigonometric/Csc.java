package functions.math.trigonometric;

import functions.math.MathFunc;

public class Csc implements MathFunc {
    private final Sin sin;

    public Csc(Sin sin) {
        this.sin = sin;
    }

    public double calc(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return Double.NaN;
        }

        double sinValue = this.sin.calc(x);
        final double eps = 1e-10;

        if (Double.isNaN(sinValue) || Math.abs(sinValue) < eps) {
            return Double.NaN;
        }

        return 1.0 / sinValue;
    }
}