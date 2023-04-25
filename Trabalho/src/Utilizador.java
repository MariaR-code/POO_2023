package Trabalho.src;
import java.util.ArrayList;

/* Notepad::
Cada utilizador --> Guarda a informação relativa ao seu código no sistema (fornecido automaticamente pelo sistema)
		p1 Email | p2 Nome | p3 Morada | p4 NIF

		p5 Toda a informação sobre produtos que tem à venda, que já vendeu, bem como os que já adquiriu
		Guardar a informação sobre as vendas que foram efectuadas e o valor que delas resultou.

		p6 Note que um mesmo utilizador poderá actuar como vendedor e como comprador.
	 -------------------------------------------------------------------------------------------------------------------
	 Pensei em adicionar uma senha ao login (não estritamente necessário para a simulação mas nunca vi logins sem senha)
 */

public class Utilizador {
    private String email;
    private String nome;
    private String morada;
    private String nif;
    private int tipoUtilizador; // 0: comprador, 1: vendedor, 2: ambos
    private double valorTotalVendas;
    // private ArrayList<Artigo> artigosParaVenda;
    // private ArrayList<Artigo> artigosVendidos;
    // private ArrayList<Artigo> artigosComprados;


    /**
     * Contrutores dos objetos da classe Utilizador
     * */
    public Utilizador(String email, String nome, String morada, String nif, int tipoUtilizador) {
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.tipoUtilizador = tipoUtilizador;
        this.valorTotalVendas = 0.0;
        // this.artigosParaVenda = new ArrayList<Artigo>();
        // this.artigosVendidos = new ArrayList<Artigo>();
        // this.artigosComprados = new ArrayList<Artigo>();
    }


    /**
     * Construtor de cópia de Utilizador.
     */
    public Utilizador(Utilizador utilizador) {
        this.email = utilizador.getEmail();
        this.nome = utilizador.getNome();
        this.morada = utilizador.getMorada();
        this.nif = utilizador.getNif();
        this.tipoUtilizador = utilizador.getTipoUtilizador();
        this.valorTotalVendas = utilizador.getValorTotalVendas();
    }

    /**
     * Getters dos objetos da classe Artigo
     * */
    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getMorada() {
        return morada;
    }

    public String getNif() {
        return nif;
    }

    public int getTipoUtilizador() {
        return tipoUtilizador;
    }

    public double getValorTotalVendas() {
        return  valorTotalVendas;
    }
/*
    public ArrayList<Artigo> getArtigosVendidos() {
        return artigosVendidos;
    }

    public ArrayList<Artigo> getArtigosComprados() {
        return artigosComprados;
    }

    public ArrayList<Artigo> getArtigosParaVenda() {
        return artigosParaVenda;
    }
*/
    /**
     * Setters dos objetos da classe Utilizador
     * */
    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setTipoUtilizador(int tipoUtilizador) {
        if (tipoUtilizador < 0 || tipoUtilizador > 2) {
            throw new IllegalArgumentException("Valor inválido para o tipo de utilizador");
        }
        this.tipoUtilizador = tipoUtilizador;
    }


/*
    public void adicionarArtigoParaVenda(Artigo artigo) {
        this.artigosParaVenda.add(artigo);
    }

    public void removerArtigoParaVenda(Artigo artigo) {
        this.artigosParaVenda.remove(artigo);
    }

    public void adicionarArtigoVendido(Artigo artigo, double preco) {
        this.artigosVendidos.add(artigo);
        this.valorTotalVendas += preco;
    }

    public void removerArtigoVendido(Artigo artigo, double preco) {
        this.artigosVendidos.remove(artigo);
        this.valorTotalVendas -= preco;
    }

    public void adicionarArtigoComprado(Artigo artigo) {
        this.artigosComprados.add(artigo);
    }
*/

    /**
     * Método para clonar um objeto da classe Utilizador
     * */
    public Utilizador clone(){
        return new Utilizador(this);
    }

    /**
     * Método de verificação de igualdade de dois objetos, sendo um deles da classe Utilizador
     * */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Utilizador user = (Utilizador) o;
        return this.email.equals(user.getEmail()) &&
                this.nome.equals(user.getNome()) &&
                this.morada.equals(user.getMorada()) &&
                this.nif.equals(user.getNif()) &&
                this.tipoUtilizador == user.getTipoUtilizador() &&
                this.valorTotalVendas == user.getValorTotalVendas();
    }


    public String toString() {
        String tipo = "";
        if (tipoUtilizador == 0) {
            tipo = "Comprador";
        } else if (tipoUtilizador == 1) {
            tipo = "Vendedor";
        } else if (tipoUtilizador == 2) {
            tipo = "Ambos";
        }
        return "Utilizador{" +
                "Email='" + email + '\'' +
                ", Nome='" + nome + '\'' +
                ", Morada='" + morada + '\'' +
                ", NIF='" + nif + '\'' +
                ", Tipo de utilizador=" + tipo +
                ", Valor total de vendas=" + valorTotalVendas + // faz sentido mostrar este para o tipo Comprador?
               // ", Artigos para venda=" + artigosParaVenda +
               // ", Artigos Vendidos=" + artigosVendidos +
               // ", Artigos Comprados=" + artigosComprados +
                '}';
    }

}