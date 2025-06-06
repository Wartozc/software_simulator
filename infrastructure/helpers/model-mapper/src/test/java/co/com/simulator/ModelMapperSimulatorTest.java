package co.com.simulator;

import co.com.simulator.user.Account;
import co.com.simulator.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ModelMapperSimulatorTest {

    private ModelMapperSimulator<Object> modelMapperSimulator;
    private Object userDTO;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.modelMapperSimulator = new ModelMapperSimulator<>(Object.class);

        var userDTOJson = """
                {
                    "name": "Walther Zapata",
                    "documentType": "CC",
                    "documentNumber": "555555555",
                    "account": {
                        "email": "test@gmail.com",
                        "password": "Walther12345"
                    }
                }
                """;

        var objectMapper = new ObjectMapper();
        this.userDTO = objectMapper.readValue(userDTOJson, new TypeReference<>() {});
    }

    @Test
    void shouldReturnAnUserClassSuccessFully() {
        var user = modelMapperSimulator.toUser(this.userDTO);

        Assertions.assertEquals("Walther Zapata", user.getName());
        Assertions.assertEquals("CC", user.getDocumentType());
        Assertions.assertEquals("555555555", user.getDocumentNumber());
        Assertions.assertEquals("test@gmail.com", user.getAccount().getEmail());
        Assertions.assertEquals("Walther12345", user.getAccount().getPassword());
    }

    @Test
    void shouldReturnAGenericClassSuccessFully() {
        var id = UUID.randomUUID().toString();
        var user = new User(id, "Walther Zapata", "CC",
                "541651651", new Account("test@test.com", "pass123456"));
        var userDTO = modelMapperSimulator.fromUser(user).toString();

        Assertions.assertTrue(userDTO.contains(id));
        Assertions.assertTrue(userDTO.contains("Walther Zapata"));
        Assertions.assertTrue(userDTO.contains("CC"));
        Assertions.assertTrue(userDTO.contains("541651651"));
        Assertions.assertTrue(userDTO.contains("test@test.com"));
        Assertions.assertTrue(userDTO.contains("pass123456"));
    }
}