package Trabalho.src.Controlo;

import Trabalho.src.Erros.NaoExisteTransportadora;
import Trabalho.src.Estado.Recuperar;
import Trabalho.src.Estado.Salvaguarda;
import Trabalho.src.Modelo.*;
import Trabalho.src.Vista.Insercao;
import Trabalho.src.Vista.Menu;
import Trabalho.src.Erros.NaoExisteUtilizador;
import Trabalho.src.Erros.ErroCriarConta;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.Iterator;

public class Controlo {
    private Menu gui;
    private Mercado model;
    private Supplier<String> supplier_String = () -> {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    };
    private Supplier<Integer> supplier_Int = () -> {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    };
    private Supplier<Double> supplier_Double = () -> {
        Scanner input = new Scanner(System.in);
        return input.nextDouble();
    };
    private Supplier<LocalDate> supplier_LocalDate = () -> {
        Scanner input = new Scanner(System.in);
        String dataString = input.nextLine();
        LocalDate data = LocalDate.parse(dataString);
        return data;
    };

    private Supplier<Tshirt.Tamanho> supplier_Tamanho = () -> {
        Scanner input = new Scanner(System.in);
        String tamanho = input.nextLine();

        return Tshirt.Tamanho.valueOf(tamanho);
    };

    private Supplier<Tshirt.Padrao> supplier_Padrao = () -> {
        Scanner input = new Scanner(System.in);
        String padrao = input.nextLine();

        return Tshirt.Padrao.valueOf(padrao);
    };

    private Function<String, Boolean> function_Boolean = s -> {
        boolean b = false;
        boolean valid = false;
        do {
            Scanner input = new Scanner(System.in);
            Menu.mostraMensagem(s + "? (Se sim, escreva sim. Caso contrário escreva não): ");
            String sn = input.nextLine();

            if (sn.equals("sim")) {
                b = true;
                valid = true;
            }
            if (sn.equals("não")) {
                b = false;
                valid = true;
            }
        } while (!valid);

        return b;
    };

    /**
     * Construtor dos objetos da classe Controlo
     * */
    public Controlo() {
        this.gui = new Menu();
        this.model = new Mercado();

    }

    /**
     * Apresenta o menu principal ao utilizador
     * */
    public void run() {
        int op = this.gui.menu(" Bem-vind@ à Vintage ", Menu.menu_Principal_Vintage);

        switch (op) {
            case 1:
                this.logIn(1);
                break;
            case 2:
                this.logIn(0);
                break;

            case 3:
                this.criar_conta();
                this.run();
                break;

            case 4:
                this.transportadoras();
                break;

            case 5:
                this.salvaguardaEstado();
                this.run();
                break;

            case 6:
                this.recuperarEstado();
                this.run();
                break;

            case 7:
                Menu.mostraMensagem("Obrigado por usar Vintage!");
                System.exit(0);
                break;
        }
    }

    /**
     * Apresenta o menu de Log In na aplicação
     * @param tipo É o tipo de utilizador, ou seja, comprador, vendedor ou ambos
     * */
    public void logIn(int tipo) {
        int op = this.gui.menu(" Log In ", Menu.menu_Log_In);

        switch (op) {
            case 1:
                int cod = this.loginVendedor(tipo);
                if (tipo == 1)
                    this.vendedor(cod);
                else this.comprador(cod);
                break;
            case 2:
                this.run();
                break;
        }
    }

    /**
     * Apresenta o menu após a primeira opção selecionada ter sido "Transportadoras"
     * */
    public void transportadoras() {
        int op = this.gui.menu(" Transportadoras ", Menu.menu_Transportadoras);

        switch (op) {
            case 1:
                this.adicionar_transportadora();
                this.run();
                break;

            case 2:
                this.ver_transportadoras();
                this.run();
                break;

            case 3:
                this.run();
                break;
        }
    }

    /**
     * Método que deixa o utilizador entrar na sua conta da Vintage
     * @param tipo É o tipo de utilizador, ou seja, comprador, vendedor ou ambos
     * @return int Retorna -1 em caso de erro
     * */
    public int loginVendedor(int tipo) {
        try {
            String email = Insercao.get_valor("email", this.supplier_String);
            if (!(this.model.procuraUtilizador(email, tipo))) {
                throw new NaoExisteUtilizador("Utilizador não registado");
            }

            return model.codigoUtilizador(email);
        } catch (NaoExisteUtilizador e) {
            Menu.erro(e.getMessage());
            this.run();
        }
        return -1;
    }
    /**
     * Método que apresenta o menu depois de a primeira opção selecionada ter sido "Vendedor"
     * @param cod É o código, ou seja, o identificador do utilizador
     * */
    public void vendedor(int cod) {
        int op = this.gui.menu(" Vendedor ", Menu.menu_Vendedor);

        switch (op) {
            case 1:
                this.adicionar_artigo(cod);
                break;

            case 2:
                this.historico_vendas(cod);
                break;

            case 3:
                //Algum menu que mostre os artigos que se encontram à venda
                this.artigos_para_venda(cod);
                break;

            case 4:
                this.run();
                break;
        }
    }

    /**
     * Método que apresenta o menu de adição de um artigo, de modo a escolher qual o artigo que pretende adicionar
     * @param cod É o código, ou seja, o identificador do utilizador
     * */
    public void adicionar_artigo(int cod) {
        int op = this.gui.menu(" Adicionar Artigo ", Menu.menu_Adicionar_Artigo);

        switch (op) {
            case 1:
                this.adicionaVestuario(cod);
                this.adicionar_artigo(cod);
                break;

            case 2:
                this.adicionaCalcado(cod);
                this.adicionar_artigo(cod);
                break;

            case 3:
                this.adicionaAcessorios(cod);
                this.adicionar_artigo(cod);
                break;

            case 4:
                this.vendedor(cod);
                break;
        }
    }

