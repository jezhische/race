package JUnitTests;

import org.junit.*;
import cars.*;
import supportedClasses.*;

/**
 * Created by Ежище on 05.07.2016.
 */
public class JUnitTestPrinter {
    Vehicle IncorrectMachine;
    Vehicle CorrectMachine;

    @Before
    public void setupBefore() {
        IncorrectMachine = new BmwCar("name", "car.BmvCar", 0, 0, 0);
        CorrectMachine = new BmwCar("name", "car.BmvCar", 1, 1, 1);
    }

    @Test
    public void testCheckNullMachine() throws Exception {
        String testWrongMsg = "Ошибка: исходные данные автомобиля " + IncorrectMachine.getName() + " введены неверно. " +
                "Автомобиль дисквалифицирован.";
//        Printer printer = new Printer();
        Assert.assertTrue("Method returns incorrect message", Printer.printData(IncorrectMachine).equals(testWrongMsg));
    }

    @Test
    public void testCheckCorrectMachine() throws Exception {
        String testMsg = "Автомобиль ";
//        Printer printer = new Printer();
        Assert.assertTrue("Method returns incorrect message", Printer.printData(CorrectMachine).contains(testMsg));
    }

//    @Test
//    public void testCheckAcceleration() throws Exception {
//        String testMsg = " прошел трассу за " + 5 + " часов ";
////        Printer printer = new Printer();
//        CorrectMachine.goVehicle();
//        String returned = Printer.printData(CorrectMachine);
//        Assert.assertTrue("Method returns incorrect message. The returned msg is:" + returned + ". Expected msg must " +
//                "contains: " + testMsg, returned.contains(testMsg));
//    }

    @After
    public void tearDown() {
        CorrectMachine = null;
        IncorrectMachine = null;
    }
}
