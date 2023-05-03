package Trabalho.src.Model;

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
    public Sapatilhas(){
        super();
        this.tamanho = 38;
        this.atacadores = true;
        this.cor = "";
        this.data_lancamento = LocalDate.now();
        this.premium = false;
    }

    public Sapatilhas(String cod_alfanr, double preco_base, String transportadora, int tamanho,
                      boolean atacadores, String cor,
                      LocalDate data_lancamento, boolean premium){
        super(cod_alfanr, preco_base, transportadora);
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.data_lancamento = data_lancamento;
        this.premium = premium;
    }

    public Sapatilhas(String cod_alfanr, double preco_base, int av_estado, int nr_donos, String transportadora,
                      int tamanho, boolean atacadores, String cor,
                      LocalDate data_lancamento, boolean premium){
        super(cod_alfanr, preco_base, av_estado, nr_donos, transportadora);
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.data_lancamento = data_lancamento;
        this.premium = premium;
    }

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
    * */
    public int getTamanho() {
        return tamanho;
    }

    public boolean hasAtacadores() {
        return atacadores;
    }

    public String getCor() {
        return cor;
    }

    public LocalDate getData_lancamento() {
        return data_lancamento;
    }

    public boolean isPremium() {
        return premium;
    }

    /**
    * Setters dos objetos da classe Sapatilhas
    * */

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void setAtacadores(boolean atacadores) {
        this.atacadores = atacadores;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setData_lancamento(LocalDate data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    /**
    * Método para clonar um objeto da classe Sapatilhas
    * */
    public Sapatilhas clone(){
        return new Sapatilhas(this);
    }

    /**
    * Método de verificação de igualdade de dois objetos, sendo um deles da classe Sapatilhas
    * */
    public boolean Equals(Object o){
        if(o==this)
            return true;
        if(o==null || o.getClass() != this.getClass())
            return false;

        if(!super.Equals(o))
            return false;

        Sapatilhas s = (Sapatilhas) o;
        return this.tamanho == s.getTamanho() && this.atacadores == s.hasAtacadores() &&
                this.cor.equals(s.getCor()) && this.data_lancamento.equals(s.getData_lancamento())
                && this.premium == s.isPremium();
    }

    /**
    * Método que apresenta uma representação da forma String do objeto da classe Sapatilhas
    * */
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
    * Método para obter o preço
    * */
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