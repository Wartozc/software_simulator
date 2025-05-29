package co.com.simulator.usecase;

import co.com.simulator.User;
import co.com.simulator.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreatorUseCase {

    private final UserRepository userRepository;

    public Mono<User> createUser(User user) {
        return userRepository.createUser(user);
    }
}
