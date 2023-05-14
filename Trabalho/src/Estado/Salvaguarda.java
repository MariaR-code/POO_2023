package Trabalho.src.Estado;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Salvaguarda {

    /**
     * Método que escreve os objetos de uma lista num ficheiro de texto
     * @param objs É a lista de objetos que se pretende escrever no ficheiro
     * @param consumer É o Consumer que define como é que cada objeto é escrito no ficheiro
     * */
    public static <T> void escreveFicheiro(List<T> objs, Consumer<T> consumer){
        for(T o : objs){
            consumer.accept(o);
        }
    }

    /**
     * Método que escreve as entradas de um map num ficheiro de texto
     * @param map É o map de objetos cujas entradas se pretende escrever no ficheiro
     * @param consumer É o Consumer que define como é que cada entrada é escrita no ficheiro
     * */
    public static <T> void escreveFicheiroMap(Map<Integer, List<T>> map, Consumer<Map.Entry<Integer, List<T>>> consumer){
        map.entrySet().forEach(entrada -> consumer.accept(entrada));
    }

    /**
     * Método que escreve as entradas de um map num ficheiro de texto
     * @param map É o map de objetos cujas entradas se pretende escrever no ficheiro
     * @param nome É o nome que se pretende escrever no ficheiro de forma a identifocar o map
     * @param biConsumer É o BiConsumer que define como é que cada entrada é escrita no ficheiro
     * */
    public static <T> void escreveFicheiroMap(Map<Integer, List<T>> map, String nome, BiConsumer<String, Map.Entry<Integer, List<T>>> biConsumer){
        map.entrySet().forEach(entrada -> biConsumer.accept(nome, entrada));
    }
}
