package co.com.simulator.util;

import co.com.simulator.dto.UserDTO;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@UtilityClass
public class ResponseUtil {

    public Mono<ServerResponse> buildResponseCreateUser(UserDTO user) {
        return ServerResponse.status(HttpStatus.CREATED).bodyValue(user);
    }
}
