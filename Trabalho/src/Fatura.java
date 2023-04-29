//package Trabalho.src;

public class Fatura {
    private Encomenda enc;
    private double custo;
    private String nif;

    /**
     * Construtores da classe Fatura.
     * Declaração dos construtores por omissão (vazio),
     * parametrizado e cópia.
     */

    /**
     * Construtor por omissão de Fatura.
     */
    public Fatura(){
        this.enc = new Encomenda();
        this.custo = 0;
        this.nif = "n/a";
    }

    /**
     * Construtor parametrizado de Fatura.
     */
    public Fatura(Encomenda enc_, double custo_, String nif_){
        this.enc = enc_.clone();
        this.custo = custo_;
        this.nif = nif_;
    }

    /**
     * Construtor de cópia de Fatura.
     */
    public Fatura(Fatura f){
        this.enc = f.getEnc();
        this.custo = f.getCusto();
        this.nif = f.getNif();
    }

    /**
     * Métodos de instância da classe Fatura.
     */

    /**
     * Getters dos objetos da classe Fatura.
     */
    public Encomenda getEnc() {
        return this.enc.clone();
    }

    public double getCusto() {
        return this.custo;
    }

    public String getNif() {
        return this.nif;
    }

    /**
     * Setters dos objetos da classe Fatura.
     */
    public void setEnc(Encomenda enc) {
        this.enc = enc;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Método clone que faz uma cópia do objeto,
     * utilizando o contrutor de cópia.
     */
    public Fatura clone(){
        return new Fatura(this);
    }

    /**
     * Método equals que compara e verifica
     * se os objetos em questão são iguais.
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Fatura f = (Fatura) o;
        return (this.enc.equals(f.getEnc()) && this.custo == f.getCusto()
                && this.nif.equals(f.getNif()));
    }

    /**
     * Método toString que devolve a representação em String da Fatura.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Fatura: ");
        sb.append("<<<<<<<<<<");
        sb.append("NIF: ").append(this.nif);
        sb.append("Encomenda: ").append(this.enc.toString());
        sb.append("Custo: ").append(this.custo);
        sb.append(">>>>>>>>>>");
        return sb.toString();
    }
}
