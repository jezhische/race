package testSupport.forScannerSystemIn;

import java.io.*;

/**
 * Created by Ежище on 14.08.2016.
 * Перенаправляем поток System.out в файл, затем считываем из файла и переводим в String, его и возвращаем
 */
public class SystemOutReader {
    public String readSystemOut() {
        // создаем потоки: PrintStream для записи в файл, а также BufferedReader(new FileReader()) для чтения из файла:
        try(PrintStream printOutInFile = new PrintStream("src\\main\\resources\\testSupport\\" +
                "Test1ForOut_InitialDataScannerSystemIn.txt"); BufferedReader outReader =
                new BufferedReader(new FileReader("src\\main\\resources\\testSupport\\" +
                "Test1ForOut_InitialDataScannerSystemIn.txt"))) {
            // перенаправляем System.out в поток PrintStream printOutInFile, который записывает исходящие данные в файл:
            System.setOut(printOutInFile);
            // читаем данные из файла в поток BufferedReader(new FileReader()) outReader с помощью метода
            // readLine(), который пишет данные в String, этот String и возвращаем:
            return outReader.readLine();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
