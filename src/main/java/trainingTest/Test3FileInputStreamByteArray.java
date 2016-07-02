package trainingTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Ежище on 22.06.2016.
 */
public class Test3FileInputStreamByteArray {
    FileInputStream fin = null;
    void readByteArray() throws FileNotFoundException {
        try(FileInputStream fin = new FileInputStream("src//main//resources//pilotProbesData//Probe1.txt")) {
            this.fin = fin;
            byte [] buffer = new byte [fin.available()];
            fin.read(buffer, 0, fin.available());
            for (int i = 0; i < buffer.length; i++) {
                System.out.print((char)buffer[i]);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
//        finally {
//            if (fin!=null) {
//                try {
//                    fin.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            } // не нужно, поскольку при AutoCloseable (блок try-with-resources) close вызывается
                // автоматически после завершения работы try.

        }
        public static void main(String[] args) throws FileNotFoundException {
        Test3FileInputStreamByteArray test3 = new Test3FileInputStreamByteArray ();
        test3.readByteArray();
    }
    }
