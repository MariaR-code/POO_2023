package Trabalho.src.Erros;

public class NaoExisteUtilizador extends RuntimeException{
    public NaoExisteUtilizador(String email){
        super(email);
    }
}