    /**
     * Método que pede os dados ao utilizador de forma a criar uma conta
     * */
    public void criar_conta() {
        try {
            int tipo_user = Insercao.get_valor(("o seu tipo de conta: \n0 - Comprador \n1 - Vendedor \n2 - Ambos \nEscolha a opção que pretende"), supplier_Int);
            if (tipo_user < 0 || tipo_user > 2) {
                throw new ErroCriarConta("Tipo de utilizador inválido.");
            }
            String email = Insercao.get_valor("email", supplier_String);
            boolean userExists = false; // ver se funciona
            for (int i = 0; i <= 2; i++) {
                userExists = userExists || this.model.procuraUtilizador(email, i);
            }
            if (!(Utilizador.isValidEmail(email)) || userExists) {
                throw new ErroCriarConta("Email inválido ou pré-existente");
            }
            String nome = Insercao.get_valor("nome", supplier_String);
            String morada = Insercao.get_valor("morada", supplier_String);
            String nif = Insercao.get_valor("nif", supplier_String);
            if (!(Utilizador.isValidNIF(nif))) {
                throw new ErroCriarConta("NIF inválido");
            }

            Utilizador user = new Utilizador(email, nome, morada, nif, tipo_user);
            this.model.addUtilizador(user);
            Menu.mostraMensagem("Utilizador criado com sucesso.");
        } catch (ErroCriarConta e) {
            Menu.erro("Não foi possível criar a conta devido a " + e.getMessage());
            this.run();
        }
    }

    /**
     * Método que apresenta o histórico de vendas de um vendedor
     * @param cod É o código, ou seja, o identificador do utilizador
     * */
    public void historico_vendas(int cod) {
        Utilizador utilizador = model.getUtilizadores().get(cod-1);
        List<Fatura> faturas = utilizador.getFaturaVendedor();
        if (faturas.isEmpty()) {
            Menu.mostraMensagem("O utilizador '" + utilizador.getNome() + "' ainda não tem faturas de vendas registadas.");
            this.vendedor(cod);
        }
        Menu.mostraMensagem("Histórico de vendas do utilizador '" + utilizador.getNome() + "':");
        for (Fatura fatura : faturas) {
            Menu.mostraMensagem(fatura.toString());
        }
        this.vendedor(cod);
    }

    /**
     * Método que mostra os artigos que um determinado vendedor ainda tem para venda
     * @param cod É o código, ou seja, o identificador do utilizador
     * */
    public void artigos_para_venda(int cod) {
            Utilizador utilizador = model.getUtilizadores().get(cod - 1);
            List<String> artigosVenda = model.getArtigos_venda().get(cod);
            if (artigosVenda != null) {
                Menu.mostraMensagem("Artigos do vendedor '" + utilizador.getNome() + "':");
                for (String codAlfanr : artigosVenda) {
                    for (Artigo artigo : model.getArtigos()) {
                        if (artigo.getCod_alfanr().equals(codAlfanr)) {
                            Menu.mostraMensagem(artigo.toString());
                        }
                    }
                }
            } else Menu.mostraMensagem("Este utilizador não tem artigos à venda.");
        this.vendedor(cod);
    }

    /**
     * Método que adiciona uma transportadora à aplicação
     * */
    public void adicionar_transportadora() {
        Transportadora transportadora = null;
        String nome= "";
        String preco_expedicao,preco_expedicao_premium;
        try{
            nome = Insercao.get_valor("nome da transportadora", supplier_String);

            if(this.model.existeTransportadora(nome)||nome=="")
                throw new NaoExisteTransportadora("A transportadora já se encontra associada.");

        }catch (NaoExisteTransportadora e){
            Menu.erro(e.getMessage());
            this.transportadoras();
        }
        preco_expedicao = Insercao.get_valor("a fórmula do preço de expedição de forma genérica onde escreve ValorBase, Imposto e margemlucro", supplier_String);
        boolean premium = Insercao.get_tipo("Faz entregas de artigos premium", function_Boolean);

        if(premium){
            preco_expedicao_premium = Insercao.get_valor(" a fórmula do preço de expedição premium", supplier_String);
            transportadora = new Transportadora(nome, preco_expedicao, preco_expedicao_premium);
        }else transportadora = new Transportadora(nome, preco_expedicao);

        model.addTransportadora(transportadora);
        Menu.mostraMensagem("Transportadora adicionada com sucesso!");
    }

    /**
     * Método que informa o utilizador quais as transportadoras associadas à Vintage
     * */
    public void ver_transportadoras() {
        List<Transportadora> transportadoras = model.getTransportadoras();

        if (transportadoras.isEmpty()) {
            Menu.mostraMensagem("Não há transportadoras associadas atualmente.");
        } else {
            Menu.mostraMensagem("Transportadoras associadas atualmente:");

            for (Transportadora t : transportadoras) {
                Menu.mostraMensagem("- " + t.getNome());
            }
        }
    }

