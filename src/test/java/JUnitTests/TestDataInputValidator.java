package JUnitTests;

import org.junit.*;
import supportedClasses.DataInputValidator;
import supportedClasses.ErrCountCauseException;
import testSupport.ConsoleReader;
import testSupport.SystemOutReader;

import static dataStorageAndProcessing.MessageStore.ERR_COUNT_FIRST_LEVEL_MSG;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ежище on 14.08.2016.
 */
public class TestDataInputValidator {
    private DataInputValidator validator;
    private SystemOutReader outData;
    private ConsoleReader console;
    private String msg;


    @Before
    public void setupBefore() {
        validator = new DataInputValidator();
        outData = new SystemOutReader();
        console = new ConsoleReader();
        msg = "some message";
    }

    @After
    public void tearDown() {
        validator = null;
        outData = null;
        console = null;
        msg = null;
    }

    @Test
    public void testBreakWithAppendixPrintingFirstErr() throws ErrCountCauseException {
        validator.breakWithAppendixPrinting(msg);
//        outData.readSystemOut();
        assertTrue(console.readConsole().equals(msg + "\n" + ERR_COUNT_FIRST_LEVEL_MSG.getMessage()));
    }
}
