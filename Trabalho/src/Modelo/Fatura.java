package Trabalho.src.Modelo;

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
     * Construtor parametrizado da classe Fatura.
     * @param enc_ Encomenda
     * @param custo_ double
     * @param nif_ String -> número fiscal do utilizador.
     */
    public Fatura(Encomenda enc_, double custo_, String nif_){
        this.enc = enc_.clone();

        this.nif = nif_;
    }

    /**
     * Construtor de cópia de Fatura.
     * @param f Fatura
     */
    public Fatura(Fatura f){
        this.enc = f.getEnc();
        this.custo = f.getCusto();
        this.nif = f.getNif();
    }

    /**
     * Getters dos objetos da classe Fatura.
     */

    /**
     * Retorna a encomenda.
     * @return Encomenda
     */
    public Encomenda getEnc() {
        return this.enc.clone();
    }

    /**
     * Retorna o custo.
     * @return double
     */
    public double getCusto() {
        return this.custo;
    }

    /**
     * Retorna o número fiscal do utilizador.
     * @return String
     */
    public String getNif() {
        return this.nif;
    }

    /**
     * Setters dos objetos da classe Fatura.
     */

    /**
     * Define a encomenda.
     * @param enc Encomenda
     */
    public void setEnc(Encomenda enc) {
        this.enc = enc;
    }

    /**
     * Define o custo.
     * @param custo double
     */
    public void setCusto(double custo) {
        this.custo = custo;
    }

    /**
     * Define o número fiscal do utilizador.
     * @param nif String
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Método clone que faz uma cópia do objeto,
     * utilizando o contrutor de cópia.
     * @return Fatura
     */
    public Fatura clone(){
        return new Fatura(this);
    }

    /**
     * Método equals que compara e verifica
     * se os objetos em questão são iguais.
     * @param o Object
     * @return boolean -> true se forem iguais ou false se forem diferentes.
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
     * @return String
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nFatura: ");
        sb.append("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        sb.append("\nNIF: ").append(this.nif);
        sb.append("\nEncomenda: ").append(this.enc.toString());
        sb.append("\nCusto: ").append(this.custo);
        sb.append("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return sb.toString();
    }

    /**
     * Método que representa um objeto da classe Fatura numa string com apenas uma linha
     * @return String
     * */
    public String umalinhaString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Fatura§");
        sb.append(this.enc.umalinhaString()).append("%");
        sb.append(this.custo).append(",");
        sb.append(this.nif);

        return sb.toString();
    }

    /**
     * Método que calcula o custo.
     * @return double
     */
    public double calcCusto(){
        return this.custo = 0.0;
    }
}
