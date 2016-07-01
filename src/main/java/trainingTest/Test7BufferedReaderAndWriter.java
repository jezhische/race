package trainingTest;

import java.io.*;

/**
 * Created by Ежище on 29.06.2016.
 */
public class Test7BufferedReaderAndWriter {
    // буферизация - для уменьшения количества обращений к физическому носителю, соответственно, повышение
    // производительности. Здесь считываем текст с консоли, записываем в буфер, из буфера - в файл.
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new FileWriter("src//main//resources//pilotProbesData//Test3.txt"))) {
            // чтение построчно (readLine)
            String text;
            while (!(text = br.readLine()).equals("ESC")) { //NOTE нужно именно написать ESC и нажать на Enter,
                // в файл это ESC не запишется

                bw.write(text + "\n");
                bw.flush();
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