    /**
     * Método que coloca uma Tshirt à venda
     * @param cod É o código, ou seja, o identificador do utilizador
     * */
    public void adicionaVestuario(int cod){
        Tshirt tshirt = null;

        String cod_alfnr = "";
        while (cod_alfnr == "") {
            cod_alfnr = Insercao.get_valor("um código alfanumérico válido", supplier_String);
            for (Artigo artigo : model.getArtigos()) {
                if (artigo.getCod_alfanr().equals(cod_alfnr)) {
                    Menu.mostraMensagem("Já existe um artigo à venda com este código.");
                    cod_alfnr = "";
                }
            }
        }
        String marca = Insercao.get_valor("a marca", supplier_String);
        String descricao = Insercao.get_valor("a descrição", supplier_String);
        double preco_base = Insercao.get_valor("o preço base", supplier_Double);
        String transportadora = "";

        try {
            transportadora = Insercao.get_valor("o nome da transportadora", supplier_String);

            if(!this.model.existeTransportadora(transportadora))
                throw new NaoExisteTransportadora("Essa transportadora não se encontra associada à Vintage.");

        }catch (NaoExisteTransportadora e){
            Menu.erro(e.getMessage());
            this.adicionar_artigo(cod);
        }

        Tshirt.Tamanho tamanho = Insercao.get_valor("o tamanho da thirt (S, M, L, XL)", supplier_Tamanho);
        Tshirt.Padrao padrao = Insercao.get_valor("o padrao da tshirt (Liso, Riscas, Palmeiras)", supplier_Padrao);
        boolean usado = Insercao.get_tipo("É usado", function_Boolean);

        if(usado){
            int av_estado = Insercao.get_valor("a avaliação do seu estado (1- bom, 2- razoável e 3- mau)", supplier_Int);
            int nr_donos = Insercao.get_valor("o número de donos", supplier_Int);
            tshirt = new Tshirt(descricao, marca, cod_alfnr, preco_base,  transportadora, av_estado, nr_donos, tamanho, padrao);
        }else tshirt = new Tshirt(descricao, marca, cod_alfnr, preco_base, transportadora, tamanho, padrao);

        model.adicionaArtigo(tshirt);

        model.adicionaArtigoVenda(cod, tshirt.getCod_alfanr());

        Menu.mostraMensagem("Artigo adicionado com sucesso.");
    }

    /**
     * Método que coloca Sapatilhas à venda
     * @param cod É o código, ou seja, o identificador do utilizador
     * */
    public void adicionaCalcado(int cod){
        Sapatilhas sapatilhas = null;

        String cod_alfnr = "";
        while (cod_alfnr == "") {
            cod_alfnr = Insercao.get_valor("um código alfanumérico válido", supplier_String);
            for (Artigo artigo : model.getArtigos()) {
                if (artigo.getCod_alfanr().equals(cod_alfnr)) {
                    Menu.mostraMensagem("Já existe um artigo à venda com este código.");
                    cod_alfnr = "";
                }
            }
        }
        String marca = Insercao.get_valor("a marca", supplier_String);
        String descricao = Insercao.get_valor("a descrição", supplier_String);
        double preco_base = Insercao.get_valor("o preço base", supplier_Double);
        String transportadora = "";
        try {
            transportadora = Insercao.get_valor("o nome da transportadora", supplier_String);

            if(!this.model.existeTransportadora(transportadora))
                throw new NaoExisteTransportadora("Transportadora inválida");

        }catch (NaoExisteTransportadora e){
            Menu.erro("Essa transportadora não se encontra associada à Vintage.");
            this.adicionar_artigo(cod);
        }

        int tamanho = Insercao.get_valor("o tamanho do calçado", supplier_Int);
        String cor = Insercao.get_valor("a cor", supplier_String);
        boolean atacadores = Insercao.get_tipo("Tem atacadores", function_Boolean);
        List<Transportadora> transportes = model.getTransportadoras();
        boolean expedicao_p = false;
        for (Transportadora transporte : transportes) {
            if (transporte.getNome().equals(transportadora)){
                expedicao_p = transporte.isPremium();
            }
        }
        boolean premium = false;
        if (expedicao_p) {
            premium = Insercao.get_tipo("É premium", function_Boolean);
        }
        LocalDate data_lancamento = Insercao.get_valor("a data de lançamento (no formato aaaa-mm-dd)", supplier_LocalDate);

        boolean usado = Insercao.get_tipo("É usado", function_Boolean);
        if(usado){
            int av_estado = Insercao.get_valor("a avaliação do seu estado (1- bom, 2- razoável e 3- mau)", supplier_Int);
            int nr_donos = Insercao.get_valor("o número de donos", supplier_Int);
            sapatilhas = new Sapatilhas(descricao, marca, cod_alfnr, preco_base, transportadora, av_estado, nr_donos, tamanho, atacadores, cor, data_lancamento, premium);
        }else sapatilhas = new Sapatilhas(descricao, marca, cod_alfnr, preco_base, transportadora, tamanho, atacadores, cor, data_lancamento, premium);

        model.adicionaArtigo(sapatilhas);

        model.adicionaArtigoVenda(cod, sapatilhas.getCod_alfanr());

        Menu.mostraMensagem("Artigo adicionado com sucesso.");
    }

