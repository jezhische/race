package JUnitTests;

import cars.MashkaCar;
import cars.Vehicle;
import org.junit.*;
import supportedClasses.*;

import testSupport.forSystemOutRedirect.FileToString;
import testSupport.forSystemOutRedirect.OutToFileRedirect;

import java.io.*;
import java.util.ArrayList;

import static dataStorageAndProcessing.MessageStore.*;
import static org.junit.Assert.*;
import static testSupport.UserInputStore.*;

/**
 * Created by Ежище on 14.08.2016.
 */
public class TestDataInputValidator {

    private DataInputValidator validator;
    private OutToFileRedirect sysOut;
    private FileToString outputMsg;
    private String msg;
    private ArrayList<String> oneLineArgs;
    private ArrayList<Vehicle> userCarsToRace;
    private Vehicle rightCarMashka;
//    private String userInputFromScanner;


    @Before
    public void setupBefore() {
        validator = new DataInputValidator();
        sysOut = new OutToFileRedirect();
        outputMsg = new FileToString();
        msg = "some message";
        oneLineArgs = new ArrayList<>(0);
        userCarsToRace = new ArrayList<>();
        rightCarMashka = new MashkaCar("mashka", "Mashka", 123, 456, 0.98);
    }

    @After
    public void tearDown() {
        validator = null;
//        sysOut.redirectOut().close(); // я не могу поместить его в AfterClass, поскольку там принимаются только
        // статические объекты
        outputMsg = null;
        msg = null;
        oneLineArgs = null;
        userCarsToRace = null;
        rightCarMashka = null;
    }

