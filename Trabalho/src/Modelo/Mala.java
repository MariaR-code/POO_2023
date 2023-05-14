package Trabalho.src.Modelo;

import java.time.LocalDate;

public class Mala extends Artigo {
    private double altura; //em cm
    private double comprimento; //em cm
    private double profundidade; //em cm
    private String material;
    private int ano_colecao;
    private boolean premium;

    /**
     * Construtores dos objetos da classe Mala
     * */

    /**
     * Construtor por omissão da classe Mala.
     */
    public Mala(){
        super();
        this.altura = 0.0;
        this.profundidade = 0.0;
        this.comprimento = 0.0;
        this.material = "";
        this.ano_colecao = LocalDate.now().getYear();
        this.premium = false;
    }

    /**
     * Construtor parametrizado da classe Mala.
     * @param descricao String -> breve descrição do objeto da classe Mala
     * @param marca String -> marca do objeto da classe Mala.
     * @param cod_alfanr String -> código alfanumérico associado ao objeto da classe Mala.
     * @param preco_base double -> valor do preço base
     * @param transportadora String -> nome da empresa de transporte
     *                                  que está associada ao objeto.
     * @param altura double -> valor da altura do objeto.
     * @param comprimento double -> valor do comprimento do objeto.
     * @param profundidade  double -> valor da profundidade do objeto.
     * @param material  String -> nome do material de que o objeto é feito.
     * @param ano_colecao int -> ano em que a coleção do objeto faz parte.
     * @param premium boolean -> True se for premium e False se não for.
     */
    public Mala(String descricao, String marca, String cod_alfanr, double preco_base,
                String transportadora, double altura, double comprimento, double profundidade,
                String material, int ano_colecao, boolean premium){
        super(descricao, marca, cod_alfanr, preco_base, transportadora);
        this.altura = altura;
        this.comprimento = comprimento;
        this.profundidade = profundidade;
        this.material = material;
        this.ano_colecao = ano_colecao;
        this.premium = premium;
    }

    /**
     * Contrutor parametrizado da classe Mala.
     * @param descricao String -> breve descrição do objeto da classe Mala
     * @param marca String -> marca do objeto da classe Mala.
     * @param cod_alfanr String -> código alfanumérico associado ao objeto da classe Mala.
     * @param preco_base double -> valor do preço base.
     * @param transportadora String -> nome da empresa de transporte
     *                                  que está associada ao objeto.
     * @param av_estado int -> avaliação do estado do objeto
     *                          (1 ou 2 ou 3. BOM ou RAZOAVEL ou MAU respetivamente).
     * @param nr_donos int -> número de donos que o objeto pertenceu.
     * @param altura double -> valor da altura do objeto.
     * @param comprimento double -> valor do comprimento do objeto.
     * @param profundidade double -> valor da profundidade do objeto.
     * @param material String -> nome do material de que o objeto é feito.
     * @param ano_colecao int -> ano em que a coleção do objeto faz parte.
     * @param premium boolean -> True se for premium e False se não for.
     */
    public Mala(String descricao, String marca, String cod_alfanr, double preco_base,
                String transportadora, int av_estado, int nr_donos, double altura, double comprimento,
                double profundidade, String material, int ano_colecao, boolean premium){
        super(descricao, marca, cod_alfanr, preco_base, av_estado, nr_donos, transportadora);
        this.altura = altura;
        this.comprimento = comprimento;
        this.profundidade = profundidade;
        this.material = material;
        this.ano_colecao = ano_colecao;
        this.premium = premium;
    }

    /**
     * Construtor de cópia da classe Mala.
     * @param mala Mala
     */
    public Mala(Mala mala){
        super(mala);
        this.altura = mala.getAltura();
        this.comprimento = mala.getComprimento();
        this.profundidade = mala.getProfundidade();
        this.material = mala.getMaterial();
        this.ano_colecao = mala.getAno_colecao();
        this.premium = mala.isPremium();
    }

    /**
     * Getters dos objetos da classe Mala
     * */

    /**
     * Retorna a altura do objeto.
     * @return double
     */
    public double getAltura() {
        return altura;
    }

