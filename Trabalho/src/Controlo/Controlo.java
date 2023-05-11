package Trabalho.src.Controlo;

import Trabalho.src.Erros.NaoExisteTransportadora;
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
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

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

    //VER SE ESTAS DUAS SÃO PRECISAS PROVAVELMENTE NÃO!!!!!!
    private Supplier<Encomenda.Dimensao> supplier_Dimensao = () -> {
        Scanner input = new Scanner(System.in);
        String dimensao = input.nextLine();

        return Encomenda.Dimensao.valueOf(dimensao);
    };

    private Supplier<Encomenda.Estado> supplier_Estado = () -> {
        Scanner input = new Scanner(System.in);
        String estado = input.nextLine();

        return Encomenda.Estado.valueOf(estado);
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



    public Controlo() {
        this.gui = new Menu();
        this.model = new Mercado();

    }

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
                //Método usado para recuperar o estado
                this.run();
                break;

            case 7:
                Menu.mostraMensagem("Obrigado por usar Vintage!");
                System.exit(0);
                break;
        }
    }

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



    public void adicionar_transportadora() {
        Transportadora transportadora = null;
        String nome= "";
        String preco_expedicao,preco_expedicao_premium;
        try{
            nome = Insercao.get_valor("nome da transportadora", supplier_String);

            if(this.model.existeTransportadora(nome))
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
    public void ver_transportadoras() { // só da o nome
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

    public void adicionaVestuario(int cod){
        Tshirt tshirt = null;

        String cod_alfnr = Insercao.get_valor("o código alfanumérico", supplier_String);
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

    public void adicionaCalcado(int cod){
        Sapatilhas sapatilhas = null;

        String cod_alfnr = Insercao.get_valor("o código alfanumérico", supplier_String);
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
        boolean premium = Insercao.get_tipo("É premium", function_Boolean);
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

    public void adicionaAcessorios(int cod){
        Mala mala = null;

        String cod_alfnr = Insercao.get_valor("o código alfanumérico", supplier_String);
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
        boolean premium = Insercao.get_tipo("É premium", function_Boolean);

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

    public void comprador(int cod){
        int op = this.gui.menu(" Comprador ", Menu.menu_Comprador);

        switch (op){
            case 1:
                this.criarEncomenda(cod);
                break;

            case 2:
                this.historico_encomendas(cod);
                break;

            case 3:
                this.run(); //???? Talvez algum que mostre os artigos que se encontram à venda
                break;
        }
    }

    public void criarEncomenda(int cod){
        int op = this.gui.menu(" Criar Encomenda ", Menu.menu_Criar_Encomenda);

        switch (op){
            case 1:   //add artigo a encomenda pendente
                add_artigo_enc(cod);
                break;

            case 2:   //remove artigo da encomenda pendente
                break;

            case 3: // finaliza encomenda
                finaliza_enc(cod);
                break;

            case 4:
                this.comprador(cod);
                break;
        }
    }


    private void add_artigo_enc(int cod) {
        // Mostrar artigos à venda
        this.artigos_a_venda();

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
        if (sellerID != -1) {

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
            }
            encomenda.addArtigo(model.getArtigoByCodigo(codalfa)); // adicionar o artigo selecionado à encomenda
            Map<Integer, List<Encomenda>> novoMap = new HashMap<>();
            novoMap.put(cod, encomendasPendentes);
            model.setEncomendas_pend(novoMap);


            // Adicionar ao map artigos_vendidos
            Map<Integer,List<String>> mapVendido = new HashMap<>();
            mapVendido = model.getArtigos_vendidos();
            List<String> artigosVendidos = model.getArtigos_vendidos().get(sellerID);
            if (artigosVendidos != null) {
                artigosVendidos.add(codalfa);
            } else {
                artigosVendidos = new ArrayList<>();
                artigosVendidos.add(codalfa);
            }
            mapVendido.put(sellerID, artigosVendidos);
            model.setArtigos_vendidos(mapVendido);

            // Remover o artigo selecionado do map artigos_venda
            Map<Integer,List<String>> mapParaVenda = new HashMap<>();
            mapParaVenda = model.getArtigos_venda();
            List<String> artigosParaVenda = model.getArtigos_venda().get(sellerID);
            artigosParaVenda.remove(codalfa);
            if (artigosParaVenda.isEmpty()) {
                mapParaVenda.remove(sellerID);
            } else {
                mapParaVenda.put(sellerID, artigosParaVenda);
            }
            model.setArtigos_venda(mapParaVenda);


            Menu.mostraMensagem("Artigo adicionado à encomenda com sucesso.");

        } else {
            Menu.mostraMensagem("O código alfanumérico não pertence aos artigos à venda");
        }
        this.comprador(cod);
    }

    public void artigos_a_venda() {
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
        }
    }

    public void historico_encomendas(int cod) {
        List<Encomenda> encomendas = model.getEncomendas_pend().get(cod);
        if (encomendas != null) {
            for (Encomenda enc : encomendas) {
                Menu.mostraMensagem(enc.toString());
            }
        } else { Menu.mostraMensagem("Ainda não existem encomendas.");
        }
        comprador(cod);
    }

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
        }

        List<Artigo> artigos = enc_pend.getArtigos();
        int dimensao = artigos.size();
        if (dimensao == 0) {
            Menu.mostraMensagem("Não tem artigos na encomenda.");
            comprador(cod);
        }
        if (dimensao < 2) {
            enc_pend.setDimensao(Encomenda.Dimensao.PEQUENO);
        }
        if (dimensao < 2) {
            enc_pend.setDimensao(Encomenda.Dimensao.MEDIO);
        } else {
            enc_pend.setDimensao(Encomenda.Dimensao.GRANDE);
        }

        double preco_final = enc_pend.getPreco_final();
        Menu.mostraMensagem("O preço final da encomenda são: " + preco_final + "€.");

        // Depois de mostrar o preço, deseja continuar?
        boolean continuar = Insercao.get_tipo("Deseja continuar com a compra", function_Boolean);
        if (!continuar) {
            comprador(cod);
        }
        // Finaliza
        enc_pend.setEstado(Encomenda.Estado.FINALIZADA); //<-- useless, só teria sentido na vida real
        enc_pend.setData(LocalDate.now());
        enc_pend.setEstado(Encomenda.Estado.EXPEDIDA); // porque aqui o único delay foi mudar a data
        encomendas.add(enc_pend);
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



        comprador(cod);
    }

    public void criarArtigo(){

    }

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
}