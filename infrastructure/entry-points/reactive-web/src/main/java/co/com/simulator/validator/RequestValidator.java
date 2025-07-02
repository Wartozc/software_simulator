package co.com.simulator.validator;

import co.com.simulator.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class RequestValidator {

    public UserDTO validateDTO(@Valid UserDTO userDTO){
        return userDTO;
    }
}
