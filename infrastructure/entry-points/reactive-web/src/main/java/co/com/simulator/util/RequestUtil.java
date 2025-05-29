package co.com.simulator.util;

import co.com.simulator.dto.UserDTO;
import lombok.experimental.UtilityClass;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

@UtilityClass
public class RequestUtil {

    public Mono<UserDTO> buildRequestCreateUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserDTO.class);
    }
}
