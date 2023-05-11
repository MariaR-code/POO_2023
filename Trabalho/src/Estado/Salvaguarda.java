package Trabalho.src.Estado;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class Salvaguarda {
    public static <T> void escreveFicheiro(List<T> objs, Consumer<T> consumer){
        for(T o : objs){
            consumer.accept(o);
        }
    }

    public static <T> void escreveFicheiroMap(Map<Integer, List<T>> map, Consumer<Map.Entry<Integer, List<T>>> consumer){
        map.entrySet().forEach(entrada -> consumer.accept(entrada));
    }

    public static <T> void escreveFicheiroMap(Map<Integer, List<T>> map, String nome, BiConsumer<String, Map.Entry<Integer, List<T>>> biConsumer){
        map.entrySet().forEach(entrada -> biConsumer.accept(nome, entrada));
    }
}
