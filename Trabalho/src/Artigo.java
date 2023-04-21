public class Artigo {
    public static final int MAU = 1;
    public static final int RAZOAVEL = 2;
    public static final int BOM = 3;
    private String descricao;
    private String marca;
    private String cod_alfanr;
    private double preco_base;
    private double correcao_preco;
    private boolean usado;
    private int av_estado;
    private int nr_donos;

    /*
    * Contrutores dos objetos da classe Artigo
    * */
    public Artigo(String descricao, String marca, String cod_alfanr, double preco_base,
                  double correcao_preco){
        this.descricao = descricao;
        this.marca = marca;
        this.cod_alfanr = cod_alfanr;
        this.preco_base = preco_base;
        this.correcao_preco = correcao_preco;
        this.usado = false;
        this.av_estado = 0;
        this.nr_donos = 0;
    }

    public Artigo(String descricao, String marca, String cod_alfanr, double preco_base,
                  double correcao_preco, int av_estado, int nr_donos){
        this.descricao = descricao;
        this.marca = marca;
        this.cod_alfanr = cod_alfanr;
        this.preco_base = preco_base;
        this.correcao_preco = correcao_preco;
        this.usado = true;
        this.av_estado = av_estado;
        this.nr_donos = nr_donos;
    }

    public Artigo(Artigo art){
        this.descricao = art.getDescricao();
        this.marca = art.getMarca();
        this.cod_alfanr = art.getCod_alfanr();
        this.preco_base = art.getPreco_base();
        this.correcao_preco = art.getCorrecao_preco();
        this.usado = art.isUsado();
        this.av_estado = art.getAv_estado();
        this.nr_donos = art.getNr_donos();
    }

    /*
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

    public double getCorrecao_preco() {
        return correcao_preco;
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

    /*
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

    public void setCorrecao_preco(double correcao_preco) {
        this.correcao_preco = correcao_preco;
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

    /*
    * Método para clonar um objeto da classe Artigo
    * */
    public Artigo clone(){
        return new Artigo(this);
    }

    /*
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
                this.correcao_preco == art.getCorrecao_preco() && this.usado == art.isUsado() &&
                this.av_estado == art.getAv_estado() && this.nr_donos == art.getNr_donos();
    }

    /*
    * Método que apresenta uma representação da forma String do objeto da classe Artigo
    * */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Artigo:\n");
        sb.append("Descrição do artigo: ").append(this.descricao);
        sb.append("\nMarca: ").append(this.marca);
        sb.append("\nCódigo Alfanumérico: ").append(this.cod_alfanr);
        sb.append("\nPreço Base: ").append(this.preco_base);
        sb.append("\nCorreção do Preço: ").append(this.correcao_preco);
        if(this.usado){
            sb.append("\nArtigo usado\n");
            sb.append("Avaliação do Estado do artigo (valores entre 1(mau estado) e 3(bom estado) ): ").append(this.av_estado);
            sb.append("\nNúmerod de donos: ").append(this.nr_donos);
        }else{
            sb.append("\nArtigo novo.\n");
        }

        return sb.toString();
    }
}