    @AfterClass
    public static void logout() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    public void testBreakWithAppendixPrinting_FirstErr() throws ErrCountCauseException {
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
    public void testBreakWithAppendixPrinting_SecondErr() throws ErrCountCauseException {
        sysOut.redirectOut();
        validator.breakWithAppendixPrinting(msg);
        sysOut.redirectOut();
        validator.breakWithAppendixPrinting(msg);
        String message = outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"));
        assertTrue(message.contains(msg));
        assertTrue(message.contains(ERR_COUNT_FIRST_LEVEL_MSG.getMessage()));
    }

    @Test
    public void testBreakWithAppendixPrinting_PenultErr() throws ErrCountCauseException {
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
    public void testBreakWithAppendixPrinting_LastErr() throws ErrCountCauseException {
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
        // method condition (if) is fulfilled:
        sysOut.redirectOut();
        assertTrue(validator.isEmptyLine(EMPTY_LINE.getUserInputSample()));
        assertTrue(outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt")).
                contains(ERR_COUNT_FIRST_LEVEL_MSG.getMessage()));
        // else is fulfilled:
        sysOut.redirectOut();
        assertFalse(validator.isEmptyLine(MASHKA_RIGHT_CAR.getUserInputSample()));
        assertTrue(outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt")).equals(""));
    }

    @Test
    public void testIsNullLine() throws ErrCountCauseException {
        // method condition (if) is fulfilled:
        sysOut.redirectOut();
        assertTrue(validator.isNullLine(oneLineArgs));
        String message = outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"));
        assertTrue(message.contains(INCORRECT_DATA_INPUT_FORMAT.getMessage()));
        assertTrue(message.contains(ERR_COUNT_FIRST_LEVEL_MSG.getMessage()));
        // else is fulfilled:
        sysOut.redirectOut();
        oneLineArgs.add("something");
        assertFalse(validator.isNullLine(oneLineArgs));
        assertFalse(outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt")).
                contains(INCORRECT_DATA_INPUT_FORMAT.getMessage()));
    }

    @Test
    public void testIsDataInputBreakWithEsc() throws ErrCountCauseException {
        // method condition (else if) is fulfilled:
        sysOut.redirectOut();
        oneLineArgs.add(0, "esc");
        assertTrue(validator.isDataInputBreakWithEsc(userCarsToRace, oneLineArgs));
        assertTrue(outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt")).
                contains(NAME_IS_RESERVED.getMessage()));
        //else is fulfilled:
        sysOut.redirectOut();
        oneLineArgs.set(0, "something");
        assertFalse(validator.isDataInputBreakWithEsc(userCarsToRace, oneLineArgs));
    }

    @Test(expected = ErrCountCauseException.class)
    public void testIsDataInputBreakWithEsc_WithNotEmptyUserCarsToRace() throws ErrCountCauseException {
        // method condition (if) is fulfilled:
        oneLineArgs.add(0, "esc");
        userCarsToRace.add(rightCarMashka);
        sysOut.redirectOut();
        validator.isDataInputBreakWithEsc(userCarsToRace, oneLineArgs);
        // TODO: здесь снова из-за пробрасывания исключения вывод сообщения не учитывается:
        assertTrue(outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt")).
                contains(DATA_INPUT_IS_COMPLETED_WITH_ESC.getMessage()));
    }

    @Test(expected = ErrCountCauseException.class)
    public void testIsDataInputBreakWithEsc_WithErrorCountMoreThanZero() throws ErrCountCauseException {
        // method condition (if) is fulfilled:
        oneLineArgs.add(0, "esc");
        sysOut.redirectOut();
        validator.isDataInputBreakWithEsc(userCarsToRace, oneLineArgs);
        validator.isDataInputBreakWithEsc(userCarsToRace, oneLineArgs);
        // TODO: здесь снова из-за пробрасывания исключения вывод сообщения не учитывается:
        assertTrue(outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt")).
                contains(DATA_INPUT_IS_COMPLETED_WITH_ESC.getMessage()));
    }

    @Test
    public void testInputDataQuantityIsRedundant() throws ErrCountCauseException {
        // method condition (if) is fulfilled:
        for (int i = 0; i < 6; i++) {
            oneLineArgs.add("something");
        }
        sysOut.redirectOut();
        assertTrue(validator.inputDataQuantityIsRedundant(oneLineArgs));
        String message = outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"));
        assertTrue(message.contains(String.format(REDUNDANT_DATA_MSG.getMessage(), oneLineArgs.get(0))));
        assertTrue(message.contains(ERR_COUNT_FIRST_LEVEL_MSG.getMessage()));
        // else is fulfilled:
        sysOut.redirectOut();
        oneLineArgs.clear();
        assertFalse(validator.inputDataQuantityIsRedundant(oneLineArgs));
        oneLineArgs.add("something");
        assertFalse(validator.inputDataQuantityIsRedundant(oneLineArgs));
        assertFalse(outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt")).
                contains((String.format(REDUNDANT_DATA_MSG.getMessage(), oneLineArgs.get(0)))));
    }
    @Test
    public void testInputDataQuantityIsInsufficient() throws ErrCountCauseException {
        // method condition (if) is fulfilled:
        for (int i = 0; i < 4; i++) {
            oneLineArgs.add("something");
        }
        sysOut.redirectOut();
        assertTrue(validator.inputDataQuantityIsInsufficient(oneLineArgs));
        String message = outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"));
        assertTrue(message.contains(String.format(INSUFFICIENT_DATA_MSG.getMessage(), oneLineArgs.get(0))));
        assertTrue(message.contains(ERR_COUNT_FIRST_LEVEL_MSG.getMessage()));
        // else is fulfilled:
        sysOut.redirectOut();
        oneLineArgs.add("something");
        assertFalse(validator.inputDataQuantityIsInsufficient(oneLineArgs));
        assertFalse(outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt")).
                contains((String.format(INSUFFICIENT_DATA_MSG.getMessage(), oneLineArgs.get(0)))));
    }

    @Test
        public void testTryToDoubleValidator_IfIsFulfilled() throws ErrCountCauseException {
        // method condition (if) is fulfilled:
        for (int i = 0; i < 5; i++) {
            oneLineArgs.add(i, "something");
        }
        sysOut.redirectOut();
        assertTrue(validator.tryToDoubleValidator(oneLineArgs));
        String message = outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"));
        assertTrue(message.contains(String.format(INCORRECT_DOUBLE_FORMAT_PARAMETER_MSG.getMessage(), oneLineArgs.get(2))));
        assertTrue(message.contains(ERR_COUNT_FIRST_LEVEL_MSG.getMessage()));

        oneLineArgs.set(4, "0.5");
        sysOut.redirectOut();
        assertTrue(validator.tryToDoubleValidator(oneLineArgs));
        message = outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"));
        assertTrue(message.contains(String.format(INCORRECT_DOUBLE_FORMAT_PARAMETER_MSG.getMessage(), oneLineArgs.get(2))));
        assertTrue(message.contains(ERR_COUNT_FIRST_LEVEL_MSG.getMessage()));

        oneLineArgs.set(3, "125.5");
        sysOut.redirectOut();
        assertTrue(validator.tryToDoubleValidator(oneLineArgs));
        message = outputMsg.readFileToString(new File("src\\main\\resources\\testSupport\\output.txt"));
        assertTrue(message.contains(String.format(INCORRECT_DOUBLE_FORMAT_PARAMETER_MSG.getMessage(), oneLineArgs.get(2))));
        assertTrue(message.contains(ERR_COUNT_PENULT_LEVEL_MSG.getMessage()));

        // else is fulfilled:
        oneLineArgs.set(2, "456");
        sysOut.redirectOut();
        assertFalse(validator.tryToDoubleValidator(oneLineArgs));

    }
}
