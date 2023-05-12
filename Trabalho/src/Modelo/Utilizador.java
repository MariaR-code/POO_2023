package Trabalho.src.Modelo;
/* Notepad::
Cada utilizador --> Guarda a informação relativa ao seu código no sistema (fornecido automaticamente pelo sistema)
		p1 Email | p2 Nome | p3 Morada | p4 NIF

		p5 Toda a informação sobre produtos que tem à venda, que já vendeu, bem como os que já adquiriu
		Guardar a informação sobre as vendas que foram efectuadas e o valor que delas resultou.

		p6 Note que um mesmo utilizador poderá actuar como vendedor e como comprador.

		p7 Fazer um ID incremental
 */

import java.util.ArrayList;
import java.util.List;

public class Utilizador {
    private static int idCount = 0; // pertence à própria classe, em vez de pertencer a uma instância da classe. Partilhada por todas as instâncias da classe
    private int id;
    private String email;
    private String nome;
    private String morada;
    private String nif;
    private int tipoUtilizador; // 0: comprador, 1: vendedor, 2: ambos
    private double valorTotalVendas;
    private List<Fatura> faturaVendedor;
    private List<Fatura> faturaComprador;


    /**
     * Contrutores dos objetos da classe Utilizador
     * */
    public Utilizador(String email, String nome, String morada, String nif, int tipoUtilizador) {
        this.id = ++idCount;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.tipoUtilizador = tipoUtilizador;
        this.valorTotalVendas = 0.0;
        this.faturaVendedor = new ArrayList<>();
        this.faturaComprador = new ArrayList<>();
    }

    public Utilizador(int id, String email, String nome, double valorTotalVendas, String morada,
                      String nif, int tipoUtilizador) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.tipoUtilizador = tipoUtilizador;
        this.valorTotalVendas = valorTotalVendas;
        this.faturaVendedor = new ArrayList<>();
        this.faturaComprador = new ArrayList<>();

        if(id>idCount)
            idCount = id;
    }

    /**
     * Construtor por omissão de Utilizador.
     */
    public Utilizador() {
        this.id = ++idCount;
        this.email = "";
        this.nome = "";
        this.morada = "";
        this.nif = "";
        this.tipoUtilizador = 0;
        this.valorTotalVendas = 0.0;
        this.faturaVendedor = new ArrayList<>();
        this.faturaComprador = new ArrayList<>();
}

    /**
     * Construtor de cópia de Utilizador.
     */
    public Utilizador(Utilizador utilizador) {
        this.id = utilizador.getId();
        this.email = utilizador.getEmail();
        this.nome = utilizador.getNome();
        this.morada = utilizador.getMorada();
        this.nif = utilizador.getNif();
        this.tipoUtilizador = utilizador.getTipoUtilizador();
        this.valorTotalVendas = utilizador.getValorTotalVendas();
        this.faturaVendedor = utilizador.getFaturaVendedor();
        this.faturaComprador = utilizador.getFaturaComprador();
    }

    /**
     * Getters dos objetos da classe Artigo
     * */
    public int getId() {
        return id;
    }

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

    public List<Fatura> getFaturaVendedor() {
        return faturaVendedor;
    }

    public List<Fatura> getFaturaComprador() {
        return faturaComprador;
    }

    /**
     * Setters dos objetos da classe Utilizador
     * */
    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        }
        else throw new IllegalArgumentException("Valor inválido para email.");

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setNif(String nif) {
        if (isValidNIF(nif)) {
            this.nif = nif;
        } else {
            throw new IllegalArgumentException("Valor inválido para NIF.");
        }
    }


    public void setTipoUtilizador(int tipoUtilizador) {
        if (tipoUtilizador < 0 || tipoUtilizador > 2) {
            throw new IllegalArgumentException("Valor inválido para o tipo de utilizador.");
        }
        this.tipoUtilizador = tipoUtilizador;
    }

    public void setFaturaVendedor(List<Fatura> faturaVendedor) {
        this.faturaVendedor = faturaVendedor;
    }

    public void setFaturaComprador(List<Fatura> faturaComprador) {
        this.faturaComprador = faturaComprador;
    }

    /**
    * Métodos que adicionam ou removem uma fatura de uma encomenda.
     */

    public void addFaturaVendedor(Fatura fatura) {
        this.faturaVendedor.add(fatura.clone());
    }

    public void removeFaturaVendedor(Fatura fatura) {
        this.faturaVendedor.remove(fatura);
    }

    public void addFaturaComprador(Fatura fatura) {
        this.faturaComprador.add(fatura.clone());
    }

    public void removeFaturaComprador(Fatura fatura) {
        this.faturaComprador.remove(fatura);
    }


