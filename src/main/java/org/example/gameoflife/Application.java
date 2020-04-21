package org.example.gameoflife;

import java.util.Scanner;

public class Application {

    public static void main(final String... args) {
        final Grid grid = Grid.parse(findGridString(args));
        System.out.println(grid.nextGeneration().toString()); // NOSONAR
    }

    static String findGridString(final String... args) {
        if (args.length >= 1) {
            return args[0];
        } else {
            System.out.println("Please enter your grid:"); // NOSONAR
            final StringBuilder stringBuilder = new StringBuilder();
            try (final Scanner scanner = new Scanner(System.in)) {
                final String line = scanner.nextLine();
                final int count = Integer.parseInt(line.split(" ")[0]);
                stringBuilder.append(line);
                for (int i = 0; i < count; i++) {
                    stringBuilder.append('\n').append(scanner.nextLine());
                }
            }
            return stringBuilder.toString();
        }
    }

}
