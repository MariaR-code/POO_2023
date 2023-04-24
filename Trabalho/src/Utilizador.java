package Trabalho.src; // isto já vinha aqui. para que serve?
// import java.util.ArrayList; é capaz de ser necessário para p5

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
    /* idk about p5 yet
    private ArrayList<Artigo> artigosParaVenda;
    private ArrayList<Artigo||Encomenda?> artigosAdquiridos;
    private ArrayList<Artigo||Encomenda?> vendasRealizadas;
    */

    public Utilizador(String email, String nome, String morada, String nif, String senha, int tipoUtilizador) {
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.senha = senha;
        this.tipoUtilizador = tipoUtilizador;
        this.valorTotalVendas = 0.0;

        // this.artigosAVenda = new ArrayList<>();
        // this.artigosAdquiridos = new ArrayList<>();
        // this.vendasRealizadas = new ArrayList<>();
    }

    // Métodos de acesso e modificação dos atributos TODO Clones
public String getEmail() {
    return email;
}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() { // dúvida: "return nome;" vs "return this.nome;"
        return nome;          // há problema caso haja outros 'nome' por exemplo em Artigo?
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

    }