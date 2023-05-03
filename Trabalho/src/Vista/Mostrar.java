package Trabalho.src.Vista;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Mostrar {
    private List<String> opcoes;
    private String nome;
    private int opcao;

    public Mostrar(String nome, List<String> opcoes){
        this.nome = nome;
        this.opcoes = Arrays.asList(opcoes);
        this.opcao = 0;
    }
}