    /**
     * Método que coloca uma Mala à venda
     * @param cod É o código, ou seja, o identificador do utilizador
     * */
    public void adicionaAcessorios(int cod){
        Mala mala = null;

        String cod_alfnr = "";
        while (cod_alfnr == "") {
            cod_alfnr = Insercao.get_valor("um código alfanumérico válido", supplier_String);
            for (Artigo artigo : model.getArtigos()) {
                if (artigo.getCod_alfanr().equals(cod_alfnr)) {
                    Menu.mostraMensagem("Já existe um artigo à venda com este código.");
                    cod_alfnr = "";
                }
            }
        }
        String marca = Insercao.get_valor("a marca", supplier_String);
        String descricao = Insercao.get_valor("a descrição", supplier_String);
        double preco_base = Insercao.get_valor("o preço base", supplier_Double);
        String transportadora = "";
        try {
            transportadora = Insercao.get_valor("o nome da transportadora", supplier_String);

            if(!this.model.existeTransportadora(transportadora))
                throw new NaoExisteTransportadora("Transportadora inválida");

        }catch (NaoExisteTransportadora e){
            Menu.erro("Essa transportadora não se encontra associada à Vintage.");
            this.adicionar_artigo(cod);
        }

        double altura = Insercao.get_valor("a altura", supplier_Double);
        double comprimento = Insercao.get_valor("o comprimento", supplier_Double);
        double profundidade = Insercao.get_valor("a profundidade", supplier_Double);
        String material = Insercao.get_valor("o material", supplier_String);
        int ano_colecao = Insercao.get_valor("o ano da coleção", supplier_Int);
        List<Transportadora> transportes = model.getTransportadoras();
        boolean expedicao_p = false;
        for (Transportadora transporte : transportes) {
            if (transporte.getNome().equals(transportadora)){
            expedicao_p = transporte.isPremium();
            }
        }
        boolean premium = false;
        if (expedicao_p) {
             premium = Insercao.get_tipo("É premium", function_Boolean);
        }
        boolean usado = Insercao.get_tipo("É usado", function_Boolean);

        if(usado){
            int av_estado = Insercao.get_valor("a avaliação do seu estado (1- bom, 2- razoável e 3- mau)", supplier_Int);
            int nr_donos = Insercao.get_valor("o número de donos", supplier_Int);
            mala = new Mala(descricao, marca, cod_alfnr, preco_base, transportadora, av_estado, nr_donos, altura, comprimento, profundidade, material, ano_colecao, premium);
        }else mala = new Mala(descricao, marca, cod_alfnr, preco_base, transportadora, altura, comprimento, profundidade, material, ano_colecao, premium);

        model.adicionaArtigo(mala);

        model.adicionaArtigoVenda(cod, mala.getCod_alfanr());

        Menu.mostraMensagem("Artigo adicionado com sucesso.");
    }
    /**
     * Método que mostra ao comprador o menu após ter selecionado a opção comprador e ter entrado na sua conta
     * @param cod É o código, ou seja, o identificador do utilizador
     * */
    public void comprador(int cod){
        int op = this.gui.menu(" Comprador ", Menu.menu_Comprador);

        switch (op){
            case 1:
                this.criarEncomenda(cod);
                break;

            case 2:
                this.artigos_a_venda(cod,true);
                this.comprador(cod);
                break;

            case 3:
                this.historico_encomendas(cod);
                break;

            case 4:
                this.devolucao(cod);
                this.comprador(cod);
                break;

            case 5:
                this.run();
                break;
        }
    }

    /**
     * Método que apresenta ao utilizador o menu após selecionar a opção "gerir encomenda"
     * @param cod É o código, ou seja, o identificador do utilizador
     * */
    public void criarEncomenda(int cod){
        int op = this.gui.menu(" Criar Encomenda ", Menu.menu_Criar_Encomenda);

        switch (op){
            case 1:   //add artigo a encomenda pendente
                this.add_artigo_enc(cod);
                break;

            case 2:  //remove artigo da encomenda pendente
                this.remove_artigo_enc(cod);
                break;

            case 3: // finaliza encomenda
                finaliza_enc(cod);
                break;

            case 4:
                this.comprador(cod);
                break;
        }
    }

