package functions.math.trigonometric;

import functions.math.MathFunc;

public class Tan implements MathFunc {
    private final Sin sin;
    private final Cos cos;

    public Tan(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    public double calc(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return Double.NaN;
        }

        double sinValue = this.sin.calc(x);
        double cosValue = this.cos.calc(x);
        final double eps = 1e-10;

        if (Double.isNaN(sinValue) || Double.isNaN(cosValue) || Math.abs(cosValue) < eps) {
            return Double.NaN;
        }

        return sinValue / cosValue;
    }
}