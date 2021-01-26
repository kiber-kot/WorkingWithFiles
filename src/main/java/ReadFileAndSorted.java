
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ReadFileAndSorted {
    public static void main(String[] args) {
        Set<Path> set = new TreeSet<>();

        // считываю название всех фалов из корневой папки folder и всех подпапок.
        try {
            Files.walk(Paths.get("folder"))
                    .filter(Files::isRegularFile)
                    .forEach(set::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // тут создается в корне файл, куда в дальнейшем запишутся значение из всех файлов.
        // ѕри этом происходит генерация названия файла по дате
        LocalDateTime localDateTime = LocalDateTime.now();
        String formatDate = localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yy hh-mm-ss"));
        Path output = Paths.get("fullFile"+formatDate+".txt");
        Charset charset = StandardCharsets.UTF_8;

        // тут считываю файлы и их директории из set, после кидаю в TreeMap, чтобы произошла лексикографическая сортировка по ключу
        Map<Path, Path> map = new TreeMap<>();
        for(Path value : set){
            for(int i = 0; i < set.size(); i++){
                map.put(value.getFileName(), value.getParent());
            }
        }

        // сортированные названия файла записываются в новом файле который появиться в корне проекта
        List<String> lines = null;
        // использую сепоратор из-за того что допустим у винды и юникс систем, отличный путь к файлу
        String separator = File.separator;
        try {
            for(Map.Entry<Path, Path> value2 : map.entrySet()){
                lines = Files.readAllLines(Paths.get(value2.getValue() + separator + value2.getKey()), charset);
                Files.write(output, lines, charset, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
