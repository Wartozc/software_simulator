package co.com.simulator;

import co.com.simulator.exception.BusinessException;
import co.com.simulator.exception.message.BusinessExceptionMessage;
import co.com.simulator.message.TechnicalExceptionMessage;
import co.com.simulator.user.User;
import co.com.simulator.user.gateways.UserRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class DynamoDBAdapter implements UserRepository {
    @Override
    public Flux<User> listAllUsers() {
        return null;
    }

    @Override
    public Mono<User> createUser(User user) {

        return Mono.just(user);
    }

    @Override
    public Mono<User> updateUser(User user) {
        return null;
    }

    @Override
    public Mono<Boolean> deleteUser(String userId) {
        return null;
    }
}
