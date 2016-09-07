package supportedClasses;

import cars.Vehicle;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by WORK on 07.09.2016.
 */
public class DiagramPrinter {

    public static void diaPrint(TreeSet<Vehicle> carsAfterRace) {
        Vehicle nextCar;
        int counter = 1;
        double timeForTwoPercentSpasing = 50 / carsAfterRace.first().getRegisteredTime(); // таким образом, если
        // умножить это число на registeredTime данной машины (с самым большим временем), получим 50 делений (100%),
        // а если умножить на время других машин - то соответственно меньше делений
        double speedForTwoPercentSpasing = 50 / carsAfterRace.first().getAverageSpeed();
        Iterator<Vehicle> descendIter = carsAfterRace.descendingIterator(); // можно было бы сразу сортировать в
        // правильном порядке, но очень хотелось нисходящий итератор попробовать...
        while (descendIter.hasNext()) {
            nextCar = descendIter.next();
            int timeDiagram = (int)(nextCar.getRegisteredTime() * timeForTwoPercentSpasing);
            for (int i = 0; i < timeDiagram; i++) {
                System.out.print("|");
            }
            System.out.printf("%10d позиция. Автомобиль %s класса %s прошел трассу за %3.1f секунд\n", counter,
                    nextCar.getName(), nextCar.getMarker(), nextCar.getRegisteredTime());
            counter++;
        }
        System.out.println("");
        while (descendIter.hasNext()) {
            nextCar = descendIter.next();
            int speedDiagram = (int)(nextCar.getAverageSpeed() * speedForTwoPercentSpasing);
            for (int i = 0; i < speedDiagram; i++) {
                System.out.print("|");
            }
            System.out.printf("%10d позиция. Автомобиль %s класса %s прошел трассу со средней " +
                            "скоростью %3.2f м/сек\n", counter, nextCar.getName(), nextCar.getMarker(),
                    nextCar.getAverageSpeed());
            counter++;
        }
    }
}
