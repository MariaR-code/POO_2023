package Trabalho.src.Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe que implementa uma Encomenda
 * que possui uma lista de Artigos nela contidos.
 */
public class Encomenda {
    //variáveis de instância
    private List<Artigo> artigos;
    public enum Dimensao
    {
        PEQUENO, //Quando a encomenda possui apenas 1 artigo.
        MEDIO,   //Quando a encomenda possui entre 2 a 5 artigos.
        GRANDE   //Quando a encomenda possui mais que 5 artigos.
    }
    private Dimensao dimensao;
    private double preco_final;
    private LocalDate data;
    public enum Estado
    {
        PENDENTE, // Quando a encomenda é iniciada
        FINALIZADA,// Quando a compra é concluída
        EXPEDIDA // Quando a transportadora a tiver enviado
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
     * @param artigos_ List<Artigo> -> lista de artigos que pertencem à encomenda.
     * @param preco_final_ double -> preço final da encomenda.
     * @param data_ LocalDate -> data em que foi/está a ser efetuada a encomenda.
     * @param estado_ Estado -> PENDENTE quando a encomenda é iniciada
     *                          FINALIZADA quando a compra é concluída
     *                          EXPEDIDA quando a transportadora a tiver enviado.
     */
    public Encomenda(List<Artigo> artigos_, double preco_final_, LocalDate data_,
                     Estado estado_){
        this.setArtigos(artigos_);
        this.preco_final = preco_final_;
        this.data = data_;
        this.estado = estado_;
        if(this.artigos.size() == 1)
            this.dimensao = Dimensao.PEQUENO;
        else if(this.artigos.size() < 5)
            this.dimensao = Dimensao.MEDIO;
        else this.dimensao = Dimensao.GRANDE;
    }

    /**
     * Construtor de cópia de Encomenda.
     * @param enc Encomenda
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
     * Getters dos objetos da classe Encomenda.
     */

