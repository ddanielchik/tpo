package functions.math;

import functions.math.logarithmic.Ln;
import functions.math.logarithmic.Log10;
import functions.math.logarithmic.Log2;
import functions.math.logarithmic.Log3;
import functions.math.logarithmic.Log5;
import functions.math.trigonometric.Cos;
import functions.math.trigonometric.Csc;
import functions.math.trigonometric.Sec;
import functions.math.trigonometric.Sin;
import functions.math.trigonometric.Tan;

public class FunctionSystem implements MathFunc {
    private static final double EPS = 1e-10;

    private final Sin sin;
    private final Cos cos;
    private final Tan tan;
    private final Csc csc;
    private final Sec sec;

    private final Ln ln;
    private final Log2 log2;
    private final Log3 log3;
    private final Log5 log5;
    private final Log10 log10;

    public FunctionSystem() {
        this.sin = new Sin();
        this.cos = new Cos(sin);
        this.tan = new Tan(sin, cos);
        this.csc = new Csc(sin);
        this.sec = new Sec(cos);

        this.ln = new Ln();
        this.log2 = new Log2(ln);
        this.log3 = new Log3(ln);
        this.log5 = new Log5(ln);
        this.log10 = new Log10(ln);
    }

    @Override
    public double calc(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return Double.NaN;
        }

        return x <= 0.0 ? calcTrigonometricBranch(x) : calcLogarithmicBranch(x);
    }

    private double calcTrigonometricBranch(double x) {
        TrigonometricValues values = getTrigonometricValues(x);
        if (!values.areValid()) {
            return Double.NaN;
        }

        double firstFactor = (values.cos * values.cos) - values.sin;
        double secondFactor = values.tan - values.csc;

        // (sin(x) - sin(x)) = 0
        return (firstFactor * secondFactor * secondFactor) + values.sec;
    }

    private double calcLogarithmicBranch(double x) {
        LogarithmicValues values = getLogarithmicValues(x);
        if (!values.areValid()) {
            return Double.NaN;
        }

        double leftMultiplier =
                (((values.log5 * values.log10) * values.log3) + values.log10)
                        * ((values.log5 * values.log5) * values.ln);

        double denominator = values.log10 - 2.0 * values.log2;
        if (Math.abs(denominator) < EPS) {
            return Double.NaN;
        }

        double rightMultiplier =
                (values.log2 + values.ln + values.log3) / denominator;

        return leftMultiplier * rightMultiplier;
    }

    private TrigonometricValues getTrigonometricValues(double x) {
        return new TrigonometricValues(
                sin.calc(x),
                cos.calc(x),
                tan.calc(x),
                csc.calc(x),
                sec.calc(x)
        );
    }

    private LogarithmicValues getLogarithmicValues(double x) {
        return new LogarithmicValues(
                ln.calc(x),
                log2.calc(x),
                log3.calc(x),
                log5.calc(x),
                log10.calc(x)
        );
    }

    private static boolean allFinite(double... values) {
        for (double value : values) {
            if (Double.isNaN(value)) {
                return false;
            }
        }
        return true;
    }

    private record TrigonometricValues(
            double sin,
            double cos,
            double tan,
            double csc,
            double sec
    ) {
        private boolean areValid() {
            return allFinite(sin, cos, tan, csc, sec);
        }
    }

    private record LogarithmicValues(
            double ln,
            double log2,
            double log3,
            double log5,
            double log10
    ) {
        private boolean areValid() {
            return allFinite(ln, log2, log3, log5, log10);
        }
    }
}