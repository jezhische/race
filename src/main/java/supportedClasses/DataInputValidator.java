package supportedClasses;

import cars.*;
import entities.CarModel;

import java.util.*;
import java.util.regex.*;

import static dataStorageAndProcessing.MessageStore.*;


/**
 * Created by Ежище on 10.08.2016.
 */
public final class DataInputValidator {
    private int errorCount = 0;
    private boolean cycleIsRunning = true;

    public int getErrorCount() {
        return errorCount;
    }
    public boolean getCycleIsRunning() {
        return cycleIsRunning;
    }

    public boolean getFalse(boolean cycleIsRunning) {
        if (cycleIsRunning = false)
        return false;
    return true;
    }

    // метод для распечатки сообщения, если оно ненулевое:
    public void printMessage(String message) {
        if (message != null)
            System.out.println(message);
    }

    // метод для отслеживания количества неправильных попыток юзера и предупреждения об окончании ввода
    // данных - вспомогательный метод для isNullLine и для всех последующих методов проверки на ошибки,
    // но не для isDataInputBreakWithEsc:
    public void breakWithAppendixPrinting(String message) {
        printMessage(message); // вначале конкретное описание ошибки - message из метода, который вызвал этот метод для
        // завершения цикла
        if (errorCount <= 1) {
            message = ERR_COUNT_FIRST_LEVEL_MSG.getMessage(); // выкидывает сообщение "Введите параметры автомобиля
            // либо напечатайте esc и нажмите Enter для перехода на следующий этап."
            errorCount++;
        } else if (errorCount == 3) {
                message = ERR_COUNT_LAST_LEVEL_MSG.getMessage(); // выкидывает сообщение "Ввод данных прерван. Пожалуйста,
                // запустите программу снова."
            cycleIsRunning = false;
            }
        else  {
            message = ERR_COUNT_PENULT_LEVEL_MSG.getMessage(); // выкидывает сообщение "После следующей неправильной
            // попытки ввода программа будет завершена."
            errorCount++;
        }
        printMessage(message);
    }

    //метод выдает сообщения о нулевой строчке и увеличивает счетчик ошибочных вводов, после определенного количества
    // нулевых вводов выкидывает из цикла через cycleIsRunning = false; значение по дефолту false:
    public boolean isNullLine(String userInputFromScanner) {
        if (userInputFromScanner.equals("")) {
            String message = null;
            breakWithAppendixPrinting(message);
            return true;
        }
        return false;
    }

    // parser анализирует строчку, забитую юзером в консоли, разбивает на отдельные стринги и заносит в лист для
    // последующего создания модели автомобиля, из которой затем будет создан автомобиль
    public ArrayList<String> parser(String arg) {
        Pattern userGroupsPattern = Pattern.compile("(-)?\\w+(.\\w+)?"); // regex для очистки аргументов, заносимых
        // в конструктор new Vehicle, от пробелов, запятых и возможной иной шелухи.
        Matcher userGroupsMatcher = userGroupsPattern.matcher(arg);
        ArrayList<String> oneLineArgs = new ArrayList<>(); // сюда заносятся очищенные аргументы на 1 автомобиль.
        // Это не массив, так что исключений при превышении емкости не будет, так что программа не будет выдавать ошибки.
        while (userGroupsMatcher.find()) {
            oneLineArgs.add(userGroupsMatcher.group());
        }
        return oneLineArgs;
    }

    // метод для создания модели автомобиля на основе аргументов, очищенных парсером и забитых в лист oneLineArgs
    public CarModel carModelCreator(List<String> oneLineArgs) {
        CarModel carModel = new CarModel();
        carModel.name = oneLineArgs.get(0);
        carModel.marker = oneLineArgs.get(1);
        carModel.acceleration = Double.valueOf(oneLineArgs.get(2));
        carModel.fullSpeed = Double.valueOf(oneLineArgs.get(3));
        carModel.mobility = Double.valueOf(oneLineArgs.get(4));
        return carModel;
    }

    // метод для того, чтобы юзер мог закончить ввод данных и выйти из метода readUserData(); значение по дефолту false:
    public boolean isDataInputBreakWithEsc(ArrayList<Vehicle> userCarsToRace, ArrayList<String> oneLineArgs) {
        if ((errorCount > 0 || !userCarsToRace.isEmpty()) && oneLineArgs.get(0).equals("esc")) {
            cycleIsRunning = false;
            printMessage("Вы закончили ввод данных. Если вы хотите начать гонку, введите RUN и нажмите Enter." +
                    "\nЕсли вы хотите также добавить готовый список автомобилей из файла, введите 2 и нажмите Enter.");
            return true;
        } else if (errorCount == 0 && userCarsToRace.isEmpty() && oneLineArgs.get(0).equals("esc")) { // смысл в том, что
            // с самого начала юзер не знает команды esc для выхода; если в самом начале он наберет автомобиль с
            // именем esc, то система его предупредит (с самого начала k == 0 && readUserData().isEmpty()). Потом,
            // когда он забил в список первый автомобиль (!readUserData().isEmpty()) или сделал первую ошибку (k > 0),
            // у него появляется сообщение об использовании esc для выхода, и он уже может пользоваться этой командой.
            errorCount++;
            printMessage("Имя esc зарезервировано как команда для перехода на следующий этап.");
            return true;
        }
        return false;
    }

