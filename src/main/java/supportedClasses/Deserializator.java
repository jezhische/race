package supportedClasses;

import cars.Vehicle;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import static supportedClasses.DiagramPrinter.diaPrint;

/**
 * Created by Ежище on 06.09.2016.
 */
public class Deserializator {
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
    private class CarsResultComparator implements Comparator<Vehicle>  {
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

    private File[] listFiles(File dir) { // по сути, переопределение метода public File[] listFiles() (хотя ни
        // интерфейс не имплементирован, ни суперкласса нет) - просто чтобы отловить исключение. Может, и не нужно.
        this.dir = dir;
        try {
        return dir.listFiles();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    private boolean dirIsNotEmpty(File dir) {
        this.dir = dir;
            return (dir.exists() && dir.isDirectory() && listFiles(dir).length != 0);
    }

    /**
     * deserialization of the serialized cars after race with concurrent streams:
     */
    public TreeSet<Vehicle> deserialize() {
        if (dirIsNotEmpty(dir)) {
            for (File item : listFiles(dir)) {
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
        int counter = 1;
        Iterator<Vehicle> descendIter = carsAfterRace.descendingIterator();
        while (descendIter.hasNext()) {
            nextCar = descendIter.next();
            System.out.printf("\n%d позиция. Автомобиль %s класса %s прошел трассу за %3.1f секунд со средней " +
                            "скоростью %3.2f м/сек", counter, nextCar.getName(), nextCar.getMarker(),
                    nextCar.getRegisteredTime(), nextCar.getAverageSpeed());
            counter++;
        }
    }

    // TODO: убрать потом main
    public static void main(String[] args) {
        Deserializator deser = new Deserializator();
//        File file = new File("src\\main\\resources\\serialStore\\log0_mashka_cars.MashkaCar.dat");
//        Vehicle car = deser.deserialize(file);
//        System.out.printf("\nавтомобиль %s класса %s прошел трассу за %3.1f секунд со средней скоростью %3.2f м/сек",
//                car.getName(), car.getMarker(), car.getRegisteredTime(), car.getAverageSpeed());
        diaPrint(deser.deserialize());
        System.out.println("");
//        deser.printSortedResults();

    }
}
