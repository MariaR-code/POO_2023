package Trabalho.src.Controlo;

import Trabalho.src.Erros.NaoExisteTransportadora;
import Trabalho.src.Modelo.*;
import Trabalho.src.Vista.Insercao;
import Trabalho.src.Vista.Menu;
import Trabalho.src.Erros.NaoExisteUtilizador;
import Trabalho.src.Erros.ErroCriarConta;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Controlo{
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
        do{
            Scanner input = new Scanner(System.in);
            Menu.mostraMensagem(s + "? (Se sim, escreva sim. Caso contrário escreva não): ");
            String sn = input.nextLine();

            if(sn.equals("sim")){
                b= true;
                valid = true;
            }
            if(sn.equals("não")){
                b= false;
                valid = true;
            }
        }while(!valid);

        return b;
    };

    public Controlo(){
        this.gui = new Menu();
        this.model = new Mercado();

    }

    public void run(){
        int op = this.gui.menu(" Bem-vind@ à Vintage ", Menu.menu_Principal_Vintage);

        switch (op){
            case 1:
                this.logIn(1);
                break;
            case 2:
                this.logIn(0);
                break;

            case 3:

                this.criar_conta();
                break;

            case 4:
                this.transportadoras();
                break;

            case 5:
                //Método usado para salvaguardar o estado
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
    public void logIn(int tipo){
        int op = this.gui.menu(" Log In ", Menu.menu_Log_In);

        switch (op){
            case 1:
                int cod = this.loginVendedor(tipo);
                if(tipo == 1)
                    this.vendedor(cod);
                else this.comprador(cod);
                break;
            case 2:
                this.run();
                break;
        }
    }

    public void transportadoras(){
        int op = this.gui.menu(" Transportadoras ", Menu.menu_Transportadoras);

        switch (op){
            case 1:
                this.adicionar_transportadora();
                break;

            case 2:
                this.ver_transportadoras();
                break;

            case 3:
                this.run();
                break;
        }
    }

    public int loginVendedor(int tipo) throws NaoExisteUtilizador{
        try{
            String email = Insercao.get_valor("email", this.supplier_String);
            if(!(this.model.procuraUtilizador(email,tipo))) {
                throw new NaoExisteUtilizador("Utilizador não registado");
            }

            return model.codigoUtilizador(email);
        }
        catch(NaoExisteUtilizador e){
            Menu.erro(e.getMessage());
            this.run();
        }
        return -1;
    }

    public void vendedor(int cod){
        int op = this.gui.menu(" Vendedor ", Menu.menu_Vendedor);

        switch (op){
            case 1:
                this.adicionar_artigo(cod);
                break;

            case 2:
                break;

            case 3:
                this.run(); //??? Talvez algum que mostre os artigos que se encontram à venda
                break;
        }
    }

    public void adicionar_artigo(int cod) {
        int op = this.gui.menu(" Adicionar Artigo ", Menu.menu_Adicionar_Artigo);

        switch (op){
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
        try { // cria-se um menu para esta tipo_user selection ou isto serve? O que muda é as opções em vez de começar em 1 é em 0.
            int tipo_user = Insercao.get_valor(("o seu tipo de conta: \n0 - Comprador \n1 - Vendedor \n2 - Ambos \nEscolha a opção que pretende"), supplier_Int);
            if (tipo_user < 0 || tipo_user > 2) {
                throw new ErroCriarConta("Tipo de utilizador inválido.");
            }
            String email = Insercao.get_valor("email", supplier_String);
            boolean userExists = false; // ver se funciona
            for (int i = 0; i <= 2; i++) {
                userExists = userExists || this.model.procuraUtilizador(email, i);
            }
            if(!(Utilizador.isValidEmail(email)) || userExists) {
                throw new ErroCriarConta("Email inválido");
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
            this.run();
        } catch (ErroCriarConta e) {
            Menu.erro("Não foi possível criar a conta devido a " + e.getMessage());
            this.run();
        }
    }

    public void adicionar_transportadora() { String nome,preco_expedicao,preco_expedicao_premium; // por enquanto, não verifica nada, só cria
        nome = Insercao.get_valor("nome da transportadora", supplier_String);
        preco_expedicao = Insercao.get_valor("preço de expedição", supplier_String);
        preco_expedicao_premium = Insercao.get_valor("preço de expedição premium", supplier_String);
        Transportadora transportadora = new Transportadora(nome, preco_expedicao, preco_expedicao_premium);
        model.addTransportadora(transportadora);
        Menu.mostraMensagem("Transportadora adicionada com sucesso!");
        this.run();
    }
    public void ver_transportadoras() { // só da o nome
        List<Transportadora> transportadoras = model.getTransportadoras();

        if (transportadoras.isEmpty()) {
            Menu.mostraMensagem("Não há transportadoras associadas atualmente.");
            this.run();
        } else {
            Menu.mostraMensagem("Transportadoras associadas atualmente:");

            for (Transportadora t : transportadoras) {
                Menu.mostraMensagem("- " + t.getNome());
            }
        }
        this.run();
    }

    public void adicionaVestuario(int cod){
        Tshirt tshirt = null;

        String cod_alfnr = Insercao.get_valor("o código alfanumérico", supplier_String);
        String marca = Insercao.get_valor("a marca", supplier_String);
        String descricao = Insercao.get_valor("a descrição", supplier_String);
        double preco_base = Insercao.get_valor("o preço base", supplier_Double);
        String transportadora = Insercao.get_valor("o nome da transportadora", supplier_String); //ver se a transportadora existe
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

    public void adicionaCalcado(int cod) throws NaoExisteTransportadora{
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
        String transportadora = Insercao.get_valor("o nome da transportadora", supplier_String); //ver se a transportadora existe
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
                break;

            case 3:
                this.run(); //???? Talvez algum que mostre os artigos que se encontram à venda
                break;
        }
    }

    public void criarEncomenda(int cod){
        int op = this.gui.menu(" Criar Encomenda ", Menu.menu_Criar_Encomenda);

        switch (op){
            case 1:
                break;

            case 2:
                break;

            case 3:
                this.comprador(cod);
                break;
        }
    }

    public void criarArtigo(){

    }
}