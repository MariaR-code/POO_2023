package Trabalho.src;

import java.util.Scanner;

public class CLI {
    private Scanner scanner;

    public CLI() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n== Welcome to Vintage ==");
            System.out.println("1. Create User");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Please select an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> createUser();
                case "2" -> login();
                case "3" -> {
                    System.out.println("Thank you for using Vintage! Goodbye.");
                    running = false;
                }
                default -> System.out.println("Invalid input. Please choose a valid number.");
            }
        }
    }

    private void createUser() {
        // TODO: implementação da criação de um novo usuário
            System.out.println("\n=== CREATE USER ===");
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Address: ");
            String address = scanner.nextLine();
            System.out.print("NIF: ");
            String nif = scanner.nextLine();
            System.out.print("User type (0 - buyer, 1 - seller, 2 - both): ");
            int userType = scanner.nextInt();
            scanner.nextLine();

            try {
                Utilizador user = new Utilizador(email, name, address, nif, userType);
                System.out.println("User created successfully!");
            } catch (IllegalArgumentException e) {
                System.out.println("Error creating user: " + e.getMessage());
            }
        }


    private void login() {
        // TODO: implementação do login de um usuário existente
        System.out.println("\n=== LOGIN ===");
        System.out.print("Email: ");
        String email = scanner.nextLine();

        //Verificação de usuário com o email
        if (email != "") {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Invalid email.");
        }
    }
}

