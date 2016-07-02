package trainingTest;

import java.io.*;

/**
 * Created by Ежище on 29.06.2016.
 */
public class Test6FileWriterAndReader {
    public static void main(String[] args) {

        try(FileWriter writer = new FileWriter("src//main//" +
                "resources//pilotProbesData//Test2.txt", false)) // false - заменяем текст в файле,
                // true - добавляем к уже содержащемуся там
        {
            // запись всей строки
            String text = "\nМама мыла раму, раму мыла мама";
            writer.write(text);
            // запись по символам
            writer.append('\n');
            writer.append('E');
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        // считывание файла прямо в массив
        File file = new File("src//main//resources//pilotProbesData//Test2.txt");
        char[] arrayBuffer = new char[(int)file.length()];
        try (FileReader reader = new FileReader(file)) {
            // считываем файл в массив символов - заставляем "reader читать в arrayBuffer" - @NotNull CharBuffer target:
            reader.read(arrayBuffer);
            System.out.println(new String(arrayBuffer));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
