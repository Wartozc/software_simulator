package co.com.simulator.gateways;

import co.com.simulator.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Flux<User> listAllUsers();

    Mono<User> createUser(User user);

    Mono<User> updateUser(User user);

    Mono<Boolean> deleteUser(String userId);

}
