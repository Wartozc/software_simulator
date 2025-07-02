package co.com.simulator.usecase;

import co.com.simulator.authentication.gateways.AuthenticationGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AuthenticationUseCase {
    private final AuthenticationGateway authenticationGateway;

    public Mono<String> getAuthenticationForUser(String userName, String password) {
        return authenticationGateway.getAuthenticationForUser(userName, password);
    }
}
