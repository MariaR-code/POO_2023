package Trabalho.src.Vista;

import java.util.InputMismatchException;
import java.util.function.Function;
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
                Menu.erro(e.getMessage());
                System.out.println("Introduza um valor válido: ");
            }catch (IllegalArgumentException e){
                Menu.erro(e.getMessage());
                System.out.println("Introduza um valor válido: ");
            }
        }
        return valor;
    }

    public static <T,R> R get_tipo(T t, Function<T,R> function){
        boolean ok = false;
        R valor = null;

        while(!ok){
            try{
                valor = function.apply(t);
                ok = true;
            }catch (InputMismatchException e){
                Menu.erro(e.getMessage());
                System.out.print("Introduza um valor válido: ");
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