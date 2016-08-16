package testSupport;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Created by Ежище on 16.08.2016.
 */
public class OutToFileRedirect {
    public PrintStream redirectOut() {
        try  {
            PrintStream output = new PrintStream("src\\main\\resources\\testSupport\\output.txt");
            System.setOut(output);
            return System.out;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