    /**
     * Método que adiciona um artigo a uma encomenda
     * @param cod É o código, ou seja, o identificador do utilizador
     * */
    private void add_artigo_enc(int cod) {
        // Mostrar artigos à venda
        this.artigos_a_venda(cod, true);

        // Selecionar o código alfa numérico do artigo que deseja
        String codalfa = Insercao.get_valor("o código alfa numérico do artigo que deseja", supplier_String);

        // Iterar sobre o Map artigos_venda e encontrar o ID do vendedor associado ao codalfa
        int sellerID = -1;
        for (Map.Entry<Integer, List<String>> entry : model.getArtigos_venda().entrySet()) {
            List<String> alfanr = entry.getValue();
            if (alfanr.contains(codalfa)) {
                sellerID = entry.getKey();
            }
        }

        // Verificar se o codalfa recebido é de um artigo que está à venda
        if (sellerID == -1) {
            Menu.mostraMensagem("O código alfanumérico não pertence aos artigos à venda");
            this.comprador(cod);
        }

            List<Encomenda> encomendasPendentes = model.getEncomendas_pend().get(cod);
            // Criar uma nova lista de encomendas pendentes se ainda não houver nenhuma para o comprador
            if (encomendasPendentes == null) {
                encomendasPendentes = new ArrayList<>();
            }

            // adicionar à lista de encomendas pendentes do comprador
            Encomenda encomenda;
            if (encomendasPendentes.isEmpty()) {
                encomenda = new Encomenda();
                encomendasPendentes.add(encomenda);
            } else {
                encomenda = encomendasPendentes.get(encomendasPendentes.size() - 1); // obter a última encomenda criada
                if (encomenda.getEstado()!= Encomenda.Estado.PENDENTE) {
                    encomenda = new Encomenda();
                    encomendasPendentes.add(encomenda);
                }
            }
            encomenda.addArtigo(this.model.getArtigoByCodigo(codalfa)); // adicionar o artigo selecionado à encomenda
            Map<Integer, List<Encomenda>> novoMap = new HashMap<>();
            novoMap.put(cod, encomendasPendentes);
            model.setEncomendas_pend(novoMap);


            // Adicionar ao map artigos_vendidos
            Map<Integer,List<String>> mapVendido = this.model.getArtigos_vendidos();
            List<String> artigosVendidos = this.model.getArtigos_vendidos().get(sellerID);
            if (artigosVendidos != null) {
                artigosVendidos.add(codalfa);
            } else {
                artigosVendidos = new ArrayList<>();
                artigosVendidos.add(codalfa);
            }
            mapVendido.put(sellerID, artigosVendidos);
            model.setArtigos_vendidos(mapVendido);


            // Remover o artigo selecionado do map artigos_venda
            Map<Integer, List<String>> mapParaVenda = this.model.getArtigos_venda();
            List<String> artigosParaVenda = this.model.getArtigos_venda().get(sellerID);
            artigosParaVenda.remove(codalfa);
            if (artigosParaVenda.isEmpty()) {
                mapParaVenda.remove(sellerID);
            } else {
                mapParaVenda.put(sellerID, artigosParaVenda);
            }
            model.setArtigos_venda(mapParaVenda);
            Menu.mostraMensagem("Artigo adicionado à encomenda com sucesso.");
            this.criarEncomenda(cod);
    }
    /**
     * Método remove_artigo_enc que remove um artigo da encomenda.
     * @param cod , sendo este o código de utilizador associado à encomenda.
     */
    public void remove_artigo_enc(int cod){
        // Mostrar artigos na encomenda
        if(!this.model.getEncomendas_pend().containsKey(cod) || this.model.getEncomendas_pend().isEmpty()){
            Menu.mostraMensagem("Ainda não existem encomendas.");
        }
        List<Encomenda> encs = this.model.getEncomendas_pend().get(cod);
        if (encs != null) {
            for (Encomenda enc : encs) {
               if (enc.getEstado()== Encomenda.Estado.PENDENTE) {Menu.mostraMensagem(enc.toString());}
            }
        } else { Menu.mostraMensagem("Ainda não existem encomendas.");}

        // Selecionar o código alfa numérico do artigo que deseja remover
        String codalfa = Insercao.get_valor("o código alfa numérico do artigo que deseja remover", supplier_String);

        // remover da encomenda
        Map<Integer, List<Encomenda>> enc_map = model.getEncomendas_pend();
        if (enc_map.isEmpty()) {
            Menu.mostraMensagem("Não existem encomendas pendentes");
            comprador(cod);
        }

        List<Encomenda> encomendas = enc_map.get(cod);
        if (encomendas == null) {
            Menu.mostraMensagem("Não existem encomendas pendentes");
            comprador(cod);
        } else {
            for (Encomenda enc : encomendas) {
                if (enc.getEstado() == Encomenda.Estado.PENDENTE) {
                    List<Artigo> artigos = enc.getArtigos();
                    List<Artigo> artigosToRemove = new ArrayList<>();

                    if (artigos.isEmpty()) {
                        Menu.mostraMensagem("Não existem artigos para remover.");
                        comprador(cod);
                    } else {
                        for (Artigo art : artigos) {
                            if (art.getCod_alfanr().equals(codalfa)) {
                                artigosToRemove.add(art);
                            }
                        }
                        if (artigosToRemove.isEmpty()) {
                            Menu.mostraMensagem("Não existem artigos com esse código");
                            comprador(cod);
                        } else {
                            artigos.removeAll(artigosToRemove);
                            enc.setArtigos(artigos);
                            encomendas.remove(enc);
                            encomendas.add(enc);
                            enc_map.put(cod, encomendas);
                            model.setEncomendas_pend(enc_map);
                        }
                    }
                }
            }
        }

        // Iterar sobre o Map encomendas_pend e encontrar o ID do vendedor associado ao codalfa
        int sellerID = -1;
        for (Map.Entry<Integer, List<String>> entry : this.model.getArtigos_vendidos().entrySet()) {
            int vendID = entry.getKey();
            List<String> vendArtigos = entry.getValue();
            if (vendArtigos.contains(codalfa)) {
                sellerID = vendID;
                break;
            }
        }
        // Verificar se o codalfa recebido é de um artigo que está na encomenda
        if (sellerID == -1) {
            Menu.mostraMensagem("O código alfanumérico não pertence aos artigos à venda");
            this.comprador(cod);
        }
        // Remover do map artigos_vendidos
        Map<Integer,List<String>> mapVendido = model.getArtigos_vendidos();
        List<String> artigosVendidos = model.getArtigos_vendidos().get(sellerID);
        artigosVendidos.remove(codalfa);
        if (artigosVendidos.isEmpty()) {
            mapVendido.remove(sellerID);
        }
        else{
            mapVendido.remove(sellerID);
        }
        mapVendido.put(sellerID, artigosVendidos);
        model.setArtigos_vendidos(mapVendido);

        // Adicionar ao map artigos_venda
        Map<Integer,List<String>> mapVenda = model.getArtigos_venda();
        List<String> artigosVenda = model.getArtigos_venda().get(sellerID);
        if(artigosVenda != null){
            artigosVenda.add(codalfa);
        }
        else{
            artigosVenda = new ArrayList<>();
            artigosVenda.add(codalfa);
        }
        mapVenda.put(sellerID, artigosVenda);
        model.setArtigos_venda(mapVenda);

        Menu.mostraMensagem("Artigo removido da encomenda com sucesso.");
        this.criarEncomenda(cod);
    }

