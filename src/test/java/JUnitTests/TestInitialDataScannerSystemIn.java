package JUnitTests;

import dataStorageAndProcessing.InitialDataScannerSystemIn;
import org.junit.After;
import org.junit.Before;
import testSupport.SystemInSubstitution;
import testSupport.SystemOutReader;

/**
 * Created by Ежище on 14.08.2016.
 */
public class TestInitialDataScannerSystemIn {
    SystemInSubstitution inSubst;
    SystemOutReader outData;
    InitialDataScannerSystemIn cars;

    @Before
    public void setupBefore() {
        inSubst = new SystemInSubstitution();
        outData = new SystemOutReader();
        cars = new InitialDataScannerSystemIn();
    }

    @After
    public void tearDown() {
        inSubst = null;
        outData = null;
        cars = null;
    }

//    // проверяем, что при нулевом вводе метод возвращает пустой список:
//    @Test
//    public void testReadUserDataReturnForEmptyDataIn() {
////        inSubst.createSystemIn("");
////        inSubst.createSystemIn("");
//       try {
//           inSubst.createSystemIn("hththtrtrhetreh");
////           outData.readSystemOut();
////        assertFalse(cars.readUserData() == null);
//           assertTrue(outData.readSystemOut().equals("Параметры автомобиля jkjhkjh объявлены неверно: не все параметры " +
//                   "введены. Автомобиль снят с гонки.\n" +
//                   "Введите параметры автомобиля либо напечатайте esc и нажмите Enter для перехода на следующий этап."));
//       }
//       catch (Exception e) {
//           System.out.println(e.getMessage());
//       }
//    }

}
