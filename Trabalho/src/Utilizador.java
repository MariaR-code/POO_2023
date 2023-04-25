// package Trabalho.src; // isto já vinha aqui. para que serve?
import java.util.ArrayList;
/* Notepad::
Cada utilizador --> Guarda a informação relativa ao seu código no sistema (fornecido automaticamente pelo sistema)
		p1 Email | p2 Nome | p3 Morada | p4 NIF

		p5 Deverá ser guardada toda a informação sobre produtos que tem à venda, que já vendeu, bem como os que já adquiriu
		É suposto o sistema guardar a informação sobre as vendas que foram efectuadas e o valor que delas resultou.

		p6 Note que um mesmo utilizador poderá actuar como vendedor e como comprador.
	 -------------------------------------------------------------------------------------------------------------------
	 Pensei em adicionar uma senha ao login (não estritamente necessário para a simulação mas nunca vi logins sem senha)

 */

public class Utilizador {
    private String email;
    private String nome;
    private String morada;
    private String nif; //String porque não vamos utilizar NIF para cálculos certo?
    private String senha;
    private int tipoUtilizador; // 0: comprador, 1: vendedor, 2: ambos
    private double valorTotalVendas;
    private ArrayList<Artigo> artigosParaVenda;
    private ArrayList<Artigo> artigosVendidos;
    private ArrayList<Artigo> artigosComprados;

    public Utilizador(String email, String nome, String morada, String nif, String senha, int tipoUtilizador) {
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.senha = senha;
        this.tipoUtilizador = tipoUtilizador;
        this.valorTotalVendas = 0.0;
        this.artigosParaVenda = new ArrayList<Artigo>();
        this.artigosVendidos = new ArrayList<Artigo>();
        this.artigosComprados = new ArrayList<Artigo>();
    }

    // Métodos de acesso e modificação dos atributos TODO Clone
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() { // dúvida: "return nome;" vs "return this.nome;"
        return nome;          // há problema caso haja outros 'nome'?
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public int getTipoUtilizador() {
        return tipoUtilizador;
    }

    public void setTipoUtilizador(int tipoUtilizador) {
        if (tipoUtilizador < 0 || tipoUtilizador > 2) {
            throw new IllegalArgumentException("Valor inválido para o tipo de utilizador");
        }
        this.tipoUtilizador = tipoUtilizador;
    }

    public ArrayList<Artigo> getArtigosParaVenda() {
        return artigosParaVenda;
    }

    public ArrayList<Artigo> getArtigosVendidos() {
        return artigosVendidos;
    }

    public ArrayList<Artigo> getArtigosComprados() {
        return artigosComprados;
    }

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
                ", Senha='" + senha + '\'' +
                ", Tipo de Utilizador=" + tipo +
                ", valorTotalVendas=" + valorTotalVendas + // faz sentido mostrar este para o tipo Comprador?
               // ", artigosParaVenda=" + artigosParaVenda +
               // ", artigosVendidos=" + artigosVendidos +
               // ", artigosComprados=" + artigosComprados +
                '}';
    }

}