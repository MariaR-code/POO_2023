package Trabalho.src.Erros;
import Trabalho.src.Modelo.Utilizador;
public class NaoExisteUtilizador extends RuntimeException{
    public NaoExisteUtilizador(String email){
        super(email);
    }

}
