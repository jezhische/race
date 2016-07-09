package trainingTest.utilityTest;

import cars.Vehicle;
import supportedClasses.Printer;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ежище on 09.07.2016.
 */
public class SorterWithList {
    public static void sortVehicles(List<Vehicle> list) {
        /** сортировка результатов гонки: */
        for (int k = list.size(); k >= 0; k--) {
            for (int j = 0; j < k - 1; j++) {
                if (list.get(j).registeredTime > list.get(j+1).registeredTime) {
                    Collections.swap (list, j, j+1);
                }
            }
        }

        for (Vehicle vehicle : list) {
            System.out.println(Printer.printData(vehicle));
        }
    }
}
