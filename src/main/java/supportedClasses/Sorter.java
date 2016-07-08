package supportedClasses;

import cars.Vehicle;

/**
 * Created by Ежище on 30.05.2016.
 */
public class Sorter {
//    public Sorter() {
//    }
    public static void sortVehicles(Vehicle unsortedCarList[]) {// TODO: если у меня здесь только 1 элемент в массиве, то
        // получается IndexOutOfBoundException, поскольку метод обращается и к j=0, и к j=1 элементу. Хотя при проверке
        // ошибки почему-то нет.
        /** сортировка результатов гонки: */
        for (int k = unsortedCarList.length; k >= 0; k--) {
            for (int j = 0; j < k - 1; j++) {
                if (unsortedCarList[j].registeredTime > unsortedCarList[j + 1].registeredTime) {
                    Vehicle term = unsortedCarList[j];
                    unsortedCarList[j] = unsortedCarList[j + 1];
                    unsortedCarList[j + 1] = term;
                }
            }
        }

        for (Vehicle vehicle : unsortedCarList) {
            Printer.printData(vehicle);
        }
    }


}
