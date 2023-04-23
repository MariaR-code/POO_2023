public class Mala extends Artigo{
    private String dimensao;
    private String material;
    private int ano_colecao;
    private boolean premium;

    /*
     * Construtores dos objetos da classe Mala
     * */
    public Mala(String cod_alfanr, double preco_base, String dimensao, String material,
                int ano_colecao, boolean premium){
        super(cod_alfanr, preco_base);
        this.dimensao = dimensao;
        this.material = material;
        this.ano_colecao = ano_colecao;
        this.premium = premium;
    }

    public Mala(String cod_alfanr, double preco_base, int av_estado, int nr_donos,
                String dimensao, String material, int ano_colecao,
                boolean premium){
        super(cod_alfanr, preco_base, av_estado, nr_donos);
        this.dimensao = dimensao;
        this.material = material;
        this.ano_colecao = ano_colecao;
        this.premium = premium;
    }

    public Mala(Mala mala){
        super(mala);
        this.dimensao = mala.getDimensao();
        this.material = mala.getMaterial();
        this.ano_colecao = mala.getAno_colecao();
        this.premium = mala.isPremium();
    }

    /*
     * Getters dos objetos da classe Mala
     * */
    public String getDimensao() {
        return dimensao;
    }

    public String getMaterial() {
        return material;
    }

    public int getAno_colecao() {
        return ano_colecao;
    }

    public boolean isPremium() {
        return premium;
    }

    /*
     * Setters dos objetos da classe Mala
     * */
    public void setDimensao(String dimensao) {
        this.dimensao = dimensao;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setAno_colecao(int ano_colecao) {
        this.ano_colecao = ano_colecao;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    /*
     *Método para clonar um objeto da classe Mala
     * */
    public Mala clone(){
        return new Mala(this);
    }

    /*
     * Método de verificação de igualdade de dois objetos, sendo um deles da classe Mala
     * */
    public boolean Equals(Object o){
        if(o==this)
            return true;

        if(o==null || o.getClass() != this.getClass())
            return false;

        if(!super.Equals(o))
            return false;

        Mala mala = (Mala) o;
        return this.dimensao.equals(mala.getDimensao()) && this.material.equals(mala.getMaterial()) &&
                this.ano_colecao == mala.getAno_colecao() &&
                this.premium == mala.isPremium();
    }

    /*
     * Método que apresenta uma representação da forma String do objeto da classe Mala
     * */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nDimensão da mala: ").append(this.dimensao);
        sb.append("\nMaterial: ").append(this.material);
        sb.append("\nAno da Coleção: ").append(this.ano_colecao);
        if(this.premium)
            sb.append("\nA mala é premium.");
        return sb.toString();
    }

    /*
    * Método para obter o preço
    *
    * */
    public double preco(){
        return 0.0;
    }
}