    /**
     * Retorna a lista de artigos que pertencem à encomenda.
     * @return List<Artigo>
     */
    public List<Artigo> getArtigos()
    {
        return this.artigos.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    /**
     * Retorna a dimensão da encomenda.
     * @return Dimensao -> PEQUENO quando a encomenda possui apenas 1 artigo.
     *                      MEDIO  quando a encomenda possui entre 2 a 5 artigos.
     *                      GRANDE quando a encomenda possui mais que 5 artigos.
     */
    public Dimensao getDimensao()
    {
        return this.dimensao;
    }

    /**
     * Retorna o preço final da encomenda.
     * @return double
     */
    public double getPreco_final()
    {
        return this.calculaPreco();
    }

    /**
     * Retorna a data em que encomenda foi/ está a ser efetuada.
     * @return LocalDate
     */
    public LocalDate getData()
    {
        return this.data;
    }

    /**
     * Retorna o estado da encomenda.
     * @return Estado -> PENDENTE quando a encomenda é iniciada
     *                  FINALIZADA quando a compra é concluída
     *                  EXPEDIDA quando a transportadora a tiver enviado.
     */
    public Estado getEstado()
    {
        return this.estado;
    }

    /**
     * Setters dos objetos da classe Encomenda.
     */

    /**
     * Define a lista de artigos que pertencem à encomenda.
     * @param artigos_ List<Artigo>
     */
    public void setArtigos(List<Artigo> artigos_)
    {
        this.artigos = artigos_.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    /**
     * Define a dimensão da encomenda.
     * @param dimensao_ Dimensao -> PEQUENO quando a encomenda possui apenas 1 artigo.
     *                               MEDIO  quando a encomenda possui entre 2 a 5 artigos.
     *                                GRANDE quando a encomenda possui mais que 5 artigos.
     */
    public void setDimensao(Dimensao dimensao_)
    {
        this.dimensao = dimensao_;
    }

    /**
     * Define o preço final da encomenda.
     * @param preco_final_ double
     */
    public void setPreco_final(double preco_final_)
    {
        this.preco_final = preco_final_;
    }

    /**
     * Define a data em que foi/está a ser efetuada a encomenda.
     * @param data_ LocalDate
     */
    public void setData(LocalDate data_)
    {
        this.data = data_;
    }

    /**
     * Define o estado da encomenda.
     * @param estado_ Estado -> PENDENTE quando a encomenda é iniciada
     *                          FINALIZADA quando a compra é concluída
     *                          EXPEDIDA quando a transportadora a tiver enviado.
     */
    public void setEstado(Estado estado_)
    {
        this.estado = estado_;
    }

    /**
     * Método addArtigo que adiciona um artigo de uma encomenda.
     * @param artigo Artigo
     * @return List<Artigo>
     */
    public List<Artigo> addArtigo(Artigo artigo)
    {
        this.artigos.add(artigo.clone());
        return this.artigos;
    }

    /**
     * Método removeArtigo que remove um artigo de uma encomenda.
     * @param artigo Artigo
     * @return boolean -> true se foi removido consucesso ou false se não foi.
     */
    public boolean removeArtigo(Artigo artigo)
    {
       return this.artigos.remove(artigo);
    }

    /**
     * Método calculaPreco que calcula o preço final
     * a pagar pela encomenda.
     * @return double -> preço final a pagar pela encomenda.
     */
        public double calculaPreco(){
        this.preco_final = artigos.stream().mapToDouble(Artigo::preco).sum();
        // taxa de satisfação
        for (Artigo artigo : artigos) {
            if (artigo.isUsado()) {
                preco_final += 0.25;
            } else {
                preco_final += 0.5;
            }
        }
        return preco_final;
    }

    /**
     *  Método clone que faz uma cópia do objeto,
     * utilizando o contrutor de cópia.
     * @return Encomenda
     */
    public Encomenda clone()
    {
        return new Encomenda(this);
    }

    /**
     *  Método equals que compara e verifica
     * se os objetos em questão são iguais.
     * @param o Object
     * @return boolean -> true se forem iguais ou false se forem diferentes.
     */
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Encomenda e = (Encomenda) o;
        return (this.equalsArtigos(e.getArtigos()) && this.dimensao.equals(e.getDimensao()) &&
                this.preco_final == (e.getPreco_final()) && this.data.equals(e.getData()) &&
                this.estado.equals(e.getEstado()));
    }

    /**
     * Método que compara e verifica se duas listas de artigos são iguais.
     * @param artigos List<Artigo>
     * @return boolean
     */
    public boolean equalsArtigos(List<Artigo> artigos){
        List<Artigo> thisArtigos = this.getArtigos();
        if (thisArtigos.size() != artigos.size()) {
            return false;
        }

        for(int i = 0; i < thisArtigos.size(); i++){
            if(!thisArtigos.get(i).equals(artigos.get(i))){
                return false;
            }
        }
        return true;
    }


    /**
     * Método toString que devolve a representação em String da Encomenda.
     * @return String
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nEncomenda: \n{");
        sb.append("Artigos: ").append(this.artigos.toString());
        sb.append("\nDimensão: ").append(this.dimensao);
        sb.append("\nData: ").append(this.data);
        sb.append("\nEstado: ").append(this.estado).append("}");
        return sb.toString();
    }

    /**
     * Método que representa um objeto da classe Encomenda numa string com apenas uma linha
     * @return String
     * */
    public String umalinhaString(){
        StringBuilder sb = new StringBuilder();

        sb.append(this.dimensao).append(",");
        sb.append(this.preco_final).append(",");
        sb.append(this.data).append(",");
        sb.append(this.estado).append(";");

        for(Artigo artigo : this.artigos){
            sb.append(artigo.umalinhaString()).append("/");
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    /**
     * Método que lista os artigos da encomenda
     */
    public void listarArtigosEnc(){
        this.artigos.stream().map(Artigo::toString);
    }
}