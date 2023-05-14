package Trabalho.src.Modelo;

public class Tshirt extends Artigo {
    public enum Tamanho{
        S,
        M,
        L,
        XL
    }

    public enum Padrao{
        Liso,
        Riscas,
        Palmeiras
    }

    private Tamanho tamanho;
    private Padrao padrao;

    /**
    * Construtores dos objetos da classe Tshirt
    * */

    /**
     * Construtor por omissão da classe Tshirt
     */
    public Tshirt(){
        super();
        this.tamanho = Tamanho.S;
        this.padrao = Padrao.Liso;
    }

    /**
     * Construtor parametrizado da classe Tshirt.
     * @param tamanho Tamanho -> tamanho do objeto (S ou M ou L ou XL).
     * @param padrao Padrao -> padrão do objeto (Liso ou Riscas ou Palmeiras).
     */
    public Tshirt(Tamanho tamanho, Padrao padrao){
        super();
        this.tamanho = tamanho;
        this.padrao = padrao;
    }

    /**
     * Construtor parametrizado da classe Tshirt.
     * @param descricao String -> breve descrição do objeto da classe Tshirt.
     * @param marca String -> nome da marca do objeto.
     * @param cod_alfanr String -> número alfanumérico associado ao objeto.
     * @param preco_base double -> valor do preço base do objeto.
     * @param transportadora String -> nome da empresa de transporte
     *                                  que está associada ao objeto.
     * @param av_estado int -> avaliação do estado do objeto
     *                          (1 ou 2 ou 3; BOM ou RAZOAVEL ou MAU, respetivamente).
     * @param nr_donos int -> número de donos a que o objeto pertenceu.
     * @param tamanho Tamanho -> tamanho do objeto (S ou M ou L ou XL).
     * @param padrao Padrao -> padrão do objeto (Liso ou Riscas ou Palmeiras).
     */
    public Tshirt(String descricao, String marca, String cod_alfanr, double preco_base,
                  String transportadora, int av_estado, int nr_donos, Tamanho tamanho, Padrao padrao){
        super(descricao, marca, cod_alfanr, preco_base, av_estado, nr_donos, transportadora);
        this.tamanho = tamanho;
        this.padrao = padrao;
    }

    /**
     * Contrutor parametrizado da classe Tshirt.
     * @param descricao String -> breve descrição do objeto da classe Tshirt.
     * @param marca String -> nome da marca do objeto.
     * @param cod_alfanr String -> número alfanumérico associado ao objeto.
     * @param preco_base double -> valor do preço base do objeto.
     * @param transportadora String -> nome da empresa de transporte
     *                                  que está associada ao objeto.
     * @param tamanho Tamanho -> tamanho do objeto (S ou M ou L ou XL).
     * @param padrao Padrao -> padrão do objeto (Liso ou Riscas ou Palmeiras).
     */
    public Tshirt(String descricao, String marca, String cod_alfanr, double preco_base,
                  String transportadora, Tamanho tamanho, Padrao padrao){
        super(descricao, marca, cod_alfanr, preco_base, transportadora);
        this.tamanho = tamanho;
        this.padrao = padrao;
    }

    /**
     * Construtor de cópia da classe Tshirt.
     * @param tshirt Tshirt
     */
    public Tshirt(Tshirt tshirt){
        super(tshirt);
        this.tamanho = tshirt.getTamanho();
        this.padrao = tshirt.getPadrao();
    }

    /**
     * Getters dos objetos da classe Tshirt
     */

    /**
     * Retorna o tamanho do objeto.
     * @return Tamanho
     */
    public Tamanho getTamanho() {
        return tamanho;
    }

    /**
     * Retorna o padrão do objeto.
     * @return Padrao
     */
    public Padrao getPadrao() {
        return padrao;
    }

    /**
     * Setters dos objetos da classe Tshirt.
     */

    /**
     * Defina o tamanho do objeto.
     * @param tamanho Tamanho -> S ou M ou L ou XL
     */
    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * Define o padrão do objeto.
     * @param padrao Padrao -> Liso ou Riscas ou Palmeiras.
     */
    public void setPadrao(Padrao padrao) {
        this.padrao = padrao;
    }

    /**
     * Método para clonar um objeto da classe Tshirt
     * @return Tshirt
     */
    public Tshirt clone(){
        return new Tshirt(this);
    }

    /**
     * Método de verificação de igualdade de dois objetos, sendo um deles da classe Tshirt
     * @param o Object
     * @return boolean -> true se forem iguais ou false se forem diferentes.
     */
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(o==null || o.getClass() != this.getClass()){
            return false;
        }

        if(!super.equals(o)) {
            return false;
        }

        Tshirt tshirt = (Tshirt) o;

        return this.tamanho == tshirt.getTamanho() && this.padrao == tshirt.getPadrao();
    }

    /**
     * Método que apresenta uma representação da forma String do objeto da classe Tshirt
     * @return String
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Tamanho: ").append(this.tamanho);
        sb.append("\nPadrão: ").append(this.padrao);
        return sb.toString();
    }

    /**
     * Método que representa um objeto da classe Tshirt numa string com apenas uma linha
     * @return String
     * */
    public String umalinhaString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Tshirt£");
        sb.append(super.umalinhaString()).append(",");
        sb.append(this.tamanho).append(",");
        sb.append(this.padrao);

        return sb.toString();
    }

    /**
     * Método para obter o preço
     * @return double
     */
    public double preco(){
        double preco;
        if(this.padrao != Padrao.Liso && super.isUsado())
            preco = super.getPreco_base() * 0.5;
        else
            preco = super.getPreco_base();
        return preco;
    }
    /**
     * Método que indica que nenhuma tshirt é premium
     */
    public boolean isPremium(){
        return false;
    }
}