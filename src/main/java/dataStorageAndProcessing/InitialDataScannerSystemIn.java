package dataStorageAndProcessing;

import cars.*;
import entities.CarModel;

import java.util.*;
import java.util.regex.*;

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

    private boolean CycleIsRunning = true; // переменная для продолжения основного цикла в методе; когда false,
    // происходит выброс из цикла.

    // методы:

    //метод выдает сообщения о нулевой строчке и увеличивает счетчик ошибочных вводов, после определенного количества
    // нулевых вводов выкидывает из цикла через CycleIsRunning = false; значение по дефолту false:
    private boolean isNullLine(String userInputFromScanner) {
        if (userInputFromScanner.equals("")) {
            return isBreakWithAppendixPrinting();
        }
        return false;
    }

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

    // с этого места парсером возвращен список аргуметов, пошли проверки, они д.быть атомарными и boolean, и
    // принимать в аргументы список oneLineArgs, и менять message, и после каждой message нужно печатать

    // TODO: что смущает в методах валидации введенных данных: они возвращают boolean, но при этом еще и
    // меняют три переменные: errorCount, CycleIsRunning и message, и печатают message.
    // Кроме того, получается типично процедурное программирование: "если - goto".
    // Ну... возможно, нужно boolean проверочные методы сделать, чтобы они только возвращали true или false,
    // и если возвращают true, то включаются другие методы, которые создают сообщение и меняют errorCount и CycleIsRunning

    // метод для распечатки сообщения, если оно ненулевое - в принципе, избыточный:
    private void printMessage() {
        if (message != null)
            System.out.println(message);
    }

    // метод для отслеживания количества неправильных попыток юзера и предупреждения об окончании ввода
    // данных - вспомогательный метод для isNullLine и для всех последующих методов проверки на ошибки,
    // но не для isDataInputBreakWithEsc:
    private boolean isBreakWithAppendixPrinting() {
        if (errorCount <= 1) {
            printMessage(); // это конкретное описание ошибки - message из метода, который вызвал этот метод для
            // завершения цикла
            message = "Введите параметры автомобиля либо напечатайте esc и нажмите Enter " +
                    "для перехода на следующий этап.";
            errorCount++;
            printMessage();// а вот это уже message из этого метода.
            return true;
        } else if (errorCount == 3) {
            printMessage();
            message = "Ввод данных прерван. Пожалуйста, запустите программу снова.";
            CycleIsRunning = false;
            printMessage();
            return true;
        } else if (errorCount == 2) {
            printMessage();
            message = "После следующей неправильной попытки ввода программа будет завершена.";
            errorCount++;
            printMessage();
            return true;
        }
        return false;
    }

