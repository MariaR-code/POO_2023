package Trabalho.src.Controlo;

import Trabalho.src.Modelo.Mercado;
import Trabalho.src.Vista.Menu;
import Trabalho.src.Erros.NaoExisteUtilizador;

public class Controlo{
    private Menu gui;
    private Mercado model;

    public Controlo(){
        this.gui = new Menu();
        this.model = new Mercado();

    }

    public void run(){
        int op = this.gui.menu(" Bem-vind@ à Vintage ", Menu.menu_Principal_Vintage);

        switch (op){
            case 1:
                loginVendedor(1);
                break;
            case 2:
                loginVendedor(0);
                break;

            case 3:
                break;

            case 4:
                System.out.println("Fim do programa");
                System.exit(0);
                break;
        }
    }

    public void loginVendedor(int tipo) throws NaoExisteUtilizador{
        try{
            String email = "";
            if(!(this.model.procuraUtilizador(email,tipo))){
                throw new NaoExisteUtilizador("Utilizador não registado");
            }
        }
        catch(NaoExisteUtilizador e){
            Menu.erro(e.getMessage());
            this.run();
        }
    }

    public void criarArtigo(){

    }
    public void criarEncomenda(){

    }
}