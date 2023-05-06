package Trabalho.src.Vista;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Supplier;

public class Insercao {
    public static <T> T get_valor(Supplier<T> supplier){
        Scanner scanner = new Scanner(System.in);
        boolean ok = false;
        T valor = null;

        while(!ok){
            try{
                valor = supplier.get();
                ok = true;
            }catch (InputMismatchException e){
                Menu.erro(e.getMessage());
                System.out.println("Introduza um valor válido: ");
                scanner.nextLine();
            }
        }
        return valor;
    }
}