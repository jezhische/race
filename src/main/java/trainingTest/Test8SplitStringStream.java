package trainingTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Ежище on 29.06.2016.
 */
public class Test8SplitStringStream {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("src//main//resources//pilotProbesData//Probe2.txt"))) {
            String[] carObjects = null;
            String carObject;
            int i = 0;
            while (!(carObject = br.readLine()).equals(null)) {
                System.out.println(carObject);
                carObjects[i] = carObject;
                i++;
            }

        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        }


//
//        MashkaCar mashkaCar = new MashkaCar("neMashka", 1,15,0.1);
//        Vehicle unsortedCarList[] = {mashkaCar.goVehicle()};
//        Sorter sorter = new Sorter();
//        sorter.sortVehicles(unsortedCarList);
    }
}
