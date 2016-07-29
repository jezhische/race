package dataStorageAndProcessing;

import cars.BmwCar;
import cars.FerrariCar;
import cars.MashkaCar;
import cars.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ежище on 15.07.2016.
 * * Класс для составления и хранения списка автомобилей, параметры которых юзер забивает вручную с клавиатуры.
 */
public class InitialDataScannerSystemIn extends Vehicle {
    private List<Vehicle> userCarsToRace = new ArrayList<>(); // список автомобилей, введенных юзером вручную

    public List<Vehicle> getUserCarsToRace() {
        return userCarsToRace;
    }

    private List<String> oneLineArgs = new ArrayList<>(5); // сюда заносятся очищенные аргументы на 1 автомобиль.
    // Это не массив, так что исключений при превышении емкости не будет, так что программа не будет выдавать ошибки.

    private boolean runCycle = true; // переменная для продолжения внешнего цикла в методе; когда false, происходит
    // выброс из цикла. Должна быть прописана в классе, поскольку иначе при обращении к ней из вложенных методов
    // она не будет меняться в аргументе внешнего цикла.
    public boolean getRunCycle() {return runCycle;} // чтобы можно было при тестировании вызвать runCycle.

    private boolean continueCycle = false; // индикатор ошибки, при true цикл начинается сначала без занесения
    // автомобиля в список

    private int k = 0; // счетчик неправильных попыток юзера, чтобы он не пробовал бесконечно
    public int getK() {return k;} // геттер, чтобы можно было при тестировании взвать k.
    public void setK (int k) { // и все-таки сеттер, чтобы можно было при тестировании изменить k.
        if (k >= 0 && k <= 3)
            this.k = k;
        else
            this.k = 3;
    }

    private int i = 0; // счетчик индекса автомобиля в листе private List<Vehicle> userCarsToRace.

    // метод для отслеживания неправильных попыток юзера и предупреждения об окончании программы:
    public void printAppendix() {
        if (k == 3) {
            System.out.println("Ввод данных прерван. Пожалуйста, запустите программу снова.");
            runCycle = false;
        } else if (k == 2) {
            System.out.println("После следующей неправильной попытки ввода программа будет завершена.");
        } else {
            System.out.println("Введите следующий автомобиль, либо напечатайте esc и нажмите Enter " +
                    "для перехода на следующий этап.");
        }
    }

