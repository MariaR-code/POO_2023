// Useless. Delete later?

package Trabalho.src.Vista;

import Trabalho.src.Modelo.Utilizador;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CLI {
    private Scanner scanner;

    public CLI() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
                System.out.println("=== VINTAGE ===");
                System.out.println("1 - Criar usuário - TODO");
                System.out.println("2 - Login - TODO");
                System.out.println("3 - Sair");
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
        // TODO: implementação da criação de um novo utilizador
        boolean creatingUser = true;
        while (creatingUser) {
            System.out.println("=== CRIAR UTILIZADOR ===");

            String email = "";
            while (!Utilizador.isValidEmail(email)) { //é melhor também verificar se o email ja foi usado ou não. depois ve se
                System.out.print("Email: ");
                email = scanner.nextLine();
                if (!Utilizador.isValidEmail(email)) {
                    System.out.println("Email inválido. Tente novamente.");
                }
            }

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Morada: ");
            String morada = scanner.nextLine();

            String nif = "";
            while (!Utilizador.isValidNIF(nif)) {
                System.out.print("NIF: ");
                nif = scanner.nextLine();
                if (!Utilizador.isValidNIF(nif)) {
                    System.out.println("NIF inválido. Tente novamente.");
                }
            }

            int tipoDeUser = -1;
            while (tipoDeUser < 0 || tipoDeUser > 2) {
                System.out.print("Tipo de utilizador (0 - Comprador, 1 - Vendedor, 2 - Ambos): ");
                try {
                    tipoDeUser = scanner.nextInt();
                    scanner.nextLine();
                    if (tipoDeUser < 0 || tipoDeUser > 2) {
                        System.out.println("Tipo de utilizador inválido. Tente novamente.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Tipo de utilizador inválido. Tente novamente.");
                    scanner.nextLine();
                }
            }

            try {
                Utilizador user = new Utilizador(email, nome, morada, nif, tipoDeUser);
                System.out.println("Utilizador criado com sucesso!");
                creatingUser = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro na criação de novo utilizador: " + e.getMessage());
            }
        }
        }


    private void login() {
        // TODO: implementação do login de um utilizador existente
        System.out.println("=== LOGIN ===");
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

