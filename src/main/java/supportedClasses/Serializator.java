package supportedClasses;

import cars.Vehicle;
import dataStorageAndProcessing.InitialDataFileReader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * Created by Ежище on 03.09.2016.
 */
public class Serializator implements Runnable {

    private ArrayList<Vehicle> carList = new ArrayList<>();

    private int i;

    Serializator(ArrayList<Vehicle> carList, int i) {
        this.carList = carList;
        this.i = i;
    }

    Serializator() {
    }

    public void go(ArrayList<Vehicle> carList) {
        for (i = 0; i < carList.size(); i++) {
            new Thread(new Serializator(carList, i)).start();
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }


    @Override
    public void run() {
//        synchronized (carList) {
        Vehicle car = carList.get(i);
        try (ObjectOutputStream serialize = new ObjectOutputStream
                (new FileOutputStream(String.format("src\\main\\resources\\serialStore\\log%d_%s_%s.dat",
                        i, car.getName(), car.getMarker())))) {
            car.goVehicle();
            System.out.printf("внутри: %s %5.1f %7.2f\n", car.getName(), car.getRegisteredTime(), car.getAverageSpeed());
            serialize.writeObject(car);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        System.out.printf("снаружи: %s %5.1f %7.2f\n", car.getName(), car.getRegisteredTime(), car.getAverageSpeed());
//        }
    }


    public static void main(String[] args) {
        InitialDataFileReader readFile = new InitialDataFileReader();
        ArrayList<Vehicle> probe = readFile.readArgsFromFile();
//        for (int i = 0; i < probe.size(); i++) {
//            System.out.printf("класс %d = %s;\n", i, probe.get(i).getClass());
//            Vehicle raceCar = probe.get(i).goVehicle();
//            double time = raceCar.getRegisteredTime();
//            double avSpeed = raceCar.getAverageSpeed();
//            System.out.printf("%s прошел трассу за %5.1f секунд, средняя скорость %5.2f м/с\n",
//                    raceCar.getName(), time, avSpeed);
//
////            new Thread(new Serializator(probe)).start();
//        }

        new Serializator().go(probe);

    }
}
