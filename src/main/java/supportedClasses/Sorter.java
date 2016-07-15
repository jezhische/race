package supportedClasses;

import cars.Vehicle;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ежище on 30.05.2016.
 */
public class Sorter {
    //    public Sorter() {
//    }
    public static void sortVehicles(List<Vehicle> carsToRace) {// TODO: если у меня здесь только 1 элемент в массиве, то
        // получается IndexOutOfBoundException, поскольку метод обращается и к j=0, и к j=1 элементу. Хотя при проверке
        // ошибки почему-то нет.
        /** сортировка результатов гонки: */
        for (int k = carsToRace.size(); k >= 0; k--) {
            for (int j = 0; j < k - 1; j++) {
                if (carsToRace.get(j).getRegisteredTime() > carsToRace.get(j + 1).getRegisteredTime()) {
                    Collections.swap(carsToRace, j, j + 1);
                }
            }
        }

        for (Vehicle vehicle : carsToRace) {
            System.out.println(Printer.printData(vehicle));
        }
    }


}
