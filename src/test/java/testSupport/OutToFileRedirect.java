package testSupport;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
<<<<<<< HEAD
import java.io.FileWriter;
=======
>>>>>>> 489574ffcfbe4beb23906cbdb4962785be1a3311
import java.io.PrintStream;

/**
 * Created by Ежище on 16.08.2016.
 */
public class OutToFileRedirect {
    public PrintStream redirectOut() {
        try  {
<<<<<<< HEAD
            PrintStream output = new PrintStream(new FileOutputStream("src\\main\\resources\\testSupport\\output.txt", false));
=======
            PrintStream output = new PrintStream(new FileOutputStream("src\\main\\resources\\testSupport\\output.txt"));
>>>>>>> 489574ffcfbe4beb23906cbdb4962785be1a3311
            System.setOut(output);
            return System.out;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
