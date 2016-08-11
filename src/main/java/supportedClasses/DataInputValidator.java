package supportedClasses;

import dataStorageAndProcessing.MessageStore;

/**
 * Created by Ежище on 10.08.2016.
 */
public final class DataInputValidator {
    public static int errorCount = 0;
    public static boolean CycleIsRunning = true;


    public static void printMessage(String message) {
        if (message != null)
            System.out.println(message);
    }

    public static void breakWithAppendixPrinting(String message) {
        printMessage(message); // это конкретное описание ошибки - message из метода, который вызвал этот метод для
        // завершения цикла
        if (errorCount <= 1) {
            message = MessageStore.ERR_COUNT_FIRST_LEVEL_MSG; // выкидывает сообщение "Введите параметры автомобиля
            // либо напечатайте esc и нажмите Enter для перехода на следующий этап."
            errorCount++;
        } else if (errorCount == 3) {
            message = "Ввод данных прерван. Пожалуйста, запустите программу снова.";
            CycleIsRunning = false;
        } else  {
            message = "После следующей неправильной попытки ввода программа будет завершена.";
            errorCount++;
        }
        printMessage(message);
    }
}
