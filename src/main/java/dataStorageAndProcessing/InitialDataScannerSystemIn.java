package dataStorageAndProcessing;

import cars.BmwCar;
import cars.FerrariCar;
import cars.MashkaCar;
import cars.Vehicle;
import entities.CarModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ежище on 15.07.2016.
 * * Класс для составления списка автомобилей, параметры которых юзер забивает вручную с клавиатуры. Основной метод
 * класса readUserData() возвращает ArrayList с автомобилями в нем.
 */
public class InitialDataScannerSystemIn {

// переменные:
    private int errorCount = 0; // счетчик неправильных попыток юзера, чтобы он не пробовал вводить неправильные
    // данные бесконечно
    private String message = null; // сообщение об ошибке при вводе данных

    private boolean runCycle = true; // переменная для продолжения внешнего цикла в методе; когда false, происходит
    // выброс из цикла. Должна быть прописана в классе, поскольку иначе при обращении к ней из вложенных методов
    // она не будет меняться в аргументе внешнего цикла.
//    public boolean getRunCycle() {return runCycle;} // чтобы можно было при тестировании вызвать runCycle.

    private boolean continueCycle = false; // индикатор ошибки, при true цикл начинается сначала без занесения
    // автомобиля в список

    // методы:

    // parser анализирует строчку, забитую юзером в консоли, разбивает на отдельные стринги и заносит в лист для
    // последующего создания модели автомобиля, из которой затем будет создан автомобиль
    private ArrayList<String> parser(String arg) {
        Pattern userGroupsPattern = Pattern.compile("(-)?\\w+(.\\w+)?"); // regex для очистки аргументов, заносимых
        // в конструктор new Vehicle, от пробелов, запятых и возможной иной шелухи.
        Matcher userGroupsMatcher = userGroupsPattern.matcher(arg);
        ArrayList<String> oneLineArgs = new ArrayList<>(5); // сюда заносятся очищенные аргументы на 1 автомобиль.
        // Это не массив, так что исключений при превышении емкости не будет, так что программа не будет выдавать ошибки.
        while (userGroupsMatcher.find()) {
            oneLineArgs.add(userGroupsMatcher.group());
        }
        return oneLineArgs;
    }
        // метод для создания модели автомобиля на основе аргументов, очищенных парсером и забитых в лист oneLineArgs
        private CarModel carModelCreator(List<String> oneLineArgs){
            // TODO: сюда забить несколько методов, которые примут в аргументы лист oneLineArgs и либо выдадут message,
            // либо, если message == null, позволят создать модель
            CarModel carModel = new CarModel();
        try {
            carModel.name = oneLineArgs.get(0);
            carModel.marker = oneLineArgs.get(1);
            carModel.acceleration = Double.valueOf(oneLineArgs.get(2));
            carModel.fullSpeed = Double.valueOf(oneLineArgs.get(3));
            carModel.mobility = Double.valueOf(oneLineArgs.get(4));
        } catch (NumberFormatException e) {
            message = "Параметры автомобиля " + carModel.name + " объявлены в неверном формате " +
                    "или какие-то параметры отсутствуют. Автомобиль снят с гонки.";
            printAppendix();
            errorCount++;
        }
        return carModel;
    }

    // rawLineValidator позволяет выйти из цикла чтения забитых юзером в консоль данных readUserData() в случае, если
    // забита пустая строка или зарезервированная команда esc
    private boolean rawLineValidator(String toValidate) {
        return !(toValidate == null || toValidate.equals("esc"));
    }

    // carValidator проверяет созданную модель автомобиля на предмет соответствия конструктору автомобиля и ограничениям
    // по данным для конструктора
    private String carValidator(CarModel carModel) {

        return null;
    }

    // метод для игнорирования ошибки ввода нулевых строчек в цикле while (userGroupsMatcher.find()){}:
    private void nullLineIgnore() {
        if (errorCount == 0) {
            System.out.println("Введите параметры автомобиля либо напечатайте esc и нажмите Enter " +
                    "для перехода на следующий этап.");
            if (errorCount == 3) {
                System.out.println("Ввод данных прерван. Пожалуйста, запустите программу снова.");
                runCycle = false;
            } else if (errorCount == 2) {
                System.out.println("После следующей неправильной попытки ввода программа будет завершена.");
                errorCount++;
                continueCycle = true;
            } else {
                errorCount++;
                continueCycle = true;
            }
        }
    }

    // метод для отслеживания количества неправильных попыток юзера и предупреждения об окончании ввода данных:
    private String printAppendix() {
        if (errorCount == 3) {
            message = "Ввод данных прерван. Пожалуйста, запустите программу снова.";
            runCycle = false; //TODO: прописать, что если rawLineValidator = false, то запускаем printAppendix(); а если
            // errMessage равно этому сообщению или содержит слова "Ввод данных прерван", то выйти из цикла readUserData()
        } else if (errorCount == 2) {
            message = "После следующей неправильной попытки ввода программа будет завершена.";
        } else {
            message = "Введите следующий автомобиль, либо напечатайте esc и нажмите Enter " +
                    "для перехода на следующий этап.";
        }
        return message;
    }

    // метод для распечатки сообщения, если оно ненулевое
    private void printMessage(String message) {
        if (message != null)
            System.out.println(message);
    }

