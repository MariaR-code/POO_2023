package Trabalho.src.Vista;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Mostrar {
    private List<String> opcoes;
    private String nome;
    private int opcao;

    /**
     * Construtor da classe Mostrar
     * Tem como objetivo guardar tanto o nome como as diversas opções
     * dadas de um determinado menu
     * */
    public Mostrar(String nome, String[] opcoes){
        this.nome = nome;
        this.opcoes = Arrays.asList(opcoes);
        this.opcao = 0;
    }

    /**
     * Método que apresenta o menu ao utilizador até o mesmo escolher uma opção válida
     * */
    public void executa(){
        do {
            mostra();
            this.opcao = ler_opcao();
        }while (this.opcao == -1);
    }

    /**
     * Método que apresenta ao utilizador um determinado menu e as suas opções
     * */
    public void mostra(){
        int i;
        System.out.println("\n#####################" + this.nome + "#####################");
        for( i=1; i<=this.opcoes.size(); i++)
            System.out.println(i + " - " + this.opcoes.get(i));
        System.out.println(i + " - " + "Sair");
    }

    /**
     * Método que lê o valor da opção escolhida pelo utilizador
     * @return int
     */
    public int ler_opcao(){
        int op;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Escolha a opção que pretende: ");

        try{
            op = scanner.nextInt();
        }catch (InputMismatchException e){
            op = -1;
        }

        if(op<0 || op>this.opcoes.size()){
            System.out.println("Opção inválida, por favor escolha outra.");
            op = -1;
        }

        return op;
    }

    /**
     * Método que nos informar qual foi a última opção lida de um determinado utilizador
     * @return int
     * */
    public int getOpcao(){
        return this.opcao;
    }
}
