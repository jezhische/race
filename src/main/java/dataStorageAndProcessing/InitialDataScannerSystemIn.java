package dataStorageAndProcessing;

import cars.*;
import entities.CarModel;
import supportedClasses.DataInputValidator;
import supportedClasses.ErrCountCauseException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dataStorageAndProcessing.MessageStore.UNINDENTIFIED_ERR_MSG;


/**
 * Created by Ежище on 15.07.2016.
 * * Класс для составления списка автомобилей, параметры которых юзер забивает вручную с клавиатуры. Основной метод
 * класса readUserData() возвращает ArrayList с автомобилями в нем.
 */
public class InitialDataScannerSystemIn {


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

    // метод для создания объекта Vehicle нужного типа (Mashka, BMW или Ferrari). Поскольку к сеттерам обращается CarModel,
    // то все возможные ошибки должны были быть проброшены раньше, еще на этапе создания модели:
    public Vehicle createCar(CarModel carModel, DataInputValidator validator) throws ErrCountCauseException {
        Vehicle car = new Vehicle();
        String message = null;
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
                message = UNINDENTIFIED_ERR_MSG.getMessage(); // сообщение: "Автомобиль не обнаружен. Неопознанная ошибка."

                validator.breakWithAppendixPrinting(message);
                break;
        }
        return car;
    }


    // Основной метод для считывания данных с консоли и их переработки, возвращает готовый список автомобилей:
    public ArrayList<Vehicle> readUserData() {
        ArrayList<Vehicle> userCarsToRace = new ArrayList<>();
        // TODO: Matcher не группирует адекватно, если: пишу без пробелов, хотя и через запятую (khg  Mashka,45,213,0.5);
        // пишу с одним пробелом без запятой (нужно хотя бы два)
        // TODO: здесь нужно еще какую-нибудь RunTime Error, чтобы ожидание ввода не затянулось навечно.
        DataInputValidator validator = new DataInputValidator();
        boolean cycleIsRunning = true;
        while (cycleIsRunning) {
            try { // см. catch внизу - способ выхода из цикла через пробрасывание исключения
                String message = null;
                Scanner scanUserInput = new Scanner(System.in);
                String userInputFromScanner = scanUserInput.nextLine(); // это отсканированная (еще не очищенная) строчка.

                // ставим условие возвращения цикла в начало или выхода из цикла, если отсканированная строчка нулевая:
                if (validator.isEmptyLine(userInputFromScanner)) {
                    continue;
                }
                // чистим, группируем символы и загоняем их в список аргументов для конструктора CarModel:
                ArrayList<String> oneLineArgs = parser(userInputFromScanner);

                // проверяем, не забиты ли символы вместо букв, если да, то вызываем метод isNullLine(),
                // поскольку в следующем методе идет обращение к oneLineArgs.get(0), и будет IndexOutOfBoundsException:
                if (validator.isNullLine(oneLineArgs)) {
                    continue;
                }
                // проверяем, не ввел ли юзер команду "esc":
                if (validator.isDataInputBreakWithEsc(userCarsToRace, oneLineArgs)) {
                    continue;
                }
                // проверяем, нет ли избыточных или недостаточных данных,
                // а также, являются ли три последних параметра числовыми, а также, соответствует ли второй параметр
                // одному из классов автомобилей:
                if (validator.inputDataQuantityIsRedundant(oneLineArgs)
                        || validator.inputDataQuantityIsInsufficient(oneLineArgs)
                        || validator.tryToDoubleValidator(oneLineArgs) || validator.isNoSuchClass(oneLineArgs)) {
                    continue;
                }
                // создаем экземпляр CarModel:
                CarModel carModel = carModelCreator(oneLineArgs);

                // проверяем числовые параметры автомобиля:
                if (validator.carModelValidator(carModel)) {
                    continue;
                }
                // создаем экземпляр автомобиля, сравниваем имя автомобиля с именами уже созданных ранее
                // и загоняем авто в список:
                Vehicle car = createCar(carModel, validator);
                if (validator.CarsHaveEqualNames(car, userCarsToRace)) {
                    continue;
                } else {
                    userCarsToRace.add(car);
                }

                // сообщаем об успешном добавлении автомобиля и обнуляем счетчик:
                validator.resetErrCountAndContinueCarCreating(car);

            } catch (ErrCountCauseException e) {
                cycleIsRunning = false; // continue здесь не нужно (избыточно), поскольку цикл и так начинается сначала
            }
        }
        return userCarsToRace;
    }

    public static void main(String[] args) {
        InitialDataScannerSystemIn ini = new InitialDataScannerSystemIn();
//        DataInputValidator validator = new DataInputValidator();
//        validator.isEmptyLine("");
//        validator.isEmptyLine("");
//        validator.isEmptyLine("");
//        validator.isEmptyLine("");
        ini.readUserData();
//        System.out.println(ini.userCarsToRace.get(0).getName());
//        System.out.println(ini.userCarsToRace.get(0).getMobility());
//        System.out.println(ini.userCarsToRace.get(1).getAcceleration());
    }

}
