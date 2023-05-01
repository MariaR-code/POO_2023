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
                System.out.println("\n=== VINTAGE ===\n");
                System.out.println("1 - Criar usuário");
                System.out.println("2 - Login");
                System.out.println("0 - Sair");

            System.out.println("Selecione uma opção:");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> createUser();
                case "2" -> login();
                case "3" -> {
                    System.out.println("Obrigado por usar Vintage!");
                    running = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void createUser() {
        // TODO: implementação da criação de um novo usuário
            System.out.println("\n=== CRIAR UTILIZADOR ===");
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Nome: ");
            String name = scanner.nextLine();
            System.out.print("Morada: ");
            String address = scanner.nextLine();
            System.out.print("NIF: ");
            String nif = scanner.nextLine();
            System.out.print("Tipo de utilizador (0 - Comprador, 1 - Vendedor, 2 - Ambos): ");
            int userType = scanner.nextInt();
            scanner.nextLine();

            try {
                Utilizador user = new Utilizador(email, name, address, nif, userType);
                System.out.println("Utilizador criado com sucesso!");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro na criação de utilizador: " + e.getMessage());
            }
        }


    private void login() {
        // TODO: implementação do login de um usuário existente
        System.out.println("\n=== LOGIN ===");
        System.out.print("Email: ");
        String email = scanner.nextLine();

        //Verificação de usuário com o email
        if (email != "") {
            System.out.println("Login com sucesso!");
        } else {
            System.out.println("Falha no login. Email inválido.");
        }
    }
}

