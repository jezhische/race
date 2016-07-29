package JUnitTests;

import cars.Vehicle;
import dataStorageAndProcessing.InitialDataScannerSystemIn;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

/**
 * Created by Ежище on 27.07.2016.
 */
public class InitialDataScannerSystemInJUnitTest {
    private InitialDataScannerSystemIn scan;
    private Vehicle car;
    PrintStream printOutInFile;
    BufferedReader outReader;

    @Before
    public void init() throws IOException { // throws IOException - чтобы не вводить блок try-catch,
        // здесь мне это ни к чему, будет ошибка - все равно тест провален, продолжать дальше нет смысла.
        scan = new InitialDataScannerSystemIn();
        printOutInFile = new PrintStream(("src\\main\\resources\\testSupport\\" +
                "Test1ForOut_InitialDataScannerSystemIn.txt"));
        System.setOut(printOutInFile);// Тем самым перенаправили System.out в файл, который создается
        // потоком printOutInFile
        outReader = new BufferedReader(new FileReader("src\\main\\resources\\testSupport\\" +
                "Test1ForOut_InitialDataScannerSystemIn.txt")); // и получим String из этого файла
    }

    @After
    public void tearDown() {
        scan = null;
        printOutInFile = null;
        outReader = null;
    }

    @Test
    public void testPrintAppendix() throws IOException {
        // проверяем, выдается ли нужное сообщение. Бессмысленный тест, вообще-то нужно тестировать void readUserData(),
        // который меняет объект List<Vehicle> userCarsToRace. Правда, иногда не меняет, а выдает сообщения об
        // ошибке. И при этом не прописанные как исключения. Так что можно потренироваться на этом.

        scan.setK(0);
        String testK0 = "Введите следующий автомобиль, либо напечатайте esc и нажмите Enter " +
                "для перехода на следующий этап.";
        scan.printAppendix();
        printOutInFile.flush(); // на всякий случай опорожняем буфер
        Assert.assertTrue(outReader.readLine().equals(testK0));
        Assert.assertTrue(scan.getRunCycle());

        scan.setK(1);
        scan.printAppendix();
        printOutInFile.flush();
        Assert.assertTrue(outReader.readLine().equals(testK0));
        Assert.assertTrue(scan.getRunCycle());

        scan.setK(2);
        String testK2 = "После следующей неправильной попытки ввода программа будет завершена.";
        scan.printAppendix();
        printOutInFile.flush();
        Assert.assertTrue(outReader.readLine().equals(testK2));
        Assert.assertTrue(scan.getRunCycle());

        scan.setK(3);
        String testK3 = "Ввод данных прерван. Пожалуйста, запустите программу снова.";
        scan.printAppendix();
        printOutInFile.flush();
        Assert.assertTrue(outReader.readLine().equals(testK3));
        Assert.assertFalse(scan.getRunCycle());
    }
}