    // Основной метод для считывания данных с консоли:
    public void readUserData() {
        Pattern userGroupsPattern = Pattern.compile("(-)?\\w+(.\\w+)?"); // regex для очистки аргументов, заносимых
        // в конструктор new Vehicle, от пробелов, запятых и возможной иной шелухи.
        // TODO: здесь нужно еще какую-нибудь RunTime Error, чтобы ожидание ввода не затянулось навечно. И предусмотреть
        // возможность выхода: хотите выйти, нажмите Y...
        while (runCycle) {
//            if (k == 2) {
//                System.out.println("После следующей неправильной попытки ввода программа будет завершена.");
//            } else if (k == 3) {
//                System.out.println("Ввод данных прерван. Пожалуйста, запустите программу снова.");
//                runCycle = false;
//                break;
//            }
            oneLineArgs.clear(); // почистили список от занесенных в него агрументов предыдущего автомобиля.
            Scanner scanUserCar = new Scanner(System.in);
            String userCarFromScanner = scanUserCar.nextLine(); // отсканированная (еще не очищенная) строчка

//            System.out.println(oneLineArgs.get(0));


            Matcher userGroupsMatcher = userGroupsPattern.matcher(userCarFromScanner);
            int j = 0;
            while (userGroupsMatcher.find()) {
                oneLineArgs.add(j, userGroupsMatcher.group()); // заносим очищенные аргументы в список oneLineArgs
                j++;
            }


//            System.out.println(oneLineArgs.get(0));

            // Для игнорирования нулевых строчек:
            if (oneLineArgs.size() == 0) {
                System.out.println("Введите параметры автомобиля либо напечатайте esc и нажмите Enter " +
                        "для перехода на следующий этап.");
                if (k == 3) {
                    System.out.println("Ввод данных прерван. Пожалуйста, запустите программу снова.");
                    runCycle = false;
                    break;
                } else if (k == 2) {
                    System.out.println("После следующей неправильной попытки ввода программа будет завершена.");
                    k++;
                    continue;
                } else {
                    k++;
                    continue;
                }
            }

            // для выхода из программы:
            if ((k > 0 || !userCarsToRace.isEmpty() || continueCycle) && oneLineArgs.get(0).equals("esc")) {
                runCycle = false;
                break;
            } else if ((k == 0 || userCarsToRace.isEmpty()) && oneLineArgs.get(0).equals("esc")){ // смысл в том, что
                // с самого начала юзер не знает команды esc для выхода; если в самом начале он наберет автомобиль с
                // именем esc, то система его предупредит (с самого начала k == 0 || userCarsToRace.isEmpty()). Потом,
                // когда он забил в список первый автомобиль или сделал первую ошибку, у него появляется сообщение
                // об использовании esc, и он уже может пользоваться этой командой. Впрочем,проще было указать это
                // как условие в Bookmaker.
                System.out.println("Имя esc зарезервировано как команда для перехода на следующий этап.");
                continueCycle = true;
                continue;
            }

            if (oneLineArgs.size() > 5) {
                System.out.printf("Параметры автомобиля %s объявлены неверно: найден " +
                        "избыточный параметр. Автомобиль снят с гонки.\n", oneLineArgs.get(0));
                printAppendix();
                k++;
                continue;
            }
            if (oneLineArgs.size() < 5) {
                System.out.printf("Параметры автомобиля %s объявлены неверно: не все параметры " +
                        "введены. Автомобиль снят с гонки.\n", oneLineArgs.get(0));
                printAppendix();
                k++;
                continue;
            }

             /* теперь прогоняем созданный масив через switch, чтобы выяснить, к какому классу относится данная
                 * модель и забить аргументы в соответствующий объект-автомобиль, а затем заносим объект-автомобиль
                  *  в список carsToRace. При этом отлавливаем NumberFormatException на случай, если в последних
                  *  трех аргументах записаны не цифры (а также здесь отлавливается какое-то исключение насчет
                  *  неверной инициализации массива - не хватает элемента): **/
            // Вначале обнуляем аргументы, которые хранятся в классе Vehicle с прошлого автомобиля:
            setNullConstructorArguments();
            //TODO: не забыть сделать метод для отлавливания автомобилей с одинаковыми именами
            try {
                setName(oneLineArgs.get(0));
                setMarker(oneLineArgs.get(1));
                setAcceleration(Double.valueOf(oneLineArgs.get(2)));
                setFullSpeed(Double.valueOf(oneLineArgs.get(3)));
                setMobility(Double.valueOf(oneLineArgs.get(4)));
            } catch (NumberFormatException e) {
                System.out.printf("Параметры автомобиля %s объявлены в неверном формате " +
                        "или какие-то параметры отсутствуют. Автомобиль снят с гонки.\n", oneLineArgs.get(0));
                printAppendix();
                k++;
                continue;
            }

                /* не забивать аргументы автомобиля, если какой-либо из double параметров равен 0 либо какой-либо из
                параметров отсутствует: **/
            if (getAcceleration() <= 0 || getFullSpeed() <= 0 || getMobility() <= 0) {
                System.out.printf("Неправильная инициализация числовых параметров автомобиля. Автомобиль снят с гонки.\n", oneLineArgs.get(0));
                printAppendix();
                k++;
                continue;
            }
            if (getMobility() > 1) {
                System.out.printf("Недопустимое значение параметра mobility. Автомобиль %s снят с гонки.\n",
                        oneLineArgs.get(0));
                printAppendix();
                k++;
                continue;
            }
            // TODO: выкидывать автомобиль, если такое имя уже есть.
            switch (getMarker()) {
                case "Mashka":
                    userCarsToRace.add(i, new MashkaCar(getName(), getMarker(), getAcceleration(), getFullSpeed(), getMobility()));
                    break;
                case "Bmw":
                    userCarsToRace.add(i, new BmwCar(getName(), getMarker(), getAcceleration(), getFullSpeed(), getMobility()));
                    break;
                case "Ferrari":
                    userCarsToRace.add(i, new FerrariCar(getName(), getMarker(), getAcceleration(), getFullSpeed(), getMobility()));
                    break;
                    /* В дефолте сообщение об ошибке маркера класса автомобиля **/
                default:
                    System.out.printf("Класс автомобиля %s объявлен неверно. Автомобиль" +
                            " снят с гонки.\n", oneLineArgs.get(0));
                    printAppendix();
                    continueCycle = true;
                    k++;
                    break;
            }


            if (!continueCycle) {
                System.out.printf("Автомобиль %s принят в гонку. \nВведите следующий автомобиль, " +
                        "либо напечатайте esc и нажмите Enter для перехода на следующий этап.\n", oneLineArgs.get(0));
                i++;
                k = 0;
            }
        }
    }

    public static void main(String[] args) {
        InitialDataScannerSystemIn ini = new InitialDataScannerSystemIn();
        ini.readUserData();
//        System.out.println(ini.userCarsToRace.get(0).getName());
//        System.out.println(ini.userCarsToRace.get(0).getMobility());
//        System.out.println(ini.userCarsToRace.get(1).getAcceleration());
    }

}
