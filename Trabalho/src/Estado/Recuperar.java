package Trabalho.src.Estado;

import Trabalho.src.Modelo.*;
import Trabalho.src.Vista.Menu;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Recuperar {
    public static List<String> lerFicheiro(String nomeFicheiro){
        List<String> linhas = new ArrayList<>();

        try {
            linhas = Files.readAllLines(Paths.get(nomeFicheiro), StandardCharsets.UTF_8);
        }catch (IOException e){
            Menu.erro(e.getMessage());
        }
        return linhas;
    }

    //1,a@b.c,M,J,132546895,0,0.0,[],[]
    public static Utilizador parseUtilizador(String linha){
        return ;
    }

    public static Transportadora parseTransportadora(String linha){
        return ;
    }

    public static Sapatilhas parseSapatilhas(String linha){
        return ;
    }

    public static Mala parseMala(String linha){
        return ;
    }

    public static Tshirt parseTshirt(String linha){
        return ;
    }

    public static int parseEncomendas_PendenteChave(String linha){
        return ;
    }

    public static Encomenda parseEncomendas_PendenteValor(String linha){
        return ;
    }

    public static int parseArtigos_vChave(String linha){
        return ;
    }

    public static String parseArtigos_vValor(String linha){
        return ;
    }
}
