package dataStorageAndProcessing;

/**
 * Created by Ежище on 10.08.2016.
 */
public enum  MessageStore {
    ERR_COUNT_FIRST_LEVEL_MSG("Введите параметры автомобиля либо напечатайте esc и нажмите Enter для перехода на следующий этап."),
    ERR_COUNT_PENULT_LEVEL_MSG("После следующей неправильной попытки ввода программа будет завершена."),
    ERR_COUNT_LAST_LEVEL_MSG("Ввод данных прерван. Пожалуйста, запустите программу снова.");

    private String message;
    MessageStore(String message) { this.message = message; }
    public String getMessage() { return message; }



//    public final static String ERR_COUNT_FIRST_LEVEL_MSG = "Введите параметры автомобиля либо напечатайте esc и нажмите Enter " +
//            "для перехода на следующий этап.";
}
