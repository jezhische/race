package supportedClasses;

import cars.Vehicle;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by Ежище on 06.09.2016.
 */
public class Deserializator2 {
    /**
    * for specification the path to the serialized cars after race:
            */
    private File dir = new File("src\\main\\resources\\serialStore");

    private CarsResultComparator carComparator = new CarsResultComparator();
    /**
     * for writing cars after race with sorting:
     */
    private TreeSet<Vehicle> carsAfterRace = new TreeSet<>(carComparator);
//    /**
//     * counter of the serialized cars:
//     */
//    private int counter;

    /**
     * for specification of the parameters of sorting cars after race:
     */
    private class CarsResultComparator implements Comparator<Vehicle> {
        @Override
        public int compare(Vehicle o1, Vehicle o2) {
            if (o1.getAverageSpeed() < o2.getAverageSpeed())
                return -1;
            else if (o1.getAverageSpeed() > o2.getAverageSpeed())
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

    /**
     * deserialization of the serialized cars after race with concurrent streams:
     */
    public TreeSet<Vehicle> deserialize() {
        if (dirIsNotEmpty(dir)) {
            for (File item : dir.listFiles()) {
                try(ObjectInputStream deserializator = new ObjectInputStream(new FileInputStream(item))) {
                    carsAfterRace.add((Vehicle) deserializator.readObject());
                } catch (Exception e) { // здесь много всяких исключений
                    System.out.println(e.getMessage());
                    return null;
                }
            }
        } else {
            System.out.println("Directory is empty or does not exist");
            return null;
        }
        return carsAfterRace;
    }

    public void printSortedResults() {
        Vehicle nextCar;
        Iterator<Vehicle> descendIter = carsAfterRace.descendingIterator();
        while (descendIter.hasNext()) {
            nextCar = descendIter.next();
            System.out.printf("\nАвтомобиль %s класса %s прошел трассу за %3.1f секунд со средней скоростью %3.2f м/сек",
                    nextCar.getName(), nextCar.getMarker(), nextCar.getRegisteredTime(), nextCar.getAverageSpeed());
        }
    }

    // TODO: убрать потом main
    public static void main(String[] args) {
        Deserializator2 deser2 = new Deserializator2();
//        File file = new File("src\\main\\resources\\serialStore\\log0_mashka_cars.MashkaCar.dat");
//        Vehicle car = deser.deserialize(file);
//        System.out.printf("\nавтомобиль %s класса %s прошел трассу за %3.1f секунд со средней скоростью %3.2f м/сек",
//                car.getName(), car.getMarker(), car.getRegisteredTime(), car.getAverageSpeed());
        deser2.deserialize();
        deser2.printSortedResults();
    }
}