//    private boolean isNoValue(ArrayList<String> oneLineArgs) {
//        if (oneLineArgs.size() == 0) {
//            return isBreakWithAppendixPrinting();
//        }
//        return false;
//    }

    // метод для того, чтобы юзер мог закончить ввод данных и выйти из метода readUserData(); значение по дефолту false:
    private boolean isDataInputBreakWithEsc(ArrayList<Vehicle> userCarsToRace, ArrayList<String> oneLineArgs) {
        if ((errorCount > 0 || !userCarsToRace.isEmpty()) && oneLineArgs.get(0).equals("esc")) {
            CycleIsRunning = false;
            message = "Вы закончили ввод данных. Если вы хотите начать гонку, введите RUN и нажмите Enter." +
                    "\nЕсли вы хотите также добавить готовый список автомобилей из файла, введите 2 и нажмите Enter.";
            printMessage();
            return true;
        } else if (errorCount == 0 && userCarsToRace.isEmpty() && oneLineArgs.get(0).equals("esc")) { // смысл в том, что
            // с самого начала юзер не знает команды esc для выхода; если в самом начале он наберет автомобиль с
            // именем esc, то система его предупредит (с самого начала k == 0 && readUserData().isEmpty()). Потом,
            // когда он забил в список первый автомобиль (!readUserData().isEmpty()) или сделал первую ошибку (k > 0),
            // у него появляется сообщение об использовании esc для выхода, и он уже может пользоваться этой командой.
            message = "Имя esc зарезервировано как команда для перехода на следующий этап.";
            errorCount++;
            printMessage();
            return true;
        }
        return false;
    }

    // метод на случай, если введено более 5 необходимых параметров:
    private boolean inputDataQuantityIsMoreThanNecessary(ArrayList<String> oneLineArgs) {
        if (oneLineArgs.size() > 5) {
            message = "Параметры автомобиля " + oneLineArgs.get(0) + " объявлены неверно: найден " +
                    "избыточный параметр. Автомобиль снят с гонки.";
            return isBreakWithAppendixPrinting();
        }
        return false;
    }

    // метод на случай, если введено менее 5 необходимых параметров:
    private boolean inputDataQuantityIsLessThanNecessary(ArrayList<String> oneLineArgs) {
        if (oneLineArgs.size() < 5) {
            message = "Параметры автомобиля " + oneLineArgs.get(0) + " объявлены неверно: не все параметры " +
                    "введены. Автомобиль снят с гонки.";
            return isBreakWithAppendixPrinting();
        }
        return false;
    }

    // метод для проверки, являются ли три последних параметра числовыми:
    private boolean tryToDoubleValidator(List<String> oneLineArgs) {
        try {
            double a = Double.valueOf(oneLineArgs.get(2));
            double b = Double.valueOf(oneLineArgs.get(3));
            double c = Double.valueOf(oneLineArgs.get(4));
        }
        catch (NumberFormatException e) {
            message = "Числовые параметры автомобиля " + oneLineArgs.get(2) + " объявлены в неверном формате. " +
                    "Автомобиль снят с гонки.";
            printMessage();
            return isBreakWithAppendixPrinting();
        }
        return false;
    }

    // метод для проверки, совпадает ли параемтр oneLineArgs.get(1) с одним из классов автомобилей:
    private boolean isNoSuchClass(List<String> oneLineArgs) {
        switch(oneLineArgs.get(1)) {
            case "Mashka":
            case "BMW":
            case "Ferrari":
                return false;
            default:
                message = "Неправильно указан класс автомобиля " + oneLineArgs.get(0) + ".";
                return isBreakWithAppendixPrinting();
        }
//        if (!oneLineArgs.get(1).equals("Mashka") || !oneLineArgs.get(1).equals("BMW") ||
//                !oneLineArgs.get(1).equals("Ferrari")) {
//        message = "Класс автомобиля " + oneLineArgs.get(0) + " объявлен неверно. Автомобиль" +
//                " снят с гонки.";
//        return isBreakWithAppendixPrinting();
//    }
//        return false;
    }

    // метод для создания модели автомобиля на основе аргументов, очищенных парсером и забитых в лист oneLineArgs
    private CarModel carModelCreator(List<String> oneLineArgs) {
        CarModel carModel = new CarModel();
            carModel.name = oneLineArgs.get(0);
            carModel.marker = oneLineArgs.get(1);
            carModel.acceleration = Double.valueOf(oneLineArgs.get(2));
            carModel.fullSpeed = Double.valueOf(oneLineArgs.get(3));
            carModel.mobility = Double.valueOf(oneLineArgs.get(4));
        return carModel;
    }

    // метод для проверки числовых параметров созданной модели автомобиля на предмет соответствия
    // условиям конструктора автомобиля:
    private boolean carModelValidator(CarModel carModel) {
        if (carModel.acceleration <= 0 || carModel.fullSpeed <= 0) {
            message = "Неправильное значение параметров ускорения или максимальной скорости автомобиля"
                    + carModel.name + ": \nпараметры должны быть больше нуля. Автомобиль снят с гонки.";
            return isBreakWithAppendixPrinting();
        }
        else if (carModel.mobility > 1 || carModel.mobility <= 0) {
            message = "Недопустимое значение параметра мобильность автомобиля " + carModel.name + ": \nмобильность " +
                    "указывается в пределах больше нуля до 1 включительно. Автомобиль снят с гонки.";
            return isBreakWithAppendixPrinting();
        }
        return false;
    }

    // метод для создания объекта Vehicle нужного типа (Mashka, BMW или Ferrari). Поскольку к сеттерам обращается CarModel,
    // то все возможные ошибки должны были быть проброшены раньше, еще на этапе создания модели:
    private Vehicle createCar(CarModel carModel) {
        Vehicle car = new Vehicle();
        switch (carModel.marker) {
            case "Mashka":
                car = new MashkaCar(carModel);
                break;
            case "BMW":
                car = new BmwCar(carModel);
                break;
            case "Ferrari":
                car = new FerrariCar(carModel);
                break;
                    /* В дефолте сообщение о неопознанной ошибке **/
            default:
                message = "Автомобиль не обнаружен. Неопознанная ошибка.";
                printMessage();
                break;
        }
        return car;
    }

    // метод, чтобы проверить, не совпадает ли имя нового автомобиля с именем уже занесенного в список:
    private boolean CarsHaveEqualNames(Vehicle car, ArrayList<Vehicle> userCarsToRace) {
        for (Vehicle listCar: userCarsToRace) {
            if (listCar.getName().equals(car.getName())) {
                message = "Автомобиль с именем " + listCar.getName() + " уже участвует в гонке. Введите другое имя.";
                return isBreakWithAppendixPrinting();
            }
        }
        return false;
    }


    // Основной метод для считывания данных с консоли и их переработки, возвращает готовый список автомобилей:
    public ArrayList<Vehicle> readUserData() {
        ArrayList<Vehicle> userCarsToRace = new ArrayList<>();
        // TODO: Matcher не группирует адекватно, если: пишу без пробелов, хотя и через запятую (khg  Mashka,45,213,0.5);
        // пишу с одним пробелом без запятой (нужно хотя бы два)
        // TODO: здесь нужно еще какую-нибудь RunTime Error, чтобы ожидание ввода не затянулось навечно. .
        while (CycleIsRunning) {
            message = null; // (чтобы не прочесть чего, оставшегося от прежнего)
            Scanner scanUserInput = new Scanner(System.in);
            String userInputFromScanner = scanUserInput.nextLine(); // это отсканированная (еще не очищенная) строчка.
            // ставим условие возвращения цикла в начало или выхода из цикла, если отсканированная строчка нулевая:
            if (isNullLine(userInputFromScanner)) {
                continue;
            }
            // чистим, группируем символы и загоняем их в список аргументов для конструктора CarModel:
            ArrayList<String> oneLineArgs = parser(userInputFromScanner);

//            if (isNoValue(oneLineArgs)) {
//                continue;
//            }
            // проверяем, не ввел ли юзер команду "esc":
            if (isDataInputBreakWithEsc(userCarsToRace, oneLineArgs)) {
                continue;
            }
            // проверяем, нет ли избыточных или недостаточных данных, а также, являются ли три последних параметра
            // числовыми, а также, соответствует ли второй параметр одному из классов автомобилей:
            if (inputDataQuantityIsMoreThanNecessary(oneLineArgs) || inputDataQuantityIsLessThanNecessary(oneLineArgs)
                    || tryToDoubleValidator(oneLineArgs) || isNoSuchClass(oneLineArgs)) {
                continue;
            }
            // создаем экземпляр CarModel:
            CarModel carModel = carModelCreator(oneLineArgs);

            // проверяем числовые параметры автомобиля:
            if (carModelValidator(carModel)) {
                continue;
            }
            // создаем экземпляр автомобиля, сравниваем имя автомобиля с именами уже созданных ранее
            // и загоняем авто в список:
            Vehicle car = createCar(carModel);
            if (CarsHaveEqualNames(car, userCarsToRace)) {
                continue;
            } else {
                userCarsToRace.add(car);
            }
        message = "Автомобиль " + car.getName() + " принят в гонку. \nВведите следующий автомобиль, " +
        "либо напечатайте esc и нажмите Enter для перехода на следующий этап.";
            printMessage();
            errorCount = 0;
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
