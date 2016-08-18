package testSupport;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by Ежище on 16.08.2016.
 * Один метод, который перенаправляет поток System.out в файл
 */
public class OutToFileRedirect {
    public PrintStream redirectOut() {
        try  {
            PrintStream output = new PrintStream(new FileOutputStream("src\\main\\resources\\testSupport\\output.txt", false));
            System.setOut(output);
            return System.out;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
