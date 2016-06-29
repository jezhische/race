package trainingTest;

import java.util.Arrays;

/**
 * Created by Ежище on 28.06.2016.
 */

//http://prologistic.com.ua/primer-ispol-zovaniya-metoda-split-na-java.html
public class Tes6SplitStringIntoStringArray {
    public static void main(String[] args) {
        String line = "Как использовать метод split";
        String[] words = line.split(" ");
        String[] twoWords = line.split(" ", 2);
        System.out.println("Используем разделитель: " + Arrays.toString(words));
        System.out.println("Разделяем на 2 строки: " + Arrays.toString(twoWords));
        //метод split со специальным разделителем
        String wordsSpecial = "Как|использовать|метод|split";
        String[] numbers = wordsSpecial.split("\\|");
        System.out.println("метод split со специальным разделителем: " + Arrays.toString(numbers));

        //еще метод
        // написали регулярку
        String pat_tern = "\\s+|,\\s*";
// создали какую-то строку с разными разделителями
        String inputString = "Просто, строка в,     java";

        String[] splitResult = inputString.split(pat_tern);
        System.out.println("Регулярное выражение: " + Arrays.toString(splitResult));


    }
}
