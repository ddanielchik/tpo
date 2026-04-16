package functions;

import functions.util.CsvExporter;
import functions.math.FunctionSystem;
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

import java.io.IOException;

public class Main {
    private static final String OUTPUT_DIR = "output/";
    private static final String DELIMITER = ";";

    private static final double NEGATIVE_FROM = -10.0;
    private static final double POSITIVE_FROM = 0.1;
    private static final double POSITIVE_TO = 10.0;
    private static final double FULL_FROM = -10.0;
    private static final double FULL_TO = 10.0;
    private static final double STEP = 0.1;

    public static void main(String[] args) {
        try {
            generateAllCsv();
            System.out.println("CSV-файлы успешно созданы.");
        } catch (IOException e) {
            System.err.println("Ошибка при записи CSV-файлов: " + e.getMessage());
        }
    }

    private static void generateAllCsv() throws IOException {
        CsvExporter csvWriter = new CsvExporter(DELIMITER);

        Sin sin = new Sin();
        Cos cos = new Cos(sin);
        Tan tan = new Tan(sin, cos);
        Csc csc = new Csc(sin);
        Sec sec = new Sec(cos);

        Ln ln = new Ln();
        Log2 log2 = new Log2(ln);
        Log3 log3 = new Log3(ln);
        Log5 log5 = new Log5(ln);
        Log10 log10 = new Log10(ln);

        FunctionSystem functionSystem = new FunctionSystem();

        generateTrigonometricCsv(csvWriter, sin, cos, tan, csc, sec);
        generateLogarithmicCsv(csvWriter, ln, log2, log3, log5, log10);
        generateSystemCsv(csvWriter, functionSystem);
    }

    private static void generateTrigonometricCsv(
            CsvExporter csvWriter,
            Sin sin,
            Cos cos,
            Tan tan,
            Csc csc,
            Sec sec
    ) throws IOException {
        csvWriter.write(OUTPUT_DIR + "sin.csv", sin, NEGATIVE_FROM, FULL_TO, STEP);
        csvWriter.write(OUTPUT_DIR + "cos.csv", cos, NEGATIVE_FROM, FULL_TO, STEP);
        csvWriter.write(OUTPUT_DIR + "tan.csv", tan, NEGATIVE_FROM, FULL_TO, STEP);
        csvWriter.write(OUTPUT_DIR + "csc.csv", csc, NEGATIVE_FROM, FULL_TO, STEP);
        csvWriter.write(OUTPUT_DIR + "sec.csv", sec, NEGATIVE_FROM, FULL_TO, STEP);
    }

    private static void generateLogarithmicCsv(
            CsvExporter csvWriter,
            Ln ln,
            Log2 log2,
            Log3 log3,
            Log5 log5,
            Log10 log10
    ) throws IOException {
        csvWriter.write(OUTPUT_DIR + "ln.csv", ln, POSITIVE_FROM, POSITIVE_TO, STEP);
        csvWriter.write(OUTPUT_DIR + "log2.csv", log2, POSITIVE_FROM, POSITIVE_TO, STEP);
        csvWriter.write(OUTPUT_DIR + "log3.csv", log3, POSITIVE_FROM, POSITIVE_TO, STEP);
        csvWriter.write(OUTPUT_DIR + "log5.csv", log5, POSITIVE_FROM, POSITIVE_TO, STEP);
        csvWriter.write(OUTPUT_DIR + "log10.csv", log10, POSITIVE_FROM, POSITIVE_TO, STEP);
    }

    private static void generateSystemCsv(
            CsvExporter csvWriter,
            FunctionSystem functionSystem
    ) throws IOException {
        csvWriter.write(OUTPUT_DIR + "function-system.csv", functionSystem, FULL_FROM, FULL_TO, STEP);
    }
}