package functions.math.logarithmic;

import functions.math.MathFunc;

public class Ln implements MathFunc {
    public double calc(double x) {
        return calc(x, Integer.MAX_VALUE);
    }

    public double calc(double x, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }

        if (Double.isNaN(x) || Double.isInfinite(x) || x <= 0) {
            return Double.NaN;
        }

        if (x == 1.0) {
            return 0.0;
        }

        double z = (x - 1) / (x + 1);
        double zPow = z;
        double res = 0.0;
        final double eps = 1e-12;

        for (int i = 0; i < n; i++) {
            double term = zPow / (2 * i + 1);
            res += term;

            if (Math.abs(term) < eps) {
                break;
            }

            zPow *= z * z;
        }

        return 2 * res;
    }
}