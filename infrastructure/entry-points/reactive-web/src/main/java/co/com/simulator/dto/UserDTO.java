package co.com.simulator.dto;

import co.com.simulator.Account;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class UserDTO {
    private String userId;
    private String name;
    private String documentType;
    private String documentNumber;
    private Account account;
}
