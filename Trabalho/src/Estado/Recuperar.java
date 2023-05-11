package Trabalho.src.Estado;

import Trabalho.src.Vista.Menu;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Recuperar {
    //Talvez tenha de adaptar para ler um caminho e não o nome do ficheiro
    public static void parse(String nomeFicheiro){
        List<String> linhas = lerFicheiro(nomeFicheiro);
        String[] linhaPartida;

        for(String linha : linhas){
            //ver slide 30
        }
    }

    public static List<String> lerFicheiro(String nomeFicheiro){
        List<String> linhas = new ArrayList<>();

        try {
            linhas = Files.readAllLines(Paths.get(nomeFicheiro), StandardCharsets.UTF_8);
        }catch (IOException e){
            Menu.erro(e.getMessage());
        }
        return linhas;
    }
}
