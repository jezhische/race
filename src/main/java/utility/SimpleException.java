package utility;

/**
 * Created by Ежище on 21.06.2016.
 */
public class SimpleException extends Exception {
    // Поле для хранения информации, которая будет использована при возникновении исключения
    private int errorCode;

    // переопределяем конструктор
    public SimpleException(String message)
    {
        this(0, message); //это вызов конструктора, написанного ниже
    }

    // Создаем свой конструктор
    public SimpleException(int errorCode, String message)
    {
        // Вызываем конструктор предка
        super(message);
        // Добавляем инициализацию своего поля
        this.errorCode = errorCode;
    }

    // Метод для получения кода ошибки
    public int getErrorCode()
    {
        return errorCode;
    }
}
