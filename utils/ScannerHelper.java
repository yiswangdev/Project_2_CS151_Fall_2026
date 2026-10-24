package utils;

import java.util.Scanner;

public class ScannerHelper {
    static Scanner scanner = new Scanner(System.in);
 
    public static String getInput() {
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("exit")) {
            System.out.println("User chose \'EXIT\'. Bye!");
            System.exit(0);
        }
        return input;
    }
}