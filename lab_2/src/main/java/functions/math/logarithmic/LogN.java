package functions.math.logarithmic;

import functions.math.MathFunc;

public class LogN implements MathFunc {
    private final Ln ln;
    private final double base;
    private final double lnBase;

    public LogN(Ln ln, double base) {
        if (ln == null) {
            throw new IllegalArgumentException("ln must not be null");
        }

        if (Double.isNaN(base) || Double.isInfinite(base) || base <= 0.0 || base == 1.0) {
            throw new IllegalArgumentException("base must be positive and not equal to 1");
        }

        this.ln = ln;
        this.base = base;
        this.lnBase = ln.calc(base);

        double eps = 1e-12;
        if (Double.isNaN(this.lnBase) || Math.abs(this.lnBase) < eps) {
            throw new IllegalArgumentException("invalid logarithm base");
        }
    }

    public double calc(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x) || x <= 0.0) {
            return Double.NaN;
        }

        double lnX = this.ln.calc(x);

        if (Double.isNaN(lnX)) {
            return Double.NaN;
        }

        return lnX / lnBase;
    }

    public double getBase() {
        return base;
    }
}