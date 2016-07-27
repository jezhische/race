package supportedClasses;

import cars.Vehicle;
import dataStorageAndProcessing.InitialDataFileReader;
import dataStorageAndProcessing.InitialDataScannerSystemIn;

import java.util.Scanner;

/**
 * Created by Ежище on 07.06.2016.
 */
public class Bookmaker extends Vehicle {
    //    Bookmaker (String name, String marker, double acceleration, double fullSpeed, double mobility) {
//        super(name, marker, acceleration, fullSpeed, mobility);
//    }
    InitialDataScannerSystemIn userScannedData = new InitialDataScannerSystemIn();

    InitialDataFileReader dataFromFile = new InitialDataFileReader();

    public Vehicle dataInput() {
        System.out.println("Приветствуем вас на гонках. \nВы можете выбрать автомобили любой из трех марок: " +
                "Mashka, BMW, Ferrari, и настроить их по своему усмотрению. \nЩелкните мышью на этом поле ввода-вывода " +
                "данных и введите одну из следующих цифр: \n1 - создать машины и настроить их вручную; " +
                "\n2 - выбрать предустановленный список машин; \n3 - посмотреть демо-версию гонок. " +
                "\nВы можете также комбинировать 1 и 2 способы ввода данных. " +
                "\nПожалуйста, щелкните мышкой в любом месте этого поля и введите желаемую цифру, затем нажмите Enter");

        int i = 0; // счетчик неправильных попыток юзера, чтобы он не пробовал бесконечно
        boolean exitCycle = true;
        while (exitCycle) {
            Scanner scanDataInputTypeChoice = new Scanner(System.in);
            // TODO: здесь нужно еще какую-нибудь RunTime Error, чтобы ожидание ввода не затянулось навечно.
//            String dataInputTypeChoice = scanDataInputTypeChoice.nextLine();
            switch (scanDataInputTypeChoice.nextLine()) {
                case "1":
                    System.out.println("Введите параметры автомобиля в формате: имя, марка, ускорение, максимальная " +
                            "скорость, мобильность. \nИмя задается латинскими буквами. Марка: Mashka, BMW или Ferrari. " +
                            "Остальные три параметра - число без точки или с плавающей точкой, \nпри этом ускорение и " +
                            "максимальная скорость должны быть больше 0, а мобильность указывается в пределах от 0 до 1. " +
                            "Например: mashka, Mashka, 45.3, 250, 0.88\nПожалуйста, " +
                            "введите параметры автомобиля, затем нажмите Enter. Для перехода на следующий этап " +
                            "напечатайте esc и нажмите Enter.");
                    userScannedData.readUserData();
                    exitCycle = false; //TODO: при каких условиях?
                    break;
                case "2":
                case "3":
                    dataFromFile.readArgsFromFile();
                    exitCycle = false;
                    break;
                case "Y":
                    if (i > 0) {
                        System.out.println("Вы прервали ввод данных. Для участия в новых гонках запустите " +
                                "программу заново.");
                        exitCycle = false;
                        break;
                    }
                default:
                    if (i != 3) {
                        System.out.println("Пожалуйста, введите только цифру 1, 2 или 3. Если вы хотите завершить программу," +
                                " наберите Y и нажмите Enter");
//                    Scanner exit = new Scanner(System.in);
//                    if (exit.nextLine() == "Y") {
//                        exitCycle = false;
//                        break;
//                    }
                        if (i == 2)
                            System.out.println("После следующей неправильной попытки ввода программа будет завершена.");
                    } else {
                        System.out.println("Ввод данных прерван. Пожалуйста, запустите программу снова.");
                        exitCycle = false;
                        break;
                    }
            }
            i++;
        }

        return this;
    }


    public static void main(String[] args) {
        Bookmaker b = new Bookmaker();
        b.dataInput();
        System.out.println("В гонках участвует " + b.dataFromFile.getCarsToRace().size() + " автомобиля из предустановленного списка.");
        int y = 0;
        while (y < b.dataFromFile.getCarsToRace().size()) {
            System.out.println("acceleration of car " + y + " " + b.dataFromFile.getCarsToRace().get(y).getName() + " = " +
                    b.dataFromFile.getCarsToRace().get(y).getAcceleration());
            y++;
        }
    }
}
