package org.example.task1;

public class App {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java lab1.task1.App <x> [<n>]");
            return;
        }

        try {
            double x = Double.parseDouble(args[0]);
            int n = args.length >= 2 ? Integer.parseInt(args[1]) : Integer.MAX_VALUE;

            double result = Arctg.arctg(x, n);

            System.out.printf("arctg(%.10f) = %.15f%n(with %d terms)%n", x, result, n);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number for x and an integer for n.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
