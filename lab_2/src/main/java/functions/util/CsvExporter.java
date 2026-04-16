package functions.util;

import functions.math.MathFunc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class CsvExporter {
    private final String delimiter;

    public CsvExporter(String delimiter) {
        this.delimiter = delimiter;
    }

    public void write(String filePath, MathFunc function, double from, double to, double step) throws IOException {
        validate(filePath, function, from, to, step);

        File file = new File(filePath);
        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            if (!parent.mkdirs()) {
                throw new IOException("Failed to create directories: " + parent.getAbsolutePath());
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("x" + delimiter + "result");
            writer.newLine();

            for (double x = from; x <= to + step / 2; x += step) {
                double y = function.calc(x);
                writer.write(format(x) + delimiter + format(y));
                writer.newLine();
            }
        }
    }

    private void validate(String filePath, MathFunc function, double from, double to, double step) {
        if (function == null) {
            throw new IllegalArgumentException("function must not be null");
        }

        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("filePath must not be blank");
        }

        if (Double.isNaN(from) || Double.isNaN(to) || Double.isNaN(step)) {
            throw new IllegalArgumentException("range values must not be NaN");
        }

        if (Double.isInfinite(from) || Double.isInfinite(to) || Double.isInfinite(step)) {
            throw new IllegalArgumentException("range values must be finite");
        }

        if (step <= 0.0) {
            throw new IllegalArgumentException("step must be positive");
        }

        if (from > to) {
            throw new IllegalArgumentException("from must be less than or equal to to");
        }
    }

    private String format(double value) {
        if (Double.isNaN(value)) {
            return "NaN";
        }

        if (Double.isInfinite(value)) {
            return value > 0 ? "Infinity" : "-Infinity";
        }

        return String.format(Locale.US, "%.10f", value);
    }
}