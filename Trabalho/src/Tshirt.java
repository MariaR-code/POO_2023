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
    private double preco;

    /*
    * Construtores dos objetos da classe Tshirt
    * */
    public Tshirt(Tshirt tshirt){
        super(tshirt);
        this.tamanho = tshirt.getTamanho();
        this.padrao = tshirt.getPadrao();
        this.preco = tshirt.getPreco();
    }

    /*
    * Getters dos objetos da classe Tshirt
    * */
    public Tamanho getTamanho() {
        return tamanho;
    }

    public Padrao getPadrao() {
        return padrao;
    }

    public double getPreco() {
        return preco;
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

    public void setPreco(double preco) {
        this.preco = preco;
    }

    /*
    *Método para clonar um objeto da classe Tshirt
    * */
    public Tshirt clone(){
        return new Tshirt(this);
    }

    /*
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
        return this.tamanho == tshirt.getTamanho() && this.padrao == tshirt.getPadrao()
                && this.preco == tshirt.getPreco();
    }

    /*
    * Método que apresenta uma representação da forma String do objeto da classe Tshirt
    * */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nTamanho: ").append(this.tamanho);
        sb.append("\nPadrão: ").append(this.padrao);
        sb.append("\nPreço: ").append(this.preco);
        return sb.toString();
    }
}