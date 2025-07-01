package co.com.simulator;

import co.com.simulator.dbo.UserDBO;
import co.com.simulator.user.Account;
import co.com.simulator.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DynamoDBAdapterTest {

    @InjectMocks
    private DynamoDBAdapter dynamoDBAdapter;

    @Mock
    private DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient;

    @Mock
    private DynamoDbAsyncTable<UserDBO> dynamoDbAsyncTable;

    @BeforeEach
    void setUp() {
        when(dynamoDbEnhancedAsyncClient.table(any(String.class), any(TableSchema.class)))
                .thenReturn(dynamoDbAsyncTable);
        this.dynamoDBAdapter = new DynamoDBAdapter(dynamoDbEnhancedAsyncClient);
    }

    @Test
    void shouldCreateAUserSuccessFully() {

        var id = UUID.randomUUID().toString();
        var user = new User(id, "Walther Zapata", "CC", "566456635",
                new Account("test@test.com", "1651616515"));
        var completableFuture = new CompletableFuture<Void>();
        completableFuture.complete(null);

        when(dynamoDbAsyncTable.putItem(any(UserDBO.class))).thenReturn(completableFuture);

        StepVerifier.create(this.dynamoDBAdapter.createUser(user))
                .expectNext(user)
                .verifyComplete();
    }
}