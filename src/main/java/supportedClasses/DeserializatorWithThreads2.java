package supportedClasses;
import cars.Vehicle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by WORK on 06.09.2016.
 */
public class DeserializatorWithThreads2 implements Runnable {
    /**
     * for specification the path to the serialized cars after race:
     */
    private File dir = new File("src\\main\\resources\\serialStore");

    private CarsResultComparator2 carComparator = new CarsResultComparator2();
    /**
     * for writing cars after race with sorting:
     */
    private TreeSet<Vehicle> carsAfterRace = new TreeSet<>(carComparator);

    private ArrayList<Vehicle> newCarsAfterRace = new ArrayList<>();
    /**
     * for specification of new ObjectInputStream in run():
     */
    private FileInputStream fileReader;
//    /**
//     * counter of the serialized cars:
//     */
//    private int counter;

    /**
     * for specification of the parameters of sorting cars after race:
     */
    private class CarsResultComparator2 implements Comparator<Vehicle> {
        @Override
        public int compare(Vehicle o1, Vehicle o2) {
            if (o1.getRegisteredTime() > o2.getRegisteredTime())
                return -1;
            else if (o1.getRegisteredTime() < o2.getRegisteredTime())
                return 1;
            else
                return 0;
        }
    }

    private boolean dirIsNotEmpty(File dir) {
        this.dir = dir;
        try {
            return (dir.exists() && dir.isDirectory() && dir.listFiles().length != 0);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public void run() {
        if (dirIsNotEmpty(dir)) {
            for (File item : dir.listFiles()) {
                try {
                    fileReader = new FileInputStream(item);
                    ObjectInputStream deserializator = new ObjectInputStream(fileReader);
                    Vehicle nextCar = (Vehicle)deserializator.readObject();
                    carsAfterRace.add(nextCar); //TODO: почему-то не добавляются объекты в лист, хотя они считываются
                    // из файла вполне нормально - на печать ведь поступают
                    newCarsAfterRace.add(nextCar);
                    System.out.println(Thread.currentThread().getName());

                    System.out.printf("\nАвтомобиль %s класса %s прошел трассу за %3.1f секунд со средней скоростью %3.2f м/сек",
                            nextCar.getName(), nextCar.getMarker(), nextCar.getRegisteredTime(), nextCar.getAverageSpeed());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } else
        {
            System.out.println("Directory is empty or does not exist");
        }
    }

    /**
     * deserialization of the serialized cars after race with concurrent streams:
     */
//    public TreeSet<Vehicle> deserialize() {
////        if (dirIsNotEmpty(dir)) {
////            for (File item : dir.listFiles()) {
////                try {
////                    fileReader = new FileInputStream(item);
////                    new Thread(new DeserializatorWithThreads()).start();
////                } catch (IOException e) {
////                    System.out.println(e.getMessage());
////                    return null;
////                }
////            }
////        } else
////        {
////            System.out.println("Directory is empty or does not exist");
////            return null;
////        }
////        return carsAfterRace;
//    }

    public void printSortedResults() {
        Vehicle nextCar;
        Iterator<Vehicle> descendIter = carsAfterRace.descendingIterator();
        while (descendIter.hasNext()) {
            nextCar = descendIter.next();
            System.out.printf("\nАвтомобиль %s класса %s прошел трассу за %3.1f секунд со средней скоростью %3.2f м/сек",
                    nextCar.getName(), nextCar.getMarker(), nextCar.getRegisteredTime(), nextCar.getAverageSpeed());
        }
    }

    public void printUnsortedResults() {
        for(Vehicle nextCar: newCarsAfterRace) {
            System.out.printf("\nАвтомобиль %s класса %s прошел трассу за %3.1f секунд со средней скоростью %3.2f м/сек",
                    nextCar.getName(), nextCar.getMarker(), nextCar.getRegisteredTime(), nextCar.getAverageSpeed());
        }
    }

    public static void main(String[] args) {
        DeserializatorWithThreads2 deser2 = new DeserializatorWithThreads2();
//        File file = new File("src\\main\\resources\\serialStore\\log0_mashka_cars.MashkaCar.dat");
//        Vehicle car = deser.deserialize(file);
//        System.out.printf("\nавтомобиль %s класса %s прошел трассу за %3.1f секунд со средней скоростью %3.2f м/сек",
//                car.getName(), car.getMarker(), car.getRegisteredTime(), car.getAverageSpeed());
//        deser.run();
        new Thread(deser2).start();
        deser2.printSortedResults();
        deser2.printUnsortedResults();
    }
}
