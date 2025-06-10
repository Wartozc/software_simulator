package co.com.simulator;

import co.com.simulator.dto.UserDTO;
import co.com.simulator.usecase.CreatorUseCase;
import co.com.simulator.util.RequestUtil;
import co.com.simulator.util.ResponseUtil;
import co.com.simulator.validator.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HandlerSimulator {

    private final CreatorUseCase creatorUseCase;
    private final RequestValidator requestValidator;

    private final ModelMapperSimulator<UserDTO> modelMapperSimulator;

    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        return RequestUtil.buildRequestCreateUser(serverRequest, requestValidator)
                .map(modelMapperSimulator::toUser)
                .map(user -> user.toBuilder().userId(UUID.randomUUID().toString()).build())
                .flatMap(creatorUseCase::createUser)
                .map(modelMapperSimulator::fromUser)
                .doOnSuccess(userDTO -> LoggerSimulator.logInfo(userDTO.toString()))
                .flatMap(ResponseUtil::buildResponseCreateUser);
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
