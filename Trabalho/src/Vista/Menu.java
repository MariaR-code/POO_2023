package Trabalho.src.Vista;

public class Menu {
    public static final String[] menu_Principal_Vintage = {
            "Vendedor",
            "Comprador",
            "Criar conta"
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
}
