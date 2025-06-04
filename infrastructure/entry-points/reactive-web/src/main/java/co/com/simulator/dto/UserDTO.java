package co.com.simulator.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class UserDTO {
    private String userId;
    private String name;
    private String documentType;
    private String documentNumber;
    @Valid
    private AccountDTO account;
}
