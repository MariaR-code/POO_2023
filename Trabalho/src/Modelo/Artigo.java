package Trabalho.src.Modelo;

public abstract class Artigo {
    public static final int MAU = 3;
    public static final int RAZOAVEL = 2;
    public static final int BOM = 1;
    private String descricao;
    private String marca;
    private String cod_alfanr;
    private double preco_base;
    private boolean usado;
    private int av_estado;
    private int nr_donos;
    private String transportadora;

    /**
    * Contrutores dos objetos da classe Artigo
    * */

    /**
     * Construtor por omissão da classe Artigo
     */
    public Artigo(){
        this.descricao = "";
        this.marca = "";
        this.cod_alfanr = "";
        this.preco_base = 0.0;
        this.usado = false;
        this.av_estado = 0;
        this.nr_donos = 0;
        this.transportadora = "";
    }

    /**
     * Construtor parametrizado da classe Artigo
     * @param descricao String -> uma breve descrição do artigo.
     * @param marca String -> marca do artigo.
     * @param cod_alfanr String -> código alfanumérico do artigo.
     * @param preco_base double -> preço base do artigo.
     * @param transportadora String -> nome da empresa de transporte
     *                                  que está associada ao artigo
     */
    public Artigo(String descricao, String marca, String cod_alfanr, double preco_base,
                  String transportadora){
        this.descricao = descricao;
        this.marca = marca;
        this.cod_alfanr = cod_alfanr;
        this.preco_base = preco_base;
        this.usado = false;
        this.av_estado = 0;
        this.nr_donos = 0;
        this.transportadora = transportadora;
    }

    /**
     * Construtor parametrizado da classe Artigo
     * @param descricao String -> uma breve descrição do artigo.
     * @param marca String -> marca do artigo.
     * @param cod_alfanr String -> código alfanumérico do artigo.
     * @param preco_base double -> preço base do artigo.
     * @param av_estado Int -> avaliação do estado do artigo
     *                         (1 ou 2 ou 3. BOM ou RAZOAVEL ou MAU respetivamente)
     * @param nr_donos Int -> número de donos que o artigo pertenceu.
     * @param transportadora String -> nome da empresa de transporte
     *                                   que está associada ao artigo
     */
    public Artigo(String descricao, String marca, String cod_alfanr, double preco_base,
                  int av_estado, int nr_donos, String transportadora){
        this.descricao = descricao;
        this.marca = marca;
        this.cod_alfanr = cod_alfanr;
        this.preco_base = preco_base;
        this.usado = true;
        this.av_estado = av_estado;
        this.nr_donos = nr_donos;
        this.transportadora = transportadora;
    }

    /**
     * Contrutor de cópia da classe Artigo
     * @param art Artigo ->
     */
    public Artigo(Artigo art){
        this.descricao = art.getDescricao();
        this.marca = art.getMarca();
        this.cod_alfanr = art.getCod_alfanr();
        this.preco_base = art.getPreco_base();
        this.usado = art.isUsado();
        this.av_estado = art.getAv_estado();
        this.nr_donos = art.getNr_donos();
        this.transportadora = art.getTransportadora();
    }

    /**
     * Construtor parametrizado da classe Artigo.
     * @param cod_alfanr String -> código alfanumérico do artigo.
     * @param preco_base double -> preço base do artigo.
     * @param transportadora String -> nome da empresa de transporte
     *                                   que está associada ao artigo
     */
    public Artigo(String cod_alfanr, double preco_base, String transportadora){
        this.descricao = " ";
        this.marca = " ";
        this.cod_alfanr = cod_alfanr;
        this.preco_base = preco_base;
        this.usado = false;
        this.av_estado = 0;
        this.nr_donos = 0;
        this.transportadora = transportadora;
    }

    /**
     * Contrutor parametrizado da classe Artigo.
     * @param cod_alfanr String -> código alfanumérico do artigo.
     * @param preco_base double -> preço base do artigo.
     * @param av_estado int -> avaliação do estado do artigo
     *                          (1 ou 2 ou 3. BOM ou RAZOAVEL ou MAU respetivamente).
     * @param nr_donos Int -> número de donos que o artigo pertenceu.
     * @param transportadora String -> nome da empresa de transporte
     *                                   que está associada ao artigo
     */
    public Artigo(String cod_alfanr, double preco_base, int av_estado, int nr_donos, String transportadora){
        this.descricao = " ";
        this.marca = " ";
        this.cod_alfanr = cod_alfanr;
        this.preco_base = preco_base;
        this.usado = true;
        this.av_estado = av_estado;
        this.nr_donos = nr_donos;
        this.transportadora = transportadora;
    }

    /**
     * Retorna a descrição do artigo.
     *
     * @return String -> A descrição do artigo.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna a marca do artigo.
     *
     * @return String -> A marca do artigo.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Retorna o código alfanumérico do artigo.
     *
     * @return String -> O código alfanumérico do artigo.
     */
    public String getCod_alfanr() {
        return cod_alfanr;
    }

    /**
     * Retorna o preço base do artigo.
     *
     * @return double -> O preço base do artigo.
     */
    public double getPreco_base() {
        return preco_base;
    }

