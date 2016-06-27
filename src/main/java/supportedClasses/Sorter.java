package supportedClasses;

import cars.Vehicle;

/**
 * Created by Ежище on 30.05.2016.
 */
public class Sorter {
    public Sorter() {
    }

    public void sortVehicles(Vehicle unsortedCarList[]) {
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
        // Вместо цикла for организовываем foreach (т.е. значение вновь созданной переменной result
        // приравниваем последовательно к каждому из значений уже отсортированного массива unsortedCarList[]):
        for (Vehicle vehicle : unsortedCarList) {
            Printer.printData(vehicle);
        }
    }


}
