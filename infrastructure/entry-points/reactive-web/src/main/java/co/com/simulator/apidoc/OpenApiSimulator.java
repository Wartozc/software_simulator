package co.com.simulator.apidoc;

import lombok.experimental.UtilityClass;
import org.springdoc.core.fn.builders.operation.Builder;

@UtilityClass
public class OpenApiSimulator {

    public static Builder createUser(Builder builder) {
        return builder.operationId("Creación de usuario");
    }

    public static Builder listUsers(Builder builder) {
        return builder.operationId("Creación de usuario");
    }

    public static Builder updateUser(Builder builder) {
        return builder.operationId("Creación de usuario");
    }

    public static Builder deleteUser(Builder builder) {
        return builder.operationId("Creación de usuario");
    }
}