    /**
     * Método que mostra ao utilizador os artigos que se encontram à venda
     * @param cod É o código, ou seja, o identificador do utilizador
     * @param enc É um booleano que identica se é necessária a criação de uma encomenda
     * */
    public void artigos_a_venda(int cod, boolean enc) {
        Menu.mostraMensagem("Artigos à venda:");
        List<String> artigosVenda = new ArrayList<>();
        for (List<String> strings : model.getArtigos_venda().values()) {
            for (String str : strings) {
                artigosVenda.add(str);
            }
        }
        if (!artigosVenda.isEmpty()) {
            for (String codAlfanr : artigosVenda) {
                Artigo artigo = model.getArtigoByCodigo(codAlfanr);
                Menu.mostraMensagem(artigo.toString());
            }
        } else {
            Menu.mostraMensagem("Não tem artigos à venda.");
            if(enc)
                this.criarEncomenda(cod);
            else this.comprador(cod);
        }
    }

    /**
     * Mostra o histórico de encomendas de um determinado comprador, identificado pelo seu código.
     * Se não existirem encomendas para o comprador, mostra uma mensagem de aviso.
     *
     * @param cod O código do comprador.
     */
    public void historico_encomendas(int cod) {
        if(!this.model.getEncomendas_pend().containsKey(cod) || this.model.getEncomendas_pend().isEmpty()){
            Menu.mostraMensagem("Ainda não existem encomendas.");
        }
        List<Encomenda> encomendas = this.model.getEncomendas_pend().get(cod);
        if (encomendas != null) {
            for (Encomenda enc : encomendas) {
                Menu.mostraMensagem(enc.toString());
            }
        } else { Menu.mostraMensagem("Ainda não existem encomendas.");
        }
        this.comprador(cod);
    }

    /**
     * Método que vai dar uma encomenda como finalizada, não sendo possível adicionar mais nenhum artigo
     * @param cod É o código, ou seja, o identificador do utilizador
     * */
    public void finaliza_enc(int cod) {
        Map<Integer, List<Encomenda>> map_enc = model.getEncomendas_pend();
        List<Encomenda> encomendas = map_enc.get(cod);
        if (encomendas == null) {
            Menu.mostraMensagem("Não tem artigos na encomenda.");
            comprador(cod);
        }
        Encomenda enc_pend = null;
        for (Encomenda enc : encomendas) {
            if (enc.getEstado() == Encomenda.Estado.PENDENTE) {
                enc_pend = enc;
                break;
            }
        }
        if (enc_pend == null) {
            Menu.mostraMensagem("Não tem artigos na encomenda.");
            comprador(cod);
            return;
        }

        List<Artigo> artigos = enc_pend.getArtigos();
        int dimensao = artigos.size();
        if (dimensao == 0) {
            Menu.mostraMensagem("Não tem artigos na encomenda.");
            comprador(cod);
            return;
        }
        if (dimensao < 2) {
            enc_pend.setDimensao(Encomenda.Dimensao.PEQUENO);
        } else if (dimensao < 5) {
            enc_pend.setDimensao(Encomenda.Dimensao.MEDIO);
        } else {
            enc_pend.setDimensao(Encomenda.Dimensao.GRANDE);
        }

        double preco_final = enc_pend.getPreco_final() +  preco_transporte(enc_pend);
        Menu.mostraMensagem("O preço final da encomenda são: " + preco_final + "€.");

        // Depois de mostrar o preço, deseja continuar?
        boolean continuar = Insercao.get_tipo("Deseja continuar com a compra", function_Boolean);
        if (!continuar) {
            comprador(cod);
            return;
        }
        // Finaliza
        enc_pend.setEstado(Encomenda.Estado.FINALIZADA); //<-- useless
        enc_pend.setData(LocalDate.now());
        enc_pend.setEstado(Encomenda.Estado.EXPEDIDA);
        map_enc.put(cod, encomendas);
        model.setEncomendas_pend(map_enc);

        // Faturas
        List<Utilizador> utilizadores = model.getUtilizadores();
        Utilizador comprador = null;
        for (Utilizador u : utilizadores) {
            if (u.getId() == cod) {
                comprador = u;
                break;
            }
        }

        //add fatura compra
        String nif_comprador = comprador.getNif();
        Fatura fatura_compra = new Fatura(enc_pend, preco_final, nif_comprador);
        comprador.addFaturaComprador(fatura_compra);

        //add fatura venda
        // Percorrer cada Artigo no arquivo enc_pend. Para cada Artigo, encontrar o vendedor. Se já processou um Artigo a ser vendido pelo mesmo Utilizador, some o seu preço ao total preco_artigos desse vendedor. Caso contrário, adicionar o preço do Artigo atual. Fatura fatura_venda = new Fatura(enc_pend, preco_artigos, nif_vendedor); para cada vendedor. Adicione a Fatura à lista de Fatura do vendedor.
        // Para cada artigo na encomenda pendente
        for (Artigo artigo : enc_pend.getArtigos()) {
            // Inicializa as variáveis que serão utilizadas para obter as informações do vendedor
            String codalfa = artigo.getCod_alfanr();
            int sellerID = -1;
            double preco_artigos = 0.0;

            // Percorre o mapa de artigos vendidos para encontrar o ID do vendedor
            for (Map.Entry<Integer, List<String>> entry : model.getArtigos_vendidos().entrySet()) {
                int vendID = entry.getKey();
                List<String> vendArtigos = entry.getValue();

                // Se a lista de artigos vendidos do vendedor contém o código alfanumérico do artigo da encomenda,
                // então o ID do vendedor é atribuído à variável sellerID e o loop é interrompido.
                if (vendArtigos.contains(codalfa)) {
                    sellerID = vendID;
                    break;
                }
            }

            // Se o ID do vendedor foi encontrado
            if (sellerID != -1) {
                Utilizador vendedor = null;

                // Procura o vendedor na lista de utilizadores
                for (Utilizador u : utilizadores) {
                    if (u.getId() == sellerID) {
                        vendedor = u;
                        break;
                    }
                }

                // Se o vendedor foi encontrado
                if (vendedor != null) {
                    String nif_vendedor = vendedor.getNif();
                    // Percorre todos os artigos da encomenda pendente novamente
                    // para encontrar todos os artigos vendidos pelo mesmo vendedor e calcular o preço total
                    for (Artigo art : enc_pend.getArtigos()) {
                        if (art.getCod_alfanr().equals(codalfa)) {
                            preco_artigos += art.preco();
                        }
                    }

                    // Cria a fatura para o vendedor com o preço total e o NIF do vendedor
                    Fatura fatura_venda = new Fatura(enc_pend, preco_artigos, nif_vendedor);
                    vendedor.addFaturaVendedor(fatura_venda);
                }
            }
        }
        Menu.mostraMensagem("Compra realizada com sucesso!");
        comprador(cod);
    }

