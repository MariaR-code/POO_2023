package Trabalho.src.Erros;

public class NaoExisteTransportadora extends Exception{
    public NaoExisteTransportadora(String nome_transportadora){
        super(nome_transportadora);
    }
}
