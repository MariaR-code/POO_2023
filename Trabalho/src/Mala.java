import java.time.LocalDate;

public class Mala extends Artigo{
    private String dimensao;
    private String material;
    private LocalDate ano_colecao; //ver isto
    private double preco;
    private boolean premium;

    /*
     * Construtores dos objetos da classe Mala
     * */
    public Mala(Mala mala){
        super(mala);
        this.dimensao = mala.getDimensao();
        this.material = mala.getMaterial();
        this.ano_colecao = mala.getAno_colecao();
        this.preco = mala.getPreco();
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

    public LocalDate getAno_colecao() {
        return ano_colecao;
    }

    public double getPreco() {
        return preco;
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

    public void setAno_colecao(LocalDate ano_colecao) {
        this.ano_colecao = ano_colecao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
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
                this.ano_colecao.equals(mala.getAno_colecao()) && this.preco == mala.getPreco() &&
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
        sb.append("\nPreço: ").append(this.preco);
        if(this.premium)
            sb.append("\nA mala é premium.");
        return sb.toString();
    }
}
