package testSupport;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Ежище on 15.08.2016.
 */
public class FileToString {
    public String readFileToString(File file) {

        try(FileReader getStringFromFile = new FileReader(file)) {
            char[] buffer = new char[(int)file.length()];
            getStringFromFile.read(buffer); // здесь считываем все содержимое файла в виде char[]
            return new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }

    }
//
//    public static void main(String[] args) {
//        FileToString fs = new FileToString();
//        System.out.println(fs.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt")));
//    }
}
