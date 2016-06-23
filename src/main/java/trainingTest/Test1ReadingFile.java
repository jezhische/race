package trainingTest;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Ежище on 21.06.2016.
 */
public class Test1ReadingFile {
    public static void main(String[] args) {
// https://docs.oracle.com/javase/7/docs/api/
        // http://metanit.com/java/tutorial/6.2.php
        FileInputStream fin=null;
        try
        {
            fin = new FileInputStream("F://еж//программирование//мои примеры//aaa//race//src//main//resources//" +
                    "pilotProbesData//Probe1.txt");

            int i=-1;
            while((i=fin.read())!=-1){

                System.out.print((char)i);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        // Поскольку при открытии или считывании файла может произойти ошибка ввода-вывода, то код считывания помещается
        // в блок try. И чтобы быть уверенным, что поток в любом случае закроется, даже если при работе с ним возникнет
        // ошибка, вызов метода close() помещается в блок finally. И, так как метод close() также в случае ошибки может
        // генерировать исключение IOException, то его вызов также помещается во вложенный блок try..catch
        finally{

            try{

                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }
    }
}
