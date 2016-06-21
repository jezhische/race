package trainingTest;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Ежище on 21.06.2016.
 */
public class Test1ReadingFile {
    public static void main(String[] args) {

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
