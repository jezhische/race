package cars;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by Ежище on 26.06.2016.
 */
@RunWith(JUnit4.class)
public class BmwCarTest {
    private BmwCar car = new BmwCar("i35", 56D, 160d, 0.7d);

    @Test
    public void goVehicle() {
        Vehicle result = car.goVehicle();
//        System.out.println(result.acceleration);
//        Assert.assertEquals(56.0d,result.acceleration,0);
    }
}
