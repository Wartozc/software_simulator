package co.com.simulator;

import co.com.simulator.authentication.gateways.AuthenticationGateway;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class authenticationUserAdapter implements AuthenticationGateway {

    @Override
    public Mono<String> getAuthenticationForUser(String userName, String password) {
        return Mono.just("wartotest@test.com");
    }
}
