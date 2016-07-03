package trainingTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ежище on 02.07.2016.
 */
public class Test11StringArrayInList {
    /* Список, в котором будет храниться какое-то количество массивов со значениями аргументов, полученными из файла,
     * а также, возможно, еще и дополнительно забитыми вручную пользователем: **/
    List<String[]> carsArgs = new ArrayList<>();

    //    // сюда выводим строчки с записями из файла:
//    List<String> carsFromFile = new ArrayList<>();
//    // здесь очищаем их от шелухи и превращаем в списки из 5 стрингов, заносим эти "подсписки" в список:
    /* читаем файл по строкам, строки сплитим, чистим от шелухи, забиваем в массивы, а массивы - в лист: **/
    void getCarsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src//main//resources//pilotProbesData//" +
                "Probe3.txt"))) {
            String carFromFile; // это будет наша прочитанная строчка
            /* Массив, в который будут забиты очищенные от шелухи значения аргументов для одного
    * автомобиля из файла (всего 5 аргументов): **/
            String[] splitCarFromFile; // можно было бы сразу забить длину массива [5], но вдруг чего изменится в условии...
    /* Массив, в который будут забиты уже очищенные значения аргументов: **/
            String[] formatedCarFromFile = null;
            String carArg;
            int i = 0;
//            Pattern pattern = Pattern.compile()
            /* читаем файл по строкам: **/
            while ((carFromFile = br.readLine()) != null) {
                /* каждую строку расщепляем и забиваем в массив: **/
                splitCarFromFile = (carFromFile.split("\\s+"));
                //затем нужно выцепить вначале только буквы \w
                // затем только цифры \d+(\.\d+)?  из каждого элемента splitCarFromFile, и
                // переписать их в массив formatedCarFromFile
//                for (int j = 0; j < splitCarFromFile.length; j++) {
//
//                }
                for (String string : splitCarFromFile) {


                }
                carsArgs.add(i, formatedCarFromFile);
//                carsFromFile.add(i, carFromFile);
                i++;
            }
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        }

        // ^\d+(\.\d+)?
    }
// использование названия пакета и класса, например cars.FerrariCar, может быть следующим:
    //"if" вот этот элемент массива эквивалентен FerrariCar, то создать образец класса FerrariCar с такими-то параметрами

    public static void main(String[] args) {
        Test11StringArrayInList lil = new Test11StringArrayInList();
        lil.getCarsFromFile();
//        System.out.println(lil.carsFromFile);
////        System.out.println(lil.carsFromFile.toString().matches("\\w.*"));
//        for (String string : lil.splitCarFromFile) {
//            System.out.print(string);
//        }

    }

}