    /**
     * Verifica se o artigo é usado.
     *
     * @return Boolean -> True se o artigo é usado, false caso contrário.
     */
    public boolean isUsado() {
        return usado;
    }

    /**
     * Retorna a avaliação da condição do artigo.
     *
     * @return Int -> A avaliação da condição do artigo.
     */
    public int getAv_estado() {
        return av_estado;
    }

    /**
     * Retorna o número de proprietários anteriores do artigo.
     *
     * @return Int -> O número de proprietários anteriores do artigo.
     */
    public int getNr_donos() {
        return nr_donos;
    }

    /**
     * Retorna o nome da empresa de transporte do artigo.
     *
     * @return String -> O nome da empresa de transporte do artigo.
     */
    public String getTransportadora() {
        return transportadora;
    }

    /**
     * Define a descrição do artigo.
     *
     * @param descricao String -> A nova descrição do artigo.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Define a marca do artigo.
     *
     * @param marca String -> A nova marca do artigo.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Define o código alfanumérico do artigo.
     *
     * @param cod_alfanr String -> O novo código alfanumérico do artigo.
     */
    public void setCod_alfanr(String cod_alfanr) {
        this.cod_alfanr = cod_alfanr;
    }

    /**
     * Define o preço base do artigo.
     *
     * @param preco_base double -> O novo preço base do artigo.
     */
    public void setPreco_base(double preco_base) {
        this.preco_base = preco_base;
    }

    /**
     * Define se o artigo é usado.
     *
     * @param usado boolean -> True se o artigo é usado, false caso contrário.
     */
    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    /**
     * Define a avaliação da condição do artigo.
     *
     * @param av_estado int -> A nova avaliação da condição do artigo.
     */
    public void setAv_estado(int av_estado) {
        this.av_estado = av_estado;
    }

    /**
     * Define o número de proprietários anteriores do artigo.
     *
     * @param nr_donos int -> O novo número de proprietários anteriores do artigo.
     */
    public void setNr_donos(int nr_donos) {
        this.nr_donos = nr_donos;
    }

    /**
     * Define o nome da empresa de transporte do artigo.
     *
     * @param transportadora String -> O novo nome da empresa de transporte do artigo.
     */
    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }


    /**
    * Método para clonar um objeto da classe Artigo
    * */
    public Artigo clone(){
        Artigo clone = (Artigo) clone();
        clone.descricao = this.getDescricao();
        clone.marca = this.getMarca();
        clone.cod_alfanr = this.getCod_alfanr();
        clone.preco_base = this.getPreco_base();
        clone.usado = this.isUsado();
        clone.av_estado = this.getAv_estado();
        clone.nr_donos = this.getNr_donos();
        clone.transportadora = this.getTransportadora();

        return clone;
    }

    /**
     * Método de verificação de igualdade de dois objetos, sendo um deles da classe Artigo
     * @param o Object
     * @return boolean
     */
    public boolean equals(Object o){
        if(o==this)
            return true;

        if(o==null || (o.getClass() != this.getClass()) )
            return false;

        Artigo art = (Artigo) o;
        return this.descricao.equals(art.getDescricao()) && this.marca.equals(art.getMarca()) &&
                this.cod_alfanr.equals(art.getCod_alfanr()) && this.preco_base == art.getPreco_base() &&
                this.usado == art.isUsado() && this.av_estado == art.getAv_estado() &&
                this.nr_donos == art.getNr_donos() && this.transportadora.equals(art.getTransportadora());
    }

    /**
     * Método que apresenta uma representação da forma String do objeto da classe Artigo
     * @return String
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Artigo:\n");
        sb.append("\nDescrição do artigo: ").append(this.descricao);
        sb.append("\nMarca: ").append(this.marca);
        sb.append("\nCódigo Alfanumérico: ").append(this.cod_alfanr);
        sb.append("\nPreço Base: ").append(this.preco_base);
        sb.append("\nA transportadora utilizada por este artigo é: ").append(this.transportadora);
        if(this.usado){
            sb.append("\nArtigo usado\n");
            sb.append("Avaliação do Estado do artigo (valores entre 1-[bom estado] e 3-[mau estado] ): ").append(this.av_estado);
            sb.append("\nNúmero de donos: ").append(this.nr_donos);
        }else{
            sb.append("\nArtigo novo.");
        }
            sb.append("\nPreço final:").append(this.preco()).append("\n");

        return sb.toString();
    }

    /**
     * Método que representa um objeto da classe Artigo numa string com apenas uma linha
     * @return String
     * */
    public String umalinhaString(){
        StringBuilder sb = new StringBuilder();

        sb.append(this.descricao).append(",");
        sb.append(this.marca).append(",");
        sb.append(this.cod_alfanr).append(",");
        sb.append(this.preco_base).append(",");
        sb.append(this.usado).append(",");
        sb.append(this.av_estado).append(",");
        sb.append(this.nr_donos).append(",");
        sb.append(this.transportadora);

        return sb.toString();
    }

    /**
    * Método abstrato
    * */
    public abstract double preco();
    public abstract boolean isPremium();
}
