package JUnitTests;

import org.junit.*;
import supportedClasses.*;
import testSupport.*;

import java.io.*;
import java.util.ArrayList;

import static dataStorageAndProcessing.MessageStore.*;
import static org.junit.Assert.*;
import static testSupport.UserInputEnam.*;

/**
 * Created by Ежище on 14.08.2016.
 */
public class TestDataInputValidator {

    private DataInputValidator validator;
    private OutToFileRedirect sysOut;
    private FileToString outputMsg;
    private String msg;
    private ArrayList<String> oneLineArgs;
//    private String userInputFromScanner;


    @Before
    public void setupBefore() {
        validator = new DataInputValidator();
        sysOut = new OutToFileRedirect();
        outputMsg = new FileToString();
        msg = "some message";
        oneLineArgs = new ArrayList<>(0);
    }

    @After
    public void tearDown() {
        validator = null;
        sysOut.redirectOut().close(); // я не могу поместить его в AfterClass, поскольку там принимаются только
        // статические объекты
        outputMsg = null;
        msg = null;
        oneLineArgs = null;
    }

    @AfterClass
    public static void logout() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    public void testBreakWithAppendixPrintingFirstErr() throws ErrCountCauseException, IOException {
        sysOut.redirectOut();
        validator.breakWithAppendixPrinting(msg);
        //TODO: полное равенство не срабатывает, потому что в файл в конце печатается еще куча пустых символов:
//        assertTrue(outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"))
//                .equals(msg + "\r\n" + ERR_COUNT_FIRST_LEVEL_MSG.getMessage() + "\r\n"));
        String message = outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"));
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
    @Test(expected = ErrCountCauseException.class)
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
    }
    @Test
    public void testIsEmptyLine() throws ErrCountCauseException {
        sysOut.redirectOut();
        assertFalse(validator.isEmptyLine(MASHKA_RIGHT_CAR.getUserInputSample()));
        assertTrue(outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt")).equals(""));
        sysOut.redirectOut();
        assertTrue(validator.isEmptyLine(EMPTY_LINE.getUserInputSample()));
        assertTrue(outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt")).
                contains(ERR_COUNT_FIRST_LEVEL_MSG.getMessage()));
    }
    @Test
    public void testIsNullLine() throws ErrCountCauseException {
        sysOut.redirectOut();
        assertTrue(validator.isNullLine(oneLineArgs));
        String message = outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"));
        assertTrue(message.contains(INCORRECT_DATA_INPUT_FORMAT.getMessage()));
        assertTrue(message.contains(ERR_COUNT_FIRST_LEVEL_MSG.getMessage()));
//        assertTrue(outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt")).equals(""));
//        sysOut.redirectOut();
//        assertTrue(validator.isEmptyLine(EMPTY_LINE.getUserInputSample()));
//        assertTrue(outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt")).
//                contains(ERR_COUNT_FIRST_LEVEL_MSG.getMessage()));
    }


}
