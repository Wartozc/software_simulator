package co.com.simulator.interceptor;

import co.com.simulator.LoggerSimulator;
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
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JWTVerificationInterceptor implements WebFilter {

    private final JWTHandler jwtHandler;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        LoggerSimulator.setTransactionId(UUID.randomUUID().toString());

        return getHeader(exchange)
                .flatMap(headerData -> headerData.isEmpty() ? chain.filter(exchange) :
                        jwtHandler.getUserNameOfJwt(headerData)
                                .flatMap(user -> chain.filter(exchange)
                                        .contextWrite(ReactiveSecurityContextHolder.withAuthentication(
                                                new UsernamePasswordAuthenticationToken(user, null,
                                                        Collections.emptyList())))));
    }

    private Mono<String> getHeader(ServerWebExchange exchange) {
        var headerAuth = exchange.getRequest().getHeaders().getFirst("Authorization");
        return Mono.just(Objects.nonNull(headerAuth) ? headerAuth.substring(7) : "");
    }
}
