package co.com.simulator.authentication.gateways;

import reactor.core.publisher.Mono;

public interface AuthenticationGateway {
    Mono<String> getAuthenticationForUser(String userName, String password);
}
