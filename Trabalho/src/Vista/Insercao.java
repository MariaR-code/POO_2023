package Trabalho.src.Vista;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Insercao {
    public static <T> T get_tipo(Supplier<T> supplier){
        boolean ok = false;
        T valor = null;

        while(!ok){
            try{
                valor = supplier.get();
                ok = true;
            }catch (InputMismatchException e){
                Scanner scanner = new Scanner(System.in);
                Menu.erro(e.getMessage());
                System.out.println("Introduza um valor válido: ");
                scanner.nextLine();
            }
        }
        return valor;
    }

    public static <T> T get_valor(String texto, Supplier<T> supplier){
        System.out.print("\nIntroduza " + texto + ": ");
        T valor = get_tipo(supplier);
        return valor;
    }
}