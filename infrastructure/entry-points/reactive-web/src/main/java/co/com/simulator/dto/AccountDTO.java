package co.com.simulator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class AccountDTO {

    @NotNull
    @Email
    private String email;

    @Size(min = 8, message = "Por favor ingrese al menos 8 caracteres")
    private String password;
}
