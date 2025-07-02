package co.com.simulator.util;

import co.com.simulator.dto.UserAuthenticationDTO;
import co.com.simulator.dto.UserDTO;
import co.com.simulator.validator.RequestValidator;
import lombok.experimental.UtilityClass;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

@UtilityClass
public class RequestUtil {

    public Mono<UserDTO> buildRequestCreateUser(ServerRequest serverRequest,
                                                RequestValidator requestValidator) {
        return serverRequest.bodyToMono(UserDTO.class)
                .map(requestValidator::validateDTO);
    }

    public Mono<UserAuthenticationDTO> buildRequestAuthentication(ServerRequest serverRequest) {
        return Mono.just(serverRequest)
                .map(request -> UserAuthenticationDTO.builder()
                        .userName(request.headers().firstHeader("user-name"))
                        .password(request.headers().firstHeader("password"))
                        .build());
    }
}
