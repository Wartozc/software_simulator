package co.com.simulator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class LoggerSimulatorTest {

    private static final String ID = UUID.randomUUID().toString();
    private static final String MESSAGE = "Test";
    private static final String EXPECTED_MESSAGE = String.format("TRANSACTION_ID: %s, %s", ID, MESSAGE);
    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;

    @BeforeEach
    void setUp() {
        LoggerSimulator.setTransactionId(ID);
        this.outputStream = new ByteArrayOutputStream();
        this.printStream = System.out;
        System.setOut(new PrintStream(this.outputStream));

    }

    @Test
    void shouldLogInfoSuccessFully() {
        try {
            LoggerSimulator.logInfo(MESSAGE);
            Assertions.assertTrue(outputStream.toString().contains(EXPECTED_MESSAGE));
        } catch (Exception exception) {
            Assertions.fail(exception);
        } finally {
            System.setOut(printStream);
        }
    }

    @Test
    void shouldLogErrorSuccessFully() {
        try {
            LoggerSimulator.logError(MESSAGE);
            Assertions.assertTrue(outputStream.toString().contains(EXPECTED_MESSAGE));
        } catch (Exception exception) {
            Assertions.fail(exception);
        } finally {
            System.setOut(printStream);
        }
    }
}