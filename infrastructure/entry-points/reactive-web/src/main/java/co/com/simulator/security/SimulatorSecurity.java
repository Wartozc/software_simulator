package co.com.simulator.security;

import co.com.simulator.interceptor.JWTVerificationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@RequiredArgsConstructor
public class SimulatorSecurity {

    private final JWTVerificationInterceptor requestInterceptor;

    @Bean
    public SecurityWebFilterChain getSecurityFilter(ServerHttpSecurity serverHttpSecurity) {
        return serverHttpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                        .pathMatchers("/login").permitAll()
                        .pathMatchers("/health").permitAll()
                        .pathMatchers("/doc/**").permitAll()
                        .anyExchange().authenticated())
                .addFilterAt(requestInterceptor, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }
}
