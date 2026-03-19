package org.example.task1;


public class Arctg {

    public static double arctg(double x) {
        return arctg(x, Integer.MAX_VALUE);
    }

    /**
     * Реализация разложения arctg(x) в степенной ряд.
     *
     * @param x аргумент функции
     * @param n максимальное количество членов ряда для вычисления
     * @return значение arctg(x)
     */
    public static double arctg(double x, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }

        if (Double.isNaN(x)) {
            return Double.NaN;
        }

        if (x == -0d) {
            return x;
        }

        if (Double.isInfinite(x)) {
            return Math.copySign(Math.PI / 2.0, x);
        }

        if (x < 0) {
            return -arctg(-x, n);
        }

        if (x > 1.0) {
            return (Math.PI / 2.0) - arctg(1.0 / x, n);
        }

        if (x > 0.5) {
            double t = (x - 1.0) / (x + 1.0);
            return (Math.PI / 4.0) + arctg(t, n);
        }

        double res = 0.0;
        double term = x;
        final double eps = 1e-16;

        for (int i = 0; i < n; i++) {
            double add = term / (2.0 * i + 1.0);
            res += add;

            if (Math.abs(add) < eps) {
                break;
            }

            term *= -(x * x);
        }

        return res;
    }
}