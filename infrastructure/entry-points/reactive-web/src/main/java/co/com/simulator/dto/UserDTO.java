package co.com.simulator.dto;

import jakarta.validation.Valid;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@ToString
public class UserDTO {
    private String userId;
    private String name;
    private String documentType;
    private String documentNumber;
    @Valid
    private AccountDTO account;
}
