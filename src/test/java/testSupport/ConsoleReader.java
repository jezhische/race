package testSupport;

import java.util.Scanner;

/**
 * Created by Ежище on 15.08.2016.
 */
public class ConsoleReader {
    public String readConsole() {
//        StringBuilder builder = new StringBuilder();
        Scanner scan = new Scanner(System.in);
        String input = null;
//        String input = new Scanner(System.in).useDelimiter("\\A").next(); // \\A - это символ начала потока
        while (scan.hasNext()) {
//            builder.append(scan.nextLine());
            input = new Scanner(System.in).useDelimiter("\\A").next();
        }
//        return builder.toString();
       return input;
    }

}