    /**
     * Retorna o comprimento do objeto.
     * @return double
     */
    public double getComprimento() {
        return comprimento;
    }

    /**
     * Retorna a profundidade do objeto.
     * @return double
     */
    public double getProfundidade() {
        return profundidade;
    }

    /**
     * Retorna o nome do material que o objeto é feito.
     * @return String
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Retorna o ano da coleção em que o objeto pertence.
     * @return int
     */
    public int getAno_colecao() {
        return ano_colecao;
    }

    /**
     * Retorna true o se for um objeto premium ou false se não for o caso.
     * @return boolean
     */
    public boolean isPremium() {
        return premium;
    }

    /**
     * Setters dos objetos da classe Mala
     * */

    /**
     * Define a altura do objeto.
     * @param altura double
     */
    public void setAltura(double altura) {
        this.altura = altura;
    }

    /**
     * Define o comprimento do objeto.
     * @param comprimento double
     */
    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    /**
     * Define a profundidade do objeto.
     * @param profundidade double
     */
    public void setProfundidade(double profundidade) {
        this.profundidade = profundidade;
    }

    /**
     * Define o material que o objeto é feito.
     * @param material String
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * Define o ano da coleção em que objeto pertence.
     * @param ano_colecao int
     */
    public void setAno_colecao(int ano_colecao) {
        this.ano_colecao = ano_colecao;
    }

    /**
     * Define se o objeto é Premium
     * @param premium boolean
     */
    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    /**
     * Método para clonar um objeto da classe Mala
     * @return Mala
     */
    public Mala clone(){
        return new Mala(this);
    }

    /**
     * Método de verificação de igualdade de dois objetos, sendo um deles da classe Mala
     * @param o Object
     * @return boolean -> true se for igual ou false se for diferente
     */
    public boolean equals(Object o){
        if(o==this)
            return true;

        if(o==null || o.getClass() != this.getClass())
            return false;

        if(!super.equals(o))
            return false;

        Mala mala = (Mala) o;
        return this.altura == mala.getAltura() && this.comprimento == mala.getComprimento() &&
                this.profundidade == mala.getProfundidade() && this.material.equals(mala.getMaterial()) &&
                this.ano_colecao == mala.getAno_colecao() &&
                this.premium == mala.isPremium();
    }

    /**
     * Método que apresenta uma representação da forma String do objeto da classe Mala
     * @return String
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nDimensão da mala: ").append(this.altura).append("x").append(this.comprimento).append("x").append(this.profundidade);
        sb.append("\nMaterial: ").append(this.material);
        sb.append("\nAno da Coleção: ").append(this.ano_colecao);
        if(this.premium)
            sb.append("\nA mala é premium.");
        return sb.toString();
    }

    /**
     * Método que representa um objeto da classe Mala numa string com apenas uma linha
     * @return String
     * */
    public String umalinhaString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Mala£");
        sb.append(super.umalinhaString()).append(",");
        sb.append(this.altura).append(",");
        sb.append(this.comprimento).append(",");
        sb.append(this.profundidade).append(",");
        sb.append(this.material).append(",");
        sb.append(this.ano_colecao).append(",");
        sb.append(this.premium);

        return sb.toString();
    }

    /**
     * Método para obter o preço.
     * @return double -> preço do objeto.
     */
    public double preco(){
        double preco;
        if(super.isUsado()) {
            double volume = this.altura * this.comprimento * this.profundidade;
            preco = super.getPreco_base() - (super.getPreco_base() / (volume/10*super.getNr_donos()+super.getAv_estado()));
        }
        else if(this.premium) {
            double volume = this.altura * this.comprimento * this.profundidade;
            double valoracao;
            if(volume < 1000)
                valoracao = super.getPreco_base() * 0.15;
            else if(volume < 2205)
                valoracao = super.getPreco_base() * 0.20;
            else
                valoracao = super.getPreco_base() * 0.25;
            preco = super.getPreco_base() +
                    (valoracao * (LocalDate.now().getYear() - this.ano_colecao));
        }
        else preco = super.getPreco_base();
        return preco;
    }
}
