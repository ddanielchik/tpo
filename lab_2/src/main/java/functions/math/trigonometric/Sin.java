package functions.math.trigonometric;

import functions.math.MathFunc;

public class Sin implements MathFunc {
    public double calc(double x) {
        return calc(x, Integer.MAX_VALUE);
    }

    /**
     * Реализация разложения sin(x) в степенной ряд.
     *
     * @param x аргумент функции
     * @param n максимальное количество членов ряда для вычисления
     * @return значение sin(x)
     */
    public double calc(double x, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }

        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return Double.NaN;
        }

        if (x == 0d) {
            return x;
        }

        if (x < 0) {
            return -calc(-x, n);
        }

        double twoPi = 2.0 * Math.PI;
        x = x % twoPi;

        if (x > Math.PI) {
            x -= twoPi;
        }

        double res = 0.0;
        double term = x;
        final double eps = 1e-12;

        for (int i = 0; i < n; i++) {
            res += term;

            if (Math.abs(term) < eps) {
                break;
            }

            term *= -(x * x) / ((2.0 * i + 2.0) * (2.0 * i + 3.0));
        }

        return res;
    }
}