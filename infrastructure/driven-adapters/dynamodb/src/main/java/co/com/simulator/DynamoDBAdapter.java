package co.com.simulator;

import co.com.simulator.dbo.UserDBO;
import co.com.simulator.user.User;
import co.com.simulator.user.gateways.UserRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class DynamoDBAdapter /*implements UserRepository */{

    private final ModelMapperSimulator<?> modelMapperSimulator;
    private final DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient;
    private final DynamoDbAsyncTable<UserDBO> dynamoDbAsyncTable;

    public DynamoDBAdapter(DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient) {
        this.dynamoDbEnhancedAsyncClient = dynamoDbEnhancedAsyncClient;
        this.dynamoDbAsyncTable = dynamoDbEnhancedAsyncClient.table("user",
                TableSchema.fromBean(UserDBO.class));
        this.modelMapperSimulator = new ModelMapperSimulator<>(UserDBO.class);
    }

    //@Override
    public Flux<User> listAllUsers() {
        return null;
    }

    //@Override
    public Mono<User> createUser(User user) {
        return Mono.fromFuture(dynamoDbAsyncTable.putItem((UserDBO) modelMapperSimulator.fromUser(user)))
                .doOnSuccess(isOk -> LoggerSimulator.logInfo("Se ha guardado el usuario en la DB"))
                .thenReturn(user);
    }

    //@Override
    public Mono<User> updateUser(User user) {
        return null;
    }

    //@Override
    public Mono<Boolean> deleteUser(String userId) {
        return null;
    }
}
