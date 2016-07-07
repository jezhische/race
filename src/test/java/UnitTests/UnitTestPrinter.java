package UnitTests;

import org.junit.*;
import cars.*;
import supportedClasses.*;

/**
 * Created by Ежище on 05.07.2016.
 */
public class UnitTestPrinter {
    Vehicle IncorrectMachine;
    Vehicle CorrectMachine;

    @Before
    public void setupBefore() {
//        this.IncorrectMachine = new BmwCar("name", 0, 0, 0);
//        this.CorrectMachine = new BmwCar("name", 1, 1, 1);
    }

    @Test
    public void testCheckNullMachine() throws Exception {
        String testWrongMsg = "Ошибка: исходные данные автомобиля " + IncorrectMachine.getName() + " введены неверно. Автомобиль дисквалифицирован.";
        Printer printer = new Printer();
        Assert.assertTrue("Method return incorrect message", printer.printData(IncorrectMachine).equals(testWrongMsg));
    }

    @Test
    public void testCheckCorrectMachine() throws Exception {
        String testMsg = "Автомобиль ";
        Printer printer = new Printer();
        Assert.assertTrue("Method return incorrect message", printer.printData(CorrectMachine).contains(testMsg));
    }

    @Test
    public void testCheckAcceleration() throws Exception {
        String testMsg = " прошел трассу за " + 5 + " часов ";
        Printer printer = new Printer();
        CorrectMachine.goVehicle();
        String returned = printer.printData(CorrectMachine);
        Assert.assertTrue("Method return incorrect message. The returned msg is:" + returned + "Expected msg must contains: " + testMsg, returned.contains(testMsg));
    }

    @After
    public void tearDown() {
        CorrectMachine = null;
        IncorrectMachine = null;
    }
}