    // метод для создания объекта Vehicle нужного типа (Mashka, Bmw или Ferrari):
    private Vehicle createCar(CarModel carModel) {
        Vehicle car = new Vehicle();
        switch (carModel.marker) {
            case "Mashka":
                car = new MashkaCar(carModel);
                break;
            case "Bmw":
                car = new BmwCar(carModel);
                break;
            case "Ferrari":
                car = new FerrariCar(carModel);
                break;
                    /* В дефолте сообщение об ошибке маркера класса автомобиля **/
            default:
                message = "Класс автомобиля " + carModel.name + " объявлен неверно. Автомобиль" +
                        " снят с гонки.";
                printAppendix();
                continueCycle = true;
                errorCount++;
                break;
        }
        return car;
    }

    // метод для того, чтобы юзер мог закончить ввод данных и выйти из метода readUserData():
    public void breakDataInput() {
        if ((errorCount > 0 || !userCarsToRace.isEmpty() || continueCycle) && oneLineArgs.get(0).equals("esc")) {
            runCycle = false;
        } else if ((errorCount == 0 || userCarsToRace.isEmpty()) && oneLineArgs.get(0).equals("esc")){ // смысл в том, что
            // с самого начала юзер не знает команды esc для выхода; если в самом начале он наберет автомобиль с
            // именем esc, то система его предупредит (с самого начала k == 0 || userCarsToRace.isEmpty()). Потом,
            // когда он забил в список первый автомобиль или сделал первую ошибку, у него появляется сообщение
            // об использовании esc, и он уже может пользоваться этой командой.
            System.out.println("Имя esc зарезервировано как команда для перехода на следующий этап.");
            continueCycle = true;
        }
    }

    // Основной метод для считывания данных с консоли и их переработки, возвращает готовый список автомобилей:
    public ArrayList<Vehicle> readUserData() {
        ArrayList<Vehicle> userCarsToRace = new ArrayList<>();
        // TODO: Matcher не группирует адекватно, если: пишу без пробелов, хотя и через запятую (khg  Mashka,45,213,0.5);
        // пишу с одним пробелом без запятой (нужно хотя бы два)
        // TODO: здесь нужно еще какую-нибудь RunTime Error, чтобы ожидание ввода не затянулось навечно. .
        while (runCycle) {
            continueCycle = false; // чтобы не выпрыгнуть сразу из следующего цикла
            message = null; // чтобы не прочесть чего, оставшегося от прежнего
            // TODO: можно было бы сканер также вынести отдельным методом
            Scanner scanUserCar = new Scanner(System.in);
            String userCarFromScanner = scanUserCar.nextLine(); // отсканированная (еще не очищенная) строчка
            if (rawLineValidator(userCarFromScanner)) {
                ArrayList<String> oneLineArgs = parser(userCarFromScanner);
                // TODO: перед тем, как добавить строчку в лист userCarsToRace, нужно ее всячески проверить
            CarModel carModel = carModelCreator(oneLineArgs);
                userCarsToRace.add(createCar(carModel));
//            } else breakDataInput();
            } else breakDataInput();


            if (continueCycle)
                continue;

            // метод для того, чтобы юзер мог закончить ввод данных и выйти из метода readUserData():
            if ((errorCount > 0 || !userCarsToRace.isEmpty() || continueCycle) && oneLineArgs.get(0).equals("esc")) {
                runCycle = false;
                break;
            } else if ((errorCount == 0 || userCarsToRace.isEmpty()) && oneLineArgs.get(0).equals("esc")){ // смысл в том, что
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
                errorCount++;
                continue;
            }
            if (oneLineArgs.size() < 5) {
                System.out.printf("Параметры автомобиля %s объявлены неверно: не все параметры " +
                        "введены. Автомобиль снят с гонки.\n", oneLineArgs.get(0));
                printAppendix();
                errorCount++;
                continue;
            }

             /* теперь прогоняем созданный масив через switch, чтобы выяснить, к какому классу относится данная
                 * модель и забить аргументы в соответствующий объект-автомобиль, а затем заносим объект-автомобиль
                  *  в список carsToRace. При этом отлавливаем NumberFormatException на случай, если в последних
                  *  трех аргументах записаны не цифры (а также здесь отлавливается какое-то исключение насчет
                  *  неверной инициализации массива - не хватает элемента): **/
            // Вначале обнуляем аргументы, которые хранятся в классе Vehicle с прошлого автомобиля:
//            setNullConstructorArguments();
            //TODO: не забыть сделать метод для отлавливания автомобилей с одинаковыми именами


                /* не забивать аргументы автомобиля, если какой-либо из double параметров равен 0 либо какой-либо из
                параметров отсутствует: **/
            if (carModel.acceleration <= 0 || carModel.fullSpeed <= 0 || carModel.mobility <= 0) {
                System.out.printf("Неправильная инициализация числовых параметров автомобиля. Автомобиль снят с гонки.\n", oneLineArgs.get(0));
                printAppendix();
                errorCount++;
                continue;
            }
            if (carModel.mobility > 1) {
                System.out.printf("Недопустимое значение параметра mobility. Автомобиль %s снят с гонки.\n",
                        oneLineArgs.get(0));
                printAppendix();
                errorCount++;
                continue;
            }
            // TODO: выкидывать автомобиль, если такое имя уже есть.



            if (!continueCycle) {
                System.out.printf("Автомобиль %s принят в гонку. \nВведите следующий автомобиль, " +
                        "либо напечатайте esc и нажмите Enter для перехода на следующий этап.\n", carModel.name);
//                i++;
                errorCount = 0;
            }
        }
        return userCarsToRace;
    }

    public static void main(String[] args) {
        InitialDataScannerSystemIn ini = new InitialDataScannerSystemIn();
        ini.readUserData();
//        System.out.println(ini.userCarsToRace.get(0).getName());
//        System.out.println(ini.userCarsToRace.get(0).getMobility());
//        System.out.println(ini.userCarsToRace.get(1).getAcceleration());
    }

}
