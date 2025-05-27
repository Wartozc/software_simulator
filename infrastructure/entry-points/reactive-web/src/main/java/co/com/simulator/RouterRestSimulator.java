package co.com.simulator;

import co.com.simulator.apidoc.OpenApiSimulator;
import co.com.simulator.config.ApiPaths;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;

@Configuration
public class RouterRestSimulator {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(HandlerSimulator handlerSimulator, ApiPaths apiPaths) {
        return route().POST(apiPaths.getCreateUser(), handlerSimulator::createUser,
                        OpenApiSimulator::createUser)
                .GET(apiPaths.getListUsers(), handlerSimulator::listUsers,
                        OpenApiSimulator::listUsers)
                .PUT(apiPaths.getUpdateUser(), handlerSimulator::updateUser,
                        OpenApiSimulator::updateUser)
                .DELETE(apiPaths.getDeleteUser(), handlerSimulator::deleteUser,
                        OpenApiSimulator::deleteUser)
                .build();
    }
}
