package supportedClasses;

import cars.Vehicle;
import dataStorageAndProcessing.InitialDataFileReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by Ежище on 03.09.2016.
 */
public class Serializator implements Callable<Serializator> {

    /** list of the cars ready to race: */
    private ArrayList<Vehicle> carList = new ArrayList<>();

    /** counter of the serializing cars: */
    private int counter;

    private volatile int threadCounter;

    /** for specification the path to the cars serialization: */
    private File dir = new File("src\\main\\resources\\serialStore");

    public Serializator(ArrayList<Vehicle> carList, int counter) {
        this.carList = carList;
        this.counter = counter;
    }

    public Serializator() {
    }

    /** to create required directory for serialized files writing and to delete all the files there if it exists: */
    // (тут наверчено булиня, просто, чтобы поупражняться, можно было бы void):
    private boolean fileDealer(File dir) {
        this.dir = dir;
        try {
            if (!dir.exists()) {
                dir.mkdir();
            } else {
                if (dir.isDirectory()) { // && dir.listFiles().length != 0  - в этой строчке нужды нет, ошибка не вылазит
                    for (File item :
                            dir.listFiles()) {
                        item.delete(); // получаем список всех файлов в директории и стираем их в цикле один за другим.
                        // Проще было бы стереть всю директорию, но так интереснее
                    }
                }
            }
            if (dir.exists())
                return (dir.listFiles().length == 0);
            return false;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /** to serialize results of the race in run(): */
    @Override
    public Serializator call() throws Exception { //TODO: стереть все выводы в консоль
        Vehicle car = carList.get(counter);
        File file = new File(String.format("src\\main\\resources\\serialStore\\log%d_%s_%s.dat",
                counter, car.getName(), car.getMarker()));
//        if (fileDealer(dir)) {
        fileDealer(dir);
            try (ObjectOutputStream serialize = new ObjectOutputStream
                    (new FileOutputStream(file))) {
                car.goVehicle();
                System.out.printf("внутри: %s %5.1f %7.2f\n", car.getName(), car.getRegisteredTime(), car.getAverageSpeed());
                serialize.writeObject(car);
                serialize.flush();
                increment();
            } catch (IOException e) {
                System.out.println("выкинуло!");
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            System.out.printf("снаружи: %s %5.1f %7.2f\n", car.getName(), car.getRegisteredTime(), car.getAverageSpeed());
//        }
        return new Serializator(carList, counter);
    }

    /**
     * to create some concurrent streams
     */
    public void go(ArrayList<Vehicle> carList) throws ExecutionException, InterruptedException {
        this.carList = carList;
        if (fileDealer(dir)) {
            ExecutorService executor = Executors.newFixedThreadPool(carList.size());
            for (counter = 0; counter < carList.size(); counter++) {
                Future threadDetector = executor.submit(new Serializator(carList, counter));
                try {         // если дать поспать подольше, сенсоры заработают, но записывается только последний файл
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                if (threadDetector.isDone())
                    increment();
//                Serializator serializator = (Serializator)threadDetector.get();
//                serializator.increment();
            }
            executor.shutdown();
            if (executor.isShutdown()) {
                printWrittenFilesCount();
                printThreadsCount();
            }

        }
    }

    private void increment() {
        threadCounter++;
    }
    public void printThreadsCount() {
        System.out.println("Запущено потоков: " + threadCounter);
    }
    private void printWrittenFilesCount() {
        System.out.println("Сериализовано объектов: " + dir.listFiles().length);
    }

    // TODO: потом уничтожить main
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        InitialDataFileReader readFile = new InitialDataFileReader(new File("src//main//resources//pilotProbesData//Probe4.txt"));
        ArrayList<Vehicle> probe = readFile.readArgsFromFile();
//        for (int counter = 0; counter < probe.size(); counter++) {
//            System.out.printf("класс %d = %s;\n", counter, probe.get(counter).getClass());
//            Vehicle raceCar = probe.get(counter).goVehicle();
//            double time = raceCar.getRegisteredTime();
//            double avSpeed = raceCar.getAverageSpeed();
//            System.out.printf("%s прошел трассу за %5.1f секунд, средняя скорость %5.2f м/с\n",
//                    raceCar.getName(), time, avSpeed);
//        }

        Serializator ser = new Serializator();
        ser.go(probe);
//        ser.printThreadsCount();
    }
}
