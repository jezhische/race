package supportedClasses;

import cars.Vehicle;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by Ежище on 04.09.2016.
 */
public class Deserializator {
    private File dir = new File("src\\main\\resources\\serialStore");

    private boolean dirIsNotEmpty(File dir) {
        this.dir = dir;
        try {
            return (dir.exists() && dir.isDirectory() && dir.listFiles().length != 0);
        } catch(NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Vehicle deserialize(File log) { //TODO: здесь список-дерево в аргументы, и возвращать не Vehicle, а этот список
//        try {
//
//        }
//        catch(FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
        if (dirIsNotEmpty(dir)) {
            for (File item:
                 dir.listFiles()) {
                try(ObjectInputStream deserializator = new ObjectInputStream(new FileInputStream(item))) {
                    return (Vehicle) deserializator.readObject(); //TODO: здесь записать в список с сортировкой, например, в дерево
                } catch(Exception e) { // здесь много всяких исключений
                    System.out.println(e.getMessage());
                    return null;
                }
            }

        }

    }

    public static void main(String[] args) {
        Deserializator deser = new Deserializator();
        File file = new File("src\\main\\resources\\serialStore\\log0_mashka_cars.MashkaCar.dat");
        Vehicle car = deser.deserialize(file);
        System.out.printf("\nавтомобиль %s класса %s прошел трассу за %3.1f секунд со средней скоростью %3.2f м/сек",
                car.getName(), car.getMarker(), car.getRegisteredTime(), car.getAverageSpeed());
    }
}
