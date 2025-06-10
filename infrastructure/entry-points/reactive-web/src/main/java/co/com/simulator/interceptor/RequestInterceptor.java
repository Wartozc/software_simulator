package co.com.simulator.interceptor;

import co.com.simulator.LoggerSimulator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class RequestInterceptor implements WebFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        LoggerSimulator.TRANSACTION_ID = UUID.randomUUID().toString();
        return chain.filter(exchange);
    }
}
