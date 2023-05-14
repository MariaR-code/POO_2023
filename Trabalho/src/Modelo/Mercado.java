package Trabalho.src.Modelo;

import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Mercado {
    private Map<Integer, List<Encomenda>> encomendas_pend;
    private List<Transportadora> transportadoras;
    private List<Utilizador> utilizadores;
    private List<Artigo> artigos;
    private Map<Integer, List<String>> artigos_venda; //Relaciona o código do utilizador à lista dos códigos alfanuméricos dos artigos que tem à venda
    private Map<Integer, List<String>> artigos_vendidos;

    /**
     * Construtores dos objetos da classe Mercado
     */
    public Mercado() {
        this.encomendas_pend = new HashMap<>();
        this.transportadoras = new ArrayList<>();
        this.utilizadores = new ArrayList<>();
        this.artigos = new ArrayList<>();
        this.artigos_venda = new HashMap<>();
        this.artigos_vendidos = new HashMap<>();
    }

    public Mercado(Map<Integer, List<Encomenda>> encomendas_pend, List<Transportadora> transportadoras, List<Utilizador> utilizadores,
                   List<Artigo> artigos, Map<Integer, List<String>> artigos_venda, Map<Integer, List<String>> artigos_vendidos) {
        this.setEncomendas_pend(encomendas_pend);
        this.setTransportadoras(transportadoras);
        this.setUtilizadores(utilizadores);
        this.setArtigos(artigos);
        this.setArtigos_venda(artigos_venda);
        this.setArtigos_vendidos(artigos_vendidos);
    }

    public Mercado(Mercado marketPlace) {
        this.encomendas_pend = marketPlace.getEncomendas_pend();
        this.transportadoras = marketPlace.getTransportadoras();
        this.utilizadores = marketPlace.getUtilizadores();
        this.artigos = marketPlace.getArtigos();
        this.artigos_venda = marketPlace.getArtigos_venda();
        this.artigos_vendidos = marketPlace.getArtigos_vendidos();
    }


    /**
     * Getters dos objetos da classe Mercado
     */
    public Map<Integer, List<Encomenda>> getEncomendas_pend() {
        Map<Integer, List<Encomenda>> copia = new HashMap<>();

        for (Map.Entry<Integer, List<Encomenda>> entrada : this.encomendas_pend.entrySet()) {
            int chave = entrada.getKey();
            List<Encomenda> nova_lista = new ArrayList<>();
            nova_lista = entrada.getValue().stream().map(Encomenda::clone).collect(Collectors.toList());

            copia.put(chave, nova_lista);
        }
        return copia;
    }

    public Map<Integer, List<Encomenda>> getEncomendas_pend_semC(){
        return this.encomendas_pend;
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

    public Map<Integer, List<String>> getArtigos_venda() {
        Map<Integer, List<String>> copia = new HashMap<>();

        for (Map.Entry<Integer, List<String>> entrada : this.artigos_venda.entrySet()) {
            int chave = entrada.getKey();
            List<String> nova_lista = new ArrayList<>(entrada.getValue());

            copia.put(chave, nova_lista);
        }
        return copia;
    }

    public Map<Integer, List<String>> getArtigos_vendidos() {
        Map<Integer, List<String>> copia = new HashMap<>();

        for (Map.Entry<Integer, List<String>> entrada : this.artigos_vendidos.entrySet()) {
            int chave = entrada.getKey();
            List<String> nova_lista = new ArrayList<>(entrada.getValue());

            copia.put(chave, nova_lista);
        }
        return copia;
    }

    /**
     * Setters dos objetos da classe Mercado
     */

    public void setTransportadoras(List<Transportadora> transportadoras) {
        this.transportadoras = transportadoras.stream().map(Transportadora::clone).collect(Collectors.toList());
    }


    public void setEncomendas_pend(Map<Integer, List<Encomenda>> encomendas_pend) {
        if(encomendas_pend.isEmpty())
            this.encomendas_pend = new HashMap<>();
        for (Map.Entry<Integer, List<Encomenda>> entrada : encomendas_pend.entrySet()) {
            int chave = entrada.getKey();
            this.encomendas_pend.put(chave, entrada.getValue().stream().map(Encomenda::clone).collect(Collectors.toList()));
        }
    }

    public void setUtilizadores(List<Utilizador> utilizadores) {
        this.utilizadores = utilizadores.stream().map(Utilizador::clone).collect(Collectors.toList());
    }

    public void setArtigos(List<Artigo> artigos) {
        this.artigos = artigos.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public void setArtigos_venda(Map<Integer, List<String>> artigos_venda) {
        if(artigos_venda.isEmpty())
            this.artigos_venda = new HashMap<>();
        for (Map.Entry<Integer, List<String>> entrada : artigos_venda.entrySet()) {
            int chave = entrada.getKey();
            List<String> nova_lista = new ArrayList<>(entrada.getValue());

            this.artigos_venda.put(chave, nova_lista);
        }
    }

    public void setArtigos_vendidos(Map<Integer, List<String>> artigos_vendidos) {
        for (Map.Entry<Integer, List<String>> entrada : artigos_vendidos.entrySet()) {
            int chave = entrada.getKey();
            List<String> nova_lista = new ArrayList<>(entrada.getValue());

            this.artigos_vendidos.put(chave, nova_lista);
        }
    }

    /**
     * Método que adiciona um Utilizador à lista Utilizadores
     *
     * @param utilizador
     */
    public void addUtilizador(Utilizador utilizador) {
        this.utilizadores.add(utilizador.clone());
    }

    /**
     * Método que adiciona uma Transportador à lista transportadoras
     *
     * @param t
     */
    public void addTransportadora(Transportadora t) {
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

        Mercado mercado = (Mercado) o;
        return this.transportadoras.equals(mercado.getTransportadoras()) && this.utilizadores.equals(mercado.getUtilizadores())
                && this.artigos.equals(mercado.getArtigos()) && this.artigos_venda.equals(mercado.getArtigos_venda())
                && this.encomendas_pend.equals(mercado.getEncomendas_pend()) && this.artigos_vendidos.equals(mercado.getArtigos_vendidos());
    }

    /**
     * Método toString que devolve a representação em String do Mercado.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("As encomendas pendentes são as seguintes: ").append(this.encomendas_pend.toString());
        sb.append("\nAs transportadoras são: ").append(this.transportadoras.toString());
        sb.append("\nOs utilizadores são: ").append(this.utilizadores.toString());
        sb.append("\nOs artigos são: ").append(this.artigos.toString());
        sb.append("\nO utilizador com o código x tem os artigos a seguir para venda: ").append(this.artigos_venda.toString());
        sb.append("\nO vendedor com o código x vendeu os artigos com código alfanúmero: ").append(this.artigos_vendidos.toString());

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
     *
     * @param artigo É o artigo que se pretende adicionar
     */
    public void adicionaArtigo(Artigo artigo) {
        this.artigos.add(artigo.clone());
    }

    /**
     * Método que devolve o código do utilizador com o email passado em parâmetro
     *
     * @param email Email do utilizador
     * @return int que é o código do utilizador
     */
    public int codigoUtilizador(String email) {
        for (Utilizador u : utilizadores) {
            if (u.getEmail().equals(email))
                return u.getId();
        }
        return -1;
    }

    /**
     *
     */
    public void adicionaArtigoVenda(int cod, String cod_alfanr) {
        if (this.artigos_venda.containsKey(cod))
            this.artigos_venda.get(cod).add(cod_alfanr);
        else {
            List<String> l = new ArrayList<>();
            l.add(cod_alfanr);
            this.artigos_venda.put(cod, l);
        }
    }

    public void adicionaArtigoVendido(int cod, String cod_alfanr) {
        if (this.artigos_vendidos.containsKey(cod))
            this.artigos_vendidos.get(cod).add(cod_alfanr);
        else {
            List<String> l = new ArrayList<>();
            l.add(cod_alfanr);
            this.artigos_vendidos.put(cod, l);
        }
    }

    public void adicionaEncomendaPend(int cod, Encomenda encomenda) {
        if (this.encomendas_pend.containsKey(cod))
            this.encomendas_pend.get(cod).add(encomenda);
        else {
            List<Encomenda> encomendaList = new ArrayList<>();
            encomendaList.add(encomenda);
            this.encomendas_pend.put(cod, encomendaList);
        }
    }

    public boolean existeTransportadora(String nome) {
        for (Transportadora t : this.transportadoras) {
            if (t.getNome().equals(nome))
                return true;
        }
        return false;
    }

    public Artigo getArtigoByCodigo(String alfnr) {
        for (Artigo artigo : this.artigos) {
            if (artigo.getCod_alfanr().equals(alfnr)) {
                return artigo;
            }
        }
        return null; // not found
    }

    /**
     * Método getUltimaEncPend que obtém a última encomenda pendente do utilizador.
     *
     * @param cod , sendo este o código de utilizador associado à encomenda.
     * @return a última encomenda pendente do utilizador.
     */
    public Encomenda getUltimaEncPend(int cod) {
        List<Encomenda> encomendas = encomendas_pend.get(cod);
        Encomenda enc_pend = new Encomenda();
        if (encomendas != null && !encomendas.isEmpty()) {
            for (Encomenda enc: encomendas) {
                if (enc.getEstado()== Encomenda.Estado.PENDENTE){
                    enc_pend = enc;
                }

            }
            return enc_pend;
        }
        return null;
    }

    public int getIndice(Encomenda encomenda, int cod){
        for(Encomenda enc : this.encomendas_pend.get(cod)){
            if(enc.equals(encomenda)) {
                return this.encomendas_pend.get(cod).indexOf(enc);
            }
        }

        return -1;
    }
}