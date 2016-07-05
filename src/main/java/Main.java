import cars.BmwCar;
import cars.FerrariCar;
import cars.MashkaCar;
import cars.Vehicle;
import supportedClasses.Sorter;

/**
 * Created by Ежище on 23.05.2016.
 */
public class Main {

    public static void main(String[] args) {
        /** Гонка 1 */
        System.out.println("\nГонка 1\n");
        MashkaCar mashkaCar = new MashkaCar("mashka", 45,600,0.9);
        BmwCar bmwCar = new BmwCar("bmw", 2,120,0.3);
        FerrariCar ferrariCar = new FerrariCar("ferrari", 5,180,0.8);

        Vehicle unsortedCarList[] = {mashkaCar.goVehicle(), bmwCar.goVehicle(), ferrariCar.goVehicle()}; // этот массив
        // содержит объекты класса cars.Vehicle, возвращенные из метода goVehicle().
        Sorter sorter = new Sorter();
        sorter.sortVehicles(unsortedCarList);

        /** Гонка 2 */
        System.out.println("\nГонка 2\n");
        mashkaCar = new MashkaCar("neMashka", 1,15,0.1);
        bmwCar = new BmwCar("byebyeBmw", 0.23,0.1,0.1);
        ferrariCar = new FerrariCar("monstrousFerrari", 45,180000,1);

        unsortedCarList = new Vehicle[] {mashkaCar.goVehicle(), bmwCar.goVehicle(), ferrariCar.goVehicle()};
        sorter.sortVehicles(unsortedCarList);

        /** Гонка 3 */
        System.out.println("\nГонка 3\n");
        mashkaCar = new MashkaCar("kvaziMashka", 450,6000,1);
        bmwCar = new BmwCar("falseBmw", 0,12,0.1);
        ferrariCar = new FerrariCar("antiFerrari", -5, 0, 10);

        unsortedCarList = new Vehicle[] {mashkaCar.goVehicle(), bmwCar.goVehicle(), ferrariCar.goVehicle()};
        sorter.sortVehicles(unsortedCarList);
    }
}
