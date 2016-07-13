package dataStorageAndProcessing;

import cars.BmwCar;
import cars.FerrariCar;
import cars.MashkaCar;
import cars.Vehicle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ежище on 08.07.2016.
 */
public class InitialDataFileReader {
    /* Список, в котором будет храниться какое-то количество массивов со значениями аргументов, полученными из файла,
     * а также, возможно, еще и дополнительно забитыми вручную пользователем: **/
    List<Vehicle> carsToRace = new ArrayList<>();

    void readArgsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src//main//resources//pilotProbesData//" +
                "Probe3.txt"))) {
            /* переменная для записи прочитанной строки: **/
            String carFromFile; // это будет прочитанная строчка
            /* счетчик прочитанных строк: **/
            int i = 0;
            /* regex для освобождения от ненужной шелухи строчек, которые будут получены из файла: **/
            Pattern groupWordsPattern = Pattern.compile("\\w+(.\\w+)?");
//            Pattern groupWordsPattern = Pattern.compile("\"[\\w.]*\"");

            /* читаем файл по строкам: **/
            while ((carFromFile = br.readLine()) != null) {

                /* создаем подборщик для написанного ранее регулярного выражения, применяем его
                * в прочитанной строчке:**/
                Matcher groupWordsMatcher = groupWordsPattern.matcher(carFromFile);
                /* Создаем масив String, в который будут забиты очищенные от шелухи значения аргументов для одного
                * автомобиля из файла (всего 5 аргументов) в качестве модели автомобиля: **/
                String[] oneLineArgs = new String[5];
                /* и заливаем группы символов типа "\\w+(.\\w+)?" в этот массив стрингов. При этом отлавливаем
                 * возможный IndexOutOfBoundException с помощью блока try / catch: **/
                int j = 0;
                try {
                        while (groupWordsMatcher.find()) {
                                oneLineArgs[j] = groupWordsMatcher.group();
                                j++;
                        }
                } catch (Exception e) {
                    System.out.println("Параметры автомобиля " + oneLineArgs[0] + " объявлены неверно: найден " +
                            "избыточный параметр. Автомобиль снят с гонки.");
                    continue;
                }
                /* для игнорирования нулевых строчек: **/
                if (oneLineArgs[1] == null)
                    continue;
                /* теперь прогоняем созданный масив через switch, чтобы выяснить, к какому классу относится данная
                 * модель и забить аргументы в соответствующий объект-автомобиль, а затем заносим объект-автомобиль
                  *  в список carsToRace. При этом отлавливаем NumberFormatException на случай, если в последних
                  *  трех аргументах записаны не цифры (а также здесь отлавливается какое-то исключение насчет
                  *  неверной инициализации массива - не хватает элемента): **/
                String name;
                String marker;
                double acceleration;
                double fullSpeed;
                double mobility;

                try {
                    name = oneLineArgs[0];
                    marker = oneLineArgs[1];
                    acceleration = Double.valueOf(oneLineArgs[2]);
                    fullSpeed = Double.valueOf(oneLineArgs[3]);
                    mobility = Double.valueOf(oneLineArgs[4]);
                } catch (Exception e) {
                    System.out.println("Параметры автомобиля " + oneLineArgs[0] + " объявлены в неверном формате " +
                            "или какие-то параметры отсутствуют. Автомобиль снят с гонки.");
                    continue;
                }
                /* не забивать аргументы автомобиля, если какой-либо из double параметров равен 0 либо какой-либо из
                параметров отсутствует: **/
                if (acceleration <= 0 || fullSpeed <= 0 || mobility <= 0) {
                    System.out.println("Параметры автомобиля " + oneLineArgs[0] + " объявлены неверно: один из " +
                            "числовых параметров равен 0. Автомобиль снят с гонки.");
                    continue;
                }
                if (mobility > 1) {
                    System.out.println("Параметры автомобиля " + oneLineArgs[0] + " объявлены неверно: " +
                            "недопустимое значение параметра mobility. Автомобиль снят с гонки.");
                    continue;
                }
                switch (marker) {
                    case "cars.MashkaCar":
                        carsToRace.add(i, new MashkaCar(name, marker, acceleration, fullSpeed, mobility));
                        break;
                    case "cars.BmwCar":
                        carsToRace.add(i, new BmwCar(name, marker, acceleration, fullSpeed, mobility));
                        break;
                    case "cars.FerrariCar":
                        carsToRace.add(i, new FerrariCar(name, marker, acceleration, fullSpeed, mobility));
                        break;
                    /* В дефолте сообщение об ошибке маркера класса автомобиля **/
                    default:
                        System.out.println("Класс автомобиля " + oneLineArgs[0] + " объявлен неверно. Автомобиль" +
                                " снят с гонки.");
                        continue;
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        InitialDataFileReader gsa = new InitialDataFileReader();
        gsa.readArgsFromFile();
        System.out.println("acceleration of car 0 " + gsa.carsToRace.get(0).getName() + " = " +
                gsa.carsToRace.get(0).getAcceleration());
        System.out.println("acceleration of car 1 " + gsa.carsToRace.get(1).getName() + " = " +
                gsa.carsToRace.get(1).getAcceleration());
        System.out.println("acceleration of car 2 " + gsa.carsToRace.get(2).getName() + " = " +
                gsa.carsToRace.get(2).getAcceleration());
        System.out.println("acceleration of car 3 " + gsa.carsToRace.get(3).getName() + " = " +
                gsa.carsToRace.get(3).getAcceleration());
        System.out.println("acceleration of car 4 " + gsa.carsToRace.get(4).getName() + " = " +
                gsa.carsToRace.get(4).getAcceleration());
        System.out.println("acceleration of car 5 " + gsa.carsToRace.get(5).getName() + " = " +
                gsa.carsToRace.get(5).getAcceleration());
        System.out.println("acceleration of car 6 " + gsa.carsToRace.get(6).getName() + " = " +
                gsa.carsToRace.get(6).getAcceleration());
        System.out.println("acceleration of car 7 " + gsa.carsToRace.get(7).getName() + " = " +
                gsa.carsToRace.get(7).getAcceleration());
        System.out.println("acceleration of car 8 " + gsa.carsToRace.get(8).getName() + " = " +
                gsa.carsToRace.get(8).getAcceleration());
    }
}
