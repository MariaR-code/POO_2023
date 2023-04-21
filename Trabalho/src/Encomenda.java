package Trabalho.src;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe que implementa uma Encomenda
 * que possui uma lista de Artigos nela contidos.
 */
public class Encomenda {
    //variáveis de instância
    private List<Artigo> artigos;
    public enum Dimensao
    {
        PEQUENO,
        MEDIO,
        GRANDE
    }
    private Dimensao dimensao;
    private double preco_final;
    private LocalDate data;
    public enum Estado
    {
        PENDENTE, /* Quando a encomenda é iniciada */
        FINALIZADA,/* Quando a compra é concluída */
        EXPEDIDA /* Quando a transportadora a tiver enviado */
    }
    private Estado estado;

    /**
     * Construtores da classe Encomenda.
     * Declaração dos construtores por omissão (vazio),
     * parametrizado e cópia.
     */

    /**
     * Construtor por omissão de Encomenda.
     */
    public Encomenda()
    {
        this.artigos = new ArrayList<>();
        this.dimensao = Dimensao.PEQUENO;
        this.preco_final = 0;
        this.data = LocalDate.now();
        this.estado = Estado.PENDENTE;
    }

    /**
     * Construtor parametrizado de Encomenda.
     */
    public Encomenda(List<Artigo> artigos_, Dimensao dimensao_, double preco_final_, LocalDate data_,
                     Estado estado_)
    {
        setArtigos(artigos_);
        this.dimensao = dimensao_;
        this.preco_final = preco_final_;
        this.data = data_;
        this.estado = estado_;
    }

    /**
     * Construtor de cópia de Encomenda.
     */
    public Encomenda(Encomenda enc)
    {
        this.artigos = enc.getArtigos();
        this.dimensao = enc.getDimensao();
        this.preco_final = enc.getPreco_final();
        this.data = enc.getData();
        this.estado = enc.getEstado();
    }

    /**
     * Métodos de instância da classe Encomenda.
     */

    /**
     * Getters dos objetos da classe Encomenda.
     */
    //TODO pode ser necessário fazer um clone
    public List<Artigo> getArtigos()
    {
        return this.artigos;
    }


    public Dimensao getDimensao()
    {
        return this.dimensao;
    }

    public double getPreco_final()
    {
        return this.preco_final;
    }

    public LocalDate getData()
    {
        return this.data;
    }

    public Estado getEstado()
    {
        return this.estado;
    }

    /**
     * Setters dos objetos da classe Encomenda.
     */
    //TODO verificar se é necesário fazer clone
    public void setArtigos(List<Artigo> artigos_)
    {
        this.artigos = artigos_;
    }

    public void setDimensao(Dimensao dimensao_)
    {
        this.dimensao = dimensao_;
    }

    public void setPreco_final(double preco_final_)
    {
        this.preco_final = preco_final_;
    }

    public void setData(LocalDate data_)
    {
        this.data = data_;
    }

    public void setEstado(Estado estado_)
    {
        this.estado = estado_;
    }

    /**
     * Método clone que faz uma cópia do objeto,
     * utilizando o contrutor de cópia.
     */
    public Encomenda clone()
    {
        return new Encomenda(this);
    }

    /**
     * Método equals que compara e verifica
     * se os objetos em questão são iguais.
     */
    public boolean equals(Object o)
    {
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Encomenda e = (Encomenda) o;
        return (this.artigos.equals(e.getArtigos()) && this.dimensao.equals(e.getDimensao()) &&
                this.preco_final == (e.getPreco_final()) && this.data.equals(e.getData()) &&
                this.estado.equals(e.getEstado()));

    }

    /**
     * Método toString que devolve a representação em String da Encomenda.
     */
    //TODO pedir à Maria para checkar
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Encomenda: {");
        sb.append("Artigos: ").append(this.artigos);
        sb.append("Dimensão: ").append(this.dimensao);
        sb.append("Data: ").append(this.data);
        sb.append("Estado: ").append(this.estado).append("}");
        return sb.toString();
    }

}