package trainingTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ежище on 02.07.2016.
 */
public class Test11ListInList {
    // сюда выводим строчки с записями из файла:
    List<String> carsFromFile = new ArrayList<>();
    // здесь очищаем их от шелухи и превращаем в списки из 5 стрингов, заносим эти "подсписки" в список:
    List<ArrayList> carsArg = new ArrayList<>();

    // и еще один лист, в котором будут содержаться машины. Аргументы для них по порядку забиваем из каждого
    // подсписка, содержащегося в списке carArg

    /* записываем строки файла как элементы нового списка:  **/
    void getCarsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src//main//resources//pilotProbesData//" +
                "Probe3.txt"))) {
            String carFromFile;
            int i = 0;
            while ((carFromFile = br.readLine()) != null) {
                carsFromFile.add(i, carFromFile);
                i++;
            }
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

    }

}
