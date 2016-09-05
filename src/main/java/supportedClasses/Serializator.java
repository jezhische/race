package supportedClasses;

import cars.Vehicle;
import dataStorageAndProcessing.InitialDataFileReader;

import java.io.File;
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

    private File dir = new File("src\\main\\resources\\serialStore");

    Serializator(ArrayList<Vehicle> carList, int i) {
        this.carList = carList;
        this.i = i;
    }

    Serializator() {
    }

    /** to create required directory for serialized files writing and to delete all the files there if it exists */
    // (тут наверчено булиня, просто, чтобы поупражняться, можно было бы void):
    private boolean fileDealer(File dir) {
        this.dir = dir;
        try {
            if (!dir.exists()) {
                dir.mkdir();
            }
            else {
                if (dir.isDirectory()) { // && dir.listFiles().length != 0  - в этой строчке нужды нет, ошибка не вылазит
                    for (File item:
                            dir.listFiles()) {
                        item.delete(); // получаем список всех файлов в директории и стираем их в цикле один за другим.
                        // Проще было бы стереть всю директорию, но так интереснее
                    }
                }
            }
            if (dir.exists())
                return (dir.listFiles().length == 0);
            return false;
        } catch(NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /** to serialize results of the race in run() */
    @Override
    public void run() { //TODO: стереть все выводы в консоль
        if (fileDealer(dir)) {
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
        }
    }

    /** to create some concurrent streams */
    public void go(ArrayList<Vehicle> carList) {
        for (i = 0; i < carList.size(); i++) {
            new Thread(new Serializator(carList, i)).start();
//            try { // это чтобы потоки выстроились по порядку
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

// TODO: потом уничтожить main
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
