package dataStorageAndProcessing;

import cars.*;
import entities.CarModel;
import supportedClasses.DataInputValidator;
import supportedClasses.ErrCountCauseException;

import java.util.*;


/**
 * Created by Ежище on 15.07.2016.
 * * Класс для составления списка автомобилей, параметры которых юзер забивает вручную с клавиатуры. Основной метод
 * класса readUserData() возвращает ArrayList с автомобилями в нем.
 */
public class InitialDataScannerSystemIn {


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
                ArrayList<String> oneLineArgs = validator.parser(userInputFromScanner);

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
                CarModel carModel = validator.carModelCreator(oneLineArgs);

                // проверяем числовые параметры автомобиля:
                if (validator.carModelValidator(carModel)) {
                    continue;
                }
                // создаем экземпляр автомобиля, сравниваем имя автомобиля с именами уже созданных ранее
                // и загоняем авто в список:
                Vehicle car = validator.createCar(carModel);
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
