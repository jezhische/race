package dataStorageAndProcessing;

/**
 * Created by Ежище on 10.08.2016.
 */
public enum  MessageStore {
    ERR_COUNT_FIRST_LEVEL_MSG("Введите параметры автомобиля либо напечатайте esc и нажмите Enter для перехода на следующий этап.");

    private String message;
    MessageStore(String message) { this.message = message; }
    public String getMessage() { return message; }



//    public final static String ERR_COUNT_FIRST_LEVEL_MSG = "Введите параметры автомобиля либо напечатайте esc и нажмите Enter " +
//            "для перехода на следующий этап.";
}
