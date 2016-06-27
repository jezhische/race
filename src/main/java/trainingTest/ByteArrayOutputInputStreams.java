package trainingTest;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Ежище on 24.06.2016.
 */
public class ByteArrayOutputInputStreams {
    // ByteArrayInputStream записывает не в файл, а в защищенную область в памяти, в буфер, откуда эти байты потом
    // можно достать.
    public static void main(String[] args) {
        String probe2Add = "\n4 \"mashya\", \"cars.MashkaCar\", \"28\", \"385\", \"0.9\"";
        byte [] stringIn = probe2Add.getBytes();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            baos.write(stringIn);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try(FileInputStream fin = new FileInputStream("F://еж//программирование//мои примеры//aaa//race//src//main//" +
                "resources//pilotProbesData//Probe2.txt"); FileOutputStream fos = new FileOutputStream(
                "F://еж//программирование//мои примеры//aaa//race//src//main//resources//pilotProbesData//Probe2.txt"))
        {
            // сейчас нужно прочитать содержимое файла и переписать его, а потом добавить новое
            fos.write(stringIn);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

    }
}
