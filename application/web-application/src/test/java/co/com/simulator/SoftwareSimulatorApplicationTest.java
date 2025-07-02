package co.com.simulator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SoftwareSimulatorApplicationTest {

    @Test
    void shouldInitializerTheSimulatorApplication() {
        Assertions.assertDoesNotThrow(() -> SoftwareSimulatorApplication.main(new String[]{}));
    }

}