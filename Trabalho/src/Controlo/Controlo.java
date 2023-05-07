package Trabalho.src.Controlo;


import Trabalho.src.Erros.ErroCriarConta;
import Trabalho.src.Modelo.Mercado;
import Trabalho.src.Modelo.Utilizador; //remove later?
import Trabalho.src.Vista.Insercao;
import Trabalho.src.Vista.Menu;
import Trabalho.src.Erros.NaoExisteUtilizador;
import Trabalho.src.Erros.ErroCriarConta;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.Supplier;

public class Controlo{
    private Menu gui;
    private Mercado model;
    private Supplier<String> supplier_String = () -> {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    };
    private Supplier<Integer> supplier_Int = () -> {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    };
    private Supplier<Double> supplier_Double = () -> {
        Scanner input = new Scanner(System.in);
        return input.nextDouble();
    };
    private Supplier<Boolean> supplier_Boolean = () -> {
        Scanner input = new Scanner(System.in);
        return input.nextBoolean();
    };
    //Verificar se isto funciona, se não se utilizar apagar
    //Falta a exceção caso o formato não seja respeitado
    //Ver melhor
    private Supplier<LocalDate> supplier_LocalDate = () -> {
        Scanner input = new Scanner(System.in);
        LocalDate data = null;
        String format = "dd-mm-aaaa";
        DateTimeFormatter f = DateTimeFormatter.ofPattern(format);
        System.out.print("Escreva no seguinte formato: " + format);
        data = LocalDate.parse(Insercao.get_tipo(supplier_String), f);
        return data;
    };

    public Controlo(){
        this.gui = new Menu();
        this.model = new Mercado();

    }

    public void run(){
        int op = this.gui.menu(" Bem-vind@ à Vintage ", Menu.menu_Principal_Vintage);

        switch (op){
            case 1:
                this.logIn(1);
                break;
            case 2:
                this.logIn(0);
                break;

            case 3:

                this.criar_conta();
                break;

            case 4:
                this.transportadoras();
                break;

            case 5:
                //Método usado para salvaguardar o estado
                this.run();
                break;

            case 6:
                //Método usado para recuperar o estado
                this.run();
                break;

            case 7:
                System.out.println("Fim do programa");
                System.exit(0);
                break;
        }
    }
    public void logIn(int tipo){
        int op = this.gui.menu(" Log In ", Menu.menu_Log_In);

        switch (op){
            case 1:
                this.loginVendedor(tipo);
                if(tipo == 1)
                    this.vendedor();
                else this.comprador();
                break;
            case 2:
                this.run();
                break;
        }
    }

    public void transportadoras(){
        int op = this.gui.menu(" Transportadoras ", Menu.menu_Transportadoras);

        switch (op){
            case 1:
                break;

            case 2:
                break;

            case 3:
                this.run();
                break;
        }
    }

    public void loginVendedor(int tipo) throws NaoExisteUtilizador{
        try{
            String email = Insercao.get_valor("email", this.supplier_String);
            if(!(this.model.procuraUtilizador(email,tipo))) {
                throw new NaoExisteUtilizador("Utilizador não registado");
            }
        }
        catch(NaoExisteUtilizador e){
            Menu.erro(e.getMessage());
            this.run();
        }
    }

    public void vendedor(){
        int op = this.gui.menu(" Vendedor ", Menu.menu_Vendedor);

        switch (op){
            case 1:
                this.adicionar_artigo();
                break;

            case 2:
                break;

            case 3:
                this.run(); //??? Talvez algum que mostre os artigos que se encontram à venda
                break;
        }
    }

    public void adicionar_artigo(){
        int op = this.gui.menu(" Adicionar Artigo ", Menu.menu_Adicionar_Artigo);

        switch (op){
            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                this.vendedor();
                break;
        }
    }

        public void criar_conta() {
            try {
                int tipo_user = Insercao.get_valor(("tipo de utilizador (0 - Comprador, 1 - Vendedor, 2 - Ambos)"), supplier_Int);
                String email = Insercao.get_valor("email", supplier_String);
                if(!(Utilizador.isValidEmail(email)) || (this.model.procuraUtilizador(email,tipo_user))) {
                    throw new ErroCriarConta("Email inválido");
                }
            String nome = Insercao.get_valor("nome", supplier_String);
            String morada = Insercao.get_valor("morada", supplier_String);
            String nif = Insercao.get_valor("nif", supplier_String);
            if (!(Utilizador.isValidNIF(nif))) {
                throw new ErroCriarConta("NIF inválido");
            }


                Utilizador user = new Utilizador(email, nome, morada, nif, tipo_user);
                throw new ErroCriarConta("idk");
            } catch (ErroCriarConta e) {e.getMessage();
            }
    }



    public void comprador(){
        int op = this.gui.menu(" Comprador ", Menu.menu_Comprador);

        switch (op){
            case 1:
                this.criarEncomenda();
                break;

            case 2:
                break;

            case 3:
                this.run(); //???? Talvez algum que mostre os artigos que se encontram à venda
                break;
        }
    }

    public void criarEncomenda(){
        int op = this.gui.menu(" Criar Encomenda ", Menu.menu_Criar_Encomenda);

        switch (op){
            case 1:
                break;

            case 2:
                break;

            case 3:
                this.comprador();
                break;
        }
    }

    public void criarArtigo(){

    }
}