package trainingTest.utilityTest;

import cars.BmwCar;
import cars.FerrariCar;
import cars.MashkaCar;
import cars.Vehicle;
import dataStorageAndProcessing.CarModel;
import supportedClasses.Sorter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ежище on 09.07.2016.
 */
public class MainWithListProbe {
    public static void main(String[] args) {
        CarModel car1 = new CarModel();
        car1.name = "mashkaTest";
        car1.acceleration = 5.8;
        car1.fullSpeed = 160;
        car1.mobility = 0.8;
        CarModel car2 = new CarModel();
        car2.name = "bmwTest";
        car2.acceleration = 5.8;
        car2.fullSpeed = 160;
        car2.mobility = 0.8;
        CarModel car3 = new CarModel();
        car3.name = "ferrariTest";
        car3.acceleration = 5.8;
        car3.fullSpeed = 160;
        car3.mobility = 0.8;
        MashkaCar mashkaTest = new MashkaCar(car1);
        BmwCar bmwTest = new BmwCar(car2);
        FerrariCar ferrariTest = new FerrariCar(car3);
        Vehicle unsortedCarList[] = {mashkaTest.goVehicle(), bmwTest.goVehicle(), ferrariTest.goVehicle()};
        Sorter.sortVehicles(unsortedCarList);
        System.out.println("");
        // Теперь сделаем лист с объектами, засунем туда несортированный список, вытащим и посмотрим, что там с ним стало.
        List<Vehicle> list = new ArrayList<>();
        for (Vehicle vehicle : unsortedCarList) {
            list.add(vehicle);
        }
        SorterWithList.sortVehicles(list);

    }
}
