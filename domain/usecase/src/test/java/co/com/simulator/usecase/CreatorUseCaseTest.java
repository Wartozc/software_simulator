package co.com.simulator.usecase;

import co.com.simulator.Account;
import co.com.simulator.User;
import co.com.simulator.gateways.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatorUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreatorUseCase creatorUseCase;

    @Test
    void shouldCreateUserSuccessFully() {

        var userId = UUID.randomUUID().toString();

        var user = new User(userId, "Walther Zapata", "CC",
                "555555555", new Account("test@gmail.com",
                "Walther12345"));

        when(userRepository.createUser(any(User.class))).thenReturn(Mono.just(user));

        StepVerifier.create(creatorUseCase.createUser(user))
                .expectNext(user)
                .verifyComplete();


    }
}