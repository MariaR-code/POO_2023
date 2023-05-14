package Trabalho.src.Modelo;

import java.time.LocalDate;

public class Sapatilhas extends Artigo {
    private int tamanho;
    private boolean atacadores;
    private String cor;
    private LocalDate data_lancamento;
    private boolean premium;

    /**
    * Construtores dos objetos da classe Sapatilhas
    * */

    /**
     * Construtor por omissão da classe Sapatilhas.
     */
    public Sapatilhas(){
        super();
        this.tamanho = 38;
        this.atacadores = true;
        this.cor = "";
        this.data_lancamento = LocalDate.now();
        this.premium = false;
    }

    /**
     * Construtor parametrizado da classe Sapatilhas.
     * @param descricao String -> breve descrição do objeto da classe Sapatilhas.
     * @param marca String -> nome da marca do objeto.
     * @param cod_alfanr String -> número alfanumérico associado ao objeto.
     * @param preco_base double -> valor do preço base do objeto.
     * @param transportadora String -> nome da empresa de transporte
     *                                  que está associada ao objeto.
     * @param tamanho int -> tamanho do objeto.
     * @param atacadores boolean -> true se tiver atacadores ou false se não tiver.
     * @param cor String -> cor do objeto.
     * @param data_lancamento LocalDate -> data de lançamento do objeto.
     * @param premium boolean -> true se for premium ou false se não for.
     */
    public Sapatilhas(String descricao, String marca, String cod_alfanr, double preco_base,
                      String transportadora,  int tamanho, boolean atacadores, String cor,
                      LocalDate data_lancamento, boolean premium){
        super(descricao, marca, cod_alfanr, preco_base, transportadora);
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.data_lancamento = data_lancamento;
        this.premium = premium;
    }

    /**
     * Contrutor parametrizado da classe Sapatilhas.
     * @param descricao String -> breve descrição do objeto da classe Sapatilhas.
     * @param marca String -> nome da marca do objeto.
     * @param cod_alfanr String -> número alfanumérico associado ao objeto.
     * @param preco_base double -> valor do preço base do objeto.
     * @param transportadora String -> nome da empresa de transporte
     *                                 que está associada ao objeto.
     * @param av_estado int -> avaliação do estado do objeto
     *                          (1 ou 2 ou 3; BOM ou RAZOAVEL ou MAU, respetivamente).
     * @param nr_donos int -> número de donos que o objeto pertenceu.
     * @param tamanho int -> tamanho do objeto.
     * @param atacadores boolean -> true se tiver atacadores ou false se não tiver.
     * @param cor String -> cor do objeto.
     * @param data_lancamento LocalDate -> data de lançamento do objeto.
     * @param premium boolean -> true se for premium ou false se não for.
     */
    public Sapatilhas(String descricao, String marca, String cod_alfanr, double preco_base,
                      String transportadora, int av_estado, int nr_donos, int tamanho, boolean atacadores, String cor,
                      LocalDate data_lancamento, boolean premium){
        super(descricao, marca, cod_alfanr, preco_base, av_estado, nr_donos, transportadora);
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.data_lancamento = data_lancamento;
        this.premium = premium;
    }

    /**
     * Construtor de cópia da classe Sapatilhas.
     * @param sapatilhas Sapatilhas
     */
    public Sapatilhas(Sapatilhas sapatilhas){
        super(sapatilhas);
        this.tamanho = sapatilhas.getTamanho();
        this.atacadores = sapatilhas.hasAtacadores();
        this.cor = sapatilhas.getCor();
        this.data_lancamento = sapatilhas.getData_lancamento();
        this.premium = sapatilhas.isPremium();
    }

    /**
     * Getters dos objetos da classe Sapatilhas
     */

    /**
     * Retorna o tamanho do objeto.
     * @return int
     */
    public int getTamanho() {
        return tamanho;
    }

    /**
     * Retorna se o objeto tem atacadores ou não.
     * @return boolean -> true se tiver atacadores ou false se não tiver.
     */
    public boolean hasAtacadores() {
        return atacadores;
    }

    /**
     * Retorna a cor do objeto.
     * @return String
     */
    public String getCor() {
        return cor;
    }

    /**
     * Retorna a data de lançamento do objeto.
     * @return LocalDate
     */
    public LocalDate getData_lancamento() {
        return data_lancamento;
    }

    /**
     * Retorna se o objeto é Premium ou não.
     * @return boolean -> true se for Premium ou false se não for.
     */
    public boolean isPremium() {
        return premium;
    }

    /**
    * Setters dos objetos da classe Sapatilhas
    * */

    /**
     * Define o tamanho do objeto.
     * @param tamanho int
     */
    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * Define se o objeto tem atacadores.
     * @param atacadores boolean -> true se tiver atacadores ou false se não tiver atacadores.
     */
    public void setAtacadores(boolean atacadores) {
        this.atacadores = atacadores;
    }

    /**
     * Define a cor do objeto.
     * @param cor String
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * Define a data de lançamento do objeto.
     * @param data_lancamento LocalDate
     */
    public void setData_lancamento(LocalDate data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    /**
     * Define se o objeto é Premium.
     * @param premium -> boolean true se for Premium ou false se não for.
     */
    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    /**
     * Método para clonar um objeto da classe Sapatilhas
     * @return Sapatilhas
     */
    public Sapatilhas clone(){
        return new Sapatilhas(this);
    }

    /**
     * Método de verificação de igualdade de dois objetos, sendo um deles da classe Sapatilhas
     * @param o Object
     * @return boolean -> true se forem iguais ou false se forem diferentes.
     */
    public boolean equals(Object o){
        if(o==this)
            return true;
        if(o==null || o.getClass() != this.getClass())
            return false;

        if(!super.equals(o))
            return false;

        Sapatilhas s = (Sapatilhas) o;
        return this.tamanho == s.getTamanho() && this.atacadores == s.hasAtacadores() &&
                this.cor.equals(s.getCor()) && this.data_lancamento.equals(s.getData_lancamento())
                && this.premium == s.isPremium();
    }

    /**
     * Método que apresenta uma representação da forma String do objeto da classe Sapatilhas
     * @return String
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nTipo de Artigo: Sapatilhas");
        sb.append("\nTamanho numérico: ").append(this.tamanho);
        sb.append("\nCor: ").append(this.cor);
        sb.append("\nData de Lançamento da coleção: ").append(this.data_lancamento);
        if(this.atacadores)
            sb.append("\nTem atacadores/atilhos");
        if(this.premium)
            sb.append("\nSão sapatilhas Premium");
        return sb.toString();
    }

    /**
     * Método que representa um objeto da classe Sapatilhas numa string com apenas uma linha
     * @return String
     * */
    public String umalinhaString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Sapatilhas£");
        sb.append(super.umalinhaString()).append(",");
        sb.append(this.tamanho).append(",");
        sb.append(this.atacadores).append(",");
        sb.append(this.cor).append(",");
        sb.append(this.data_lancamento).append(",");
        sb.append(this.premium);

        return sb.toString();
    }

    /**
     * Método para obter o preço
     * @return double
     */
    public double preco(){
        double preco;
        if(super.isUsado() || this.tamanho >= 45)
            preco = super.getPreco_base() - (super.getPreco_base() / (super.getNr_donos() * super.getAv_estado()));
        else if(this.premium) {
            double aumento = super.getPreco_base() * 0.20;
            preco = super.getPreco_base() +
                    (aumento * (LocalDate.now().getYear() - this.data_lancamento.getYear()));
        }else
            preco = super.getPreco_base();
        return preco;
    }
}