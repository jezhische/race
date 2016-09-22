package testSupport.forScannerSystemIn;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by Ежище on 14.08.2016.
 */
public class SystemInSubstitution {

    // метод, создающий System.in для сканнера:
    public void createSystemIn(String data) {
        try(ByteArrayInputStream dataIn = new ByteArrayInputStream(data.getBytes())) {
            System.setIn(dataIn);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
