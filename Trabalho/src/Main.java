package Trabalho.src;
import Trabalho.src.Controlo.Controlo;
import Trabalho.src.Modelo.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Utilizador utilizador = new Utilizador("a", "Vendedor 1", "Rua A", "niftest1", 2);
        Utilizador user2 = new Utilizador("b", "Vendedor 2", "Rua b", "testnif2", 2);
        Encomenda encomenda = new Encomenda();

        Tshirt tshirt = new Tshirt("T-shirt", "Brand", "TS123", 29.99, "Transport", Tshirt.Tamanho.M, Tshirt.Padrao.Riscas);
        Sapatilhas sapatilhas = new Sapatilhas("Sapatilhas", "Brand", "SP456", 79.99, "Transport", 42, true, "Blue", LocalDate.of(2021, 1, 1), true);
        encomenda.addArtigo(tshirt);
        encomenda.addArtigo(sapatilhas);

        Fatura fatura = new Fatura(encomenda, 510.0, "987654321");

        utilizador.addFaturaVendedor(fatura);
        Controlo controller = new Controlo();
        // controller.model.addUtilizador(utilizador); // precisa de model public para isto funcionar assim
        // controller.model.addUtilizador(user2);
        controller.run();
    }
}
