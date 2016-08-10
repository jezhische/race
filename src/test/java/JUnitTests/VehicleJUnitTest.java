package JUnitTests;

import cars.Vehicle;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
//    import static junit.framework.Assert.*;

/**
 * Created by Ежище on 13.07.2016.
 */
public class VehicleJUnitTest {
    private Vehicle correctCar;
    private Vehicle incorrectCar;

    @Before
    public void init() {
        correctCar = new Vehicle("vehicle", "cars.Vehicle", 10.0, 100.0, 0.5);
        incorrectCar = new Vehicle(null, null, 0, 0, 2);
    }

    @After
    public void tearDown() {
        correctCar = null;
        incorrectCar = null;
    }

    //    @Test (expected = Exception.class)
//    public void testSetName()throws Exception {
//            String nameExMsg = "Не введено имя автомобиля класса " + getMarker() + ".";
//        correctCar.setName(null);
//        Assert.assertTrue("Method returns Exception message when argument is null", correctCar.equals(testExMsg));
//    }
    @Test
    public void testGoVehicle() throws Exception {
        String nameExMsg = "Не введено имя автомобиля класса " + correctCar.getMarker() + ".";
//        correctCar.setName(null);
        Assert.assertTrue(correctCar.getName().equals("vehicle"));
        Assert.assertTrue(correctCar.getMarker().equals("cars.Vehicle"));
        incorrectCar.setName(null);
    }


        public static void main(String[] args) throws Exception {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(VehicleJUnitTest.class);
        System.out.println("run tests: " + result.getRunCount());
        System.out.println("failed tests: " + result.getFailureCount());
        System.out.println("ignored tests: " + result.getIgnoreCount());
        System.out.println("success: " + result.wasSuccessful());
    }

}
