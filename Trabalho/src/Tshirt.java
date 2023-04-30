package Trabalho.src;

public class Tshirt extends Artigo{
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
    public Tshirt(){
        super();
        this.tamanho = Tamanho.S;
        this.padrao = Padrao.Liso;
    }

    public Tshirt(String cod_alfanr, double preco_base, String transportadora, Tamanho tamanho,
                  Padrao padrao){
        super(cod_alfanr, preco_base, transportadora);
        this.tamanho = tamanho;
        this.padrao = padrao;
    }

    public Tshirt(String cod_alfanr, double preco_base, int av_estado, int nr_donos, String transportadora,
                  Tamanho tamanho, Padrao padrao){
        super(cod_alfanr, preco_base, av_estado, nr_donos, transportadora);
        this.tamanho = tamanho;
        this.padrao = padrao;
    }

    public Tshirt(Tshirt tshirt){
        super(tshirt);
        this.tamanho = tshirt.getTamanho();
        this.padrao = tshirt.getPadrao();
    }

    /**
    * Getters dos objetos da classe Tshirt
    * */
    public Tamanho getTamanho() {
        return tamanho;
    }

    public Padrao getPadrao() {
        return padrao;
    }

    /*
    * Setters dos objetos da classe Tshirt
    * */
    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public void setPadrao(Padrao padrao) {
        this.padrao = padrao;
    }

    /**
    *Método para clonar um objeto da classe Tshirt
    * */
    public Tshirt clone(){
        return new Tshirt(this);
    }

    /**
    * Método de verificação de igualdade de dois objetos, sendo um deles da classe Tshirt
    * */
    public boolean Equals(Object o){
        if(o == this)
            return true;
        if(o==null || o.getClass() != this.getClass())
            return false;

        if(!super.equals(o))
            return false;

        Tshirt tshirt = (Tshirt) o;
        return this.tamanho == tshirt.getTamanho() && this.padrao == tshirt.getPadrao();
    }

    /**
    * Método que apresenta uma representação da forma String do objeto da classe Tshirt
    * */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nTamanho: ").append(this.tamanho);
        sb.append("\nPadrão: ").append(this.padrao);
        return sb.toString();
    }

    /**
     * Método para obter o preço
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
     * */
    public boolean isPremium(){
        return false;
    }
}