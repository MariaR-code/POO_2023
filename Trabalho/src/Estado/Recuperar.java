package Trabalho.src.Estado;

import Trabalho.src.Modelo.*;
import Trabalho.src.Vista.Menu;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        String[] var = linha.split(",");
        int id = Integer.parseInt(var[0]);
        String email = var[1];
        String nome = var[2];
        String morada = var[3];
        String nif = var[4];
        int tipo = Integer.parseInt(var[5]);
        double valorTotalVendas = Double.parseDouble(var[6]);

        //VER COMO FAZER A PARTE DAS FATURAS e ver o return

        return new Utilizador(id, email, nome, valorTotalVendas, morada, nif, tipo);
    }

    public static Transportadora parseTransportadora(String linha){
        String[] var = linha.split(",");
        String nome = var[0];
        String preco_expedicao = var[1];
        boolean premium = Boolean.parseBoolean(var[2]);
        String preco_expedicao_premium = "";

        if(premium) {
            preco_expedicao_premium = var[3];
            return new Transportadora(nome, preco_expedicao, preco_expedicao_premium);
        }else
            return new Transportadora(nome, preco_expedicao);
    }

    public static Sapatilhas parseSapatilhas(String linha){
        String[] var = linha.split(",");
        Sapatilhas sapatilhas = null;

        String descricao = var[0];
        String marca = var[1];
        String cod_alfanr = var[2];
        double preco_base = Double.parseDouble(var[3]);
        boolean usado = Boolean.parseBoolean(var[4]);
        String transportadora = var[7];
        int tamanho = Integer.parseInt(var[7]);
        boolean atacadores = Boolean.parseBoolean(var[8]);
        String cor = var[9];
        LocalDate data = LocalDate.parse(var[10]);
        boolean premium = Boolean.parseBoolean(var[11]);
        if(usado){
            int av_estado = Integer.parseInt(var[5]);
            int nr_donos = Integer.parseInt(var[6]);
            sapatilhas = new Sapatilhas(descricao, marca, cod_alfanr, preco_base, transportadora,
                    av_estado, nr_donos, tamanho, atacadores, cor, data, premium);
        }else sapatilhas = new Sapatilhas(descricao, marca, cod_alfanr, preco_base, transportadora, tamanho,
                atacadores, cor, data, premium);

        return sapatilhas;
    }

    public static Mala parseMala(String linha){
        String[] var = linha.split(",");
        Mala mala = null;

        String descricao = var[0];
        String marca = var[1];
        String cod_alfanr = var[2];
        double preco_base = Double.parseDouble(var[3]);
        boolean usado = Boolean.parseBoolean(var[4]);
        String transportadora = var[7];
        double altura = Double.parseDouble(var[8]);
        double comprimento = Double.parseDouble(var[9]);
        double profundidade = Double.parseDouble(var[10]);
        String material = var[11];
        int ano = Integer.parseInt(var[12]);
        boolean premimum = Boolean.parseBoolean(var[13]);
        if(usado){
            int av_estado = Integer.parseInt(var[5]);
            int nr_donos = Integer.parseInt(var[6]);
            mala = new Mala(descricao, marca, cod_alfanr, preco_base, transportadora,
                    av_estado, nr_donos,altura, comprimento, profundidade, material, ano, premimum);
        }else mala = new Mala(descricao, marca, cod_alfanr, preco_base, transportadora, altura,
                comprimento, profundidade, material, ano, premimum);

        return mala;
    }

    public static Tshirt parseTshirt(String linha){
        String[] var = linha.split(",");
        Tshirt tshirt = null;

        String descricao = var[0];
        String marca = var[1];
        String cod_alfanr = var[2];
        double preco_base = Double.parseDouble(var[3]);
        boolean usado = Boolean.parseBoolean(var[4]);
        String transportadora = var[7];
        Tshirt.Tamanho tamanho = Tshirt.Tamanho.valueOf(var[8]);
        Tshirt.Padrao padrao = Tshirt.Padrao.valueOf(var[9]);
        if(usado){
            int av_estado = Integer.parseInt(var[5]);
            int nr_donos = Integer.parseInt(var[6]);
            tshirt = new Tshirt(descricao, marca, cod_alfanr, preco_base, transportadora,
                    av_estado, nr_donos,tamanho, padrao);
        }else tshirt = new Tshirt(descricao, marca, cod_alfanr, preco_base, transportadora, tamanho, padrao);

        return tshirt;
    }

    public static int parseChaves(String linha){
        int chave = Integer.parseInt(linha);
        return chave;
    }

    public static Encomenda parseEncomendas_PendenteValor(String linha){
        List<Artigo> lstArt = new ArrayList<>();
        String[] enc = linha.split(";");
        String[] var_enc = enc[0].split(",");

        Encomenda.Dimensao dimensao = Encomenda.Dimensao.valueOf(var_enc[0]);
        double preco_final = Double.parseDouble(var_enc[1]);
        LocalDate data = LocalDate.parse(var_enc[2]);
        Encomenda.Estado estado = Encomenda.Estado.valueOf(var_enc[3]);

        String[] artigos = var_enc[1].split("/");
        for(String artigo : artigos){
            String[] nomeartigo = artigo.split(":");
            Artigo art = null;

            switch(nomeartigo[0]){
                case "Sapatilhas":
                    art = parseSapatilhas(nomeartigo[1]);
                    break;
                case "Mala":
                    art = parseMala(nomeartigo[1]);
                    break;
                case "Tshirt":
                    art = parseTshirt(nomeartigo[1]);
                    break;
            }
            lstArt.add(art);
        }

        return new Encomenda(lstArt, preco_final, data, estado);
    }

    //2=lkn
    public static String parseArtigos_vValor(String linha){
        return ;
    }
}
