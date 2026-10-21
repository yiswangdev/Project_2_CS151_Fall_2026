package classes;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        while (running) {
            System.out.println("Welcome to the restaurant!");
            System.out.println("1. View Menu");
            System.out.println("2. Place Order");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            
        }
        scanner.close();
    }
}
