package trainingTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by Ежище on 23.06.2016.
 */
public class Test4ReadingAndWritingFiles {
    public static void main(String[] args) {

        try (FileInputStream fin = new FileInputStream("F://еж//программирование//мои примеры//aaa//race//src//main//" +
                "resources//pilotProbesData//Probe1.txt"); FileOutputStream fos = new FileOutputStream("F://еж//" +
                "программирование//мои примеры//aaa//race//src//main//resources//pilotProbesData//Probe2.txt");
             FileInputStream fin2 = new FileInputStream("F://еж//программирование//мои примеры//aaa//race//src//main//" +
                     "resources//pilotProbesData//Probe2.txt"))
        // NOTE: файла Probe2.txt не существовало, пока я не запустил программу; после этого
        // он был создан автоматически
        {
            byte[] buffer = new byte[fin.available()];
            // считываем буфер
            fin.read(buffer, 0, buffer.length);
            // записываем из буфера в файл
            fos.write(buffer, 0, buffer.length);
            String newText = "\n\"ferrfurr\", \"cars.FerraryCar\", \"22\", \"300\", \"0.3\"";
            // переводим строку в байты:
            byte[] buffer2 = newText.getBytes(); //NOTE даже без указания длины массива, сразу запись байтов в массив:
            // public byte[] getBytes()
            // Encodes this String into a sequence of bytes using the platform's default charset,
            // storing the result into a new byte array.
            fos.write(buffer2, 0, buffer2.length);
            byte[] buffer3 = new byte[fin2.available()];
            fin2.read(buffer3, 0, buffer3.length);
            for (int i = 0; i < buffer3.length; i++) {
                System.out.print((char) buffer3[i]);
            }

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        //Запись в файл с помощью PrintStream:

        String s1 = "Привет мир!";
        String s2 = "Hello World!";
        try (PrintStream printStream = new PrintStream("src//main//resources//pilotProbesData//Test1.txt")) {
            printStream.println(s1);
            int i = 2;
            printStream.printf("Квадрат числа %d равен %d \n", i, i * i);
            byte[] s2_toBytes = s2.getBytes();
            printStream.write(s2_toBytes);// здесь метод write, который считывает байтовый массив
            printStream.print("Конец");
            System.out.println("Запись в файл произведена");
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        //Еще пример записи:
        String text = "Привет мир!"; // строка для записи
        try (FileOutputStream fos = new FileOutputStream("F://еж//программирование//мои примеры//aaa//race//src//main//" +
                "resources//pilotProbesData//Test2.txt");
             PrintStream printStream = new PrintStream(fos)) {
            printStream.println(text);
            System.out.println("Запись в файл произведена");
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
    //ctrl+alt+L - форматирование кода
}
