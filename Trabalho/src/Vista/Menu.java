package Trabalho.src.Vista;

public class Menu {
    public static final String[] menu_Principal_Vintage = {
            "Log In - Vendedor",
            "Log In - Comprador",
            "Criar conta",
            "Associar nova transportadora à Vintage",
            "Salvaguardar o estado",
            "Recuperar o estado"
    };

    /**
     * Método que vai mostrar o menu e devolve a opção escolhida pelo utilizador
     * @param nome Vai ser o título do menu apresentado
     * @param opcoes São as diferentes opções apresentadas ao utilizador
     * @return int que é a opção escolhida
     * */
    public int menu(String nome, String[] opcoes){
        Mostrar mostrar_menu = new Mostrar(nome, opcoes);
        mostrar_menu.executa();
        System.out.println("\n");

        return mostrar_menu.getOpcao();
    }

    /**
     * Método que imprime no ecrã uma mensagem de erro
     * @param msg É a mensagem de erro
     * */
    public static void erro(String msg){
        System.out.println("\nErro: " + msg);
    }
}
