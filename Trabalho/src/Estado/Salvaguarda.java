package Trabalho.src.Estado;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;

public class Salvaguarda {
    public static void criaFicheiro(String filename) throws IOException{
        Path path = Paths.get(filename);
        if (!Files.exists(path))
            Files.createFile(path);
    }

    public static <T> void escreveFicheiro(List<T> objs, Consumer<T> consumer){
        for(T o : objs){
            consumer.accept(o);
        }
    }
}
