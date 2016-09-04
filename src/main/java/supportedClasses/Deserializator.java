package supportedClasses;

import cars.MashkaCar;
import cars.Vehicle;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by Ежище on 04.09.2016.
 */
public class Deserializator {

    double regTime;
    double averSpeed;
    String nm;
    public Vehicle deserialize(File log) {
        try(ObjectInputStream deserializator = new ObjectInputStream(new FileInputStream(log))) {
            MashkaCar m = (MashkaCar) deserializator.readObject();
            regTime = m.getRegisteredTime();
            averSpeed = m.getAverageSpeed();
            nm = m.getName();
            return m;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Deserializator deser = new Deserializator();
        File file = new File("src\\main\\resources\\serialStore\\log0_mashka_cars.MashkaCar.dat");
        deser.deserialize(file);
        System.out.println(deser.regTime);
        System.out.println(deser.averSpeed);
        System.out.println(deser.nm);
        System.out.println(deser.deserialize(file).getAcceleration());
//        System.out.println(ser.somewhat);
    }
}
