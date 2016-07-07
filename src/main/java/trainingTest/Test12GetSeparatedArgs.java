package trainingTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ежище on 03.07.2016.
 */
public class Test12GetSeparatedArgs {
    /* Список, в котором будет храниться какое-то количество массивов со значениями аргументов, полученными из файла,
     * а также, возможно, еще и дополнительно забитыми вручную пользователем: **/
    List<String[]> carsArgs = new ArrayList<>();

    void getArgsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src//main//resources//pilotProbesData//" +
                "Probe3.txt"))) {
            String carFromFile; // это будет наша прочитанная строчка

            int i = 0;
            /* regex для освобождения от ненужной шелухи строчек, которые мы будем получать из файла: **/
            Pattern groupWordsPattern = Pattern.compile("\\w+(.\\w+)?");
//            Pattern groupWordsPattern = Pattern.compile("\"[\\w.]*\"");
            /* читаем файл по строкам: **/
            while ((carFromFile = br.readLine()) != null) { // TODO: здесь нужно писать исключение, потому что подборщик
                    // нечаянно найти больше совпадений, чем 5 (больше, чем capacity String[] separateCarArgsFromFile)
                    
                /* создаем подборщик для написанного ранее регулярного выражения, применяем его
                * в прочитанной строчке:**/
                Matcher groupWordsMatcher = groupWordsPattern.matcher(carFromFile);
                /* Создаем новый массив, в который будут забиты очищенные от шелухи значения аргументов для одного
                * автомобиля из файла (всего 5 аргументов): **/
                String[] separateCarArgsFromFile = new String[5];
                /* и забиваем группы символов типа "\\w+(.\\w+)?" в этот массив стрингов: **/
                int j = 0;
                while (groupWordsMatcher.find()) {
                    separateCarArgsFromFile[j] = groupWordsMatcher.group();
                    j++;
                }
                /* теперь полученный массив загоняем в лист как элемент с соответствующим индексом,
                 * а затем возвращаемся в начало и читаем следующую строчку: **/
                carsArgs.add(i, separateCarArgsFromFile);
//                System.out.println(i + ". " + Arrays.deepToString(carsArgs.get(i))); // этот вывод на консоль
//                // я оставил здесь временно, специально, чтобы убедиться, что массив, содержащий прочитанную
//                // строчку, был записан под соответствующим индексом в лист.
                i++;
            }
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        Test12GetSeparatedArgs gsa = new Test12GetSeparatedArgs();
        gsa.getArgsFromFile();
        for (int i = 0; i < gsa.carsArgs.size(); i++) {
//            String[] s = gsa.carsArgs.get(i);
//            for (String string: s) {
//                System.out.print(string + ", ");
//            }
            System.out.println(Arrays.deepToString(gsa.carsArgs.get(i)));
        }
    }
}
