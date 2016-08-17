package JUnitTests;

import org.junit.*;
import supportedClasses.*;
import testSupport.*;

import java.io.*;

import static dataStorageAndProcessing.MessageStore.*;
import static org.junit.Assert.*;

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
<<<<<<< HEAD
//        sysOut.redirectOut().close();
//        sysOut = null;
        outputMsg = null;
        msg = null;
    }
    @AfterClass
    public static void logout() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }
=======
//        try {
//            sysOut.redirectOut().close();
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
        sysOut = null;
        outputMsg = null;
        msg = null;
    }
//    @AfterClass
//    public static void logout() {
//        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
//    }
>>>>>>> 489574ffcfbe4beb23906cbdb4962785be1a3311

    @Test
    public void testBreakWithAppendixPrintingFirstErr() throws ErrCountCauseException, IOException {
        sysOut.redirectOut();
        validator.breakWithAppendixPrinting(msg);
<<<<<<< HEAD
        //TODO: полное равенство не срабатывает, потому что в файл в конце печатается еще куча пустых символов:
        String message = outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"));
//        assertTrue(outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"))
//                .equals(msg + "\r\n" + ERR_COUNT_FIRST_LEVEL_MSG.getMessage() + "\r\n"));
        assertTrue(message.contains(msg));
        assertTrue(message.contains(ERR_COUNT_FIRST_LEVEL_MSG.getMessage()));
    }
    @Test
    public void testBreakWithAppendixPrintingSecondErr() throws ErrCountCauseException, IOException {
        sysOut.redirectOut();
        validator.breakWithAppendixPrinting(msg);
        sysOut.redirectOut();
        validator.breakWithAppendixPrinting(msg);
        String message = outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"));
        assertTrue(message.contains(msg));
        assertTrue(message.contains(ERR_COUNT_FIRST_LEVEL_MSG.getMessage()));
    }
    @Test
    public void testBreakWithAppendixPrintingPenultErr() throws ErrCountCauseException, IOException {
        sysOut.redirectOut();
        validator.breakWithAppendixPrinting(msg);
        sysOut.redirectOut();
        validator.breakWithAppendixPrinting(msg);
        sysOut.redirectOut();
        validator.breakWithAppendixPrinting(msg);
        String message = outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"));
        assertTrue(message.contains(msg));
        assertTrue(message.contains(ERR_COUNT_PENULT_LEVEL_MSG.getMessage()));
    }
    @Test (expected = ErrCountCauseException.class)
    public void testBreakWithAppendixPrintingLastErr() throws ErrCountCauseException, IOException {
        sysOut.redirectOut();
        validator.breakWithAppendixPrinting(msg);
        sysOut.redirectOut();
        validator.breakWithAppendixPrinting(msg);
        sysOut.redirectOut();
        validator.breakWithAppendixPrinting(msg);
        sysOut.redirectOut();
        validator.breakWithAppendixPrinting(msg);
        String message = outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"));
        // TODO: из-за пробрасывания исключения все вот эти месседжи все равно не учитываются. Как их проверить?
        assertTrue(message.contains(msg));
        assertTrue(message.contains(ERR_COUNT_LAST_LEVEL_MSG.getMessage()));
=======
        validator.breakWithAppendixPrinting(msg);
        validator.breakWithAppendixPrinting(msg);
String message = outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"));
//        assertTrue(outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"))
//                .equals(msg + "\n" + ERR_COUNT_FIRST_LEVEL_MSG.getMessage() + "\n"));
        assertTrue(message.contains(msg));
        assertTrue(message.contains(ERR_COUNT_FIRST_LEVEL_MSG.getMessage()));
>>>>>>> 489574ffcfbe4beb23906cbdb4962785be1a3311
    }

}
