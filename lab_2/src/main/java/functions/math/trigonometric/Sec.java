package functions.math.trigonometric;

import functions.math.MathFunc;

public class Sec implements MathFunc {
    private final Cos cos;

    public Sec(Cos cos) {
        this.cos = cos;
    }

    public double calc(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return Double.NaN;
        }

        double cosValue = this.cos.calc(x);
        final double eps = 1e-10;

        if (Double.isNaN(cosValue) || Math.abs(cosValue) < eps) {
            return Double.NaN;
        }

        return 1.0 / cosValue;
    }
}