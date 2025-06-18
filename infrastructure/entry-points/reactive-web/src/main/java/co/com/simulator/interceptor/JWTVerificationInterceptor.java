package co.com.simulator.interceptor;

import co.com.simulator.security.JWTHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JWTVerificationInterceptor implements WebFilter {

    private final JWTHandler jwtHandler;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return Mono.just(exchange.getRequest())
                .flatMap(request -> Mono.just(request.getHeaders().getFirst("Authorization")))
                .filter(jwt -> !jwt.isEmpty())
                .map(jwtData -> jwtData.substring(7))
                .map(jwtHandler::getUserNameOfJwt)
                .flatMap(user -> chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(
                        new UsernamePasswordAuthenticationToken(user, null, Collections.EMPTY_LIST)
                )));
    }
}
