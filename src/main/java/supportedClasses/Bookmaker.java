package supportedClasses;

import cars.Vehicle;
import dataStorageAndProcessing.InitialDataFileReader;

import java.util.Scanner;

/**
 * Created by Ежище on 07.06.2016.
 */
public class Bookmaker extends Vehicle {
    //    Bookmaker (String name, String marker, double acceleration, double fullSpeed, double mobility) {
//        super(name, marker, acceleration, fullSpeed, mobility);
//    }
    InitialDataFileReader dataFromFile = new InitialDataFileReader();

    Vehicle dataInput() {
        System.out.println("Приветствуем вас на гонках. \nВы можете выбрать автомобили любой из трех марок: " +
                "Mashka, BMW, Ferrari, и настроить их по своему усмотрению. \nЩелкните мышью на этом поле ввода-вывода " +
                "данных и введите одну из следующих цифр: \n1 - создать машины и настроить их вручную; " +
                "\n2 - выбрать предустановленный список машин; \n3 - посмотреть демо-версию гонок. " +
                "\nВы можете также комбинировать 1 и 2 способы ввода данных. " +
                "\nПожалуйста, щелкните мышкой в любом месте этого поля и введите желаемую цифру, затем нажмите Enter");
        int i = 0;
        boolean exitCycle = true;
        while (exitCycle) {
            i++;
            Scanner scanDataInputTypeChoice = new Scanner(System.in);
            String dataInputTypeChoice = scanDataInputTypeChoice.nextLine();
            switch (dataInputTypeChoice) {
                case "1":
                case "2":
                case "3":
                    dataFromFile.readArgsFromFile();
                    exitCycle = false;
                    break;
                default:
                    System.out.println("Пожалуйста, введите только цифру 1, 2 или 3");
                    if (i == 3) {
                        System.out.println("Ввод данных прерван, пожалуйста, запустите программу снова.");
                        exitCycle = false;
                        break;
                    }
            }
            scanDataInputTypeChoice.close();
        }


        // adding comment for testing branch switching
        // adding new comment for testing branch switching

        while (exitCycle) {
            i++;
            Scanner scanDataInputTypeChoice = new Scanner(System.in);
            String dataInputTypeChoice = scanDataInputTypeChoice.nextLine();
            if (dataInputTypeChoice.equals("1") || dataInputTypeChoice.equals("2") || dataInputTypeChoice.equals("3")) {
                System.out.println(dataInputTypeChoice); // TODO: there must be some logic here
                exitCycle = false;
                break;
            } else {
                System.out.println("Пожалуйста, введите только цифру 1, 2 или 3");
                if (i == 3) {
                    System.out.println("Ввод данных прерван, пожалуйста, запустите программу снова.");
                    exitCycle = false;
                    break;
                }
            }
        }


        return this;
    }

    public static void main(String[] args) {
        Bookmaker b = new Bookmaker();
        b.dataInput();
        int y = 0;
        System.out.println("В гонках участвует " + b.dataFromFile.carsToRace.size() + " автомобиля.");
        while (y < b.dataFromFile.carsToRace.size()) {
            System.out.println("acceleration of car " + y + " " + b.dataFromFile.carsToRace.get(y).getName() + " = " +
                    b.dataFromFile.carsToRace.get(y).getAcceleration());
            y++;
        }
    }
}
