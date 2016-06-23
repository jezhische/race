package trainingTest;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Ежище on 22.06.2016.
 */
public class Test2AutoCloseable {
    public static void main(String[] args) {

        try(FileInputStream fin=new FileInputStream("F://еж//программирование//мои примеры//aaa//race//src//main//" +
                "resources//pilotProbesData//Probe1.txt"))
        // Синтаксис конструкции следующий: try(название_класса имя_переменной = конструктор_класса).
        // Данная конструкция также не исключает использования блоков catch.
        // После окончания работы в блоке try у ресурса (в данном случае у объекта FileInputStream)
        // автоматически вызывается метод close().
        // Если нам надо использовать несколько потоков, которые после выполнения надо закрыть,
        // то мы можем указать объекты потоков через точку с запятой:
        // try(FileInputStream fin=new FileInputStream("C://SomeDir//Hello.txt");
        // FileOutputStream fos = new FileOutputStream("C://SomeDir//Hello2.txt"))
        {
            int i=-1;
            while((i=fin.read())!=-1){

                System.out.print((char)i);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