// todo
//  falta Valor total de vendas
//  somar faturaVendedor

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
        return  this.id == user.getId() &&
                this.email.equals(user.getEmail()) &&
                this.nome.equals(user.getNome()) &&
                this.morada.equals(user.getMorada()) &&
                this.nif.equals(user.getNif()) &&
                this.tipoUtilizador == user.getTipoUtilizador() &&
                this.valorTotalVendas == user.getValorTotalVendas() &&
                this.faturaVendedor.equals(user.getFaturaVendedor()) &&
                this.faturaComprador.equals(user.getFaturaComprador());
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Utilizador{");
        sb.append("ID='").append(this.id).append("',\n");
        sb.append("Email='").append(this.email).append("',\n");
        sb.append("Nome='").append(this.nome).append("',\n");
        sb.append("Morada='").append(this.morada).append("',\n");
        sb.append("NIF='").append(this.nif).append("',\n");

        String tipo = "";
        if (tipoUtilizador == 0) {
            tipo = "Comprador";
        } else if (tipoUtilizador == 1) {
            tipo = "Vendedor";
        } else if (tipoUtilizador == 2) {
            tipo = "Ambos";
        }

        sb.append("Tipo de utilizador='").append(tipo).append("',\n");
        sb.append("Valor total de vendas='").append(this.valorTotalVendas).append("',\n");
        sb.append("Faturas das Vendas='").append(this.faturaVendedor.toString()).append("',\n"); // não garanto nada deste
        sb.append("Faturas das Compras='").append(this.faturaComprador.toString()).append("'");  // ou deste
        sb.append("}");
        return sb.toString();
    }

    /**
     * Método que representa um objeto da classe Utilizador numa string com apenas uma linha
     * @return String
     * */
    public String umalinhaString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Utilizador:");
        sb.append(this.id).append(",");
        sb.append(this.email).append(",");
        sb.append(this.nome).append(",");
        sb.append(this.morada).append(",");
        sb.append(this.nif).append(",");
        sb.append(this.tipoUtilizador).append(",");
        sb.append(this.valorTotalVendas).append(",");
        sb.append("FaturasVendedor").append(",");
        for(Fatura fatura : this.faturaVendedor)
            sb.append(fatura.umalinhaString()).append(",");
        //sb.deleteCharAt(sb.length() - 1);

        sb.append("FaturasComprador").append(",");
        for(Fatura fatura : this.faturaComprador)
            sb.append(fatura.umalinhaString()).append(",");
        sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }

    // Validações de atributos
    public static boolean isValidEmail(String email) {
        if (email == null || email.length() < 5) { // "a@b.c" pelo menos length 5
            return false;
        }
        int count = 0;
        for (int i = 0; i < email.length(); i++) { // verifica se existe mais de 1 '@'
            if (email.charAt(i) == '@') {
                count++;
            }
        }
        if (count != 1) {
            return false;
        }

        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');

        return (atIndex > 0 && dotIndex > atIndex + 1 && dotIndex < email.length() - 1);
        }

    public static boolean isValidNIF(String nif) { // não nulo, com 9 digitos (0-9)
        if (nif == null || nif.length() != 9) {
            return false;
        }
        for (int i = 0; i < nif.length(); i++) {
            char c = nif.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

}
