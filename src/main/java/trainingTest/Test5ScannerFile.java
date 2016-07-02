package trainingTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Created by Ежище on 25.06.2016.
 */
public class Test5ScannerFile {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("src//main//resources//pilotProbesData//Probe2.txt"))) {
//            while (sc.hasNextDouble()) {
//                double aDouble = sc.nextDouble();
//                System.out.print(aDouble + " ");
//            }
            while (sc.hasNext()) {
                String aString = sc.next();
                System.out.print(aString + " ");

            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String input = "1 fish 2 fish red fish blue fish";
        Scanner s = new Scanner(input).useDelimiter("\\s*fish\\s*");
        System.out.print("\n" + s.next());
        System.out.print(" " + s.nextInt());
        System.out.print(" " + s.next());
        System.out.print(" " + s.next());
        s.close();

        Scanner ss = new Scanner(input);
        ss.findInLine("(\\d+) fish (\\d+) fish (\\w+) fish (\\w+)");
        MatchResult result = ss.match();
        System.out.println("");
        for (int i=1; i<=result.groupCount(); i++)
            System.out.print(" " + result.group(i));
        ss.close();
    }

}
