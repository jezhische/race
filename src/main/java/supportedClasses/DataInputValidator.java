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
//    private boolean cycleIsRunning = true;

    // метод для распечатки сообщения, если оно ненулевое:
    public void printMessage(String message) {
        if (message != null)
            System.out.println(message);
    }

    // метод для отслеживания количества неправильных попыток юзера и окончания ввода данных через
    // throw new ErrCountCauseException(), вспомогательный метод для isEmptyLine и для всех последующих методов
    // проверки на ошибки, но не для isDataInputBreakWithEsc:
    public void breakWithAppendixPrinting(String message) throws ErrCountCauseException {
        printMessage(message); // вначале конкретное описание ошибки - message из метода, который вызвал этот метод для
        // завершения цикла
        if (errorCount <= 1) {
            message = ERR_COUNT_FIRST_LEVEL_MSG.getMessage(); // сообщение: "Введите параметры автомобиля
            // либо напечатайте esc и нажмите Enter для перехода на следующий этап."
            errorCount++;
        } else if (errorCount == 3) {
            message = ERR_COUNT_LAST_LEVEL_MSG.getMessage(); // сообщение: "Ввод данных прерван. Пожалуйста,
            // запустите программу снова."
            printMessage(message);
            throw new ErrCountCauseException();
        } else {
            message = ERR_COUNT_PENULT_LEVEL_MSG.getMessage(); // сообщение: "После следующей неправильной
            // попытки ввода программа будет завершена."
            errorCount++;
        }
        printMessage(message);
    }

    //метод вызывает метод breakWithAppendixPrinting, если в строчке отсутствуют символы, значение по дефолту false:
    public boolean isEmptyLine(String userInputFromScanner) throws ErrCountCauseException {
        if (userInputFromScanner.equals("")) {
            String message = null;
            breakWithAppendixPrinting(message);
            return true;
        }
        return false;
    }

    // метод для проверки, содержит ли созданный список ненулевые элементы (null возможен, если были забиты не-числовые
    // или не-буквенные символы)
    public boolean isNullLine(ArrayList<String> oneLineArgs) throws ErrCountCauseException {
        if (oneLineArgs.size() == 0) {
            String message = INCORRECT_DATA_INPUT_FORMAT.getMessage();
            breakWithAppendixPrinting(message);
            return true;
        }
        return false;
    }

    // метод для того, чтобы юзер мог закончить ввод данных и выйти из метода readUserData(); значение по дефолту false:
    public boolean isDataInputBreakWithEsc(ArrayList<Vehicle> userCarsToRace, ArrayList<String> oneLineArgs)
            throws ErrCountCauseException {
        if ((errorCount > 0 || !userCarsToRace.isEmpty()) && oneLineArgs.get(0).equals("esc")) {
            printMessage(DATA_INPUT_IS_COMPLETED_WITH_ESC.getMessage()); // сообщение: "Вы закончили ввод данных.
            // Если вы хотите начать гонку, введите RUN и нажмите Enter. \nЕсли вы хотите также добавить готовый список
            // автомобилей из файла, введите 2 и нажмите Enter."
            throw new ErrCountCauseException();
        } else if (errorCount == 0 && userCarsToRace.isEmpty() && oneLineArgs.get(0).equals("esc")) { // смысл в том, что
            // с самого начала юзер не знает команды esc для выхода; если в самом начале он наберет автомобиль с
            // именем esc, то система его предупредит (с самого начала k == 0 && readUserData().isEmpty()). Потом,
            // когда он забил в список первый автомобиль (!readUserData().isEmpty()) или сделал первую ошибку (k > 0),
            // у него появляется сообщение об использовании esc для выхода, и он уже может пользоваться этой командой.
            errorCount++;
            printMessage(NAME_IS_RESERVED.getMessage()); // сообщение: "Имя esc зарезервировано как команда
            // для перехода на следующий этап."
            return true;
        }
        return false;
    }

    // метод на случай, если введено более 5 необходимых параметров:
    public boolean inputDataQuantityIsRedundant(ArrayList<String> oneLineArgs) throws ErrCountCauseException {
        String message;
        if (oneLineArgs.size() > 5) {
            message = String.format(REDUNDANT_DATA_MSG.getMessage(), oneLineArgs.get(0)); // сообщение: "Параметры
            // автомобиля %s объявлены неверно: найден избыточный параметр. Автомобиль снят с гонки."
            breakWithAppendixPrinting(message);
            return true;
        }
        return false;
    }

    // метод на случай, если введено менее 5 необходимых параметров:
    public boolean inputDataQuantityIsInsufficient(ArrayList<String> oneLineArgs) throws ErrCountCauseException {
        String message = null;
        if (oneLineArgs.size() < 5) {
            message = String.format(INSUFFICIENT_DATA_MSG.getMessage(), oneLineArgs.get(0)); // сообщение: "Параметры
            // автомобиля %s объявлены неверно: не все параметры введены. Автомобиль снят с гонки."
            breakWithAppendixPrinting(message);
            return true;
        }
        return false;
    }

    // метод для проверки, являются ли три последних параметра числовыми:
    public boolean tryToDoubleValidator(List<String> oneLineArgs) throws ErrCountCauseException {
        String message = null;
        try {
            double a = Double.valueOf(oneLineArgs.get(2));
            double b = Double.valueOf(oneLineArgs.get(3));
            double c = Double.valueOf(oneLineArgs.get(4));
        } catch (NumberFormatException e) {
            message = String.format(INCORRECT_DOUBLE_FORMAT_PARAMETER_MSG.getMessage(), oneLineArgs.get(2)); // сообщение:
            // "Числовые параметры автомобиля %s объявлены в неверном формате. Автомобиль снят с гонки."
            breakWithAppendixPrinting(message);
            return true;
        }
        return false;
    }

    // метод для проверки, совпадает ли параметр oneLineArgs.get(1) с одним из классов автомобилей:
    public boolean isNoSuchClass(List<String> oneLineArgs) throws ErrCountCauseException {
        String message = null;
        switch (oneLineArgs.get(1)) {
            case "Mashka":
            case "BMW":
            case "Ferrari":
                return false;
            default:
                message = String.format(INCORRECT_CLASS_MSG.getMessage(), oneLineArgs.get(0)); // сообщение:
                // "Неправильно указан класс автомобиля %s."
                breakWithAppendixPrinting(message);
                return true;
        }
    }

    // метод для проверки числовых параметров созданной модели автомобиля на предмет соответствия
    // условиям конструктора автомобиля:
    public boolean carModelValidator(CarModel carModel) throws ErrCountCauseException {
        String message = null;
        if (carModel.acceleration <= 0 || carModel.fullSpeed <= 0) {
            message = String.format(INCORRECT_ACCSEL_OR_FULL_SPEED_MSG.getMessage(), carModel.name); // сообщение:
            // "Неправильное значение параметров ускорения или максимальной скорости автомобиля %s: \nпараметры должны
            // быть больше нуля. Автомобиль снят с гонки."
            breakWithAppendixPrinting(message);
            return true;
        } else if (carModel.mobility > 1 || carModel.mobility <= 0) {
            message = String.format(INCORRECT_MOBILITY_MSG.getMessage(), carModel.name); // сообщение: "Недопустимое
            // значение параметра мобильность автомобиля %s: \nмобильность указывается в пределах больше нуля
            // до 1 включительно. Автомобиль снят с гонки."
            breakWithAppendixPrinting(message);
            return true;
        }
        return false;
    }

    // метод, чтобы проверить, не совпадает ли имя нового автомобиля с именем уже занесенного в список:
    public boolean CarsHaveEqualNames(Vehicle car, ArrayList<Vehicle> userCarsToRace) throws ErrCountCauseException {
        String message = null;
        for (Vehicle listCar : userCarsToRace) {
            if (listCar.getName().equals(car.getName())) {
                message = String.format(CAR_ALREADY_EXISTS_MSG.getMessage(), listCar.getName()); // сообщение:
                // "Автомобиль с именем %s уже участвует в гонке. Введите другое имя."
                breakWithAppendixPrinting(message);
                return true;
            }
        }
        return false;
    }

// метод, чтобы сообщить об успешном добавлении автомобиля и обнулить счетчик:
    public void resetErrCountAndContinueCarCreating(Vehicle car) {
        String message = String.format(CAR_ACCEPTED_MSG.getMessage(), car.getName()); // сообщение:
        // "Автомобиль %s принят в гонку. \nВведите следующий автомобиль, либо напечатайте esc и нажмите Enter
        // для перехода на следующий этап."
        printMessage(message);
        errorCount = 0;
    }
}
