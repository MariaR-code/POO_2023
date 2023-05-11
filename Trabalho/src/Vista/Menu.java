package Trabalho.src.Vista;

public class Menu {
    public static final String[] menu_Principal_Vintage = {
            "Vendedor",
            "Comprador",
            "Criar conta",
            "Transportadoras da Vintage",
            "Salvaguardar o estado",
            "Recuperar o estado"
    };
    public static final String[] menu_Log_In = {
            "Log In"
    };
    public static final String[] menu_Vendedor = {
            "Adicionar artigo",
            "Histórico de vendas", //Necessário para ter 16 valores
            "Ver artigos à venda"  // remover?
    };
    public static final String[] menu_Adicionar_Artigo = {
            "Vestuário", //Se calhar era melhor ser Tshirt, sapatilhas e mala
            "Calçado",
            "Acessórios"
    };
    public static final String[] menu_Comprador = {
            "Gerir encomenda",
            "Histórico de encomendas" //Podemos fazer, acho que não custa muito. Não ser prioridade
    };
    public static final String[] menu_Criar_Encomenda = {
            "Adicionar artigo",
            "Remover artigo",
            "Finalizar encomenda"
    };
    public static final String[] menu_Transportadoras = {
            "Associar nova transportadora",
            "Ver transportadoras atualmente associadas"
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

    /**
     * Método que notifica o utilizador que determinada ação foi realizada
     * @param mensagem É a informação que o controlo quer passar ao utilizador
     * */
    public static void mostraMensagem(String mensagem){
        System.out.println("\n" + mensagem);
    }
}
