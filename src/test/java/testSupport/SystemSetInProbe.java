package testSupport;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ежище on 27.07.2016.
 */
public class SystemSetInProbe {
    public static void main(String[] args) {
        InputStream ho = System.in;
        Scanner keyboard = new Scanner(ho);
//        System.out.println(keyboard.nextLine());
        ByteArrayInputStream hu = new ByteArrayInputStream("проба записи".getBytes()); // String class method getBytes()
        // returns byte[], encoded from String
        System.setIn(hu); // System class void setIn reassigns the "standard" input stream.
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine());

        byte[] array = new byte[]{'k', 15, 25, 'n', '8', 'Y'};
        System.setIn(new ByteArrayInputStream(array));
//        System.out.println(scanner.nextLine()); // выдает ошибку
        System.out.println(new Scanner(System.in).nextLine());
        array = "еще проба".getBytes();
        System.setIn(new ByteArrayInputStream(array));
        System.out.println(new Scanner(System.in).nextLine());

        
        SystemSetInProbe printFile = new SystemSetInProbe();
        /** Работающий вариант в случае, если метод print не void, а ArrayList,
         * и возвращает ArrayList (см. закомментированный метод): */

//        ArrayList<String> ee = printFile.print(new File("src\\main\\resources\\Probe4.txt"));
//        for (String s : ee) {
//            System.out.println(s);
//        }
//        System.out.println(ee.isEmpty());


        /** Еще работающий вариант в случае, если метод print не void, а ArrayList,
         * и возвращает ArrayList (см. закомментированный метод): */

//        for (String as: (ArrayList<String >)printFile.print(new File("src\\main\\resources\\Probe4.txt"))) {
//            System.out.println(as);
//
//        }
        printFile.print(new File("src\\main\\resources\\Probe4.txt"));
        for (String asa: printFile.scannedFromFile) {
            System.out.println(asa);

        }
    }

    ArrayList<String> scannedFromFile = new ArrayList<>();
    int i = 0;

    public void print(File file) {
        try (FileInputStream fReader = new FileInputStream(file)) {
            System.setIn(fReader);
            Scanner xerox = new Scanner(System.in);
            while (xerox.hasNextLine()) {
                scannedFromFile.add(i, xerox.nextLine());
                i++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//    public ArrayList print(File file) {
//        try (FileInputStream fReader = new FileInputStream(file)) {
//            System.setIn(fReader);
//            Scanner xerox = new Scanner(System.in);
////            while (xerox.hasNext()) {
////                System.out.println(xerox.nextLine());
////            }
//            while (xerox.hasNextLine()) {
//                scannedFromFile.add(i, xerox.nextLine());
//                i++;
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        return scannedFromFile;
//    }
}
