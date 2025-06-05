package co.com.simulator;

import co.com.simulator.config.ApiPaths;
import co.com.simulator.config.MapperConfig;
import co.com.simulator.dto.UserDTO;
import co.com.simulator.usecase.CreatorUseCase;
import co.com.simulator.validator.RequestValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ContextConfiguration(classes = {
        RouterRestSimulator.class,
        HandlerSimulator.class,
        RequestValidator.class,
        MapperConfig.class,
        ApiPaths.class
})
class RouterRestSimulatorTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private CreatorUseCase creatorUseCase;

    @Test
    void shouldCreateAnUserSuccessFully() {

        var userId = UUID.randomUUID().toString();
        var user = new User(userId, "Walther Zapata", "CC",
                "555555555", new Account("test@gmail.com", "Walther12345"));

        var body = """
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

        when(creatorUseCase.createUser(any(User.class))).thenReturn(Mono.just(user));

        webTestClient.post().uri("/user")
                .header("Content-Type", "application/json")
                .bodyValue(body).exchange()
                .expectStatus().isCreated()
                .expectBody(UserDTO.class)
                .consumeWith(response -> {
                    var userDto = response.getResponseBody();
                    Assertions.assertEquals(userDto.getUserId(), userId);
                    Assertions.assertEquals(userDto.getName(), "Walther Zapata");
                    Assertions.assertEquals(userDto.getDocumentType(), "CC");
                    Assertions.assertEquals(userDto.getDocumentNumber(), "555555555");
                    Assertions.assertEquals(userDto.getAccount().getEmail(), "test@gmail.com");
                    Assertions.assertEquals(userDto.getAccount().getPassword(), "Walther12345");
                });
    }
}