package JUnitTests;

import cars.Vehicle;
import dataStorageAndProcessing.InitialDataScannerSystemIn;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ежище on 27.07.2016.
 */
public class InitialDataScannerSystemInJUnitTest {
private InitialDataScannerSystemIn scan;
//    private Vehicle() car
    @Before
    private void init() {scan = new InitialDataScannerSystemIn();}
    @After
    private void tearDown() {scan = null;}

    @Test
    public void testPrintAppendix (){
        assert
    }
}
