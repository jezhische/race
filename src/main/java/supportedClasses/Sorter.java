package supportedClasses;

import cars.Vehicle;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ежище on 30.05.2016.
 */
public class Sorter {
    public static void sortVehicles(List<Vehicle> carsToRace) {
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
