package co.com.simulator;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@ToString
public class Account {
    private String email;
    private String password;

}
