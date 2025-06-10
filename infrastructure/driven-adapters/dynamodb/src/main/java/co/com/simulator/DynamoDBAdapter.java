package co.com.simulator;

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
        LoggerSimulator.logError("Se ha presentado un error en el momento de ir a la DB");
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