    /**
     * Método que nos indica o o preço de expedição de uma determinada encomenda
     * @param encomenda É a encomenda cujo preço de expedição de transporte queremos saber
     * @return double É o preço de expedição de transporte da encomenda
     * */
    public double preco_transporte(Encomenda encomenda){
        List<Artigo> artigos = encomenda.getArtigos();
        List<Transportadora> transportadoras = model.getTransportadoras();
        int nrArtigos=0;
        double preco_trans=0;
        for (Transportadora transportadora : transportadoras) {
            for (Artigo artigo : artigos) {
               if (transportadora.getNome().equals(artigo.getTransportadora())){
                   nrArtigos+=1;
                }
            }
             // o que era suposto dar
            if (nrArtigos==0) {nrArtigos=0;}
            else { if (nrArtigos<2){
                Menu.mostraMensagem("Custo do transporte dos artigos pela " + transportadora.getNome() +":" +
                 transportadora.precoTransporte(transportadora.isPremium(),1,1) );
                preco_trans += transportadora.precoTransporte(transportadora.isPremium(),1,1);
            } else if (nrArtigos<5) {
                Menu.mostraMensagem("Custo do transporte dos artigos pela " + transportadora.getNome() + ":"+
                transportadora.precoTransporte(transportadora.isPremium(), 2,1));
                preco_trans += transportadora.precoTransporte(transportadora.isPremium(), 2,1);
            } else {
                Menu.mostraMensagem("Custo do transporte dos artigos pela " + transportadora.getNome() + ":"+
                transportadora.precoTransporte(transportadora.isPremium(), 3.5,1));
                preco_trans += transportadora.precoTransporte(transportadora.isPremium(), 3.5,1);
            }
            nrArtigos=0;
            }
        }
        return preco_trans;
    }

    /**
     * Método que vai proceder à devolução de uma encomenda
     * @param cod É o código, ou seja, o identificador do utilizador
     * */
    public void devolucao(int cod) {
        // Ir ao historico de encomendas deste user
        List<Encomenda> historico = model.getEncomendas_pend().get(cod);
        if (historico == null) {
            Menu.mostraMensagem("Não existem encomendas capazes de devolução.");
            this.comprador(cod);
            return;
        }

        // Filtrar de modo a apenas apresentar as que 1- Foram expedidas 2- foram feitas à menos de 2 dias
        List<Encomenda> enc_elegiveis = new ArrayList<>();
        for (Encomenda encomenda : historico) {
            long dias_passados = ChronoUnit.DAYS.between(encomenda.getData(), LocalDate.now());
            if (encomenda.getEstado() == Encomenda.Estado.EXPEDIDA && dias_passados <= 2) {
                enc_elegiveis.add(encomenda);
            }
        }
        if (enc_elegiveis.isEmpty()) {
            Menu.mostraMensagem("Não existem encomendas capazes de devolução.");
            comprador(cod);
            return;
        }

        // Arranjar maneira de deixar o User escolher a encomenda
        Menu.mostraMensagem("Encomendas disponíveis para devolução:");
        for (int i = 0; i < enc_elegiveis.size(); i++) {
            Menu.mostraMensagem((1 + i) + ". " + enc_elegiveis.get(i).toString());
        }
        Menu.mostraMensagem("Para cancelar digite 0.");
        int escolha = -1;
        while (true) {
            escolha = Insercao.get_valor("um número válido correspondente à encomenda que deseja devolver", supplier_Int);
            if (escolha == 0) {
                this.comprador(cod);
                return;
            }
            else if (escolha > 0 && escolha <= enc_elegiveis.size()) {
                break;
            }
            else {
                Menu.mostraMensagem("Escolha inválida. Tente novamente.");
            }
        }


        Encomenda encomenda_devolvida = enc_elegiveis.get((escolha - 1));

        int indice = this.model.getIndice(encomenda_devolvida, cod);

        this.model.getEncomendas_pend_semC().get(cod).remove(indice);

        List<Artigo> artigos_devolucao = encomenda_devolvida.getArtigos();
        for (Artigo artigo : artigos_devolucao) {
            String codalfa = artigo.getCod_alfanr();
            int sellerID = -1;
            for (Map.Entry<Integer, List<String>> entry : this.model.getArtigos_vendidos().entrySet()) {
                int vendID = entry.getKey();
                List<String> vendArtigos = entry.getValue();

                // Se a lista de artigos vendidos do vendedor contém o código alfanumérico do artigo da encomenda,
                // então o ID do vendedor é atribuído à variável sellerID e o loop é interrompido.
                if (vendArtigos.contains(codalfa)) {
                    sellerID = vendID;
                    break; // adicionado para interromper o loop quando encontrar o vendedor
                }
            }
            if (sellerID != -1) {
                Utilizador vendedor = this.model.getUtilizadores().get(sellerID-1);
                Iterator<Fatura> iterator = vendedor.getFaturaVendedor().iterator();

                while(iterator.hasNext()){
                    Fatura faturaV = iterator.next();
                    if(faturaV.getEnc().equals(encomenda_devolvida))
                        iterator.remove();
                }
            }
        }

        Utilizador comprador = this.model.getUtilizadores().get(cod-1);
        Iterator<Fatura> iterator = comprador.getFaturaComprador().iterator();
        while(iterator.hasNext()){
            Fatura faturaC = iterator.next();
            if(faturaC.getEnc().equals(encomenda_devolvida))
                iterator.remove();
        }

        Menu.mostraMensagem("A encomenda foi devolvida com sucesso!");
    }

