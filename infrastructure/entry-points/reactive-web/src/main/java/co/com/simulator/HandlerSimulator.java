package co.com.simulator;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class HandlerSimulator {

    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("Creación exitosa");
    }

    public Mono<ServerResponse> listUsers(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("Se han listado los usuarios correctamente");
    }

    public Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("Usuario actualizado correctamente");
    }

    public Mono<ServerResponse> deleteUser(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("Usuario eliminado correctamente");
    }

}
