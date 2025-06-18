package co.com.simulator.dto;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class UserAuthenticationDTO {
    private String userName;
    private String password;
}
