package co.com.simulator;

import co.com.simulator.user.Account;
import co.com.simulator.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class DynamoDBAdapterTest {

    private DynamoDBAdapter dynamoDBAdapter;

    @BeforeEach
    void setUp() {
        this.dynamoDBAdapter = new DynamoDBAdapter();
    }

    @Test
    void shouldCreateAUserSuccessFully() {

        var id = UUID.randomUUID().toString();
        var user = new User(id, "Walther Zapata", "CC", "566456635",
                new Account("test@test.com", "1651616515"));
        StepVerifier.create(this.dynamoDBAdapter.createUser(user))
                .expectNext(user)
                .verifyComplete();
    }
}