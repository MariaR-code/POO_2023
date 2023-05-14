package Trabalho.src.Vista;

import java.util.InputMismatchException;
import java.util.function.Function;
import java.util.function.Supplier;

public class Insercao {

    /**
     * Método que lê a informação dada pelo utilizador pelo System.out
     * @param supplier É o Supplier que vai fazer a devida transformação de String para o T
     * @return T É o valor do tipo que o utilizador escreveu no System.out
     * */
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

    /**
     * Método que lê a informação dada pelo utilizador pelo System.out
     * @param <T> É o parâmetro para ser dado à Function
     * @param function É o Supplier que vai fazer a devida transformação de String para o T
     * @return R É o valor do tipo que o utilizador escreveu no System.out
     * */
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

    /**
     * Método que transmite ao utilizador que dados ele deve fornecer ao sistema
     * @param texto É o que se pretende que o utilizador escreva
     * @param supplier É o Supplier para dar como parâmetro ao método "get_tipo"
     * @return T É o valor do tipo que o utilizador escreveu no System.out
     * */
    public static <T> T get_valor(String texto, Supplier<T> supplier){
        System.out.print("\nIntroduza " + texto + ": ");
        T valor = get_tipo(supplier);
        return valor;
    }
}