package Trabalho.src;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Locale;

public class Transportadora {
    private String nome;
    private final static double valor_base_P = 1.0;
    private final static double valor_base_M = 2.0;
    private final static double valor_base_G = 3.5;
    private final static double imposto = 0.15;
    private String preco_expedicao;
    private boolean premium;
    private String preco_expedicao_premium;

    /*
     * Construtores dos objetos da classe Transportadora
     * */
    public Transportadora(){
        this.nome = "";
        this.preco_expedicao = "";
        this.premium = false;
        this.preco_expedicao_premium = "";
    }

    public Transportadora(String nome, String preco_expedicao){
        this.nome = nome;
        this.preco_expedicao = preco_expedicao;
        this.premium = false;
        this.preco_expedicao_premium = "";
    }

    public Transportadora(String nome, String preco_expedicao, String preco_expedicao_premium){
        this.nome = nome;
        this.preco_expedicao = preco_expedicao;
        this.premium = true;
        this.preco_expedicao_premium = preco_expedicao_premium;
    }

    public Transportadora(Transportadora transportadora){
        this.nome = transportadora.getNome();
        this.preco_expedicao = transportadora.getPreco_expedicao();
        this.premium = transportadora.isPremium();
        this.preco_expedicao_premium = transportadora.getPreco_expedicao_premium();
    }

    /*
     * Getters dos objetos da classe Transportadora
     * */
    public String getNome(){
        return nome;
    }

    public String getPreco_expedicao(){
        return preco_expedicao;
    }

    public boolean isPremium(){
        return premium;
    }

    public String getPreco_expedicao_premium(){
        return preco_expedicao_premium;
    }

    /*
     * Setters dos objetos da classe Transportadora
     * */
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setPreco_expedicao(String preco_expedicao){
        this.preco_expedicao = preco_expedicao;
    }

    public void setPremium(boolean premium){
        this.premium = premium;
    }

    public void setPreco_expedicao_premium(String preco_expedicao_premium){
        this.preco_expedicao_premium = preco_expedicao_premium;
    }

    /*
     * Método de verificação de igualdade de dois objetos, sendo um deles da classe Transportadora
     * @param objeto
     * @return boolean
     * */
    public boolean equals(Object o){
        if(this == o)
            return true;

        if(o==null || o.getClass() != this.getClass())
            return false;

        Transportadora transportadora = (Transportadora) o;
        return this.nome.equals(transportadora.getNome())
                && this.preco_expedicao.equals(transportadora.getPreco_expedicao()) &&
                this.premium == transportadora.isPremium() && this.preco_expedicao_premium.equals(transportadora.getPreco_expedicao_premium());
    }

    /*
     * Método que apresenta uma representação da forma String do objeto da classe Transportadora
     * @return String
     * */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nNome da transportadora: ").append(this.nome);
        sb.append("\nValor base de uma encomenda pequena: ").append(this.valor_base_P);
        sb.append("\nValor base de uma encomenda média: ").append(this.valor_base_M);
        sb.append("\nValor base de uma encomenda grande: ").append(this.valor_base_G);
        sb.append("\nImposto: ").append(this.imposto);
        sb.append("\nO preço de expedição é dado pela seguinte fórmula: ").append(this.preco_expedicao);
        if(this.premium) {
            sb.append("\nA transportadora permite a distribuição de artigos premium.");
            sb.append("\nO preço de expedição de artigos premium é dado pela seguinte fórmula: ").append(this.preco_expedicao_premium);
        }
        return sb.toString();
    }

    /*
     *Método para clonar um objeto da classe Transportadora
     * @return Transportadora
     * */
    public Transportadora clone(){
        return new Transportadora(this);
    }

    /*
    * Método que vai calcular o preço da expedição de uma encomenda
    * @param
    * @return
    * */
    public double preco_encomenda(String formula_preco, double valor_base, double margem_lucro){
        ScriptEngineManager formula = new ScriptEngineManager();
        ScriptEngine engine = formula.getEngineByName("nashorn");
        try {
            engine.eval("ValorBase = " + String.valueOf(valor_base));
            engine.eval("margemlucro = " + String.valueOf(margem_lucro));
            engine.eval("Imposto = " + String.valueOf(imposto));
            return (double) engine.eval(formula_preco.toLowerCase(Locale.ROOT));
        }catch (ScriptException e){
            return -1;
        }
    }
}
