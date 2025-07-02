package co.com.simulator;

import co.com.simulator.config.ApiPaths;
import co.com.simulator.config.MapperConfig;
import co.com.simulator.dto.UserDTO;
import co.com.simulator.exception.BusinessException;
import co.com.simulator.exception.ExceptionDTO;
import co.com.simulator.exception.WebExceptionHandler;
import co.com.simulator.exception.message.BusinessExceptionMessage;
import co.com.simulator.interceptor.JWTVerificationInterceptor;
import co.com.simulator.message.TechnicalExceptionMessage;
import co.com.simulator.security.JWTHandler;
import co.com.simulator.security.SimulatorSecurity;
import co.com.simulator.usecase.AuthenticationUseCase;
import co.com.simulator.usecase.CreatorUseCase;
import co.com.simulator.user.Account;
import co.com.simulator.user.User;
import co.com.simulator.validator.RequestValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
        ApiPaths.class,
        JWTVerificationInterceptor.class,
        WebExceptionHandler.class,
        JWTHandler.class,
        SimulatorSecurity.class
})
class RouterRestSimulatorTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private CreatorUseCase creatorUseCase;

    @MockitoBean
    private AuthenticationUseCase authenticationUseCase;

    private String jwt;

    @BeforeEach
    void setUp() {
        when(authenticationUseCase.getAuthenticationForUser(any(String.class), any(String.class)))
                .thenReturn(Mono.just("wartotest@test.com"));

        webTestClient.post().uri("/login")
                .header("Content-Type", "application/json")
                .header("user-name", "wartotest@test.com")
                .header("password", "test123456")
                .exchange().expectStatus().is2xxSuccessful()
                .expectBody(String.class)
                .consumeWith(jwtResponse -> this.jwt = jwtResponse.getResponseBody());
    }

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
                .header("Authorization", "Bearer " + this.jwt)
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
                    Assertions.assertEquals(userDto.getAccount().getPassword(), "*********");
                });
    }

    @Test
    void shouldReturnBusinessException() {
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

        when(creatorUseCase.createUser(any(User.class))).thenReturn(Mono
                .error(new BusinessException(BusinessExceptionMessage.UN_EXPECTED_ERROR)));

        webTestClient.post().uri("/user")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.jwt)
                .bodyValue(body).exchange()
                .expectStatus().is4xxClientError()
                .expectBody(ExceptionDTO.class)
                .consumeWith(exceptionDTOEntityExchangeResult ->
                        Assertions.assertEquals(exceptionDTOEntityExchangeResult
                                .getResponseBody().getCode(), "BESS0001"));
    }

    @Test
    void shouldReturnTechnicalException() {
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

        when(creatorUseCase.createUser(any(User.class))).thenReturn(Mono
                .error(new TechnicalException(TechnicalExceptionMessage
                        .UN_EXPECTED_EXCEPTION, new RuntimeException())));

        webTestClient.post().uri("/user")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.jwt)
                .bodyValue(body).exchange()
                .expectStatus().is4xxClientError()
                .expectBody(ExceptionDTO.class)
                .consumeWith(exceptionDTOEntityExchangeResult ->
                        Assertions.assertEquals(exceptionDTOEntityExchangeResult
                                .getResponseBody().getCode(), "TESS0001"));
    }

    @Test
    void shouldReturnThrowableException() {
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

        when(creatorUseCase.createUser(any(User.class))).thenReturn(Mono
                .error(new Throwable()));

        webTestClient.post().uri("/user")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.jwt)
                .bodyValue(body).exchange()
                .expectStatus().is4xxClientError()
                .expectBody(ExceptionDTO.class)
                .consumeWith(exceptionDTOEntityExchangeResult ->
                        Assertions.assertEquals(exceptionDTOEntityExchangeResult
                                .getResponseBody().getCode(), "00000"));
    }

    @Test
    void shouldReturnConstraintViolationException() {

        var userId = UUID.randomUUID().toString();
        var user = new User(userId, "Walther Zapata", "CC",
                "555555555", new Account("test@gmail.com", "Walther12345"));

        var body = """
                {
                    "name": "Walther Zapata",
                    "documentType": "CC",
                    "documentNumber": "555555555",
                    "account": {
                        "password": "Walther12345"
                    }
                }
                """;

        when(creatorUseCase.createUser(any(User.class))).thenReturn(Mono.just(user));

        webTestClient.post().uri("/user")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.jwt)
                .bodyValue(body).exchange()
                .expectStatus().is4xxClientError()
                .expectBody(ExceptionDTO.class)
                .consumeWith(exceptionDTOEntityExchangeResult ->
                        Assertions.assertEquals(exceptionDTOEntityExchangeResult
                                .getResponseBody().getCode(), "00000"));
    }
}