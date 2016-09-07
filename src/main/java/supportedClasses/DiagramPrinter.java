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
        double speedForTwoPercentSpasing = 50 / carsAfterRace.last().getAverageSpeed();

        Iterator<Vehicle> descendIter = carsAfterRace.descendingIterator(); // можно было бы сразу сортировать в
        // правильном порядке, но очень хотелось нисходящий итератор попробовать...
        Iterator<Vehicle> descendIter2 = carsAfterRace.descendingIterator();
        while (descendIter.hasNext()) {
            String timeStack = "";
            nextCar = descendIter.next();
            int timeDiagram = (int)(nextCar.getRegisteredTime() * timeForTwoPercentSpasing);
            for (int i = 0; i < timeDiagram; i++)
                timeStack = timeStack.concat("|");
            for (int i = 0; i < (50 - timeDiagram); i++)
                timeStack = timeStack.concat(" ");
            timeStack = timeStack.concat(String.format("%5d позиция.  Автомобиль %s класса %s прошел трассу за %s",
                    counter, nextCar.getName(), nextCar.getMarker(), timeFormat(nextCar.getRegisteredTime())));
            System.out.println(timeStack);
            counter++;
        }
        counter = 1;
        System.out.println("");
        while (descendIter2.hasNext()) {
            String speedStack = "";
            nextCar = descendIter2.next();
            int speedDiagram = (int)(nextCar.getAverageSpeed() * speedForTwoPercentSpasing);
            for (int i = 0; i < speedDiagram; i++)
                speedStack = speedStack.concat("|");
            for (int i = 0; i < (50 - speedDiagram); i++)
                speedStack = speedStack.concat(" ");
            speedStack = speedStack.concat(String.format("%5d позиция. Автомобиль %s класса %s прошел трассу " +
                    "со средней скоростью %3.2f км/ч", counter, nextCar.getName(), nextCar.getMarker(),
                    nextCar.getAverageSpeed()));
            System.out.println(speedStack);
            counter++;
        }
    }
    private static String timeFormat(double registeredTime) {
        int roundCheckInTime = (int) Math.round(registeredTime);
        int hours = (int) Math.floor(roundCheckInTime / 3600);
        int min = (int) Math.floor((roundCheckInTime - hours * 3600) / 60);
        int sec = roundCheckInTime % 60;
        return String.format("%d часов %d минут %d секунд;", hours, min, sec);
    }
}
