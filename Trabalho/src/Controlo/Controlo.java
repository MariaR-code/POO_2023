package Trabalho.src.Controlo;

import Trabalho.src.Modelo.Mercado;
import Trabalho.src.Vista.Insercao;
import Trabalho.src.Vista.Menu;
import Trabalho.src.Erros.NaoExisteUtilizador;

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
                loginVendedor(1);
                break;
            case 2:
                loginVendedor(0);
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                System.out.println("Fim do programa");
                System.exit(0);
                break;
        }
    }

    public void loginVendedor(int tipo) throws NaoExisteUtilizador{
        try{
            String email = Insercao.get_valor("email", this.supplier_String);
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