    // метод на случай, если введено более 5 необходимых параметров:
    public boolean inputDataQuantityIsMoreThanNecessary(ArrayList<String> oneLineArgs) {
        String  message = null;
        if (oneLineArgs.size() > 5) {
            message = "Параметры автомобиля " + oneLineArgs.get(0) + " объявлены неверно: найден " +
                    "избыточный параметр. Автомобиль снят с гонки.";
            breakWithAppendixPrinting(message);
            return true;
        }
        return false;
    }

    // метод на случай, если введено менее 5 необходимых параметров:
    public boolean inputDataQuantityIsLessThanNecessary(ArrayList<String> oneLineArgs) {
        String  message = null;
        if (oneLineArgs.size() < 5) {
            message = "Параметры автомобиля " + oneLineArgs.get(0) + " объявлены неверно: не все параметры " +
                    "введены. Автомобиль снят с гонки.";
            breakWithAppendixPrinting(message);
            return true;
        }
        return false;
    }

    // метод для проверки, являются ли три последних параметра числовыми:
    public boolean tryToDoubleValidator(List<String> oneLineArgs) {
        String  message = null;
        try {
            double a = Double.valueOf(oneLineArgs.get(2));
            double b = Double.valueOf(oneLineArgs.get(3));
            double c = Double.valueOf(oneLineArgs.get(4));
        }
        catch (NumberFormatException e) {
            message = "Числовые параметры автомобиля " + oneLineArgs.get(2) + " объявлены в неверном формате. " +
                    "Автомобиль снят с гонки.";
            breakWithAppendixPrinting(message);
            return true;
        }
        return false;
    }

    // метод для проверки, совпадает ли параемтр oneLineArgs.get(1) с одним из классов автомобилей:
    public boolean isNoSuchClass(List<String> oneLineArgs) {
        String message = null;
        switch (oneLineArgs.get(1)) {
            case "Mashka":
            case "BMW":
            case "Ferrari":
                return false;
            default:
                message = "Неправильно указан класс автомобиля " + oneLineArgs.get(0) + ".";
                breakWithAppendixPrinting(message);
                return true;
        }
    }

    // метод для проверки числовых параметров созданной модели автомобиля на предмет соответствия
    // условиям конструктора автомобиля:
    public boolean carModelValidator(CarModel carModel) {
        String  message = null;
        if (carModel.acceleration <= 0 || carModel.fullSpeed <= 0) {
            message = "Неправильное значение параметров ускорения или максимальной скорости автомобиля "
                    + carModel.name + ": \nпараметры должны быть больше нуля. Автомобиль снят с гонки.";
            breakWithAppendixPrinting(message);
            return true;
        }
        else if (carModel.mobility > 1 || carModel.mobility <= 0) {
            message = "Недопустимое значение параметра мобильность автомобиля " + carModel.name + ": \nмобильность " +
                    "указывается в пределах больше нуля до 1 включительно. Автомобиль снят с гонки.";
            breakWithAppendixPrinting(message);
            return true;
        }
        return false;
    }

    // метод для создания объекта Vehicle нужного типа (Mashka, BMW или Ferrari). Поскольку к сеттерам обращается CarModel,
    // то все возможные ошибки должны были быть проброшены раньше, еще на этапе создания модели:
    public Vehicle createCar(CarModel carModel) {
        Vehicle car = new Vehicle();
        String  message = null;
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
                breakWithAppendixPrinting(message);
                break;
        }
        return car;
    }

    // метод, чтобы проверить, не совпадает ли имя нового автомобиля с именем уже занесенного в список:
    public boolean CarsHaveEqualNames(Vehicle car, ArrayList<Vehicle> userCarsToRace) {
        String  message = null;
        for (Vehicle listCar: userCarsToRace) {
            if (listCar.getName().equals(car.getName())) {
                message = "Автомобиль с именем " + listCar.getName() + " уже участвует в гонке. Введите другое имя.";
                breakWithAppendixPrinting(message);
                return true;
            }
        }
        return false;
    }

    public void addCarAndContinueCarCreating (Vehicle car) {
        String message;
        message = "Автомобиль " + car.getName() + " принят в гонку. \nВведите следующий автомобиль, " +
                "либо напечатайте esc и нажмите Enter для перехода на следующий этап.";
        printMessage(message);
        errorCount = 0;
    }
}
