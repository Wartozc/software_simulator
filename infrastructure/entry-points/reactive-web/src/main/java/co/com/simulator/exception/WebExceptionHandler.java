package co.com.simulator.exception;

import co.com.simulator.TechnicalException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Component
@Order(-2)
public class WebExceptionHandler extends AbstractErrorWebExceptionHandler {

    public WebExceptionHandler(ErrorAttributes errorAttributes,
                               ServerCodecConfigurer serverCodecConfigurer,
                               ApplicationContext applicationContext) {
        super(errorAttributes, new WebProperties.Resources(), applicationContext);
        super.setMessageReaders(serverCodecConfigurer.getReaders());
        super.setMessageWriters(serverCodecConfigurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderExceptions);
    }

    private Mono<ServerResponse> renderExceptions(ServerRequest serverRequest) {
        return Mono.just(serverRequest)
                .map(this::getError)
                .flatMap(Mono::error)
                .onErrorResume(BusinessException.class, businessException ->
                        this.buildExceptionDTO(businessException, serverRequest))
                .onErrorResume(TechnicalException.class, technicalException ->
                        this.buildExceptionDTO(technicalException, serverRequest))
                .onErrorResume(ConstraintViolationException.class, constraintViolationException ->
                        this.buildExceptionDTO(constraintViolationException, serverRequest))
                .onErrorResume(Throwable.class, unDeterminedException ->
                        this.buildExceptionDTO(unDeterminedException, serverRequest))
                .cast(ExceptionDTO.class)
                .flatMap(this::buildExceptionResponse);
    }

    private Mono<ExceptionDTO> buildExceptionDTO(BusinessException businessException,
                                                 ServerRequest serverRequest) {
        return Mono.just(ExceptionDTO.builder()
                .domain(String.format("%S : %s", serverRequest.method(), serverRequest.uri()))
                .description(businessException.getBusinessExceptionMessage().getDescription())
                .cause(businessException.getBusinessExceptionMessage().getDescription())
                .code(businessException.getBusinessExceptionMessage().getCode())
                .build());
    }

    private Mono<ExceptionDTO> buildExceptionDTO(TechnicalException technicalException,
                                                 ServerRequest serverRequest) {
        return Mono.just(ExceptionDTO.builder()
                .domain(String.format("%S : %s", serverRequest.method(), serverRequest.uri()))
                .description(technicalException.getTechnicalExceptionMessage().getDescription())
                .cause(technicalException.getCause().toString())
                .code(technicalException.getTechnicalExceptionMessage().getCode())
                .build());
    }

    private Mono<ExceptionDTO> buildExceptionDTO(Throwable unDeterminedException,
                                                 ServerRequest serverRequest) {
        return Mono.just(ExceptionDTO.builder()
                .domain(String.format("%S : %s", serverRequest.method(), serverRequest.uri()))
                .description(unDeterminedException.getMessage())
                .cause(unDeterminedException.getMessage())
                .code("00000")
                .build());
    }

    private Mono<ExceptionDTO> buildExceptionDTO(ConstraintViolationException constraintViolationException,
                                                 ServerRequest serverRequest) {
        var field = constraintViolationException.getConstraintViolations().stream().findFirst().get()
                .getPropertyPath().toString().substring(20);

        return Mono.just(constraintViolationException.getConstraintViolations().stream().findFirst().get().getMessage())
                .flatMap(messageException -> Mono.just(ExceptionDTO.builder()
                        .domain(String.format("%S : %s", serverRequest.method(), serverRequest.uri()))
                        .description(String.format("El campo %s %s", field, messageException))
                        .cause(String.format("El campo %s %s", field, messageException))
                        .code("00000").build()));
    }

    private Mono<ServerResponse> buildExceptionResponse(ExceptionDTO exceptionDTO) {
        return ServerResponse.status(HttpStatus.BAD_REQUEST).bodyValue(exceptionDTO);
    }
}
