package Trabalho.src.Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Mercado {
    private List<Encomenda> encomendas_pend;
    private List<Transportadora> transportadoras;
    private List<Utilizador> utilizadores;
    private List<Artigo> artigos;
    private Map<Integer, List<String>> artigos_venda; //Relaciona o código do utilizador à lista dos códigos alfanuméricos dos artigos que tem à venda

    /**
     * Construtores dos objetos da classe Mercado
     */
    public Mercado() {
        this.encomendas_pend = new ArrayList<>();
        this.transportadoras = new ArrayList<>();
        this.utilizadores = new ArrayList<>();
        this.artigos = new ArrayList<>();
        this.artigos_venda = new HashMap<>();
    }

    public Mercado(List<Encomenda> encomendas_pend, List<Transportadora> transportadoras, List<Utilizador> utilizadores,
                   List<Artigo> artigos, Map<Integer, List<String>> artigos_venda) {
        this.setEncomendas_pend(encomendas_pend);
        this.setTransportadoras(transportadoras);
        this.setUtilizadores(utilizadores);
        this.setArtigos(artigos);
        this.setArtigos_venda(artigos_venda); //check this one later
    }

    public Mercado(Mercado marketPlace) {
        this.encomendas_pend = marketPlace.getEncomendas_pend();
        this.transportadoras = marketPlace.getTransportadoras();
        this.utilizadores = marketPlace.getUtilizadores();
        this.artigos = marketPlace.getArtigos();
        this.artigos_venda = marketPlace.getArtigos_venda(); // check this later
    }


    /**
     * Getters dos objetos da classe MarketPlace
     */
    public List<Encomenda> getEncomendas_pend() {
        return encomendas_pend.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public List<Transportadora> getTransportadoras() {
        return transportadoras.stream().map(Transportadora::clone).collect(Collectors.toList());
    }

    public List<Utilizador> getUtilizadores() {
        return utilizadores.stream().map(Utilizador::clone).collect(Collectors.toList());
    }

    public List<Artigo> getArtigos() {
        return artigos.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    //VER ISTO
    public Map<Integer, List<String>> getArtigos_venda() {
        return artigos_venda;
    }

    public void setEncomendas_pend(List<Encomenda> encomendas_pend) {
        this.encomendas_pend = encomendas_pend.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    /**
     * Setters dos objetos da classe MarketPlace
     */

    public void setTransportadoras(List<Transportadora> transportadoras) {
        this.transportadoras = transportadoras.stream().map(Transportadora::clone).collect(Collectors.toList());
    }

    public void setUtilizadores(List<Utilizador> utilizadores) {
        this.utilizadores = utilizadores.stream().map(Utilizador::clone).collect(Collectors.toList());
    }

    public void setArtigos(List<Artigo> artigos) {
        this.artigos = artigos.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    //VER ESTE SET
    public void setArtigos_venda(Map<Integer, List<String>> artigos_venda) {
        this.artigos_venda = artigos_venda;
    }

    /**
     * Método que adiciona um Utilizador à lista Utilizadores
     * @param utilizador
     */
    public void addUtilizador(Utilizador utilizador) {
        this.utilizadores.add(utilizador.clone());
    }

    /**
     * Método que adiciona uma Transportador à lista transportadoras
     * @param t
     */
    public void add_transportadora(Transportadora t) {
        this.transportadoras.add(t.clone());
    }


    /**
     * Método equals que compara e verifica
     * se os objetos em questão são iguais.
     */
    //Verificar se a comparação de listas não é shallow
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (o == null || o.getClass() != this.getClass())
            return false;

        Mercado mp = (Mercado) o;
        return this.transportadoras.equals(mp.getTransportadoras()) && this.utilizadores.equals(mp.getUtilizadores())
                && this.artigos.equals(mp.getArtigos()) && this.artigos_venda.equals(mp.getArtigos_venda())
                && this.encomendas_pend.equals(mp.getEncomendas_pend());
    }

    /**
     * Método toString que devolve a representação em String da MarketPlace.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("As encomendas pendentes são as seguintes: ").append(this.encomendas_pend.toString());
        sb.append("\nAs transportadoras são: ").append(this.transportadoras.toString());
        sb.append("\nOs utilizadores são: ").append(this.utilizadores.toString());
        sb.append("\nOs artigos são: ").append(this.artigos.toString());
        sb.append("\nO utilizador com o código x tem os artigos a seguir para venda: ").append(this.artigos_venda.toString());

        return sb.toString();
    }

    /**
     * Método clone que faz uma cópia do objeto,
     * utilizando o contrutor de cópia.
     */
    public Mercado clone() {
        return new Mercado(this);
    }

    /**
     * Método procuraUtilizador que recebe um email
     * e que verifica se existe um utilizador com o mesmo.
     */
    public boolean procuraUtilizador(String email, int tipo) {
        for (Utilizador u : utilizadores) {
            if (u.getEmail().equals(email) && (u.getTipoUtilizador() == tipo || u.getTipoUtilizador() == 2)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Método que adiciona um artigo na lista de artigos
     * @param artigo É o artigo que se pretende adicionar
     * */
    public void adicionaArtigo(Artigo artigo){
        this.artigos.add(artigo.clone());
    }

    /**
     * Método que devolve o código do utilizador com o email passado em parâmetro
     * @param email Email do utilizador
     * @return int que é o código do utilizador
     * */
    public int codigoUtilizador(String email){
        for(Utilizador u : utilizadores){
            if(u.getEmail().equals(email))
                return u.getId();
        }
        return -1;
    }

    /**
     *
     * */
    public void adicionaArtigoVenda(int cod, String cod_alfanr){
        if(this.artigos_venda.containsKey(cod))
            this.artigos_venda.get(cod).add(cod_alfanr);
        else{
            List<String> l = new ArrayList<>();
            l.add(cod_alfanr);
            this.artigos_venda.put(cod, l);
        }
    }

}