package functions.math.trigonometric;

import functions.math.MathFunc;

public class Cos implements MathFunc {
    private final Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public double calc(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return Double.NaN;
        }

        return this.sin.calc(Math.PI / 2 - x);
    }
}