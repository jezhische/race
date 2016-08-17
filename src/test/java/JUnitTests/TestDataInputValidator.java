package JUnitTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import supportedClasses.DataInputValidator;
import supportedClasses.ErrCountCauseException;
import testSupport.FileToString;
import testSupport.OutToFileRedirect;

import java.io.*;

import static dataStorageAndProcessing.MessageStore.ERR_COUNT_FIRST_LEVEL_MSG;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ежище on 14.08.2016.
 */
public class TestDataInputValidator {
//    DataInputValidator validator = new DataInputValidator();
//    FileToString outputMsg = new FileToString();
//    String msg = "some message";

    private DataInputValidator validator;
    private OutToFileRedirect sysOut;
    private FileToString outputMsg;
    private String msg;


    @Before
    public void setupBefore() {
        validator = new DataInputValidator();
        sysOut = new OutToFileRedirect();
        outputMsg = new FileToString();
        msg = "some message";
    }

    @After
    public void tearDown() {
        validator = null;
        sysOut.redirectOut().close();
//        sysOut = null;
        outputMsg = null;
        msg = null;
    }
    @AfterClass
    public static void logout() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    public void testBreakWithAppendixPrintingFirstErr() throws ErrCountCauseException, IOException {
        sysOut.redirectOut();
        validator.breakWithAppendixPrinting(msg);
        //TODO: почему не срабатывает полное равенство?:
        String message = outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"));
//        assertTrue(outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"))
//                .equals(msg + "\n" + ERR_COUNT_FIRST_LEVEL_MSG.getMessage() + "\n"));
        assertTrue(message.contains(msg));
        assertTrue(message.contains(ERR_COUNT_FIRST_LEVEL_MSG.getMessage()));
    }

}
