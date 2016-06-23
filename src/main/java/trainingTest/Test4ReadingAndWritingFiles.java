package trainingTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Ежище on 23.06.2016.
 */
public class Test4ReadingAndWritingFiles {
    public static void main(String[] args) {

        try(FileInputStream fin=new FileInputStream("F://еж//программирование//мои примеры//aaa//race//src//main//" +
                "resources//pilotProbesData//Probe1.txt"); FileOutputStream fos=new FileOutputStream("F://еж//" +
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
                System.out.print((char)buffer3[i]);
            }

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