    /**
     * Método que guarda o estado da aplicação num ficheiro texto (txt)
     * */
    public void salvaguardaEstado(){
        String caminhoFicheiro = Insercao.get_valor("o caminho (completo) pretendido para o ficheiro", supplier_String);

        try{
            PrintWriter escreve = new PrintWriter(new FileWriter(caminhoFicheiro, true));

            Consumer<Artigo> consumer_Artigo = (artigo) -> {
                escreve.println(artigo.umalinhaString());
            };

            Consumer<Utilizador> consumer_Utilizador = (utilizador) -> {
                escreve.println(utilizador.umalinhaString());
            };

            Consumer<Transportadora> consumer_Transportadora = (transportadora) -> {
                escreve.println(transportadora.umalinhaString());
            };

            Consumer<Map.Entry<Integer, List<Encomenda>>> consumer_encomenda = (entrada) -> {
                int chave = entrada.getKey();
                List<Encomenda> encomendas = entrada.getValue();

                for(Encomenda enc : encomendas){
                    escreve.print("Encomendas_Pendentes:");
                    escreve.println(chave + "=" + enc.umalinhaString());
                }
            };

            BiConsumer<String, Map.Entry<Integer, List<String>>> biConsumer_string = (nome, entrada) ->{
                int chave = entrada.getKey();
                List<String> strings = entrada.getValue();

                for(String s : strings){
                    escreve.print(nome + ":");
                    escreve.println(chave + "=" + s);
                }
            };

            Salvaguarda.escreveFicheiro(this.model.getArtigos(), consumer_Artigo);
            Salvaguarda.escreveFicheiro(this.model.getUtilizadores(), consumer_Utilizador);
            Salvaguarda.escreveFicheiro(this.model.getTransportadoras(), consumer_Transportadora);
            Salvaguarda.escreveFicheiroMap(this.model.getEncomendas_pend(), consumer_encomenda);
            Salvaguarda.escreveFicheiroMap(this.model.getArtigos_venda(), "Artigos_venda", biConsumer_string);
            Salvaguarda.escreveFicheiroMap(this.model.getArtigos_vendidos(), "Artigos_vendidos", biConsumer_string);

            escreve.close();
            Menu.mostraMensagem("Estado guardado com sucesso!");

        }catch (IOException e){
            Menu.erro("Não foi possível guardar o estado!");
        }
    }

    /**
     * Método que lê um ficheiro de texto e carrega para objetos essa informação
     * */
    public void recuperarEstado(){
        String nomeFicheiro = Insercao.get_valor("o caminho (completo) do ficheiro onde pretende recuperar o estado", supplier_String);
        List<String> linhas = Recuperar.lerFicheiro(nomeFicheiro);
        String[] linhaPartida;
        Utilizador utilizador = null;
        Transportadora transportadora = null;
        Artigo artigo = null;
        Encomenda encomenda = null;

        for(String linha : linhas){
            linhaPartida = linha.split(":");
            switch(linhaPartida[0]){
                case "Utilizador":
                    utilizador = Recuperar.parseUtilizador(linhaPartida[1]);
                    this.model.addUtilizador(utilizador);
                    break;

                case "Transportadora":
                    transportadora = Recuperar.parseTransportadora(linhaPartida[1]);
                    this.model.addTransportadora(transportadora);
                    break;

                case "Encomendas_Pendentes":
                    String[] chave_valor = linhaPartida[1].split("=");
                    int chaveEP = Recuperar.parseChaves(chave_valor[0]);
                    encomenda = Recuperar.parseEncomendas_PendenteValor(chave_valor[1]);
                    this.model.adicionaEncomendaPend(chaveEP, encomenda);
                    break;

                case "Artigos_venda":
                    String[] chave_valorAV = linhaPartida[1].split("=");
                    int chaveAV = Recuperar.parseChaves(chave_valorAV[0]);
                    this.model.adicionaArtigoVenda(chaveAV, chave_valorAV[1]);
                    break;

                case "Artigos_vendidos":
                    String[] chave_valorAv = linhaPartida[1].split("=");
                    int chaveAv = Recuperar.parseChaves(chave_valorAv[0]);
                    this.model.adicionaArtigoVendido(chaveAv, chave_valorAv[1]);
                    break;

                default:
                    String[] art = linhaPartida[0].split("£");

                    switch (art[0]) {
                        case "Sapatilhas":
                            artigo = Recuperar.parseSapatilhas(art[1]);
                            this.model.adicionaArtigo(artigo);
                            break;

                        case "Mala":
                            artigo = Recuperar.parseMala(art[1]);
                            this.model.adicionaArtigo(artigo);
                            break;

                        case "Tshirt":
                            artigo = Recuperar.parseTshirt(art[1]);
                            this.model.adicionaArtigo(artigo);
                            break;
                    }
            }
        }
    }


}