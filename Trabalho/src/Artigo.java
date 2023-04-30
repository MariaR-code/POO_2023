package Trabalho.src;
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

    public Artigo(String descricao, String marca, String cod_alfanr, double preco_base,
                  double correcao_preco, String transportadora){
        this.descricao = descricao;
        this.marca = marca;
        this.cod_alfanr = cod_alfanr;
        this.preco_base = preco_base;
        this.usado = false;
        this.av_estado = 0;
        this.nr_donos = 0;
        this.transportadora = transportadora;
    }

    public Artigo(String descricao, String marca, String cod_alfanr, double preco_base,
                  double correcao_preco, int av_estado, int nr_donos, String transportadora){
        this.descricao = descricao;
        this.marca = marca;
        this.cod_alfanr = cod_alfanr;
        this.preco_base = preco_base;
        this.usado = true;
        this.av_estado = av_estado;
        this.nr_donos = nr_donos;
        this.transportadora = transportadora;
    }

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
    * Getters dos objetos da classe Artigo
    * */
    public String getDescricao() {
        return descricao;
    }

    public String getMarca() {
        return marca;
    }

    public String getCod_alfanr() {
        return cod_alfanr;
    }

    public double getPreco_base() {
        return preco_base;
    }

    public boolean isUsado() {
        return usado;
    }

    public int getAv_estado() {
        return av_estado;
    }

    public int getNr_donos() {
        return nr_donos;
    }

    public String getTransportadora() {
        return transportadora;
    }

    /**
    * Setters dos objetos da classe Artigo
    * */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCod_alfanr(String cod_alfanr) {
        this.cod_alfanr = cod_alfanr;
    }

    public void setPreco_base(double preco_base) {
        this.preco_base = preco_base;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    public void setAv_estado(int av_estado) {
        this.av_estado = av_estado;
    }

    public void setNr_donos(int nr_donos) {
        this.nr_donos = nr_donos;
    }

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
    * */
    public boolean Equals(Object o){
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
    * */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Artigo:\n");
        sb.append("Descrição do artigo: ").append(this.descricao);
        sb.append("\nMarca: ").append(this.marca);
        sb.append("\nCódigo Alfanumérico: ").append(this.cod_alfanr);
        sb.append("\nPreço Base: ").append(this.preco_base);
        sb.append("\nA transportadora utilizada por este artigo é: ").append(this.transportadora);
        if(this.usado){
            sb.append("\nArtigo usado\n");
            sb.append("Avaliação do Estado do artigo (valores entre 1(mau estado) e 3(bom estado) ): ").append(this.av_estado);
            sb.append("\nNúmerod de donos: ").append(this.nr_donos);
        }else{
            sb.append("\nArtigo novo.\n");
        }

        return sb.toString();
    }

    /**
    * Método abstrato
    * */
    public abstract double preco();
}